package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 规则项新增/修改 Request VO")
@Data
public class RuleItemSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30914")
    private Long id;

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

}