package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 分班管理新增/修改 Request VO")
@Data
public class ClassAssignSaveReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26674")
    private Long id;

    @Schema(description = "分班规则", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分班规则不能为空")
    private String ruleContent;

    @Schema(description = "分班学生数")
    private Integer studentNum;

    @Schema(description = "分班时间")
    private LocalDateTime assignTime;

    @Schema(description = "确认人")
    private String confirmUser;

    @Schema(description = "确认时间")
    private LocalDateTime confirmTime;

    @Schema(description = "状态：未分班/已分班", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：未分班/已分班不能为空")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}