package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 国家政策信息更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class NationalPolicyInfoUpdateReqVO extends NationalPolicyInfoBaseVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "主键ID不能为空")
    private Long id;
}