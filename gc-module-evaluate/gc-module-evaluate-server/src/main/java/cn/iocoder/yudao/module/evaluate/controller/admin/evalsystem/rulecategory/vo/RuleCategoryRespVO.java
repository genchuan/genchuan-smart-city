package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
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

    // 新增的展示字段（输入交互后需要展示的）
    private String createUserName; // 创建人

    private Integer ruleItemCount; // 规则项数量
    private Integer vetoItemCount; // 否决项数量


}