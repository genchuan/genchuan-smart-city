package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 规则分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RuleCategoryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13411")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规则分类ID（UUID）", example = "555")
    @ExcelProperty("规则分类ID（UUID）")
    private String ruleCategoryId;

    @Schema(description = "规则分类名称", example = "王五")
    @ExcelProperty("规则分类名称")
    private String name;

    @Schema(description = "适用指标体系ID（关联eval_index_system.system_id）", example = "21591")
    @ExcelProperty("适用指标体系ID（关联eval_index_system.system_id）")
    private String systemId;

    @Schema(description = "规则项数量", example = "13944")
    @ExcelProperty("规则项数量")
    private Integer itemCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "8843")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "最近使用时间", example = "2024-29-56")
    @ExcelProperty("最近使用时间")
    private LocalDateTime lastUseTime;

    @Schema(description = "使用次数", example = "14")
    @ExcelProperty("使用次数")
    private Integer useCount;

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

    @Schema(description = "变更日志")
    @ExcelProperty("变更日志")
    private String changeLog;

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


    // ========== 交互后新增字段 ==========
    @Schema(description = "创建人名称")
    private String createUserName; // 创建人：sys_user.user_name

    @Schema(description = "规则项数量（自动统计）")
    private Integer ruleItemCount; // eval_rule_category.item_count

    @Schema(description = "否决项数量（自动统计）")
    private Integer vetoItemCount; // 否决项表统计数

    @Schema(description = "变更日志（截取前50字）")
    private String shortChangeLog; // 截取后的变更日志

    // ========== 关联字典/关联表字段 ==========
    @Schema(description = "适用指标体系名称")
    private String indexSystemName; // eval_index_system.name

    @Schema(description = "规则项名称")
    private String ruleItemName; // eval_rule_item.name

    @Schema(description = "关联指标项名称")
    private String indexItemName; // eval_index_item.name

    @Schema(description = "评分逻辑")
    private String scoreLogic; // eval_rule_item.score_logic

    @Schema(description = "满分值")
    private Integer fullScore; // eval_rule_item.full_score

    @Schema(description = "规则类型名称")
    private String ruleTypeName; // sys_rule_type.name

    @Schema(description = "否决项名称")
    private String vetoItemName; // eval_veto_item.name

    @Schema(description = "适用对象类型名称")
    private String objectTypeName; // sys_object_type.name

    @Schema(description = "否决条件")
    private String vetoCondition; // eval_veto_item.condition

    @Schema(description = "生效周期")
    private String validCycle; // eval_veto_item.valid_cycle

    @Schema(description = "状态名称")
    private String statusName; // sys_status.name

    @Schema(description = "停用时间")
    private LocalDateTime stopTime; // eval_rule_category.update_time（停用时间）

    @Schema(description = "停用操作人名称")
    private String stopUserName; // sys_user.user_name（update_by关联）

    @Schema(description = "status_id=1的记录数（传指定statusId时，仅该状态有值，其余为0）")
    @ExcelProperty("status_id=1的记录数")
    private Long status1Count;

    @Schema(description = "status_id=2的记录数")
    @ExcelProperty("status_id=2的记录数")
    private Long status2Count;

    @Schema(description = "符合条件的总记录数（过滤deleted=1后）")
    @ExcelProperty("符合条件的总记录数（过滤deleted=1后）")
    private Long totalCount;
}