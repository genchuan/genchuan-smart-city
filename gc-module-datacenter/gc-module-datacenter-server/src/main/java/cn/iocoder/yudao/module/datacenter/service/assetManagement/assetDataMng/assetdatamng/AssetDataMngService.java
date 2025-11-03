package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetdatamng;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngSaveReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngSimpleVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdatamng.AssetDataMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 资产数据管理 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetDataMngService {

    /**
     * 创建资产数据管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetDataMng(@Valid AssetDataMngSaveReqVO createReqVO);

    /**
     * 更新资产数据管理
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetDataMng(@Valid AssetDataMngSaveReqVO updateReqVO);

    /**
     * 删除资产数据管理
     *
     * @param id 编号
     */
    void deleteAssetDataMng(Long id);

    /**
     * 获得资产数据管理
     *
     * @param id 编号
     * @return 资产数据管理
     */
    AssetDataMngDO getAssetDataMng(Long id);

    /**
     * 获得资产数据管理分页
     *
     * @param pageReqVO 分页查询
     * @return 资产数据管理分页
     */
    PageResult<AssetDataMngDO> getAssetDataMngPage(AssetDataMngPageReqVO pageReqVO);

    List<AssetDataMngSimpleVO> getEnabledAssetDataMngList();
}