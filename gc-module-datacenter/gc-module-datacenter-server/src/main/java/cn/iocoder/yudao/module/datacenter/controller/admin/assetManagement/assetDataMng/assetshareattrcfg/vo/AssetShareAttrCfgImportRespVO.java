package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(description = "管理后台 - 资产共享属性配置导入 Response VO")
@Data
@Builder
public class AssetShareAttrCfgImportRespVO {
    @Schema(description = "创建成功的共享属性配置ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private java.util.List<String> createAssetShareAttrCfgIds;
    @Schema(description = "更新成功的共享属性配置ID数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private java.util.List<String> updateAssetShareAttrCfgIds;
    @Schema(description = "导入失败的共享属性配置集合，key 为空间数据ID，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private java.util.Map<String, String> failureAssetShareAttrCfgIds;

    @Schema(description = "新增条数")
    private Integer createCount;

    @Schema(description = "更新条数")
    private Integer updateCount;

    @Schema(description = "失败条数")
    private Integer failureCount;

    @Schema(description = "成功条数")
    private Integer successCount;
}
