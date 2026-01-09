package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 执法核心指标 Response VO")
@Data
public class LawCoreMetricsRespVO {

    @Schema(description = "案件平均办结时长（小时）", requiredMode = Schema.RequiredMode.REQUIRED, example = "48")
    @ExcelProperty("案件平均办结时长（小时）")
    private Integer avgCaseHandleTime;

    @Schema(description = "案件平均办结时长（小时）指标", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("案件平均办结时长指标内容")
    private CoreMetricVO avgCaseHandleTimeCoreMetricVO;


    @Schema(description = "执法合规率（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "95.5")
    @ExcelProperty("执法合规率（%）")
    private Double lawComplianceRate;

    @Schema(description = "执法合规率（%）指标", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("执法合规率指标内容")
    private CoreMetricVO lawComplianceRateCoreMetricVO;


    @Schema(description = "重复举报率（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2.5")
    @ExcelProperty("重复举报率（%）")
    private Double repeatRptRate;

    @Schema(description = "重复举报率（%）指标", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("重复举报率指标内容")
    private CoreMetricVO repeatRptRateCoreMetricVO;


    @Schema(description = "跨部门协同率（%）", requiredMode = Schema.RequiredMode.REQUIRED, example = "80.0")
    @ExcelProperty("跨部门协同率（%）")
    private Double crossDeptCoopRate;

    @Schema(description = "跨部门协同率（%）指标", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("跨部门协同率指标内容")
    private CoreMetricVO crossDeptCoopRateCoreMetricVO;
}
