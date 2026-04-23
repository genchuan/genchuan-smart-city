package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo;

import lombok.Data;

@Data
public class ScheduleViewChartCardData {
    /**
     * 总排班次数
     */
    private Integer scheduleCount;

    /**
     * 在岗人数
     */
    private Integer onDutyCount;
}