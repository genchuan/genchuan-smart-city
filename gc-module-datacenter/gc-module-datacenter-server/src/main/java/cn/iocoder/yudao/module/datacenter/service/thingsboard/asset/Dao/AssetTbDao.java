package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.Dao;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;
import org.thingsboard.server.common.data.asset.AssetProfile;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

import java.util.List;
import java.util.Map;

public interface AssetTbDao {
    PageResult<Asset> getAssetPage(AssetPageReqVO pageReqVO);

    Asset getAssetById(String id);

    AssetInfo getAssetInfoById(String id);

    // 获取所有资产列表（分页）
    PageData<Asset> getAllAssets(PageLink pageLink);

    // 新增：获取资产属性
    List<Map<String, Object>> getAssetAttributes(String assetId);

    // 新增：获取资产关联的设备
    List<Map<String, Object>> getAssetRelatedDevices(String assetId);

    /**
     * 创建资产到ThingsBoard
     * @param asset 资产信息
     * @return 创建后的资产信息
     */
    Asset createAsset(Asset asset);

    /**
     * 从ThingsBoard删除资产
     * @param assetId 资产ID
     */
    void deleteAsset(String assetId);

    /**
     * 获取资产配置分页列表
     * @param pageSize 每页大小
     * @param page 页码
     * @param sortProperty 排序字段
     * @param sortOrder 排序方向
     * @return 资产配置分页数据
     */
    PageData<AssetProfile> getAssetProfiles(Integer pageSize, Integer page, String sortProperty, String sortOrder);

    /**
     * 添加资产属性
     * @param assetId 资产ID
     * @param attributes 属性键值对
     */
    void addAssetAttributes(String assetId, Map<String, Object> attributes);

    /**
     * 删除资产属性
     * @param assetId 资产ID
     * @param scope 属性作用域
     * @param keys 要删除的属性键列表
     */
    void deleteAssetAttributes(String assetId, String scope, List<String> keys);

    /**
     * 获取资产向外关联的资产
     * @param assetId 资产ID
     * @return 关联资产列表
     */
    List<Map<String, Object>> getAssetOutwardRelations(String assetId);

}