package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 删除 Request VO")
@Data
public class FaceMgmtDeleteReqVO {

    @Schema(description = "人脸信息ID数组，支持批量删除", requiredMode = Schema.RequiredMode.REQUIRED, example = "[2,3]")
    @NotEmpty(message = "ID列表不能为空")
    private List<Long> ids;

}