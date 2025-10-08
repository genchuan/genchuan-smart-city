package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.nationalpolicy.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

/**
 * 管理后台 - 国家政策信息更新状态 Request VO
 *
 * @author Gyh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Schema(description = "管理后台 - 国家政策信息更新状态 Request VO")
public class NationalPolicyInfoUpdateStatusReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "启用状态，启用/禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "启用")
    @NotBlank(message = "启用状态不能为空")
    private String enableStatus;
}