package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 代付码分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentCodePageReqVO extends PageParam {

    @Schema(description = "代付码，模糊查询")
    private String code;

    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "关联规则ID")
    private Long ruleId;

    @Schema(description = "状态：unused/used/expired")
    private String status;
}
