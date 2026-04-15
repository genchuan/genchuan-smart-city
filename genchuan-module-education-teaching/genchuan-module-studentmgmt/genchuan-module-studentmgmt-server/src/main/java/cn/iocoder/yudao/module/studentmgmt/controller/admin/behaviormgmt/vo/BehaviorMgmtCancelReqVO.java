package cn.iocoder.yudao.module.studentmgmt.controller.admin.behaviormgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 行为管理审批 Request VO")
@Data
public class BehaviorMgmtCancelReqVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3836")
    private Long id;

    @Schema(description = "撤销原因", example = "不要了")
    private String cancelReason;

}