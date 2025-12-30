package cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile.util;

import org.thingsboard.server.common.data.asset.AssetProfile;

public class AssetProfileBuilder {

    /**
     * 构建AssetProfile对象
     */
    public static AssetProfile buildAssetProfile(String profileName, String description) {
        AssetProfile assetProfile = new AssetProfile();
        assetProfile.setName(profileName);
        assetProfile.setDescription(description);
        // 设置其他默认值
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