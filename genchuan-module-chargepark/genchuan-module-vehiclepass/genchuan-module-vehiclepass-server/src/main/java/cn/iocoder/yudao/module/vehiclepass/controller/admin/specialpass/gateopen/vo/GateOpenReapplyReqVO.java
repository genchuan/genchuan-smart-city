package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 开闸管理重新申请 Request VO")
@Data
public class GateOpenReapplyReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "开闸原因：紧急通行 / 故障处理 / 其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "故障处理")
    @NotEmpty(message = "开闸原因不能为空")
    private String openReason;

    @Schema(description = "备注", example = "道闸故障，需要手动开闸处理")
    private String remark;

}