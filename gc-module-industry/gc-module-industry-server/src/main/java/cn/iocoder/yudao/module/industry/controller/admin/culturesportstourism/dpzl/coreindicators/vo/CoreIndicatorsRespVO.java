package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 文旅核心指标 Response VO")
public class CoreIndicatorsRespVO {

    @Schema(description = "文旅资源总数", example = "1250")
    private Integer totalSceneCount;

    @Schema(description = "文旅资源总数环比变化(%)", example = "5.2")
    private Double totalSceneCountRate;

    @Schema(description = "当日客流峰值", example = "3280")
    private Integer maxCount;

    @Schema(description = "当日客流峰值环比变化(%)", example = "-3.1")
    private Double maxCountRate;

    @Schema(description = "投诉办结率(%)", example = "85.5")
    private Double completeRate;

    @Schema(description = "投诉办结率环比变化(%)", example = "2.0")
    private Double completeRateRate;

    @Schema(description = "设施完好率(%)", example = "92.0")
    private Double facilityGoodRate;

    @Schema(description = "设施完好率环比变化(%)", example = "-1.5")
    private Double facilityGoodRateRate;

    @Schema(description = "活动开展数", example = "28")
    private Integer newSceneCount;

    @Schema(description = "活动开展数环比变化(%)", example = "10.3")
    private Double newSceneCountRate;
}