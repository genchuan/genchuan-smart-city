package cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 违纪管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ViolateMgmtRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15947")
    @ExcelProperty("主键 ID")
    private Long id;

    @Schema(description = "学生 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27164")
    @ExcelProperty("学生 ID")
    private Long studentId;

    @Schema(description = "违纪类型：仪容仪表/行为违规/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("违纪类型")
    private String violateType;

    @Schema(description = "处分类型：警告/记过/留校察看/开除", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("处分类型")
    private String punishType;

    @Schema(description = "违纪时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("违纪时间")
    private LocalDateTime violateTime;

    @Schema(description = "违纪原因", example = "不香")
    @ExcelProperty("违纪原因")
    private String violateReason;

    @Schema(description = "审批人")
    @ExcelProperty("审批人")
    private String auditUser;

    @Schema(description = "审批时间")
    @ExcelProperty("审批时间")
    private LocalDateTime auditTime;

    @Schema(description = "家长推送时间")
    @ExcelProperty("家长推送时间")
    private LocalDateTime pushTime;

    @Schema(description = "预警时间")
    @ExcelProperty("预警时间")
    private LocalDateTime warnTime;

    @Schema(description = "状态：待审批/已执行/已预警", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
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
