package cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 缴费统计新增/修改 Request VO")
@Data
public class StatParkPaySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24155")
    private Long id;

    @Schema(description = "统计记录唯一标识码", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "统计记录唯一标识码不能为空")
    private String statCode;

    @Schema(description = "数据统计周期（如日/周/月）", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "数据统计周期（如日/周/月）不能为空")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年9月”", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "统计区间起始时间", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "统计区间起始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statStartTime;

    @Schema(description = "统计区间截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "统计区间截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statEndTime;

    @Schema(description = "统计时间点", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "统计时间点不能为空")
    private LocalDateTime statTime;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
//    @NotEmpty(message = "所属区域名称不能为空")
    private String regionName;

    @Schema(description = "GB/T 2260 标准12位地区码", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotEmpty(message = "GB/T 2260 标准12位地区码不能为空")
    private String regionFullCode;

    @Schema(description = "统计周期内缴费总订单数", requiredMode = Schema.RequiredMode.REQUIRED, example = "19906")
//    @NotNull(message = "统计周期内缴费总订单数不能为空")
    private Integer totalOrderCount;

    @Schema(description = "统计周期内缴费总金额", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "统计周期内缴费总金额不能为空")
    private BigDecimal totalPayAmount;

    @Schema(description = "统计周期内缴费成功占比", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "统计周期内缴费成功占比不能为空")
    private BigDecimal paySuccessRate;

    @Schema(description = "微信支付占比", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "微信支付占比不能为空")
    private BigDecimal wechatRatio;

    @Schema(description = "支付宝支付占比", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "支付宝支付占比不能为空")
    private BigDecimal alipayRatio;

    @Schema(description = "银行卡支付占比", requiredMode = Schema.RequiredMode.REQUIRED)
//    @NotNull(message = "银行卡支付占比不能为空")
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

}
