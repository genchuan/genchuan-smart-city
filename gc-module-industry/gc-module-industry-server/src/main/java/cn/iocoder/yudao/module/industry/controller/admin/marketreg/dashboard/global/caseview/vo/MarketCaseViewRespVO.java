package cn.iocoder.yudao.module.industry.controller.admin.marketreg.dashboard.global.caseview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 市监案件总览视图 Response VO")
@Data
public class MarketCaseViewRespVO {

    @Schema(description = "案件总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    @ExcelProperty("案件总数")
    private Integer totalCase;

    @Schema(description = "待受理案件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
    @ExcelProperty("待受理案件数")
    private Integer pendAcceptCase;

    @Schema(description = "办理中案件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    @ExcelProperty("办理中案件数")
    private Integer handlCase;

    @Schema(description = "已办结案件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "40")
    @ExcelProperty("已办结案件数")
    private Integer completedCase;

    @Schema(description = "超时案件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @ExcelProperty("超时案件数")
    private Integer overtimeCase;

    @Schema(description = "案件处置率 (%)", requiredMode = Schema.RequiredMode.REQUIRED, example = "66.7")
    @ExcelProperty("案件处置率 (%)")
    private BigDecimal caseHandleRate;
}
