package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.accessrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 人员通行统计态势 Response VO")
@Data
public class AccessRecordChartRespVO {

    @Schema(description = "各时段通行人数趋势列表")
    private List<TimeTrendItem> timeTrendList;

    @Schema(description = "每日通行总量趋势列表")
    private List<DayTrendItem> dayTrendList;

    @Schema(description = "各区域通行次数统计列表")
    private List<AreaCountItem> areaCountList;

    @Schema(description = "各人员通行频次统计列表")
    private List<UserCountItem> userCountList;

    @Schema(description = "时段趋势项")
    @Data
    public static class TimeTrendItem {
        @Schema(description = "时段")
        private String time;
        @Schema(description = "通行人数")
        private Integer count;
    }

    @Schema(description = "每日趋势项")
    @Data
    public static class DayTrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "通行人数")
        private Integer count;
    }

    @Schema(description = "区域统计项")
    @Data
    public static class AreaCountItem {
        @Schema(description = "区域")
        private String area;
        @Schema(description = "通行次数")
        private Integer count;
    }

    @Schema(description = "人员频次项")
    @Data
    public static class UserCountItem {
        @Schema(description = "人员姓名")
        private String userName;
        @Schema(description = "通行次数")
        private Integer count;
    }

}
