// AssetCatRuleCfgSimpleRespVO.java
package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 资产分类规则配置简单信息 Response VO")
@Data
public class AssetCatRuleCfgSimpleRespVO {

    @Schema(description = "分类规则ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private String assetCatRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "固定资产分类规则")
    private String ruleName;

    @Schema(description = "编码生成逻辑", requiredMode = Schema.RequiredMode.REQUIRED)
    private String codeGenLogic;

}