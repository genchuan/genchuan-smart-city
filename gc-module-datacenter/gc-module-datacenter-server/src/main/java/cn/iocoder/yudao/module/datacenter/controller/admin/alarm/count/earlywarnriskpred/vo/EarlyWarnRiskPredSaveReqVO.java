package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预警告警风险趋势预测新增/修改 Request VO")
@Data
public class EarlyWarnRiskPredSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "预测ID")
    private String trendForecastId;

    @Schema(description = "预测对象")
    private String forecastObject;

    @Schema(description = "预测对象值")
    private String forecastObjectValue;

    @Schema(description = "历史数据周期")
    private String historyDataCycle;

    @Schema(description = "历史数据范围")
    private String historyDataRange;

    @Schema(description = "预测周期")
    private String forecastCycle;

    @Schema(description = "预测时间范围")
    private String forecastTimeRange;

    @Schema(description = "预测指标")
    private String forecastIndicators;

    @Schema(description = "预测模型")
    private String forecastModel;

    @Schema(description = "预测准确率")
    private BigDecimal forecastAccuracy;

    @Schema(description = "预测人ID")
    private String forecastUserId;

    @Schema(description = "预测人姓名")
    private String forecastUserName;

    @Schema(description = "预测时间")
    private LocalDateTime forecastTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}