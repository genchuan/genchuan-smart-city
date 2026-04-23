package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScheduleViewChartData {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 排班日期
     */
    private LocalDateTime scheduleDate;

    /**
     * 班次类型
     */
    private String shiftType;

    /**
     * 排班次数
     */
    private Integer scheduleCount;
}