package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 资产新增/修改 Request VO")
@Data
public class AssetSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "资产ID", example = "04712fc0-780d-11f0-bd39-2dc5ccdb39ef")
    private String assetId;

    @Schema(description = "实体类型")
    private String entityType;

    @Schema(description = "创建时间戳")
    private Long createdTime;

    @Schema(description = "租户实体类型")
    private String tenantEntityType;

    @Schema(description = "客户ID", example = "13814000-1dd2-11b2-8080-808080808080")
    private String customerId;

    @Schema(description = "客户实体类型")
    private String customerEntityType;

    @Schema(description = "资产档案ID")
    @NotNull(message = "资产档案ID不能为空")
    private String assetProfileId;

    @Schema(description = "资产档案实体类型",example = "ASSET_PROFILE")
    private String assetProfileEntityType = "ASSET_PROFILE";;

    @Schema(description = "资产名称", example = "资产名称")
    @com.fasterxml.jackson.annotation.JsonProperty("name")
    private String assetName;

    @Schema(description = "资产类型", example = "资产配置名称")
    private String assetType;

    @Schema(description = "资产标签")
    private String label;

    @Schema(description = "外部ID", example = "3674")
    private String externalId;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "客户标题")
    private String customerTitle;

    @Schema(description = "客户是否公开")
    private Boolean customerIsPublic;

    @Schema(description = "资产档案名称")
    private String assetProfileName;

    @Schema(description = "附加信息")
    private Object additionalInfo;

    @Schema(description = "属性列表")
    private String attributes;

    @Schema(description = "关联设备列表")
    private String contextDevices;

    @Schema(description = "关联资产列表")
    private String contextAsset;

    @Schema(description = "系统租户ID")
    private Long tenantIdSys;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "资产类型")
    private String type;
}