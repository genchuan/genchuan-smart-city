package cn.iocoder.yudao.module.datacenter.service.thingsboard.assetprofile.Dao;
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

import java.util.List;
import java.util.Map;
public interface AssetProfileTbDao {

    /**
     * 获取资产配置分页列表
     */
    PageData<AssetProfile> getAssetProfiles(Integer pageSize, Integer page, String sortProperty, String sortOrder);

    /**
     * 根据ID获取资产配置
     */
    AssetProfile getAssetProfileById(String id);

    /**
     * 创建资产配置到ThingsBoard
     */
    AssetProfile createAssetProfile(AssetProfile assetProfile);

    /**
     * 从ThingsBoard删除资产配置
     */
    void deleteAssetProfile(String assetProfileId);

    /**
     * 更新资产配置到ThingsBoard
     */
    AssetProfile updateAssetProfile(AssetProfile assetProfile);
}
