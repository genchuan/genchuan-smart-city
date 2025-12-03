package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "管理后台 - 文旅核心指标 Response VO")
public class CultureCoreMetricsRespVO {

    @Data
    @Schema(description = "单个核心指标数据")
    public static class CoreMetricVO {
        @Schema(description = "指标值", example = "90")
        private BigDecimal value;

        @Schema(description = "指标更新时间",example = "1764604800000")
        private LocalDateTime updateTime;

        @Schema(description = "告警状态，0-正常，1-提醒，2-预警", example = "0")
        private Integer warnStatus;

        @Schema(description = "统计周期，如 今日 / 昨日 / 近7天 / 近30天", example = "today/yesterday/recent7/recent30")
        private String timeCycle;
    }

    @Schema(description = "文旅资源总数")
    private CoreMetricVO totalSceneCount;

    @Schema(description = "当日客流峰值")
    private CoreMetricVO maxCount;

    @Schema(description = "投诉办结率(%)")
    private CoreMetricVO completeRate;

    @Schema(description = "设施完好率(%)")
    private CoreMetricVO facilityGoodRate;

    @Schema(description = "活动开展数")
    private CoreMetricVO newSceneCount;
}
