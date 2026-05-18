package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Schema(description = "管理后台 - 人脸信息新增 Request VO")
@Data
public class FaceMgmtCreateReqVO {

    @Schema(description = "人员姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "人员姓名不能为空")
    private String userName;

    @Schema(description = "手机号（明文传入，存储时脱敏）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @Schema(description = "所属企业")
    private String company;

    @Schema(description = "通行区域")
    private String accessArea;

    @Schema(description = "权限有效期，格式时间戳")
    private Long authValidity;

}