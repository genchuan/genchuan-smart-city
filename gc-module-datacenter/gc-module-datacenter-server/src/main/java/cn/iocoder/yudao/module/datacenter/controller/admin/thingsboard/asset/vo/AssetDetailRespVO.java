package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.thingsboard.server.common.data.id.AssetId;
import org.thingsboard.server.common.data.id.TenantId;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.AssetProfileId;

import java.util.List;
import java.util.Map;

@Schema(description = "管理后台 - 资产详情 Response VO")
@Data
public class AssetDetailRespVO {

    @Schema(description = "资产ID")
    private AssetId id;

    @Schema(description = "创建时间")
    private Long createdTime;

    @Schema(description = "租户ID")
    private TenantId tenantId;

    @Schema(description = "客户ID")
    private CustomerId customerId;

    @Schema(description = "资产名称")
    private String name;

    @Schema(description = "资产类型")
    private String type;

    @Schema(description = "资产标签")
    private String label;

    @Schema(description = "资产档案ID")
    private AssetProfileId assetProfileId;

    @Schema(description = "外部ID")
    private AssetId externalId;

    @Schema(description = "版本号")
    private Integer version;

    @Schema(description = "客户标题")
    private String customerTitle;

    @Schema(description = "客户是否公开")
    private Boolean customerIsPublic;

    @Schema(description = "资产档案名称")
    private String assetProfileName;

    @Schema(description = "附加信息")
    private Map<String, Object> additionalInfo;

    @Schema(description = "属性列表")
    private List<AttributeVO> attributes;

    @Schema(description = "关联设备列表")
    private List<ContextDeviceVO> contextDevice;

    @Schema(description = "关联资产列表")
    private List<ContextAssetVO> contextAsset;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;
}
