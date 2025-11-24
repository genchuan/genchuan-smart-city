package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.coremetrics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 市监核心指标 Response VO")
@Data
public class MarketCoreMetricsRespVO {

    @Schema(description = "市场主体总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "523440")
    @ExcelProperty("市场主体总数")
    private Integer totalEntity;

    @Schema(description = "今日新增案件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "34")
    @ExcelProperty("今日新增案件数")
    private Integer todayNewCase;

    @Schema(description = "抽检合格率（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "97.5")
    @ExcelProperty("抽检合格率")
    private Double smplQualifiedRate;

    @Schema(description = "预警处置率（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "88.3")
    @ExcelProperty("预警处置率")
    private Double warnHandleRate;

    @Schema(description = "信用良好主体占比（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "72.1")
    @ExcelProperty("信用良好主体占比")
    private Double goodCreditRate;
}
