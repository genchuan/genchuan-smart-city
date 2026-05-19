package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "管理后台 - 通行验证 Request VO")
@Data
public class FaceMgmtVerifyReqVO {

    @Schema(description = "待验证人脸图片Base64编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "人脸图片不能为空")
    private String faceImg;

}