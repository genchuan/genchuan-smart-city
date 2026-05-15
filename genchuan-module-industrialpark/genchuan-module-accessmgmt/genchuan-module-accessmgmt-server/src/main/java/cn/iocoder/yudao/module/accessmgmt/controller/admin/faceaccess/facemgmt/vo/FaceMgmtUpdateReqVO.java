package cn.iocoder.yudao.module.accessmgmt.controller.admin.faceaccess.facemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(description = "管理后台 - 人脸信息修改 Request VO")
@Data
public class FaceMgmtUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "主键ID不能为空")
    private Long id;

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

    @Schema(description = "权限状态（已授权/未授权/已过期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "权限状态不能为空")
    private String authStatus;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}