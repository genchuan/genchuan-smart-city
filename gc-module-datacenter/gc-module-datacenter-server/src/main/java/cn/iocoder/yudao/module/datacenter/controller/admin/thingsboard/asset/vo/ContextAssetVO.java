package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "关联资产VO")
@Data
public class ContextAssetVO {

    @Schema(description = "资产名称")
    private String assetName;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "资产ID")
    private String assetId;

}