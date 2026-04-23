// ScheduleViewChartRespVO.java
package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;
import java.time.LocalDate;

@Schema(description = "巡查巡检 - 排班统计图表 Response VO")
@Data
public class ScheduleViewChartRespVO {

    @Schema(description = "排班日历展示数据")
    private List<CalendarData> calendarData;

    @Schema(description = "人员排班分布柱状图数据")
    private List<UserData> userData;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    @Schema(description = "日历数据")
    public static class CalendarData {
        @Schema(description = "日期，格式：yyyy-MM-dd")
        private String date;

        @Schema(description = "用户ID")
        private Long userId;

        @Schema(description = "班次类型")
        private String shiftType;

        @Schema(description = "用户姓名")
        private String userName;
    }

    @Data
    @Schema(description = "人员数据")
    public static class UserData {
        @Schema(description = "用户姓名")
        private String userName;

        @Schema(description = "排班次数")
        private Integer count;
    }

    @Data
    @Schema(description = "卡片数据")
    public static class CardData {
        @Schema(description = "总排班次数")
        private Integer scheduleCount;

        @Schema(description = "在岗人数")
        private Integer onDutyCount;
    }
}