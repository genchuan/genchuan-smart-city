package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 兑换订单 Response VO")
@Data
public class ExchangeOrderRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "主订单ID")
    private Long orderId;

    @Schema(description = "类目ID")
    private Long categoryId;

    @Schema(description = "类目名称")
    private String categoryName;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "金额")
    private BigDecimal amount;

    @Schema(description = "订单状态（待支付/已支付/已完成/已取消）")
    private String status;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "发货状态")
    private String deliverStatus;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建者名称")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
