package cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 管理事项分类简化树节点 Response VO")
@Data
public class MatterCategorySimpleTreeRespVO {

    @Schema(description = "分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "CAT001")
    private String id;

    @Schema(description = "分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "市容环境")
    private String label;

    @Schema(description = "子分类列表")
    private List<MatterCategorySimpleTreeRespVO> children;

}
