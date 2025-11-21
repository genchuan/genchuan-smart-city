package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 预警告警风险趋势预测分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EarlyWarnRiskPredPageReqVO extends PageParam {

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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] forecastTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}