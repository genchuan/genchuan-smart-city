package cn.iocoder.yudao.module.ordertrade.dal.dataobject.orderreport;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("cycle_report")
@KeySequence("cycle_report_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CycleReportDO extends BaseDO {

    @TableId
    private Long id;

    private String reportCycle;

    private String statTime;

    private LocalDateTime statStartTime;

    private LocalDateTime statEndTime;

    private Integer cycleOrderCount;

    private BigDecimal cycleRevenue;

    private BigDecimal payRate;

    private BigDecimal chargeQuantity;

    private Integer lendCount;

    private BigDecimal refundAmount;

    private Integer waitHandleAbnormalCount;

    private BigDecimal collectCompleteRate;

    private BigDecimal checkAccuracyRate;

    private String generateStatus;

    private String operator;

    private String yearOnYear;

    private String chainRatio;

    private Integer exportCount;

    private String remark;
}
