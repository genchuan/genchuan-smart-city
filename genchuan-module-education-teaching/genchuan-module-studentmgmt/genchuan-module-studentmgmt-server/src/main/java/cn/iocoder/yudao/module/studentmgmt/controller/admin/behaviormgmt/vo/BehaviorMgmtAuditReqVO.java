package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 行为管理审批 Request VO")
@Data
public class BehaviorMgmtAuditReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3836")
    private Long[] ids;

    @Schema(description = "状态：待审批/已通过/已驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：待审批/已通过/已驳回不能为空")
    private String status;

    @Schema(description = "备注", example = "随便")
    private String remark;

}