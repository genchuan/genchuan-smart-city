package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.decision;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 报表/统计聚合查询 Mapper
 *
 * 性能优化目标：将 ServiceOpReportServiceImpl 中 selectList(全表) → 内存 group 的写法，
 * 替换为数据库侧 GROUP BY DATE(create_time) 聚合，避免大数据量场景拉全表内存爆。
 *
 * 多租户隔离：依赖 yudao 框架的 TenantLineInnerInterceptor，
 * 它会在 SQL 解析阶段为带 tenant_id 列的表自动追加 tenant_id = ? 条件，
 * 因此本 Mapper 的 @Select 原生 SQL 也自动具备多租户隔离能力，无需手工拼接。
 *
 * 返回值规范：每条记录包含 day(java.sql.Date) / cnt(Long)。
 */
@Mapper
public interface ReportStatMapper {

    @Select("<script>SELECT DATE(create_time) AS day, COUNT(*) AS cnt FROM rescue_info " +
            "WHERE deleted = 0 AND create_time &gt;= #{since} " +
            "<if test='end != null'>AND create_time &lt;= #{end}</if> " +
            "GROUP BY DATE(create_time) ORDER BY day</script>")
    List<Map<String, Object>> rescueInfoDailyCount(@Param("since") LocalDateTime since,
                                                   @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE(reserve_time) AS day, COUNT(*) AS cnt FROM reserve_list " +
            "WHERE reserve_time &gt;= #{since} " +
            "<if test='end != null'>AND reserve_time &lt;= #{end}</if> " +
            "GROUP BY DATE(reserve_time) ORDER BY day</script>")
    List<Map<String, Object>> reserveListDailyCount(@Param("since") LocalDateTime since,
                                                    @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE(push_time) AS day, COUNT(*) AS cnt FROM space_push " +
            "WHERE push_time IS NOT NULL AND push_time &gt;= #{since} " +
            "<if test='end != null'>AND push_time &lt;= #{end}</if> " +
            "GROUP BY DATE(push_time) ORDER BY day</script>")
    List<Map<String, Object>> spacePushDailyCount(@Param("since") LocalDateTime since,
                                                  @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE(create_time) AS day, COUNT(*) AS cnt FROM suggestion " +
            "WHERE create_time &gt;= #{since} " +
            "<if test='end != null'>AND create_time &lt;= #{end}</if> " +
            "GROUP BY DATE(create_time) ORDER BY day</script>")
    List<Map<String, Object>> suggestionDailyCount(@Param("since") LocalDateTime since,
                                                   @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE(create_time) AS day, COUNT(*) AS cnt FROM user_appeal " +
            "WHERE create_time &gt;= #{since} " +
            "<if test='end != null'>AND create_time &lt;= #{end}</if> " +
            "GROUP BY DATE(create_time) ORDER BY day</script>")
    List<Map<String, Object>> userAppealDailyCount(@Param("since") LocalDateTime since,
                                                   @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE(create_time) AS day, COUNT(*) AS cnt FROM dispute_mediate " +
            "WHERE create_time &gt;= #{since} " +
            "<if test='end != null'>AND create_time &lt;= #{end}</if> " +
            "GROUP BY DATE(create_time) ORDER BY day</script>")
    List<Map<String, Object>> disputeMediateDailyCount(@Param("since") LocalDateTime since,
                                                       @Param("end") LocalDateTime end);

    /**
     * 通用：按 reserve_type 列做枚举分组（用于柱状图），支持按 create_time 时间范围过滤
     */
    @Select("<script>SELECT reserve_type AS k, COUNT(*) AS cnt FROM reserve_list " +
            "WHERE reserve_type IS NOT NULL " +
            "<if test='since != null'>AND reserve_time &gt;= #{since}</if> " +
            "<if test='end != null'>AND reserve_time &lt;= #{end}</if> " +
            "GROUP BY reserve_type</script>")
    List<Map<String, Object>> reserveListTypeDistribution(@Param("since") LocalDateTime since,
                                                          @Param("end") LocalDateTime end);

    /**
     * 充停地图：聚合统计（成功率、平均响应时长、总数）一次拿全，支持按 create_time 时间范围过滤
     */
    @Select("<script>SELECT " +
            " COUNT(*) AS total, " +
            " SUM(CASE WHEN result_count &gt; 0 THEN 1 ELSE 0 END) AS success_cnt, " +
            " AVG(response_duration) AS avg_resp " +
            "FROM charge_park_map " +
            "<where>" +
            "<if test='since != null'>AND create_time &gt;= #{since}</if>" +
            "<if test='end != null'>AND create_time &lt;= #{end}</if>" +
            "</where></script>")
    Map<String, Object> chargeParkMapAggregate(@Param("since") LocalDateTime since,
                                               @Param("end") LocalDateTime end);

    /**
     * 周边场站：聚合统计（station 求和、empty 求和、查询次数）
     */
    @Select("SELECT " +
            " COUNT(*) AS total, " +
            " IFNULL(SUM(station_count),0) AS station_sum, " +
            " IFNULL(SUM(empty_station_count),0) AS empty_sum " +
            "FROM near_station")
    Map<String, Object> nearStationAggregate();

    /**
     * 路径规划：聚合（总数、成功数），支持按 create_time 时间范围过滤
     */
    @Select("<script>SELECT COUNT(*) AS total, " +
            " SUM(CASE WHEN path_length &gt; 0 THEN 1 ELSE 0 END) AS success_cnt " +
            "FROM path_plan " +
            "<where>" +
            "<if test='since != null'>AND create_time &gt;= #{since}</if>" +
            "<if test='end != null'>AND create_time &lt;= #{end}</if>" +
            "</where></script>")
    Map<String, Object> pathPlanAggregate(@Param("since") LocalDateTime since,
                                          @Param("end") LocalDateTime end);

    /**
     * 话术：按类型分组，支持按 create_time 时间范围过滤
     */
    @Select("<script>SELECT type AS k, COUNT(*) AS cnt FROM wording_mgmt " +
            "WHERE type IS NOT NULL " +
            "<if test='since != null'>AND create_time &gt;= #{since}</if> " +
            "<if test='end != null'>AND create_time &lt;= #{end}</if> " +
            "GROUP BY type</script>")
    List<Map<String, Object>> wordingMgmtTypeDistribution(@Param("since") LocalDateTime since,
                                                          @Param("end") LocalDateTime end);

    /**
     * 话术：按状态计数（生效数、总数），支持按 create_time 时间范围过滤
     */
    @Select("<script>SELECT " +
            " COUNT(*) AS total, " +
            " SUM(CASE WHEN status = '已生效' THEN 1 ELSE 0 END) AS enabled_cnt " +
            "FROM wording_mgmt " +
            "<where>" +
            "<if test='since != null'>AND create_time &gt;= #{since}</if>" +
            "<if test='end != null'>AND create_time &lt;= #{end}</if>" +
            "</where></script>")
    Map<String, Object> wordingMgmtAggregate(@Param("since") LocalDateTime since,
                                             @Param("end") LocalDateTime end);

    // ========== count-by-status 系列(给前端卡片角标用,一次 GROUP BY 取所有状态) ==========
    // 多租户隔离由 TenantLineInnerInterceptor 自动追加 tenant_id = ?

    @Select("SELECT status AS k, COUNT(*) AS cnt FROM rescue_info GROUP BY status")
    List<Map<String, Object>> rescueInfoCountByStatus();

    @Select("SELECT status AS k, COUNT(*) AS cnt FROM reserve_list GROUP BY status")
    List<Map<String, Object>> reserveListCountByStatus();

    @Select("SELECT status AS k, COUNT(*) AS cnt FROM space_push GROUP BY status")
    List<Map<String, Object>> spacePushCountByStatus();

    @Select("SELECT status AS k, COUNT(*) AS cnt FROM suggestion GROUP BY status")
    List<Map<String, Object>> suggestionCountByStatus();

    @Select("SELECT status AS k, COUNT(*) AS cnt FROM user_appeal GROUP BY status")
    List<Map<String, Object>> userAppealCountByStatus();

    @Select("SELECT status AS k, COUNT(*) AS cnt FROM dispute_mediate GROUP BY status")
    List<Map<String, Object>> disputeMediateCountByStatus();

    // ========== 月度率聚合(用于服务运营趋势折线图) ==========
    // 返回 {ym, total, done_cnt},应用层合并多表后计算 rate

    @Select("<script>SELECT DATE_FORMAT(create_time, '%Y-%m') AS ym, " +
            " COUNT(*) AS total, " +
            " SUM(CASE WHEN status = '已完成' THEN 1 ELSE 0 END) AS done_cnt " +
            "FROM rescue_info " +
            "<where>" +
            "<if test='since != null'>AND create_time &gt;= #{since}</if>" +
            "<if test='end != null'>AND create_time &lt;= #{end}</if>" +
            "</where>" +
            "GROUP BY DATE_FORMAT(create_time, '%Y-%m') ORDER BY ym</script>")
    List<Map<String, Object>> rescueInfoMonthlyRate(@Param("since") LocalDateTime since,
                                                    @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE_FORMAT(create_time, '%Y-%m') AS ym, " +
            " COUNT(*) AS total, " +
            " SUM(CASE WHEN status IN ('已生效','已完成') THEN 1 ELSE 0 END) AS done_cnt " +
            "FROM reserve_list " +
            "<where>" +
            "<if test='since != null'>AND create_time &gt;= #{since}</if>" +
            "<if test='end != null'>AND create_time &lt;= #{end}</if>" +
            "</where>" +
            "GROUP BY DATE_FORMAT(create_time, '%Y-%m') ORDER BY ym</script>")
    List<Map<String, Object>> reserveListMonthlyRate(@Param("since") LocalDateTime since,
                                                     @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE_FORMAT(create_time, '%Y-%m') AS ym, " +
            " COUNT(*) AS total, " +
            " SUM(CASE WHEN status = '已完成' THEN 1 ELSE 0 END) AS done_cnt " +
            "FROM suggestion " +
            "<where>" +
            "<if test='since != null'>AND create_time &gt;= #{since}</if>" +
            "<if test='end != null'>AND create_time &lt;= #{end}</if>" +
            "</where>" +
            "GROUP BY DATE_FORMAT(create_time, '%Y-%m') ORDER BY ym</script>")
    List<Map<String, Object>> suggestionMonthlyRate(@Param("since") LocalDateTime since,
                                                    @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE_FORMAT(create_time, '%Y-%m') AS ym, " +
            " COUNT(*) AS total, " +
            " SUM(CASE WHEN status = '已完成' THEN 1 ELSE 0 END) AS done_cnt " +
            "FROM user_appeal " +
            "<where>" +
            "<if test='since != null'>AND create_time &gt;= #{since}</if>" +
            "<if test='end != null'>AND create_time &lt;= #{end}</if>" +
            "</where>" +
            "GROUP BY DATE_FORMAT(create_time, '%Y-%m') ORDER BY ym</script>")
    List<Map<String, Object>> userAppealMonthlyRate(@Param("since") LocalDateTime since,
                                                    @Param("end") LocalDateTime end);

    @Select("<script>SELECT DATE_FORMAT(create_time, '%Y-%m') AS ym, " +
            " COUNT(*) AS total, " +
            " SUM(CASE WHEN status = '已完成' THEN 1 ELSE 0 END) AS done_cnt " +
            "FROM dispute_mediate " +
            "<where>" +
            "<if test='since != null'>AND create_time &gt;= #{since}</if>" +
            "<if test='end != null'>AND create_time &lt;= #{end}</if>" +
            "</where>" +
            "GROUP BY DATE_FORMAT(create_time, '%Y-%m') ORDER BY ym</script>")
    List<Map<String, Object>> disputeMediateMonthlyRate(@Param("since") LocalDateTime since,
                                                        @Param("end") LocalDateTime end);

}
