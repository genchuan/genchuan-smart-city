package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;

/**
 * 管理后台 - 政策解读信息更新状态 Request VO
 */
@Data
@Schema(description = "管理后台 - 政策解读信息更新状态 Request VO")
public class PolicyInterpretationInfoUpdateStatusReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "解读状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "已下架")
    @NotNull(message = "解读状态不能为空")
    private String interpretationStatus;

    @Schema(description = "下架原因", example = "关联政策已失效")
    private String offShelfReason;
}