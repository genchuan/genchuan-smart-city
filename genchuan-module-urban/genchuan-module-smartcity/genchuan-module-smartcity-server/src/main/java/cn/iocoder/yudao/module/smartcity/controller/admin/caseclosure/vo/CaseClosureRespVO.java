package cn.iocoder.yudao.module.smartcity.controller.admin.caseclosure.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;import cn.idev.excel.annotation.ExcelProperty;

@Schema(description = "管理后台 - 案件结案 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CaseClosureRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26110")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "案件ID", example = "22843")
    @ExcelProperty("案件ID")
    private String caseId;

    @Schema(description = "结案原因", example = "不香")
    @ExcelProperty("结案原因")
    private String closureReason;

    @Schema(description = "结案部门")
    @ExcelProperty("结案部门")
    private String closureDepartment;

    @Schema(description = "结案人")
    @ExcelProperty("结案人")
    private String closurePerson;

    @Schema(description = "结案时间")
    @ExcelProperty("结案时间")
    private LocalDateTime closureTime;

    @Schema(description = "审批人")
    @ExcelProperty("审批人")
    private String approvalPerson;

    @Schema(description = "审批时间")
    @ExcelProperty("审批时间")
    private LocalDateTime approvalTime;

    @Schema(description = "审批意见")
    @ExcelProperty("审批意见")
    private String approvalOpinion;

    @Schema(description = "归档编号")
    @ExcelProperty("归档编号")
    private String archiveNumber;

    @Schema(description = "归档位置")
    @ExcelProperty("归档位置")
    private String archiveLocation;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}