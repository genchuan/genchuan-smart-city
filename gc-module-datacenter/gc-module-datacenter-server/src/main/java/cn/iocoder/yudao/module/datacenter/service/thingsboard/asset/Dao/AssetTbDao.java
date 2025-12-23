package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.Dao;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;
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
}