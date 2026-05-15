package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 授权 Request VO")
@Data
public class FaceMgmtAuthReqVO {

    @Schema(description = "人脸信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @NotNull(message = "人脸信息ID不能为空")
    private Long id;

    @Schema(description = "权限有效期，格式时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "权限有效期不能为空")
    private String authValidity;

}