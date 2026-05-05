package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 周期报表图表-折线图钻取 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportChartLineDrillReqVO extends PageParam {

    @Schema(description = "对应时段", requiredMode = Schema.RequiredMode.REQUIRED, example = "2026-04")
    @NotEmpty(message = "时段不能为空")
    private String date;

}
