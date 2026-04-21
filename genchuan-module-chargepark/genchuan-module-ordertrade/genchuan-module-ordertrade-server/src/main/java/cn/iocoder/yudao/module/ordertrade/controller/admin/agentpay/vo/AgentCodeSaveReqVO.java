package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 代付码新增/修改 Request VO")
@Data
public class AgentCodeSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "商户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "商户ID不能为空")
    private Long merchantId;

    @Schema(description = "关联规则ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "关联规则ID不能为空")
    private Long ruleId;

    @Schema(description = "过期时间")
    private LocalDateTime expireTime;

    @Schema(description = "状态：unused/used/expired")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;
}
