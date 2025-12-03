package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "接诊趋势对象")
public class TreatTrendVO {
    @Schema(description = "日期，格式 yyyy-MM-dd", example = "2025-11-01")
    private String date;

    @Schema(description = "接诊量", example = "150")
    private Integer treatVolume;
}
