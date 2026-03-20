package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo;

import cn.iocoder.yudao.module.evaluate.controller.admin.commentrule.vo.CommentRuleRespVO;
import com.alibaba.excel.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 规则分类管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RuleCategoryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27497")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "规则分类名称", example = "芋艿")
    @ExcelProperty("规则分类名称")
    private String name;

    @Schema(description = "规则项数量")
    @ExcelProperty("规则项数量")
    private Integer itemCount;

    @Schema(description = "适用指标体系ID（关联eval_index_system.system_id）", example = "26353")
    @ExcelProperty("适用指标体系ID（关联eval_index_system.system_id）")
    private String systemId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "15797")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "使用次数", example = "19150")
    @ExcelProperty("使用次数")
    private Integer useCount;

    @Schema(description = "最近使用时间")
    @ExcelProperty("最近使用时间")
    private LocalDateTime lastUseTime;

    @Schema(description = "变更日志关联，截取前50字")
    @ExcelProperty("变更日志关联截取前50字")
    private String changeLog;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "指标项名称ideval_rule_item.index_id关联", example = "12510")
    @ExcelProperty("指标项名称ideval_rule_item.index_id关联")
    private Integer itemId;

    @Schema(description = "规则项id关联规则类型字典表sys_rule_type", example = "3654")
    @ExcelProperty("规则项id关联规则类型字典表sys_rule_type")
    private Integer ruleId;

    @Schema(description = "对象类型ID关联对象类型字典sys_object_type", example = "26110")
    @ExcelProperty("对象类型ID关联对象类型字典表sys_object_type")
    private Integer objectTypeId;

    // ========== 关联表的主键ID ==========
    @Schema(description = "指标体系主键ID", example = "1")
    @ExcelProperty("指标体系主键ID")
    private Long systemIdPk;

    @Schema(description = "状态表主键ID", example = "1")
    @ExcelProperty("状态表主键ID")
    private Long statusIdPk;

    @Schema(description = "指标项表主键ID", example = "1")
    @ExcelProperty("指标项表主键ID")
    private Long itemIdPk;

    @Schema(description = "规则类型表主键ID", example = "1")
    @ExcelProperty("规则类型表主键ID")
    private Long ruleIdPk;

    @Schema(description = "对象类型表主键ID", example = "1")
    @ExcelProperty("对象类型表主键ID")
    private Long objectTypeIdPk;

    // ========== 关联表查询的name字段 ==========
    @Schema(description = "适用指标体系名称")
    @ExcelProperty("适用指标体系名称")
    private String systemName;

    @Schema(description = "状态名称")
    @ExcelProperty("状态名称")
    private String statusName;

    @Schema(description = "指标项名称")
    @ExcelProperty("指标项名称")
    private String itemName;

    @Schema(description = "规则类型名称")
    @ExcelProperty("规则类型名称")
    private String ruleName;

    @Schema(description = "对象类型名称")
    @ExcelProperty("对象类型名称")
    private String objectTypeName;

    @Schema(description = "创建人姓名")
    @ExcelProperty("创建人姓名")
    private String createUserName;

    @Schema(description = "更新人姓名")
    @ExcelProperty("更新人姓名")
    private String updateUserName;

    // ========== 树形结构字段 ==========
    @Schema(description = "评分规则列表")
    private List<CommentRuleRespVO> commentRules;

}
