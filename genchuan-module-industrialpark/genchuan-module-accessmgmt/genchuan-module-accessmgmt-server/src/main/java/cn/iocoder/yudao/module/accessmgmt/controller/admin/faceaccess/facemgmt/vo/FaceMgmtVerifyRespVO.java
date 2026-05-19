package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 通行验证 Response VO")
@Data
public class FaceMgmtVerifyRespVO {

    @Schema(description = "验证结果，true为通过")
    private Boolean pass;

    @Schema(description = "匹配到的人员姓名")
    private String userName;

    @Schema(description = "允许通行区域")
    private String accessArea;

    @Schema(description = "验证结果描述")
    private String msg;

}