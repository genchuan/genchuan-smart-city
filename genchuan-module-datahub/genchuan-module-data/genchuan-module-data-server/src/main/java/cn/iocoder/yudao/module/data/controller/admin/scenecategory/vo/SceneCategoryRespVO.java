package cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 应用场景分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SceneCategoryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "分类名称")
    @ExcelProperty("分类名称")
    private String categoryName;

    @Schema(description = "分类代码")
    @ExcelProperty("分类代码")
    private String categoryCode;

    @Schema(description = "上级分类ID")
    @ExcelProperty("上级分类ID")
    private String parentId;

    @Schema(description = "上级分类名称")
    @ExcelProperty("上级分类名称")
    private String parentCategory;

    @Schema(description = "适用区域")
    @ExcelProperty("适用区域")
    private String applicableArea;

    @Schema(description = "数据类型")
    @ExcelProperty("数据类型")
    private String dataType;

    @Schema(description = "分类类型ID")
    @ExcelProperty("分类类型ID")
    private String categoryTypeId;

    @Schema(description = "分类类型")
    @ExcelProperty("分类类型")
    private String categoryType;

    @Schema(description = "状态")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "审核状态")
    @ExcelProperty("审核状态")
    private String auditStatus;

    @Schema(description = "关联实例数")
    @ExcelProperty("关联实例数")
    private Integer instanceCount;

    @Schema(description = "用途说明")
    @ExcelProperty("用途说明")
    private String purpose;

    @Schema(description = "备注")
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