package cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 标准分类导出 Excel VO（含标准项展开）")
@Data
@ExcelIgnoreUnannotated
public class StandardCategoryExportVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31835")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "标准分类名称", example = "芋艿")
    @ExcelProperty("标准分类名称")
    private String name;

    @Schema(description = "适用指标体系主键ID", example = "27764")
    @ExcelProperty("适用指标体系主键ID")
    private Long systemId;

    @Schema(description = "标准项数量", example = "15548")
    @ExcelProperty("标准项数量")
    private Integer itemCount;

    @Schema(description = "状态主键ID", example = "17182")
    @ExcelProperty("状态主键ID")
    private Integer statusId;

    @Schema(description = "适用指标体系名称")
    @ExcelProperty("适用指标体系名称")
    private String systemName;

    @Schema(description = "状态名称")
    @ExcelProperty("状态名称")
    private String statusName;

    @Schema(description = "最近使用时间")
    @ExcelProperty("最近使用时间")
    private LocalDateTime lastUseTime;

    @Schema(description = "使用次数", example = "29832")
    @ExcelProperty("使用次数")
    private Integer useCount;

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

    @Schema(description = "创建人ID")
    @ExcelProperty("创建人ID")
    private String creator;

    @Schema(description = "更新人ID")
    @ExcelProperty("更新人ID")
    private String updater;

    @Schema(description = "创建人姓名")
    @ExcelProperty("创建人姓名")
    private String creatorName;

    @Schema(description = "更新人姓名")
    @ExcelProperty("更新人姓名")
    private String updaterName;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "标准项详情")
    @ExcelProperty("标准项详情")
    private String itemDetails;

}
