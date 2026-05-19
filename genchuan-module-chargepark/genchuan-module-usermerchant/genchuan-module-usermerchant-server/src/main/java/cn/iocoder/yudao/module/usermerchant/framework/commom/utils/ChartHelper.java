package cn.iocoder.yudao.module.usermerchant.framework.commom.utils;

import cn.hutool.core.util.StrUtil;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 统计图表通用工具类
 * 支持：折线图（时间聚合）、柱状图/饼图（分组统计）、总数（条件统计）、比率
 *
 * @author 宇佐见莲子
 */
@Component
public class ChartHelper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // ==================== 公开方法 ====================

    /**
     * 折线图：按时间粒度聚合数据（COUNT 或 SUM）
     *
     * @param query 查询配置
     * @return List<ChartDataVO> 每个点包含 date 和 value
     */
    public List<ChartDataVO> queryLineChart(ChartQuery query) {
        String sql = buildLineSql(query);
        return executeQuery(sql, query);
    }

    /**
     * 柱状图 / 饼图：按分组字段统计数量
     *
     * @param query 查询配置
     * @return List<ChartDataVO> 每个分组包含 name 和 value
     */
    public List<ChartDataVO> queryPieOrBar(ChartQuery query) {
        String sql = buildGroupSql(query);
        return executeQuery(sql, query);
    }

    /**
     * 总数：统计满足条件的记录数
     *
     * @param query 查询配置
     * @return 总数
     */
    public long queryTotalCount(ChartQuery query) {
        String sql = buildCountSql(query);
        Long result = jdbcTemplate.queryForObject(sql, Long.class);
        return result != null ? result : 0L;
    }

    /**
     * 比率：分子/分母
     *
     * @param numeratorSql   分子SQL（必须返回一个数值，如 SELECT COUNT(*) FROM ...）
     * @param denominatorSql 分母SQL（必须返回一个数值）
     * @return 比率（保留两位小数，若分母为0则返回0）
     */
    public BigDecimal queryRate(String numeratorSql, String denominatorSql) {
        BigDecimal numerator = jdbcTemplate.queryForObject(numeratorSql, BigDecimal.class);
        BigDecimal denominator = jdbcTemplate.queryForObject(denominatorSql, BigDecimal.class);
        if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return numerator.divide(denominator, 4, BigDecimal.ROUND_HALF_UP);
    }

    // ==================== SQL 构建 ====================

    private String buildLineSql(ChartQuery q) {
        // 时间格式化表达式
        String dateExpr = switch (q.granularity) {
            case "year" -> String.format("DATE_FORMAT(%s, '%%Y')", q.dateField);
            case "month" -> String.format("DATE_FORMAT(%s, '%%Y-%%m')", q.dateField);
            default -> String.format("DATE_FORMAT(%s, '%%Y-%%m-%%d')", q.dateField);
        };
        // 聚合表达式：COUNT(*) 或 SUM(字段)
        String aggExpr = q.aggregate == AggregateType.SUM ?
                String.format("SUM(%s)", q.sumField) : "COUNT(*)";
        String where = buildWhereClause(q);
        // 关键修改：GROUP BY 和 ORDER BY 使用原始表达式，而不是别名
        return String.format("""
            SELECT %s AS name, %s AS value
            FROM %s
            WHERE 1=1 %s
            GROUP BY %s
            ORDER BY %s
            """, dateExpr, aggExpr, q.tableName, where, dateExpr, dateExpr);
    }

    private String buildGroupSql(ChartQuery q) {
        String where = buildWhereClause(q);
        return String.format("""
                SELECT %s AS name, COUNT(*) AS value
                FROM %s
                WHERE 1=1 %s
                GROUP BY %s
                """, q.groupField, q.tableName, where, q.groupField);
    }

    private String buildCountSql(ChartQuery q) {
        String where = buildWhereClause(q);
        return String.format("SELECT COUNT(*) FROM %s WHERE 1=1 %s", q.tableName, where);
    }

    private String buildWhereClause(ChartQuery q) {
        StringBuilder sb = new StringBuilder();
        if (StrUtil.isNotBlank(q.extraWhere)) {
            sb.append(" AND ").append(q.extraWhere);
        }
        if (q.start != null) {
            sb.append(" AND ").append(q.dateField).append(" >= '").append(formatDateTime(q.start)).append("'");
        }
        if (q.end != null) {
            sb.append(" AND ").append(q.dateField).append(" <= '").append(formatDateTime(q.end)).append("'");
        }
        // 租户隔离（假设所有表都有 tenant_id 字段，且从上下文获取当前租户）
        Long tenantId = getCurrentTenantId();
        if (tenantId != null && tenantId != 0) {
            sb.append(" AND tenant_id = ").append(tenantId);
        }
        // 逻辑删除（假设表中有 deleted 字段，0 未删除）
        sb.append(" AND deleted = 0");
        return sb.toString();
    }

    // ==================== 辅助方法 ====================

    private List<ChartDataVO> executeQuery(String sql, ChartQuery query) {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<ChartDataVO> result = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            ChartDataVO vo = new ChartDataVO();
            vo.setName(String.valueOf(row.get("name")));
            Object value = row.get("value");
            if (value instanceof Number) {
                vo.setValue((Number) value);
            } else {
                vo.setValue(0);
            }
            result.add(vo);
        }
        return result;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取当前租户ID（需根据你的框架实现）
     * 如芋道框架可使用 TenantContextHolder.getTenantId()
     */
    private Long getCurrentTenantId() {
        // 示例：从 ThreadLocal 获取，请替换为实际代码
        // return TenantContextHolder.getTenantId();
        return 0L; // 默认0，表示不隔离
    }

    // ==================== 内部类 ====================

    public enum AggregateType {
        COUNT, SUM
    }

    @Data
    @Builder
    public static class ChartQuery {
        private String tableName;      // 表名
        private String dateField;      // 时间字段（用于时间范围过滤和折线图聚合）
        private String groupField;     // 分组字段（柱状图/饼图）
        private String sumField;       // 求和字段（折线图 SUM 时使用）
        private AggregateType aggregate; // 聚合类型，默认 COUNT
        private String extraWhere;      // 额外 WHERE 条件（不含 AND，例如 "status = 1"）
        private LocalDateTime start;
        private LocalDateTime end;
        private String granularity;      // day, month, year
    }

    @Data
    public static class ChartDataVO {
        private String name;
        private Number value;
    }
}