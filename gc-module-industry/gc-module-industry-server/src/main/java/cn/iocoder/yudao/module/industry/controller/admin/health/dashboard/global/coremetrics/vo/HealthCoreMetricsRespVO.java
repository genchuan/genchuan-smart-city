package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 卫健核心指标 Response VO")
@Data
public class HealthCoreMetricsRespVO {

    @Schema(description = "门诊诊疗人次指标")
    private MetricVO outpatientVolume;

    @Schema(description = "住院人次指标")
    private MetricVO inpatientVolume;

    @Schema(description = "家庭医生签约率指标")
    private MetricVO familyDoctorSignRate;

    @Schema(description = "疫苗接种完成率指标")
    private MetricVO vaccineCompleteRate;

    @Schema(description = "传染病报告及时率指标")
    private MetricVO infectRptTimelyRate;

    /**
     * 通用指标对象
     */
    @Schema(description = "通用指标对象")
    @Data
    public static class MetricVO {

        @Schema(description = "指标当前值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1200")
        @ExcelProperty("指标当前值")
        private BigDecimal currentValue;

        @Schema(description = "指标达标状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "达标")
        @ExcelProperty("达标状态")
        private String status;

        @Schema(description = "近30天趋势数据")
        private List<TrendPointVO> trendList;
    }

    /**
     * 趋势数据对象
     */
    @Schema(description = "趋势数据点")
    @Data
    public static class TrendPointVO {

        @Schema(description = "日期，格式 yyyy-MM-dd", requiredMode = Schema.RequiredMode.REQUIRED, example = "2025-11-01")
        private String date;

        @Schema(description = "指标值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1200")
        private BigDecimal value;
    }
}
