package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 资产数据管理简单信息 VO")
@Data
public class AssetDataMngSimpleVO {
    @Schema(description = "资产数据ID", example = "1418")
    private String assetDataId;

    @Schema(description = "资产名称", example = "李四")
    private String assetName;
}
