package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 应急资源总览 调度 Request VO")
@Data
public class EmergResoverDispatchReqVO {

    @Schema(description = "调度资源ID", example = "f8a6b7c2d6e947af9b27e1d34f6f0725")
    @NotBlank(message = "资源ID不能为空")
    private String resId;

    @Schema(description = "调度数量", example = "10")
    @NotNull(message = "调度数量不能为空")
    @Min(value = 1, message = "调度数量必须大于0")
    private Integer dispatchCount;

    @Schema(description = "调度原因", example = "需要调度")
    @NotBlank(message = "调度原因不能为空")
    private String reason;

    @Schema(description = "接收人id", example = "1")
    @NotNull(message = "接收人不能为空")
    private Integer recipientId;
}
