package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 周期报表存储新增/修改 Request VO")
@Data
public class CycleReportSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12328")
    private Long id;

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）不能为空")
    private String reportCycle;

    @Schema(description = "统计开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计开始时间不能为空")
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计结束时间不能为空")
    private LocalDateTime statEndTime;

    @Schema(description = "报表名称", example = "芋艿")
    private String reportName;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "新增用户数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10836")
    @NotNull(message = "新增用户数不能为空")
    private Integer newUserCount;

    @Schema(description = "绑定车辆数", requiredMode = Schema.RequiredMode.REQUIRED, example = "27419")
    @NotNull(message = "绑定车辆数不能为空")
    private Integer bindCarCount;

    @Schema(description = "车牌认证量", requiredMode = Schema.RequiredMode.REQUIRED, example = "18349")
    @NotNull(message = "车牌认证量不能为空")
    private Integer plateAuthCount;

    @Schema(description = "新增商户数", requiredMode = Schema.RequiredMode.REQUIRED, example = "8758")
    @NotNull(message = "新增商户数不能为空")
    private Integer newMerchantCount;

    @Schema(description = "对接商户数", requiredMode = Schema.RequiredMode.REQUIRED, example = "29968")
    @NotNull(message = "对接商户数不能为空")
    private Integer linkMerchantCount;

    @Schema(description = "充值金额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "充值金额不能为空")
    private BigDecimal rechargeAmount;

    @Schema(description = "发券量", requiredMode = Schema.RequiredMode.REQUIRED, example = "514")
    @NotNull(message = "发券量不能为空")
    private Integer sendCouponCount;

    @Schema(description = "新增集团数", requiredMode = Schema.RequiredMode.REQUIRED, example = "14911")
    @NotNull(message = "新增集团数不能为空")
    private Integer newGroupCount;

    @Schema(description = "会员新增数", requiredMode = Schema.RequiredMode.REQUIRED, example = "32384")
    @NotNull(message = "会员新增数不能为空")
    private Integer newMemberCount;

    @Schema(description = "平均信用分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "平均信用分不能为空")
    private Integer avgCreditScore;

    @Schema(description = "图表分布数据（JSON格式存储柱状图/饼图/折线图数据）")
    private String distributionData;

    @Schema(description = "报表生成状态（待生成/已生成/生成失败）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "报表生成状态（待生成/已生成/生成失败）不能为空")
    private String reportStatus;

    @Schema(description = "导出次数", requiredMode = Schema.RequiredMode.REQUIRED, example = "2067")
    @NotNull(message = "导出次数不能为空")
    private Integer exportCount;

}