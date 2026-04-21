package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 代付记录分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AgentRecordPageReqVO extends PageParam {

    @Schema(description = "记录编号，模糊查询")
    private String recordNo;

    @Schema(description = "关联订单ID")
    private Long orderId;

    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "状态：normal/abnormal")
    private String status;
}
