package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 规则分类分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RuleCategoryPageReqVO extends PageParam {

    @Schema(description = "规则分类ID（UUID）", example = "555")
    private String ruleCategoryId;

    @Schema(description = "规则分类名称", example = "王五")
    private String name;

    @Schema(description = "适用指标体系ID（关联eval_index_system.system_id）", example = "21591")
    private String systemId;

    @Schema(description = "规则项数量", example = "13944")
    private Integer itemCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "8843")
    private Integer statusId;

    @Schema(description = "最近使用时间", example = "2024-29-56")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastUseTime;

    @Schema(description = "使用次数", example = "14")
    private Integer useCount;

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

    @Schema(description = "变更日志")
    private String changeLog;

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
    private String statusName;
    // ========== 新增缺失的查询字段（核心修复） ==========
    @Schema(description = "适用对象类型名称（关联sys_object_type.name）")
    private String objectTypeName; // 修复NoSuchPropertyException的核心字段

    @Schema(description = "规则项名称（关联eval_rule_item.name）")
    private String ruleItemName;

    @Schema(description = "关联指标项名称（关联eval_index_item.name）")
    private String indexItemName;

    @Schema(description = "规则类型名称（关联sys_rule_type.name）")
    private String ruleTypeName;

    @Schema(description = "否决项名称（关联eval_veto_item.name）")
    private String vetoItemName;
    @Schema(description = "关联指标项名称（关联eval_rule_item.index_id）")
    private String indexItemId;

    @Schema(description = "评分逻辑")
    private String scoreLogic; // eval_rule_item.score_logic

    @Schema(description = "满分值")
    private Integer fullScore; // eval_rule_item.full_score

}