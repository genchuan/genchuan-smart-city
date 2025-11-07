package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.AssetGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.AssetGridSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetgrid.AssetGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产关联网格 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetGridService {

    /**
     * 创建资产关联网格
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetGrid(@Valid AssetGridSaveReqVO createReqVO);

    /**
     * 更新资产关联网格
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetGrid(@Valid AssetGridSaveReqVO updateReqVO);

    /**
     * 删除资产关联网格
     *
     * @param id 编号
     */
    void deleteAssetGrid(Long id);

    /**
     * 获得资产关联网格
     *
     * @param id 编号
     * @return 资产关联网格
     */
    AssetGridDO getAssetGrid(Long id);

    /**
     * 获得资产关联网格分页
     *
     * @param pageReqVO 分页查询
     * @return 资产关联网格分页
     */
    PageResult<AssetGridDO> getAssetGridPage(AssetGridPageReqVO pageReqVO);

}