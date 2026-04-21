package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 规则配置 Response VO")
@Data
public class RuleConfigRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "规则名称")
    private String name;

    @Schema(description = "规则类型")
    private String type;

    @Schema(description = "赠送比例")
    private BigDecimal giftRatio;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "匹配次数")
    private Integer matchCount;

    @Schema(description = "生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "规则描述")
    private String description;

    @Schema(description = "适用场景")
    private String scene;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
