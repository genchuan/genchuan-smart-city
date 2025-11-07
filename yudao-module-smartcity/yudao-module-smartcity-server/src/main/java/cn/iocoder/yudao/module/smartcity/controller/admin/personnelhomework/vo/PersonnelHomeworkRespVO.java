package cn.iocoder.yudao.module.smartcity.controller.admin.personnelhomework.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 人员作业 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PersonnelHomeworkRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "19217")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "作业编号")
    @ExcelProperty("作业编号")
    private String jobNumber;

    @Schema(description = "作业人员编号", example = "6884")
    @ExcelProperty("作业人员编号")
    private String operatorId;

    @Schema(description = "作业区域编号")
    @ExcelProperty("作业区域编号")
    private String assignmentAreaNumber;

    @Schema(description = "作业时间")
    @ExcelProperty("作业时间")
    private LocalDateTime operationTime;

    @Schema(description = "作业内容")
    @ExcelProperty("作业内容")
    private String homeworkContent;

    @Schema(description = "作业方式")
    @ExcelProperty("作业方式")
    private String operationMode;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String notes;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}