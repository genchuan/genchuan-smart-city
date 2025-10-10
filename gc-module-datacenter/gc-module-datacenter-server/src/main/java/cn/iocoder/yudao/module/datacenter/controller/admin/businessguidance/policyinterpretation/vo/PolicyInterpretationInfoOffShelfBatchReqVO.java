package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 管理后台 - 政策解读信息批量下架 Request VO
 */
@Data
@Schema(description = "管理后台 - 政策解读信息批量下架 Request VO")
public class PolicyInterpretationInfoOffShelfBatchReqVO {

    @Schema(description = "ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "ID列表不能为空")
    private List<Long> ids;

    @Schema(description = "下架原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "关联政策已失效")
    @NotNull(message = "下架原因不能为空")
    private String offShelfReason;
}