package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "管理后台 - 活动配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ActivityConfigPageReqVO extends PageParam {

    @Schema(description = "活动名称")
    private String name;

    @Schema(description = "活动类型")
    private String type;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "用户群体")
    private String userGroup;

    @Schema(description = "参与条件")
    private String joinCondition;

    @Schema(description = "规则内容")
    private String ruleContent;

    @Schema(description = "活动描述")
    private String description;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "审核开始时间")
    private Long auditStartTime;

    @Schema(description = "审核结束时间")
    private Long auditEndTime;

    @Schema(description = "生效开始时间")
    private Long effectStartTime;

    @Schema(description = "生效结束时间")
    private Long effectEndTime;

}
