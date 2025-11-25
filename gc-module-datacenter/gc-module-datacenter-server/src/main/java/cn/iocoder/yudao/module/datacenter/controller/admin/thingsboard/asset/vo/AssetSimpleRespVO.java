package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 资产简单信息 Response VO")
@Data
public class AssetSimpleRespVO {
    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19775")
    private String id;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "资产1")
    private String name;
}
