package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 代付规则分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentRulePageReqVO extends PageParam {

    @Schema(description = "规则名称，模糊查询")
    private String name;

    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "代付类型：merchant/enterprise/public")
    private String agentType;

    @Schema(description = "状态：pending/enabled/disabled")
    private String status;
}
