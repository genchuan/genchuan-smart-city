package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 规则项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RuleItemRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30914")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规则项ID（UUID）", example = "22621")
    @ExcelProperty("规则项ID（UUID）")
    private String ruleItemId;

    @Schema(description = "规则分类ID（关联eval_rule_category.rule_category_id）", example = "15219")
    @ExcelProperty("规则分类ID（关联eval_rule_category.rule_category_id）")
    private String ruleCategoryId;

    @Schema(description = "关联指标项ID（关联eval_index_item.item_id）", example = "23853")
    @ExcelProperty("关联指标项ID（关联eval_index_item.item_id）")
    private String indexId;

    @Schema(description = "规则项名称", example = "张三")
    @ExcelProperty("规则项名称")
    private String name;

    @Schema(description = "评分逻辑")
    @ExcelProperty("评分逻辑")
    private String scoreLogic;

    @Schema(description = "满分值")
    @ExcelProperty("满分值")
    private BigDecimal fullScore;

    @Schema(description = "规则类型ID（关联sys_rule_type.type_id）", example = "1851")
    @ExcelProperty("规则类型ID（关联sys_rule_type.type_id）")
    private String ruleTypeId;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    @ExcelProperty("更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @ExcelProperty("更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}