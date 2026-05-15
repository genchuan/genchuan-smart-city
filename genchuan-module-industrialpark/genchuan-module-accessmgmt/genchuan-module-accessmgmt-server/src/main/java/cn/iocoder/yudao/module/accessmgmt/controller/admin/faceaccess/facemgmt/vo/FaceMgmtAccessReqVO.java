package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 通行 Request VO")
@Data
public class FaceMgmtAccessReqVO {

    @Schema(description = "人脸信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "人脸信息ID不能为空")
    private Long id;

}