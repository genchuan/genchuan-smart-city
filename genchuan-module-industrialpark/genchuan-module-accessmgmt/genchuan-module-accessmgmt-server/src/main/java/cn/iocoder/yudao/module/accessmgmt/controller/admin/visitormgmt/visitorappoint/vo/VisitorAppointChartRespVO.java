package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 访客预约态势 Response VO")
@Data
public class VisitorAppointChartRespVO {

    @Schema(description = "预约总数")
    private Integer totalCount;

    @Schema(description = "待审核数")
    private Integer pendingCount;

    @Schema(description = "已通过数")
    private Integer passedCount;

    @Schema(description = "已到访数")
    private Integer arrivedCount;

    @Schema(description = "已离园数")
    private Integer leftCount;

    @Schema(description = "每日预约趋势列表")
    private List<DayTrendItem> dayTrendList;

    @Schema(description = "各企业拜访统计列表")
    private List<CompanyCountItem> companyCountList;

    @Schema(description = "时段趋势项")
    @Data
    public static class DayTrendItem {
        @Schema(description = "日期")
        private String date;
        @Schema(description = "预约数")
        private Integer count;
    }

    @Schema(description = "企业统计项")
    @Data
    public static class CompanyCountItem {
        @Schema(description = "企业名称")
        private String company;
        @Schema(description = "拜访次数")
        private Integer count;
    }

}
