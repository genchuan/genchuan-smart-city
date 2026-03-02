package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standarditem.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 标准项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StandardItemRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16705")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "标准项ID（UUID）", example = "17710")
    @ExcelProperty("标准项ID（UUID）")
    private String standardItemId;

    @Schema(description = "标准分类ID（关联eval_standard_category.standard_category_id）", example = "21735")
    @ExcelProperty("标准分类ID（关联eval_standard_category.standard_category_id）")
    private String standardCategoryId;

    @Schema(description = "标准项等级")
    @ExcelProperty("标准项等级")
    private String grade;

    @Schema(description = "分数范围")
    @ExcelProperty("分数范围")
    private String scoreRange;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNo;

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