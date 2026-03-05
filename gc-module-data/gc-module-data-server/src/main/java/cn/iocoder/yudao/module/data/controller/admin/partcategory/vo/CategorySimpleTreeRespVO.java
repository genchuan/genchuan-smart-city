package cn.iocoder.yudao.module.data.controller.admin.partcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 管理部件分类简化树节点 Response VO")
@Data
public class CategorySimpleTreeRespVO {

    @Schema(description = "分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String id;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "交通设施")
    private String label;

    @Schema(description = "子分类列表")
    private List<CategorySimpleTreeRespVO> children;

}