package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.overview.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 应急全域数据概览 Response VO")
@Data
//TODO：1.totalEvtCount与数据库有异；2.其余totalResCount、highRiskCount源自的表现在尚未建立
public class EmergOverviewRespVO {

    @Schema(description = "应急事件总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "120")
    @ExcelProperty("应急事件总数")
    private Integer totalEvtCount;

    @Schema(description = "在处置事件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "30")
    @ExcelProperty("在处置事件数")
    private Integer handlCount;

    @Schema(description = "已办结事件数", requiredMode = Schema.RequiredMode.REQUIRED, example = "90")
    @ExcelProperty("已办结事件数")
    private Integer completedCount;

    @Schema(description = "应急资源总数", requiredMode = Schema.RequiredMode.REQUIRED, example = "50")
    @ExcelProperty("应急资源总数")
    private Integer totalResCount;

    @Schema(description = "高风险隐患数", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    @ExcelProperty("高风险隐患数")
    private Integer highRiskCount;

    @Schema(description = "行政区划覆盖数", requiredMode = Schema.RequiredMode.REQUIRED, example = "12")
    @ExcelProperty("行政区划覆盖数")
    private Integer regionCoverCount;
}
