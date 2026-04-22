package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 活动配置 Response VO")
@Data
public class ActivityConfigRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "活动名称")
    private String name;

    @Schema(description = "活动类型")
    private String type;

    @Schema(description = "参与条件")
    private String joinCondition;

    @Schema(description = "规则内容")
    private String ruleContent;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "参与人数")
    private Integer joinCount;

    @Schema(description = "生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "活动描述")
    private String description;

    @Schema(description = "用户群体")
    private String userGroup;

    @Schema(description = "参与用户数")
    private Integer userCount;

    @Schema(description = "活动参与率")
    private BigDecimal joinRate;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建者名称")
    private String creatorName;

    @Schema(description = "审核人名称")
    private String auditorName;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
