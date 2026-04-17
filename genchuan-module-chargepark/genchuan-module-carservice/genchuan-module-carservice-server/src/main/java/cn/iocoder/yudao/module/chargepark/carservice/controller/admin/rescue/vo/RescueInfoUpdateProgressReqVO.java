package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 救援信息更新进度 Request VO")
@Data
public class RescueInfoUpdateProgressReqVO {

    @Schema(description = "救援信息 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "救援信息 ID 不能为空")
    private Long id;

    @Schema(description = "当前进度描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "已到达现场")
    @NotBlank(message = "救援进度不能为空")
    private String progress;

    @Schema(description = "现场照片 URL")
    private String photo;

}
