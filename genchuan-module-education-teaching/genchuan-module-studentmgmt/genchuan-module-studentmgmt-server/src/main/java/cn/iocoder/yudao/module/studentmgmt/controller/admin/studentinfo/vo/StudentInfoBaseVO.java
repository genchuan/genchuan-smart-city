package cn.iocoder.yudao.module.studentmgmt.controller.admin.studentinfo.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 学生信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StudentInfoBaseVO {
    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18139")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("学号")
    private String studentNo;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("姓名")
    private String name;
}
