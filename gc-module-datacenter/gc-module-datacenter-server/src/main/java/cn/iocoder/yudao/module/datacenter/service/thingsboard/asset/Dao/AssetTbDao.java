package cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.Dao;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.asset.vo.AssetPageReqVO;
import org.thingsboard.server.common.data.asset.Asset;
import org.thingsboard.server.common.data.asset.AssetInfo;

import java.util.List;

public interface AssetTbDao {
    PageResult<Asset> getAssetPage(AssetPageReqVO pageReqVO);

    Asset getAssetById(String id);

    AssetInfo getAssetInfoById(String id);
}