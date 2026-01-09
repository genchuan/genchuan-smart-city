package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.supvview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 市监对象分布视图 Response VO")
@Data
public class MarketSupvViewRespVO {

    @Schema(description = "监管对象总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    @ExcelProperty("监管对象总数")
    private Integer totalSupvObj;

    @Schema(description = "企业数", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    @ExcelProperty("企业数")
    private Integer entCount;

    @Schema(description = "个体户数", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
    @ExcelProperty("个体户数")
    private Integer individualCount;

    @Schema(description = "特种设备数", requiredMode = Schema.RequiredMode.REQUIRED, example = "40")
    @ExcelProperty("特种设备数")
    private Integer specEquipCount;

    @Schema(description = "未监管对象数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @ExcelProperty("未监管对象数")
    private Integer unsupvdCount;

    @Schema(description = "监管覆盖率（百分比）", requiredMode = Schema.RequiredMode.REQUIRED, example = "91.67")
    @ExcelProperty("监管覆盖率 (%)")
    private BigDecimal supvCoverageRate;

}
