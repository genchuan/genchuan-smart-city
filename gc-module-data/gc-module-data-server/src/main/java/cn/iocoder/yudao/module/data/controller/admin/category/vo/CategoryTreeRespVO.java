package cn.iocoder.yudao.module.data.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Schema(description = "管理后台 - 管理部件分类树节点 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryTreeRespVO extends CategoryRespVO {

    @Schema(description = "子分类列表")
    private List<CategoryTreeRespVO> children;

}