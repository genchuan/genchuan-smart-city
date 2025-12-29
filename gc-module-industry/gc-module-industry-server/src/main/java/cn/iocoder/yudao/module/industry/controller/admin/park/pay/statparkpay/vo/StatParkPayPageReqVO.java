package cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 缴费统计分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StatParkPayPageReqVO extends PageParam {

    @Schema(description = "统计记录唯一标识码")
    private String statCode;

    @Schema(description = "数据统计周期（如day/week/month）")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年9月”", requiredMode = Schema.RequiredMode.REQUIRED)
    private String statCycleName;

    @Schema(description = "统计时间点")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statTime;

    @Schema(description = "所属区域名称", example = "张三")
    private String regionName;

    @Schema(description = "GB/T 2260 标准12位地区码")
    private String regionFullCode;

    @Schema(description = "统计周期内缴费总订单数", example = "19906")
    private Integer totalOrderCount;

    @Schema(description = "统计周期内缴费总金额")
    private BigDecimal totalPayAmount;

    @Schema(description = "统计周期内缴费成功占比")
    private BigDecimal paySuccessRate;

    @Schema(description = "微信支付占比")
    private BigDecimal wechatRatio;

    @Schema(description = "支付宝支付占比")
    private BigDecimal alipayRatio;

    @Schema(description = "银行卡支付占比")
    private BigDecimal cardRatio;

    @Schema(description = "各区域缴费占比（数据库JSON格式）")
    private String regionPayRatio;

    @Schema(description = "分类扩展字段1，预留")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留")
    private String extCommon2;

    @Schema(description = "记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
