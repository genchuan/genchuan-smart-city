package cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysWarnCandidate {
    /** 预警类型，如：坑洼数量超标 */
    private String type;

    /** 超标指标名称，如：坑洼数量 */
    private String overIndex;

    /** 实际监测值 */
    private BigDecimal overValue;

    /** 阈值 */
    private BigDecimal thresholdValue;
}
