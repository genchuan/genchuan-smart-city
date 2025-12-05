package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "单个核心指标数据")
public class CoreMetricVO {
    @Schema(description = "指标值", example = "90")
    private BigDecimal value;

    @Schema(description = "指标更新时间",example = "1764604800000")
    private LocalDateTime updateTime;

    @Schema(description = "告警状态，0-正常，1-预警", example = "0")
    private Integer warnStatus;

    @Schema(description = "近七天趋势图")
    private List<TreatTrendVO> treatTrendVOList;

}
