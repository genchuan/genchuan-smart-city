package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.util;

import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.id.AssetId;
import org.thingsboard.server.common.data.id.AssetProfileId;
import org.thingsboard.server.common.data.id.CustomerId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class AssetBuilder {

    public static Asset buildAsset(String name, String assetProfileId, String label,
                                   String customerId, String description) {
        Asset asset = new Asset();
        asset.setName(name);

        // 设置资产配置ID - 直接使用输入的字符串，不自动生成UUID
        if (assetProfileId != null && !assetProfileId.isEmpty()) {
            try {
                // 直接使用输入的资产配置ID字符串
                AssetProfileId assetProfileIdObj = new AssetProfileId(UUID.fromString(assetProfileId));
                asset.setAssetProfileId(assetProfileIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的资产档案ID格式: " + assetProfileId, e);
            }
        }

        // 设置标签
        asset.setLabel(label);

        // 设置客户ID - 修正这里
        if (customerId != null && !customerId.isEmpty()) {
            try {
                // 使用UUID.fromString创建CustomerId
                UUID customerUuid = UUID.fromString(customerId);
                CustomerId customerIdObj = new CustomerId(customerUuid);
                asset.setCustomerId(customerIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的客户ID格式: " + customerId, e);
            }
        }

        // 设置附加信息
        if (description != null && !description.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode additionalInfo = mapper.createObjectNode()
                        .put("description", description);
                asset.setAdditionalInfo(additionalInfo);
            } catch (Exception e) {
                // 忽略JSON构建异常
            }
        }

        return asset;
    }
}