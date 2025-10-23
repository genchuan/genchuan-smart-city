package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 资产分类管理导入 Response VO")
@Data
@Builder
public class AssetCatMngImportRespVO {
    @Schema(description = "创建成功的资产分类编码数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> createAssetCatCodes;
    @Schema(description = "更新成功的资产分类编码数组", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> updateAssetCatCodes;
    @Schema(description = "导入失败的资产分类集合，key 为资产分类编码，value 为失败原因", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, String> failureAssetCatCodes;

    @Schema(description = "新增条数")
    private Integer createCount;

    @Schema(description = "更新条数")
    private Integer updateCount;

    @Schema(description = "失败条数")
    private Integer failureCount;

    @Schema(description = "成功条数")
    private Integer successCount;
}
