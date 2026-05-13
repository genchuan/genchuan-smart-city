package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 商户周期报表生成 Request VO")
@Data
public class CycleReportGenerateReqVO {

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "月报")
    @NotBlank(message = "报表周期不能为空")
    private String reportCycle;

    @Schema(description = "统计开始时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-01 00:00:00")
    @NotNull(message = "统计开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statStartTime;

    @Schema(description = "统计结束时间", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-03-31 23:59:59")
    @NotNull(message = "统计结束时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime statEndTime;

    @Schema(description = "报表名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026年3月用户商户统计报表")
    @NotBlank(message = "报表名称不能为空")
    private String reportName;

    @Schema(description = "备注", example = "月度运营统计")
    private String remark;

    @Schema(description = "租户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "租户ID不能为空")
    private Long tenantId;
}