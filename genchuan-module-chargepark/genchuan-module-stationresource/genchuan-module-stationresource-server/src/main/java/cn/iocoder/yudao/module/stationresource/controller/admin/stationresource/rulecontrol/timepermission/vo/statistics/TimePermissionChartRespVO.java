package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.timepermission.vo.statistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Schema(description = "时段权限统计图表 Response VO")
@Data
public class TimePermissionChartRespVO {

    @Schema(description = "使用趋势折线数据")
    private List<UseLine> useLineList;

    @Schema(description = "卡片统计数据")
    private CardData cardData;

    @Data
    public static class UseLine {
        @Schema(description = "日期：yyyy-MM", example = "2025-03")
        private String date;
        @Schema(description = "使用次数")
        private Integer useCount;
    }

    @Data
    public static class CardData {
        @Schema(description = "已启用规则数量")
        private Integer enableRuleCount;
        @Schema(description = "总使用次数")
        private Integer totalUseCount;
    }
//
}