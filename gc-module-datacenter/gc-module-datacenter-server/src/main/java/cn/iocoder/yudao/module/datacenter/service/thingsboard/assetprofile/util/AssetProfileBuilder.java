package cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile.util;

import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.id.AssetProfileId;
import org.thingsboard.server.common.data.id.TenantId;

import java.util.UUID;

public class AssetProfileBuilder {

    /**
     * 构建AssetProfile对象用于创建
     */
    public static AssetProfile buildAssetProfile(String profileName, String description) {
        AssetProfile assetProfile = new AssetProfile();
        assetProfile.setName(profileName);
        assetProfile.setDescription(description);
        assetProfile.setDefault(false);
        return assetProfile;
    }

    /**
     * 构建AssetProfile对象用于更新
     */
    public static AssetProfile buildAssetProfileForUpdate(String profileId, String profileName,
                                                          String description, Long version) {
        AssetProfile assetProfile = new AssetProfile();

        // 设置资产配置ID（更新操作的关键）
        if (profileId != null && !profileId.isEmpty()) {
            try {
                AssetProfileId assetProfileIdObj = new AssetProfileId(UUID.fromString(profileId));
                assetProfile.setId(assetProfileIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的资产配置ID格式: " + profileId, e);
            }
        }

        // 设置资产配置名称
        if (profileName != null && !profileName.isEmpty()) {
            assetProfile.setName(profileName);
        } else {
            throw new IllegalArgumentException("资产配置名称不能为空");
        }

        // 设置描述
        assetProfile.setDescription(description);

        // 设置版本号（用于乐观锁）
        if (version != null) {
            assetProfile.setVersion(version);
        } else {
            assetProfile.setVersion(1L); // 默认版本号
        }

        assetProfile.setDefault(false);
        return assetProfile;
    }

    /**
     * 构建带更多参数的AssetProfile对象
     */
    public static AssetProfile buildAssetProfile(String profileName, String description,
                                                 String defaultRuleChainId, String defaultDashboardId) {
        AssetProfile assetProfile = buildAssetProfile(profileName, description);
        // 可以设置更多参数
        return assetProfile;
    }
}