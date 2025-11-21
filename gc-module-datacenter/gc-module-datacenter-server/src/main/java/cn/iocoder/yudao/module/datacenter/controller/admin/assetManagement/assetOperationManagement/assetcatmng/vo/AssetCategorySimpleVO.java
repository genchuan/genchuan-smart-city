// AssetCategorySimpleVO.java
package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 资产分类简单信息 VO")
@Data
public class AssetCategorySimpleVO {

    @Schema(description = "资产分类ID", example = "1001")
    private String assetCatId;

    @Schema(description = "资产分类名称", example = "市政设施")
    private String assetCatName;

    @Schema(description = "上级分类ID", example = "25267")
    private String parentCatId;

    @Schema(description = "分类层级", requiredMode = Schema.RequiredMode.REQUIRED)
    private String catLevel;
}