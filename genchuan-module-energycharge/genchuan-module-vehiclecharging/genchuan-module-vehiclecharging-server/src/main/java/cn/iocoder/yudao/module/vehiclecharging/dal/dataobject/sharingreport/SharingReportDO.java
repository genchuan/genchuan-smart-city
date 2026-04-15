package cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 分账报表 DO
 *
 * @author 亘川智城
 */
@TableName("sharing_report")
@KeySequence("sharing_report_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharingReportDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 报表编号，唯一
     */
    private String reportCode;
    /**
     * 报表名称
     */
    private String reportName;
    /**
     * 报表类型：日/周/月/季/半年/年/自定义
     */
    private String reportType;
    /**
     * 报表时间范围（如2025-03、2025-Q1、2025-03-01~2025-03-31）
     */
    private String timeRange;
    /**
     * 合作方（为空表示全部）
     */
    private String cooperator;
    /**
     * 总结算金额（元）
     */
    private BigDecimal totalSettlementAmount;
    /**
     * 总分账金额（元）
     */
    private BigDecimal totalSharingAmount;
    /**
     * 关联结算单数量
     */
    private Integer billCount;
    /**
     * 备注
     */
    private String remark;
    /**
     * 备用字段1
     */
    private String reserve1;
    /**
     * 备用字段2
     */
    private String reserve2;
}