package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetOperationManagement.assetcatmng;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产分类管理 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetCatMngService {

    /**
     * 创建资产分类管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetCatMng(@Valid AssetCatMngSaveReqVO createReqVO);

    /**
     * 更新资产分类管理
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetCatMng(@Valid AssetCatMngSaveReqVO updateReqVO);

    /**
     * 删除资产分类管理
     *
     * @param id 编号
     */
    void deleteAssetCatMng(Long id);

    /**
     * 获得资产分类管理
     *
     * @param id 编号
     * @return 资产分类管理
     */
    AssetCatMngDO getAssetCatMng(Long id);

    /**
     * 获得资产分类管理分页
     *
     * @param pageReqVO 分页查询
     * @return 资产分类管理分页
     */
    PageResult<AssetCatMngDO> getAssetCatMngPage(AssetCatMngPageReqVO pageReqVO);

}