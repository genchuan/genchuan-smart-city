package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import lombok.Data;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/2/27 17:32
 */
@Data
public class TimePeriodPendingColumnVO {
    /** 时段名称（如：早班/中班/晚班） */
    private String timePeriod;
    /** 待执行计划数量 */
    private Long count;
}