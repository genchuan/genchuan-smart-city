package cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 学工首页 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WorkHomeRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10850")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "记录类型（荣誉 / 考评 / 违纪 / 行为 / 心理 / 资助），关联芋道字典表：work_home_record_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("记录类型")
    private String recordType;

    @Schema(description = "记录标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("记录标题")
    private String recordTitle;

    @Schema(description = "学生姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("学生姓名")
    private String studentName;

    @Schema(description = "班级名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("班级名称")
    private String className;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;
}
