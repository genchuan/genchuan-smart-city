package cn.iocoder.yudao.module.evaluate.dal.dataobject.statreport;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 统计分析报 DO
 *
 * @author 亘川智城
 */
@TableName("stat_report")
@KeySequence("stat_report_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatReportDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 报表UUID（主键，UUID）
     */
    private String reportId;
    /**
     * 报表编号
     */
    private String code;
    /**
     * 报表名称
     */
    private String name;
    /**
     * 报表类型ID（关联sys_report_type.type_id）
     */
    private String typeId;
    /**
     * 关联评价任务ID（关联eval_task.task_id）
     */
    private String taskId;
    /**
     * 统计维度（关联sys_stat_dimension.dimension_id）
     */
    private String dimension;
    /**
     * 报表状态（关联sys_report_status.status_id）
     */
    private String status;
    /**
     * 生成人（关联sys_user.user_id）
     */
    private String createBy;
    /**
     * 业务生成时间（生成时间）
     */
    private LocalDateTime bizCreateTime;
    /**
     * 导出时间
     */
    private LocalDateTime exportTime;
    /**
     * 导出人（关联sys_user.user_id）
     */
    private String exportBy;
    /**
     * 报表格式
     */
    private String format;
    /**
     * 数据更新时间
     */
    private LocalDateTime dataUpdateTime;
    /**
     * 生成耗时（秒）
     */
    private BigDecimal costTime;
    /**
     * 预览次数
     */
    private Integer previewCount;
    /**
     * 最新预览时间
     */
    private LocalDateTime latestPreviewTime;
    /**
     * 报表文件大小（字节）
     */
    private Long fileSize;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 导出次数
     */
    private Integer exportCount;
    /**
     * 最新导出时间
     */
    private LocalDateTime latestExportTime;
    /**
     * 通用扩展字段1
     */
    private String extCommon1;
    /**
     * 通用扩展字段2
     */
    private String extCommon2;
    /**
     * 通用扩展字段3
     */
    private String extCommon3;
    /**
     * 通用扩展字段4
     */
    private String extCommon4;

}