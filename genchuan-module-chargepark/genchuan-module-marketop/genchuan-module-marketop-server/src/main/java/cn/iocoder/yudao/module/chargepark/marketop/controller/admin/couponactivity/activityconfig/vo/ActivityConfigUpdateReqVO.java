package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Schema(description = "管理后台 - 活动配置更新 Request VO")
@Data
public class ActivityConfigUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "主键ID不能为空")
    private Long id;

    @Schema(description = "活动名称")
    private String name;

    @Schema(description = "参与条件")
    private String joinCondition;

    @Schema(description = "规则内容")
    private String ruleContent;

    @Schema(description = "用户群体")
    private String userGroup;

    @Schema(description = "活动描述")
    private String description;

}
