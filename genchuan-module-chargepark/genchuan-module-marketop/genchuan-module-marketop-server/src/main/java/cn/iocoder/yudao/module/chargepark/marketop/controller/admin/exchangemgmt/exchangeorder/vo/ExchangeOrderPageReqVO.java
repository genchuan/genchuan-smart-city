package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 兑换订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ExchangeOrderPageReqVO extends PageParam {

    @Schema(description = "订单编号")
    private String no;

    @Schema(description = "类目ID")
    private Long categoryId;

    @Schema(description = "商品ID")
    private Long goodsId;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "支付状态(待支付/已支付/已完成/已取消)")
    private String payStatus;

    @Schema(description = "开始时间")
    private Long startTime;

    @Schema(description = "结束时间")
    private Long endTime;

    @Schema(description = "日期")
    private String date;
}
