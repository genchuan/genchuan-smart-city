package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 周期报表详情 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CycleReportGetRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）")
    private String reportCycle;

    @Schema(description = "统计时段", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计时段")
    private LocalDateTime statTime;

    @Schema(description = "新增用户数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10836")
    @ExcelProperty("新增用户数")
    private Integer newUserCount;

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

    @Schema(description = "报表生成状态（待生成/已生成/生成失败）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("报表生成状态（待生成/已生成/生成失败）")
    private String reportStatus;

    @Schema(description = "报表生成时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("报表生成时间")
    private LocalDateTime createTime;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "导出次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2067")
    @ExcelProperty("导出次数")
    private Integer exportCount;

    // ========== 图表数据（实时统计，不存储） ==========
    @Schema(description = "折线图数据")
    private Map<String, List<Map<String, Object>>> lineData;
    @Schema(description = "柱状图数据")
    private Map<String, List<Map<String, Object>>> barData;
    @Schema(description = "饼图数据")
    private Map<String, List<Map<String, Object>>> pieData;

}