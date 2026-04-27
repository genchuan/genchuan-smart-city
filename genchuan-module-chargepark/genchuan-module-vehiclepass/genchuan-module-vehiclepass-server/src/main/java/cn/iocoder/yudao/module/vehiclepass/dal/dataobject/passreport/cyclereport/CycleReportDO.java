package cn.iocoder.yudao.module.vehiclepass.dal.dataobject.passreport.cyclereport;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("vp_cycle_report")
@KeySequence("vp_cycle_report_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CycleReportDO extends BaseDO {
    @TableId
    private Long id;
    private String reportCycle;
    private LocalDateTime statStartTime;
    private LocalDateTime statEndTime;
    private Long stationId;

    @TableField(exist = false)
    private String stationName;

    private Integer enterCount;
    private Integer leaveCount;
    private Integer parkingCount;
    private BigDecimal identifySuccessRate;
    private BigDecimal checkSuccessRate;
    private BigDecimal abnormalHandleRate;
    private BigDecimal etcPassSuccessRate;
    private String reportStatus;
    private Integer createCost;   // 耗时，单位秒
}