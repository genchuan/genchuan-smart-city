package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(description = "管理后台 - 活动配置创建 Request VO")
@Data
public class ActivityConfigCreateReqVO {

    @Schema(description = "活动名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "活动名称不能为空")
    private String name;

    @Schema(description = "活动类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "活动类型不能为空")
    private String type;

    @Schema(description = "参与条件")
    private String joinCondition;

    @Schema(description = "规则内容")
    private String ruleContent;

    @Schema(description = "用户群体")
    private String userGroup;

    @Schema(description = "活动描述")
    private String description;

}
