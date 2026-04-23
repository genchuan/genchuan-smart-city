package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 学生核心指标统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StudentInfoCoreIndexRespVO {

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计日期")
    private String date;

    @Schema(description = "新增学生数", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("新增学生数")
    private Integer newStudentCount;

    @Schema(description = "学籍异动数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("学籍异动数")
    private Integer statusChangeCount;


}
