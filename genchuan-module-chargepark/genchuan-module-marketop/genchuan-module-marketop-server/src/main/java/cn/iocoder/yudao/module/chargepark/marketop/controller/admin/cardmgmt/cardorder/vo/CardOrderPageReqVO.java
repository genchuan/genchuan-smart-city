package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 卡种订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CardOrderPageReqVO extends PageParam {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "卡种ID")
    private Long cardId;

    @Schema(description = "订单编号")
    private String no;

    @Schema(description = "支付状态")
    private String payStatus;

    @Schema(description = "开票状态")
    private String invoiceStatus;

    @Schema(description = "日期筛选，格式如：2026-04-24")
    private String date;

    @Schema(description = "开始时间")
    private Long startTime;

    @Schema(description = "结束时间")
    private Long endTime;

    @Schema(description = "支付开始时间")
    private Long payStartTime;

    @Schema(description = "支付结束时间")
    private Long payEndTime;

    @Schema(description = "激活开始时间")
    private Long activeStartTime;

    @Schema(description = "激活结束时间")
    private Long activeEndTime;

    @Schema(description = "归档开始时间")
    private Long archiveStartTime;

    @Schema(description = "归档结束时间")
    private Long archiveEndTime;

}
