package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资产服务端属性简单信息 Response VO")
@Data
public class AssetServerAttrCfgSimpleRespVO {
    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4342")
    private String relAssetId;


    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    private String attrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    private String attrCode;

    @Schema(description = "属性数据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private String attrDataType;

    @Schema(description = "属性值", requiredMode = Schema.RequiredMode.REQUIRED)
    private String attrValue;

    @Schema(description = "采集频率")
    private String collectFreq;

    @Schema(description = "最后采集时间")
    private LocalDateTime lastCollectTime;
}
