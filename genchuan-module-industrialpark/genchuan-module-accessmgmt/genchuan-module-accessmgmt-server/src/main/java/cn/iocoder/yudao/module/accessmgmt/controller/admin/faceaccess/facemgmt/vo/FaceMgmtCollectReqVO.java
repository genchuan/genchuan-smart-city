package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 人脸采集 Request VO")
@Data
public class FaceMgmtCollectReqVO {

    @Schema(description = "人脸信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "人脸信息ID不能为空")
    private Long id;

    @Schema(description = "人脸采集图片Base64编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "人脸图片不能为空")
    private String faceImg;

}