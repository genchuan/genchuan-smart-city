package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 权限配置 Request VO")
@Data
public class FaceMgmtConfigReqVO {

    @Schema(description = "人脸信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "人脸信息ID不能为空")
    private Long id;

    @Schema(description = "通行区域")
    private String accessArea;

    @Schema(description = "权限有效期，格式时间戳")
    private Long authValidity;

}