package cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MatterCategoryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12149")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "管理事项分类ID", example = "18735")
    @ExcelProperty("管理事项分类ID")
    private String matterCategoryId;

    @Schema(description = "分类名称", example = "张三")
    @ExcelProperty("分类名称")
    private String categoryName;

    @Schema(description = "分类代码")
    @ExcelProperty("分类代码")
    private String categoryCode;

    @Schema(description = "上级分类ID", example = "10662")
    @ExcelProperty("上级分类ID")
    private String parentId;

    @Schema(description = "上级分类名称", example = "张三")
    @ExcelProperty("上级分类名称")
    private String parentName;

    @Schema(description = "主管部门ID", example = "15094")
    @ExcelProperty("主管部门ID")
    private String deptId;

    @Schema(description = "主管部门名称", example = "芋艿")
    @ExcelProperty("主管部门名称")
    private String deptName;

    @Schema(description = "处置时限（小时）")
    @ExcelProperty("处置时限（小时）")
    private Integer dealLimit;

    @Schema(description = "工作流ID", example = "4355")
    @ExcelProperty("工作流ID")
    private String workflowId;

    @Schema(description = "工作流编码")
    @ExcelProperty("工作流编码")
    private String workflowCode;

    @Schema(description = "工作流描述")
    @ExcelProperty("工作流描述")
    private String workflowDesc;

    @Schema(description = "分类类型ID", example = "20172")
    @ExcelProperty("分类类型ID")
    private String categoryTypeId;

    @Schema(description = "分类类型名称", example = "芋艿")
    @ExcelProperty("分类类型名称")
    private String categoryType;

    @Schema(description = "状态", example = "芋艿")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "审核状态", example = "李四")
    @ExcelProperty("审核状态")
    private String auditStatus;

    @Schema(description = "关联事项数", example = "31735")
    @ExcelProperty("关联事项数")
    private Integer relatedMatterCount;

    @Schema(description = "用途说明")
    @ExcelProperty("用途说明")
    private String purpose;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}