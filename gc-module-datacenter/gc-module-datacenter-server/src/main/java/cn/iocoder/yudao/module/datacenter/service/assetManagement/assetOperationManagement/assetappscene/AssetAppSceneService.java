package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.AssetAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.AssetAppSceneSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetappscene.AssetAppSceneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 资产关联应用场景 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetAppSceneService {

    /**
     * 创建资产关联应用场景
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetAppScene(@Valid AssetAppSceneSaveReqVO createReqVO);

    /**
     * 更新资产关联应用场景
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetAppScene(@Valid AssetAppSceneSaveReqVO updateReqVO);

    /**
     * 删除资产关联应用场景
     *
     * @param id 编号
     */
    void deleteAssetAppScene(Long id);

    /**
     * 获得资产关联应用场景
     *
     * @param id 编号
     * @return 资产关联应用场景
     */
    AssetAppSceneDO getAssetAppScene(Long id);

    /**
     * 获得资产关联应用场景分页
     *
     * @param pageReqVO 分页查询
     * @return 资产关联应用场景分页
     */
    PageResult<AssetAppSceneDO> getAssetAppScenePage(AssetAppScenePageReqVO pageReqVO);

    /**
     * 批量删除资产关联应用场景
     *
     * @param ids 编号列表
     */
    void deleteAssetAppSceneBatch(List<Long> ids);

}