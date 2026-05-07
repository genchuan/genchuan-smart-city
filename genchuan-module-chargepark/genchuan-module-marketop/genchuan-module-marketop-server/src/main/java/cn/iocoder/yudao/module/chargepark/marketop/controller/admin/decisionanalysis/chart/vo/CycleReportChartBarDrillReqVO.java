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

    @Schema(description = "柱状图分类类型", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "activityType",
            allowableValues = {"activityType", "couponType", "prizeType", "cardType", "exchangeCategoryType"})
    @NotEmpty(message = "分类类型不能为空")
    private String categoryType;

    @Schema(description = "点击的分类名称。" +
            "activityType → 活动类型(新用户/节假日/店庆/日常)；" +
            "couponType → 券类型(满减/折扣/时长/立减)；" +
            "prizeType → 奖品类型(实物/虚拟/优惠券/卡种)；" +
            "cardType → 卡种类型(日卡/周卡/月卡/季卡/年卡)；" +
            "exchangeCategoryType → 兑换类目名称",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "新用户")
    @NotEmpty(message = "分类名称不能为空")
    private String categoryName;

    @Schema(description = "兑换订单类目ID，categoryType=exchangeCategoryType时必传，用于按类目过滤兑换订单",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Long categoryId;
}
