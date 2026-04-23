package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 车牌识别统计 响应VO")
@Data
public class PlateIdentifyChartRespVO {

    @Schema(description = "识别成功率趋势(折线图)")
    private List<SuccessRateTrendVO> successRateTrend;

    @Schema(description = "各场站识别量(柱状图)")
    private List<StationIdentifyCountVO> stationIdentifyCount;

    @Schema(description = "卡片统计数据")
    private CardDataVO cardData;

    // 成功率趋势VO
    @Data
    public static class SuccessRateTrendVO {
        @Schema(description = "日期 yyyy-MM-dd")
        private String date;
        @Schema(description = "识别成功率")
        private BigDecimal rate;
    }

    // 场站识别量VO
    @Data
    public static class StationIdentifyCountVO {
        @Schema(description = "场站名称")
        private String stationName;
        @Schema(description = "识别数量")
        private Long count;
    }

    // 卡片数据VO
    @Data
    public static class CardDataVO {
        @Schema(description = "总识别成功率")
        private BigDecimal successRate;
        @Schema(description = "平均识别时长(秒)")
        private BigDecimal avgDuration;
    }
}