package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 指标分类新增/修改 Request VO")
@Data
public class IndexCategorySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9977")
    private Long id;

    @Schema(description = "指标分类ID（UUID）", example = "16019")
    private String categoryId;

    @Schema(description = "指标体系ID（关联eval_index_system.system_id）", example = "1942")
    private String systemId;

    @Schema(description = "分类名称", example = "赵六")
    private String name;

    @Schema(description = "分类权重（如90.50）")
    private BigDecimal weight;

    @Schema(description = "排序序号")
    private Integer sortNo;

    @Schema(description = "创建人，关联sys_user.user_id")
    private String createBy;

    @Schema(description = "更新人，关联sys_user.user_id")
    private String updateBy;

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