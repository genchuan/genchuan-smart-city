package cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 开闸管理新增 Request VO")
@Data
public class GateOpenCreateReqVO {

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "5128")
    @NotNull(message = "场站ID不能为空")
    private Long stationId;

    @Schema(description = "开闸原因：紧急通行 / 故障处理 / 其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "紧急通行")
    @NotEmpty(message = "开闸原因不能为空")
    private String openReason;

    @Schema(description = "备注", example = "救护车紧急通行")
    private String remark;

}