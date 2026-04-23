package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 无牌入场审核 Request VO")
@Data
public class UnplateEnterAuditReqVO {

    @Schema(description = "记录主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "记录主键ID不能为空")
    private Long id;

    @Schema(description = "审核结果：通过/驳回", requiredMode = Schema.RequiredMode.REQUIRED, example = "通过")
    @NotBlank(message = "审核结果不能为空")
    private String auditResult;

    @Schema(description = "审核意见", example = "信息无误，允许入场")
    private String auditComment;

}