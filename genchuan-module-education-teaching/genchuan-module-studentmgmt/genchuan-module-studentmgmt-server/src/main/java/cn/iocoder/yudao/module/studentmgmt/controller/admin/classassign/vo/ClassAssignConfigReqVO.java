package cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 确认 Request VO")
@Data
public class ClassAssignConfigReqVO {

    @Schema(description = "分班任务 ID 列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分班任务 ID 列表不能为空")
    private Long[] ids;
    @Schema(description = "确认人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "确认人不能为空")
    private String confirmUser;
    @Schema(description = "确认时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "确认时间不能为空")
    private LocalDateTime confirmTime;
}