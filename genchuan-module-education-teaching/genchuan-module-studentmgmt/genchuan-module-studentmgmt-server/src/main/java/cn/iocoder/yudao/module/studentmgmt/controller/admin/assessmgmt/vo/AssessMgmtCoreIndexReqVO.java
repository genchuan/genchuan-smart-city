package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 考评核心指标统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssessMgmtCoreIndexReqVO {

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计日期")
    private String date;
    @Schema(description = "新增考评记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("新增考评记录数")
    private Integer newAssessCount;
    @Schema(description = "发布数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("发布数")
    private Integer publishCount;

}
