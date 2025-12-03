package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "接诊趋势对象")
public class TreatTrendVO {
    @Schema(description = "日期，格式 yyyy-MM-dd", example = "2025-11-01")
    private String date;

    @Schema(description = "值", example = "150")
    private BigDecimal value;
}
