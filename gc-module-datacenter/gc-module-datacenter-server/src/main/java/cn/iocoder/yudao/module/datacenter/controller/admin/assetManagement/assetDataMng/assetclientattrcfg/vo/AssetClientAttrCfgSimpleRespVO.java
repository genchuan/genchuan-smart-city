package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 资产客户端属性配置简单信息 Response VO")
@Data
public class AssetClientAttrCfgSimpleRespVO {
    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30407")
    private String relAssetId;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    private String attrName;

    @Schema(description = "属性数据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private String attrDataType;

    @Schema(description = "属性值", requiredMode = Schema.RequiredMode.REQUIRED)
    private String attrValue;

}
