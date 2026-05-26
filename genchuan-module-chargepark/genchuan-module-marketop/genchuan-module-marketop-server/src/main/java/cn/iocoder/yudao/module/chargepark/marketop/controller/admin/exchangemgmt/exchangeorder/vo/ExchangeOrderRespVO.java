package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 兑换订单 Response VO")
@Data
public class ExchangeOrderRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "订单编号(唯一)")
    private String no;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "用户名称")
    private String userName;

    @Schema(description = "类目ID")
    private Long categoryId;

    @Schema(description = "类目名称")
    private String categoryName;

    @Schema(description = "商品ID")
    private Long goodsId;

    @Schema(description = "商品名称")
    private String goodsName;

    @Schema(description = "消耗积分")
    private Integer costPoint;

    @Schema(description = "支付状态(待支付/已支付/已完成/已取消)")
    private String payStatus;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "发货时间")
    private LocalDateTime shipTime;

    @Schema(description = "物流信息")
    private String logisticsInfo;

    @Schema(description = "归档时间")
    private LocalDateTime archiveTime;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建者名称")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
