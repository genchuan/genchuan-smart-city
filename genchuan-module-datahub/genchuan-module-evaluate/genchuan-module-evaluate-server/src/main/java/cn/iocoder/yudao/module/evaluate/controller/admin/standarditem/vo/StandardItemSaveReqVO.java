package cn.iocoder.yudao.module.evaluate.controller.admin.standarditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 标准项新增/修改 Request VO")
@Data
public class StandardItemSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30162")
    private Long id;

    @Schema(description = "标准分类ID（关联eval_standard_category.id）", example = "20535")
    private Long standardCategoryId;

    @Schema(description = "标准项等级")
    private String grade;

    @Schema(description = "分数范围")
    private String scoreRange;

    @Schema(description = "排序序号")
    private Integer sortNo;

    @Schema(description = "创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}