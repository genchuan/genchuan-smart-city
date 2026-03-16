package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/28 13:28
 */
@Data
public class GarbageCollectionDailyTrendVO {
    /**
     * 时间维度（如：08:00、09:00...）
     */
    private String timePoint;

    /**
     * 该时段收运量（单位：吨）
     */
    private BigDecimal collectedVolume;

    /**
     * 累计收运量（单位：吨）
     */
    private BigDecimal cumulativeVolume;
}