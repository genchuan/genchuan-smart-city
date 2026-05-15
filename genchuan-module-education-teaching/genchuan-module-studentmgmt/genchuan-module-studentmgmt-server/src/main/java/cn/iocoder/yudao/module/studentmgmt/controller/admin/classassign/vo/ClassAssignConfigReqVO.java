package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 确认 Request VO")
@Data
public class ClassAssignConfigReqVO {

    @Schema(description = "分班任务 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分班任务 ID 列表不能为空")
    private Long[] ids;
    @Schema(description = "确认人", requiredMode = Schema.RequiredMode.REQUIRED,example = "张老师")
    @NotEmpty(message = "确认人不能为空")
    private String confirmUser;
    @Schema(description = "确认时间，时间戳格式", requiredMode = Schema.RequiredMode.REQUIRED, example = "1744088400000")
    @NotNull(message = "确认时间不能为空")
    private LocalDateTime confirmTime;
}