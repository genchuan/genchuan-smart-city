package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 规则项分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RuleItemPageReqVO extends PageParam {

    @Schema(description = "规则项ID（UUID）", example = "22621")
    private String ruleItemId;

    @Schema(description = "规则分类ID（关联eval_rule_category.rule_category_id）", example = "15219")
    private String ruleCategoryId;

    @Schema(description = "关联指标项ID（关联eval_index_item.item_id）", example = "23853")
    private String indexId;

    @Schema(description = "规则项名称", example = "张三")
    private String name;

    @Schema(description = "评分逻辑")
    private String scoreLogic;

    @Schema(description = "满分值")
    private BigDecimal fullScore;

    @Schema(description = "规则类型ID（关联sys_rule_type.type_id）", example = "1851")
    private String ruleTypeId;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}