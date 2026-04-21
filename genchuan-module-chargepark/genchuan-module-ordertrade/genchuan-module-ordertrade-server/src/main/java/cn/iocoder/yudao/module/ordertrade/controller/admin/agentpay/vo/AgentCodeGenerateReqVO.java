package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 批量生成代付码 Request VO")
@Data
public class AgentCodeGenerateReqVO {

    @Schema(description = "商户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @Schema(description = "关联规则ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联规则ID不能为空")
    private Long ruleId;

    @Schema(description = "过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "生成数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "生成数量不能为空")
    @Min(value = 1, message = "生成数量不能小于1")
    private Integer count;

    @Schema(description = "备注")
    private String remark;
}
