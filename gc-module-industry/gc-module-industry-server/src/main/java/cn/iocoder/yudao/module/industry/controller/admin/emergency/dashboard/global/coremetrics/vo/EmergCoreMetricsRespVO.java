package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 应急核心指标 Response VO")
@Data
public class EmergCoreMetricsRespVO {

    @Schema(description = "应急事件办结率（complete_rate），单位：%，来源：stat_mon_evt_rpt表",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "85.5")
    @ExcelProperty("应急事件办结率")
    private BigDecimal completeRate;

    @Schema(description = "平均处置时长（avg_handle_endure），单位：小时，来源：stat_mon_evt_rpt表",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "12.3")
    @ExcelProperty("平均处置时长")
    private BigDecimal avgHandleEndure;

    @Schema(description = "预警准确率（early_warn_acc_rate），单位：%，来源：stat_early_warn表",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "92.0")
    @ExcelProperty("预警准确率")
    private BigDecimal earlyWarnAccRate;

    @Schema(description = "资源调用率（res_use_rate），单位：%，来源：biz_emerg_res表",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "76.5")
    @ExcelProperty("资源调用率")
    private BigDecimal resUseRate;

    @Schema(description = "风险整改率（risk_rectify_rate），单位：%，来源：biz_risk_hazard表",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "88.8")
    @ExcelProperty("风险整改率")
    private BigDecimal riskRectifyRate;
}
