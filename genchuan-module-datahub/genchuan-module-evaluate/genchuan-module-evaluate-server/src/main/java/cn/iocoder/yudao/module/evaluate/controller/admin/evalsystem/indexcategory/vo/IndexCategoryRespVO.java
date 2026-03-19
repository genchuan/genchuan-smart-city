package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo;


import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class IndexCategoryRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9977")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "指标分类ID（UUID）", example = "16019")
    @ExcelProperty("指标分类ID（UUID）")
    private String categoryId;

    @Schema(description = "指标体系ID（关联eval_index_system.system_id）", example = "1942")
    @ExcelProperty("指标体系ID（关联eval_index_system.system_id）")
    private String systemId;

    @Schema(description = "分类名称", example = "赵六")
    @ExcelProperty("分类名称")
    private String name;

    @Schema(description = "分类权重（如90.50）")
    @ExcelProperty("分类权重（如90.50）")
    private BigDecimal weight;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNo;

    @Schema(description = "创建人，关联sys_user.user_id")
    @ExcelProperty("创建人，关联sys_user.user_id")
    private String createBy;

    @Schema(description = "更新人，关联sys_user.user_id")
    @ExcelProperty("更新人，关联sys_user.user_id")
    private String updateBy;

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