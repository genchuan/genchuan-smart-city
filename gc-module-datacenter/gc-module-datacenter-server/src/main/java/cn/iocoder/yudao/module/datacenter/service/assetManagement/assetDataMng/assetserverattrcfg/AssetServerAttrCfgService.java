package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetserverattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetserverattrcfg.AssetServerAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 资产服务端属性配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetServerAttrCfgService {

    /**
     * 创建资产服务端属性配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetServerAttrCfg(@Valid AssetServerAttrCfgSaveReqVO createReqVO);

    /**
     * 更新资产服务端属性配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetServerAttrCfg(@Valid AssetServerAttrCfgSaveReqVO updateReqVO);

    /**
     * 删除资产服务端属性配置
     *
     * @param id 编号
     */
    void deleteAssetServerAttrCfg(Long id);

    /**
     * 获得资产服务端属性配置
     *
     * @param id 编号
     * @return 资产服务端属性配置
     */
    AssetServerAttrCfgDO getAssetServerAttrCfg(Long id);

    /**
     * 获得资产服务端属性配置分页
     *
     * @param pageReqVO 分页查询
     * @return 资产服务端属性配置分页
     */
    PageResult<AssetServerAttrCfgDO> getAssetServerAttrCfgPage(AssetServerAttrCfgPageReqVO pageReqVO);

    AssetServerAttrCfgImportRespVO importAssetServerAttrCfgList(List<AssetServerAttrCfgImportExcelVO> list, boolean updateSupport);

    List<AssetServerAttrCfgSimpleRespVO> getAssetServerAttrList();
}