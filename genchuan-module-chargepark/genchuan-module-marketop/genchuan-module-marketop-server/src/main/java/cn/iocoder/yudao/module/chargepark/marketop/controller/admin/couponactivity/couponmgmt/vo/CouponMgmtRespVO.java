package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 优惠券 Response VO")
@Data
public class CouponMgmtRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "券名称")
    private String name;

    @Schema(description = "券类型")
    private String type;

    @Schema(description = "面额")
    private BigDecimal amount;

    @Schema(description = "使用条件")
    private String useCondition;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "发放人")
    private Long senderId;

    @Schema(description = "发放人名称")
    private String senderName;

    @Schema(description = "发放时间")
    private LocalDateTime sendTime;

    @Schema(description = "领取人")
    private Long receiverId;

    @Schema(description = "领取人名称")
    private String receiverName;

    @Schema(description = "核销时间")
    private LocalDateTime verifyTime;

    @Schema(description = "有效期")
    private LocalDateTime validTime;

    @Schema(description = "券描述")
    private String description;

    @Schema(description = "适用场站")
    private String stationIds;

    @Schema(description = "适用场站名称")
    private String stationNames;

    @Schema(description = "领取量")
    private Integer sendCount;

    @Schema(description = "核销率")
    private BigDecimal verifyRate;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建者名称")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
