package cn.iocoder.yudao.module.studentmgmt.controller.admin.assessmgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 考评核心指标统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssessMgmtCycleTrendRespVO {

    @Schema(description = "周期名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("周期名称")
    private String cycleName;
    @Schema(description = "平均得分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平均得分")
    private BigDecimal avgScore;
    @Schema(description = "班级排名", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("班级排名")
    private Integer rank;
}
