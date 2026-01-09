package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.overview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 市监全域数据地图 Response VO")
@Data
public class MarketOverviewRespVO {

    @Schema(description = "监管对象总数，来源表：biz_supv_obj", requiredMode = Schema.RequiredMode.REQUIRED, example = "5000")
    @ExcelProperty("监管对象总数")
    private Integer totalSupvObj;

    @Schema(description = "在办案件数，来源表：biz_case", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    @ExcelProperty("在办案件数")
    private Integer handlCaseCount;

    @Schema(description = "未处置预警数，来源表：biz_warn", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
    @ExcelProperty("未处置预警数")
    private Integer pendWarnCount;

    @Schema(description = "区域覆盖率（百分比），来源表：biz_supv_coverage", requiredMode = Schema.RequiredMode.REQUIRED, example = "95")
    @ExcelProperty("区域覆盖率(%)")
    private Double regionCoverageRate;

}
