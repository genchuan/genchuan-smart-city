package cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 企业档案驳回 Request VO")
@Data
public class EnterpriseFileRejectReqVO {

    @Schema(description = "企业档案 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "企业档案 ID 不能为空")
    private Long id;

    @Schema(description = "驳回原因（存储至备用字段1）", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "企业资质材料不全，请补充后重新提交")
    private String reserve1;

}