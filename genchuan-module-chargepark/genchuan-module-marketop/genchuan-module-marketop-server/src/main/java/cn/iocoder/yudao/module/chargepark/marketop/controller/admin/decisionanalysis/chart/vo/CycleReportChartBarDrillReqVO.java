package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 周期报表图表-柱状图钻取 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CycleReportChartBarDrillReqVO extends PageParam {

    @Schema(description = "柱状图分类类型（活动类型分布、奖品类型分布、优惠券类型分布、卡种类型分布、兑换类目订单分布）", requiredMode = Schema.RequiredMode.REQUIRED, example = "activityType")
    @NotEmpty(message = "分类类型不能为空")
    private String categoryType;

    @Schema(description = "点击分类名称（积分活动/优惠活动/实物奖品/满减券等）", requiredMode = Schema.RequiredMode.REQUIRED, example = "积分活动")
    @NotEmpty(message = "分类名称不能为空")
    private String categoryName;

}
