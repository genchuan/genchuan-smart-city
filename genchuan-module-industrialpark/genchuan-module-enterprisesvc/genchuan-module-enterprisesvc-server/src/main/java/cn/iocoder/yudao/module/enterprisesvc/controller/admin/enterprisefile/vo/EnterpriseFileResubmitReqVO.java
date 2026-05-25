package cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 企业档案重新提交 Request VO")
@Data
public class EnterpriseFileResubmitReqVO {

    @Schema(description = "企业档案 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "企业档案 ID 不能为空")
    private Long id;

}