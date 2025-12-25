package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 资产新增/修改 Request VO")
@Data
public class AssetSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16638")
    private Long id;

    @Schema(description = "资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32353")
    @NotEmpty(message = "资产ID不能为空")
    private String assetId;

    @Schema(description = "实体类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "实体类型不能为空")
    private String entityType;

    @Schema(description = "创建时间戳", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间戳不能为空")
    private Long createdTime;

    @Schema(description = "租户实体类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "租户实体类型不能为空")
    private String tenantEntityType;

    @Schema(description = "客户ID", example = "29253")
    private String customerId;

    @Schema(description = "客户实体类型", example = "2")
    private String customerEntityType;

    @Schema(description = "资产档案ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31817")
    @NotEmpty(message = "资产档案ID不能为空")
    private String assetProfileId;

    @Schema(description = "资产档案实体类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "资产档案实体类型不能为空")
    private String assetProfileEntityType;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "资产名称不能为空")
    private String assetName;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "资产类型不能为空")
    private String assetType;

    @Schema(description = "资产标签")
    private String assetLabel;

    @Schema(description = "外部ID", example = "3674")
    private String externalId;

    @Schema(description = "版本号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "版本号不能为空")
    private Integer version;

    @Schema(description = "客户标题")
    private String customerTitle;

    @Schema(description = "客户是否公开")
    private Boolean customerIsPublic;

    @Schema(description = "资产档案名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "资产档案名称不能为空")
    private String assetProfileName;

    @Schema(description = "附加信息")
    private String additionalInfo;

    @Schema(description = "属性列表")
    private String attributes;

    @Schema(description = "关联设备列表")
    private String contextDevices;

    @Schema(description = "系统租户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "系统租户ID不能为空")
    private Long tenantIdSys;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;
}