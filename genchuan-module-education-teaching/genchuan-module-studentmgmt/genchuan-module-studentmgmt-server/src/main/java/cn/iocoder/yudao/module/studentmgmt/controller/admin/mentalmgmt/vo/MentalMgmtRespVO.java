package cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 心理管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MentalMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29416")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9996")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "心理状态：正常/关注/高危", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("心理状态：正常/关注/高危")
    private String mentalStatus;

    @Schema(description = "风险等级：低/中/高", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("风险等级：低/中/高")
    private String riskLevel;

    @Schema(description = "评估时间")
    @ExcelProperty("评估时间")
    private LocalDateTime evaluateTime;

    @Schema(description = "咨询预约时间")
    @ExcelProperty("咨询预约时间")
    private LocalDateTime consultTime;

    @Schema(description = "干预时间")
    @ExcelProperty("干预时间")
    private LocalDateTime interveneTime;

    @Schema(description = "干预内容")
    @ExcelProperty("干预内容")
    private String interveneContent;

    @Schema(description = "状态：待评估/咨询中/已干预", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：待评估/咨询中/已干预")
    private String status;

    @Schema(description = "备注", example = "随便")
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