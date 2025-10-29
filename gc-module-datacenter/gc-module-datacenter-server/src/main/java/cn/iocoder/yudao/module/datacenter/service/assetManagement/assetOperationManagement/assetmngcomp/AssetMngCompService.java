package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetmngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo.AssetMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo.AssetMngCompSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetmngcomp.AssetMngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 资产关联管理部件 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetMngCompService {

    /**
     * 创建资产关联管理部件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetMngComp(@Valid AssetMngCompSaveReqVO createReqVO);

    /**
     * 更新资产关联管理部件
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetMngComp(@Valid AssetMngCompSaveReqVO updateReqVO);

    /**
     * 删除资产关联管理部件
     *
     * @param id 编号
     */
    void deleteAssetMngComp(Long id);

    /**
     * 获得资产关联管理部件
     *
     * @param id 编号
     * @return 资产关联管理部件
     */
    AssetMngCompDO getAssetMngComp(Long id);

    /**
     * 获得资产关联管理部件分页
     *
     * @param pageReqVO 分页查询
     * @return 资产关联管理部件分页
     */
    PageResult<AssetMngCompDO> getAssetMngCompPage(AssetMngCompPageReqVO pageReqVO);

    /**
     * 批量删除资产关联管理部件
     *
     * @param ids 编号列表
     */
    void deleteAssetMngCompBatch(List<Long> ids);

}