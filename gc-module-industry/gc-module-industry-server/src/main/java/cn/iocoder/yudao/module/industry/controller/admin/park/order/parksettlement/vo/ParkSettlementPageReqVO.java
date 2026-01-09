package cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 分账结算表分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkSettlementPageReqVO extends PageParam {

    @Schema(description = "商户ID，商户唯一标识", example = "506")
    private Long merchantId;

    @Schema(description = "结算总金额，本次结算的总金额")
    private BigDecimal totalAmount;

    @Schema(description = "分账比例配置，JSON格式varchar")
    private String splitRatio;

    @Schema(description = "平台分成金额，平台应分得的金额")
    private BigDecimal platformAmount;

    @Schema(description = "商户分成金额，商户应分得的金额")
    private BigDecimal merchantAmount;

    @Schema(description = "税费金额，本次结算产生的税费金额")
    private BigDecimal taxAmount;

    @Schema(description = "结算状态：待生成 / 待审核 / 已结算 / 已驳回", example = "2")
    private String settlementStatus;

    @Schema(description = "生成时间，结算数据生成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] generateTime;

    @Schema(description = "审核时间，结算审核时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

    @Schema(description = "审核人，结算审核人")
    private String auditBy;

    @Schema(description = "转账时间，结算转账完成时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] transferTime;

    @Schema(description = "统计唯一标识码")
    private String statCode;

    @Schema(description = "统计周期标识")
    private String statCycle;

    @Schema(description = "统计周期名称，如：2025年9月", example = "王五")
    private String statCycleName;

    @Schema(description = "统计区间起始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statStartTime;

    @Schema(description = "统计区间截止时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statEndTime;

    @Schema(description = "统计时间，实际统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "备注，业务备注说明", example = "随便")
    private String remark;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间，记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
