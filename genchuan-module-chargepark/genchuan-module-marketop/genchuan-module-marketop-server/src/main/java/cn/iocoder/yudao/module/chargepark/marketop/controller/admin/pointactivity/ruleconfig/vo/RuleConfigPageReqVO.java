package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 规则配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RuleConfigPageReqVO extends PageParam {

    @Schema(description = "规则名称")
    private String name;

    @Schema(description = "规则类型")
    private String type;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "适用场景")
    private String scene;

    @Schema(description = "审核人")
    private Long auditorId;

    @Schema(description = "审核开始时间")
    private Long auditStartTime;

    @Schema(description = "审核结束时间")
    private Long auditEndTime;

//    @Schema(description = "生效开始时间")
//    private Long effectStartTime;
//
//    @Schema(description = "生效结束时间")
//    private Long effectEndTime;

    @Schema(description = "规则描述")
    private String description;

    @Schema(description = "生效时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "匹配次数")
    private Integer matchCount;

    @Schema(description = "赠送比例")
    private BigDecimal giftRatio;
}
