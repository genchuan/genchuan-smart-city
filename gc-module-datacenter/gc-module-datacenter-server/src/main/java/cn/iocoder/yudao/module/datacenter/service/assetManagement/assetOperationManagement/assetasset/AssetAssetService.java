package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo.AssetAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo.AssetAssetSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetasset.AssetAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产关联资产 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetAssetService {

    /**
     * 创建资产关联资产
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetAsset(@Valid AssetAssetSaveReqVO createReqVO);

    /**
     * 更新资产关联资产
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetAsset(@Valid AssetAssetSaveReqVO updateReqVO);

    /**
     * 删除资产关联资产
     *
     * @param id 编号
     */
    void deleteAssetAsset(Long id);

    /**
     * 获得资产关联资产
     *
     * @param id 编号
     * @return 资产关联资产
     */
    AssetAssetDO getAssetAsset(Long id);

    /**
     * 获得资产关联资产分页
     *
     * @param pageReqVO 分页查询
     * @return 资产关联资产分页
     */
    PageResult<AssetAssetDO> getAssetAssetPage(AssetAssetPageReqVO pageReqVO);

}