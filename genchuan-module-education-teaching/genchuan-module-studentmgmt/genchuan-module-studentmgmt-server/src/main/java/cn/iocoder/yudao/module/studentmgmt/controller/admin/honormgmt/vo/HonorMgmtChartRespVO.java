package cn.iocoder.yudao.module.studentmgmt.controller.admin.honormgmt.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 荣誉管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class HonorMgmtChartRespVO {
    @Schema(description = "总荣誉数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("总荣誉数")
    private Integer totalHonorCount;
    @Schema(description = "待审核荣誉数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("待审核荣誉数")
    private Integer pendingAuditCount;
    @Schema(description = "今日推送数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("今日推送数")
    private Integer todayPushCount;
    @Schema(description = "优秀学生人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("优秀学生人数")
    private Integer excellentStudentCount;
    @Schema(description = "奖学金人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("奖学金人数")
    private Integer scholarshipCount;
    @Schema(description = "竞赛获奖人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("竞赛获奖人数")
    private Integer competitionCount;

}
