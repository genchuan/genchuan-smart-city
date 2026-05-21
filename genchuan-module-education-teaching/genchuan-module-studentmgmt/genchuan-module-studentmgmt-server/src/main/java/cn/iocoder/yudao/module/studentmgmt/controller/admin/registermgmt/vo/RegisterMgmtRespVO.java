package cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 报名管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RegisterMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17725")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("学生姓名")
    private String studentName;

    @Schema(description = "身份证号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("身份证号")
    private String idCard;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String phone;

    @Schema(description = "意向专业", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("意向专业")
    private String major;

    @Schema(description = "报名时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报名时间")
    private LocalDateTime applyTime;

    @Schema(description = "审核人")
    @ExcelProperty("审核人")
    private String auditUser;

    @Schema(description = "审核时间")
    @ExcelProperty("审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "录取确认时间")
    @ExcelProperty("录取确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "状态：待审核/已录取", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段 1")
    @ExcelProperty("备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    @ExcelProperty("备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
