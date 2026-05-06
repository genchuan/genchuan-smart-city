package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 周期报表图表-饼图钻取 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportChartPieDrillReqVO extends PageParam {

    @Schema(description = "饼图类型（ruleType/configType/packageType：规则类型、配置类型、券包类型）", requiredMode = Schema.RequiredMode.REQUIRED, example = "ruleType")
    @NotEmpty(message = "饼图类型不能为空")
    private String pieType;

    @Schema(description = "点击扇区名称（获取规则/消耗规则/赠送规则等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "获取规则")
    @NotEmpty(message = "扇区名称不能为空")
    private String pieName;

}
