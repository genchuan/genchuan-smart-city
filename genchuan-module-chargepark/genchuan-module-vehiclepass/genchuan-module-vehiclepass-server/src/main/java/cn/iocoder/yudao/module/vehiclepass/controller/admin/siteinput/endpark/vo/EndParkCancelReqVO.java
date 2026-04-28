package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 结束停车取消 Request VO")
@Data
public class EndParkCancelReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "取消理由", requiredMode = Schema.RequiredMode.REQUIRED, example = "用户取消结束停车操作")
    @NotBlank(message = "取消理由不能为空")
    private String cancelReason;

}