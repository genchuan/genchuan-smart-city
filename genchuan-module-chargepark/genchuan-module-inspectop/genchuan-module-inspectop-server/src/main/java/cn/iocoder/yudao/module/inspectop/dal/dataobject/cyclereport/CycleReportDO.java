package cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 巡检运维报表 DO
 */
@TableName("cycle_report")
@KeySequence("cycle_report_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CycleReportDO extends BaseDO {

    /**
     * 报表主键 ID
     */
    private Long id;
    /**
     * 报表周期
     */
    private String reportCycle;
    /**
     * 统计开始时间
     */
    private LocalDateTime statTimeStart;
    /**
     * 统计结束时间
     */
    private LocalDateTime statTimeEnd;
    /**
     * 正常设备数
     */
    private Integer normalDeviceNum;
    /**
     * 异常设备数
     */
    private Integer abnormalDeviceNum;
    /**
     * 巡检任务数
     */
    private Integer inspectTaskNum;
    /**
     * 任务完成率
     */
    private BigDecimal taskCompleteRate;
    /**
     * 油车占位待处置数
     */
    private Integer oilWaitHandleNum;
    /**
     * 处置完成率
     */
    private BigDecimal oilHandleCompleteRate;
    /**
     * 巡检人员在岗数
     */
    private Integer inspectUserOnlineNum;
    /**
     * 资产正常数
     */
    private Integer assetNormalNum;
    /**
     * 库存预警数
     */
    private Integer stockWarnNum;
    /**
     * 所属场站 ID
     */
    private Long stationId;
    /**
     * 生成状态（生成中 / 已生成）
     */
    private String generateStatus;
    /**
     * 报表生成时间
     */
    private LocalDateTime generateTime;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 报表导出次数
     */
    private Integer exportCount;
    /**
     * 同比数据
     */
    private String yearOnYearData;
    /**
     * 环比数据
     */
    private String chainRatioData;
    /**
     * 租户 ID
     */
    private Long tenantId;
    /**
     * 预留字段1
     */
    private String reserve1;
    /**
     * 预留字段2
     */
    private String reserve2;
}