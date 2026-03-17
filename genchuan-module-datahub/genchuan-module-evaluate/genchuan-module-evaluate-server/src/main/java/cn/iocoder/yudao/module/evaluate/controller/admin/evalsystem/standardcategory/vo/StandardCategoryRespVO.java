package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 标准分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StandardCategoryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14997")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "标准分类ID（UUID）", example = "15881")
    @ExcelProperty("标准分类ID（UUID）")
    private String standardCategoryId;

    @Schema(description = "标准分类名称", example = "张三")
    @ExcelProperty("标准分类名称")
    private String name;

    @Schema(description = "适用指标体系ID（关联eval_index_system.system_id）", example = "26662")
    @ExcelProperty("适用指标体系ID（关联eval_index_system.system_id）")
    private String systemId;

    @Schema(description = "标准项数量", example = "28016")
    @ExcelProperty("标准项数量")
    private Integer itemCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "7106")
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

    private String createUserName; // 创建人

    private Integer standardItemCount; // 标准项数量
    private String changeLogShort; // 变更日志（前50字）
    // 输入交互前展示字段
    private String standardCategoryName; // 标准分类名称
    private String indexSystemName; // 适用指标体系名称
    private String standardItemGrade; // 标准项等级
    private String scoreRange; // 分数范围
    private Integer sortNo; // 排序序号
    private String statusName; // 状态名称
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