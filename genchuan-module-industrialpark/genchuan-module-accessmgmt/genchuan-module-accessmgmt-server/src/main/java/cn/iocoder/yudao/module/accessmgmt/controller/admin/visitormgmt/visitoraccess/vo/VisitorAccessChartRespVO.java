package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitoraccess.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 访客通行区域分布 Response VO")
@Data
public class VisitorAccessChartRespVO {

    @Schema(description = "各区域通行次数统计列表")
    private List<AreaCountItem> areaCountList;

    @Schema(description = "通行时间趋势列表")
    private List<TimeTrendItem> timeTrendList;

    @Schema(description = "凭证状态分布列表")
    private List<TicketStatusItem> ticketStatusList;

    @Schema(description = "区域统计项")
    @Data
    public static class AreaCountItem {
        @Schema(description = "区域")
        private String area;
        @Schema(description = "通行次数")
        private Integer count;
    }

    @Schema(description = "时间趋势项")
    @Data
    public static class TimeTrendItem {
        @Schema(description = "时间")
        private String time;
        @Schema(description = "通行次数")
        private Integer count;
    }

    @Schema(description = "凭证状态项")
    @Data
    public static class TicketStatusItem {
        @Schema(description = "状态名称")
        private String name;
        @Schema(description = "数量")
        private Integer value;
    }

}
