package cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 商户对账单分页查询 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReconcileBillPageReqVO extends PageParam {

    @Schema(description = "对账单号")
    private String billNo;

    @Schema(description = "商户ID")
    private Long merchantId;

    @Schema(description = "对账状态：pending/reconciled/abnormal")
    private String status;

    @Schema(description = "对账周期，如 2026-03、2026-W14")
    private String cycle;
}
