package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 周期报表存储 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CycleReportPageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12328")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）")
    private String reportCycle;

    @Schema(description = "统计开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计开始时间")
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计结束时间")
    private LocalDateTime statEndTime;

    @Schema(description = "新增用户数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10836")
    @ExcelProperty("新增用户数")
    private Integer newUserCount;

    @Schema(description = "绑定车辆数", requiredMode = Schema.RequiredMode.REQUIRED, example = "27419")
    @ExcelProperty("绑定车辆数")
    private Integer bindCarCount;

    @Schema(description = "车牌认证量", requiredMode = Schema.RequiredMode.REQUIRED, example = "18349")
    @ExcelProperty("车牌认证量")
    private Integer plateAuthCount;

    @Schema(description = "新增商户数", requiredMode = Schema.RequiredMode.REQUIRED, example = "8758")
    @ExcelProperty("新增商户数")
    private Integer newMerchantCount;

    @Schema(description = "对接商户数", requiredMode = Schema.RequiredMode.REQUIRED, example = "29968")
    @ExcelProperty("对接商户数")
    private Integer linkMerchantCount;

    @Schema(description = "充值金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("充值金额")
    private BigDecimal rechargeAmount;

    @Schema(description = "发券量", requiredMode = Schema.RequiredMode.REQUIRED, example = "514")
    @ExcelProperty("发券量")
    private Integer sendCouponCount;

    @Schema(description = "新增集团数", requiredMode = Schema.RequiredMode.REQUIRED, example = "14911")
    @ExcelProperty("新增集团数")
    private Integer newGroupCount;

    @Schema(description = "会员新增数", requiredMode = Schema.RequiredMode.REQUIRED, example = "32384")
    @ExcelProperty("会员新增数")
    private Integer newMemberCount;

    @Schema(description = "平均信用分", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("平均信用分")
    private Integer avgCreditScore;

    @Schema(description = "图表分布数据（JSON格式存储柱状图/饼图/折线图数据）")
    @ExcelProperty("图表分布数据（JSON格式存储柱状图/饼图/折线图数据）")
    private String distributionData;

    @Schema(description = "报表生成状态（待生成/已生成/生成失败）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("报表生成状态（待生成/已生成/生成失败）")
    private String reportStatus;

    @Schema(description = "报表生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表生成时间")
    private LocalDateTime createTime;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}