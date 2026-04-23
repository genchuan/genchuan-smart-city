package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 卡种订单 Response VO")
@Data
public class CardOrderRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "订单编号")
    private String no;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "卡种ID")
    private Long cardId;

    @Schema(description = "卡种名称")
    private String cardName;

    @Schema(description = "订单金额")
    private BigDecimal amount;

    @Schema(description = "支付状态")
    private String payStatus;

    @Schema(description = "支付时间")
    private LocalDateTime payTime;

    @Schema(description = "激活时间")
    private LocalDateTime activeTime;

    @Schema(description = "开票状态")
    private String invoiceStatus;

    @Schema(description = "归档时间")
    private LocalDateTime archiveTime;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建者名称")
    private String creatorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
