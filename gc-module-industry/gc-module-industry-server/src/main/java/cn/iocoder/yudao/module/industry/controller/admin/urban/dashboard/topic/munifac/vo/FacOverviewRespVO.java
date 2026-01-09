package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

// 市政设施概览数据（核心指标）
@Schema(description = "管理后台 - 市政设施专题-市政设施概览数据 Response VO")
@Data
public class FacOverviewRespVO {

    @Schema(description = "紧急故障数", requiredMode = Schema.RequiredMode.REQUIRED, example = "8")
    @ExcelProperty("紧急故障数")
    private Integer emergencyCount;

    @Schema(description = "一般故障数", requiredMode = Schema.RequiredMode.REQUIRED, example = "15")
    @ExcelProperty("一般故障数")
    private Integer normalCount;

    @Schema(description = "总故障数", requiredMode = Schema.RequiredMode.REQUIRED, example = "23")
    @ExcelProperty("总故障数")
    private Integer totalFault;

    @Schema(description = "已处置数", requiredMode = Schema.RequiredMode.REQUIRED, example = "20")
    @ExcelProperty("已处置数")
    private Integer handledCount;

    @Schema(description = "处置率（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "87.0")
    @ExcelProperty("处置率（%）")
    private BigDecimal handlingRate;

    @Schema(description = "超时未处置数", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @ExcelProperty("超时未处置数")
    private Integer timeoutCount;

}
