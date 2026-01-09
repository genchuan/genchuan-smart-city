package cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.statparkpay.StatParkPayDO;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 缴费统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StatParkPayRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24155")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "统计记录唯一标识码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计记录唯一标识码")
    private String statCode;

    @Schema(description = "数据统计周期（如日/周/月）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据统计周期（如日/周/月）")
    private String statCycle;

    @Schema(description = "统计周期名称，如“2025年9月”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "统计周期名称不能为空")
    private String statCycleName;

    @Schema(description = "统计区间起始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计区间起始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statStartTime;

    @Schema(description = "统计区间截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计区间截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statEndTime;

    @Schema(description = "统计时间点", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时间点")
    private LocalDateTime statTime;

    @Schema(description = "所属区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("所属区域名称")
    private String regionName;

    @Schema(description = "GB/T 2260 标准12位地区码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("GB/T 2260 标准12位地区码")
    private String regionFullCode;

    @Schema(description = "统计周期内缴费总订单数", requiredMode = Schema.RequiredMode.REQUIRED, example = "19906")
    @ExcelProperty("统计周期内缴费总订单数")
    private Integer totalOrderCount;

    @Schema(description = "统计周期内缴费总金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期内缴费总金额")
    private BigDecimal totalPayAmount;

    @Schema(description = "统计周期内缴费成功占比", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计周期内缴费成功占比")
    private BigDecimal paySuccessRate;

    @Schema(description = "微信支付占比", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("微信支付占比")
    private BigDecimal wechatRatio;

    @Schema(description = "支付宝支付占比", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("支付宝支付占比")
    private BigDecimal alipayRatio;

    @Schema(description = "银行卡支付占比", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("银行卡支付占比")
    private BigDecimal cardRatio;

    @Schema(description = "各区域缴费占比（数据库JSON格式）")
    @ExcelProperty("各区域缴费占比（数据库JSON格式）")
    private String regionPayRatio;

    @Schema(description = "分类扩展字段1，预留")
    @ExcelProperty("分类扩展字段1，预留")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留")
    @ExcelProperty("分类扩展字段2，预留")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留")
    @ExcelProperty("通用扩展字段1，预留")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留")
    @ExcelProperty("通用扩展字段2，预留")
    private String extCommon2;

    @Schema(description = "记录创建时间")
    @ExcelProperty("记录创建时间")
    private LocalDateTime createTime;

    /**
     * 各区域缴费占比（对象结构，不落库）
     */
    @TableField(exist = false)
    private List<StatParkPayDO.RegionPayRatioItem> regionPayRatioList;
    @Data
    public static class RegionPayRatioItem {

        /** 区域名称 */
        private String regionName;

        /** 区域编码 */
        private String regionFullCode;

        /** 缴费金额 */
        private BigDecimal payAmount;

        /** 占比 */
        private BigDecimal ratio;
    }

}
