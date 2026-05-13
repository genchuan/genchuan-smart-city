package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.debtexpand.vo.chart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "拓场追缴统计图表 Response VO")
public class DebtExpandChartRespVO {

    @Schema(description = "拓场进度趋势（折线图）")
    private List<ProgressLineItem> progressLineList;

    @Schema(description = "追缴成功率分布（柱状图）")
    private List<RecoveryBarItem> recoveryBarList;

    @Schema(description = "卡片统计数据")
    private CardDataItem cardData;

    @Data
    public static class ProgressLineItem {
        private String date;    // yyyy-MM
        private Integer progress;
    }

    @Data
    public static class RecoveryBarItem {
        private Long stationId;
        private String name;   // 场站名
        private Double value;  // 追缴成功率
    }

    @Data
    public static class CardDataItem {
        private Integer expandFinishCount; // 已完成拓场数
        private Double recoveryRate;      // 整体追缴完成率
    }
}
