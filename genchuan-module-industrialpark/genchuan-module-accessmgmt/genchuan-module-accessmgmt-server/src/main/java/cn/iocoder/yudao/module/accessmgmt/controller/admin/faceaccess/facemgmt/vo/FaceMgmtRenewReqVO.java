package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 续期 Request VO")
@Data
public class FaceMgmtRenewReqVO {

    @Schema(description = "人脸信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
    @NotNull(message = "人脸信息ID不能为空")
    private Long id;

    @Schema(description = "新的权限有效期，格式时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "新的权限有效期不能为空")
    private String authValidity;

}