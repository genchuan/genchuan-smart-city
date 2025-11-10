package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetclientattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetclientattrcfg.vo.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetclientattrcfg.AssetClientAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 资产客户端属性配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetClientAttrCfgService {

    /**
     * 创建资产客户端属性配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetClientAttrCfg(@Valid AssetClientAttrCfgSaveReqVO createReqVO);

    /**
     * 更新资产客户端属性配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetClientAttrCfg(@Valid AssetClientAttrCfgSaveReqVO updateReqVO);

    /**
     * 删除资产客户端属性配置
     *
     * @param id 编号
     */
    void deleteAssetClientAttrCfg(Long id);

    /**
     * 获得资产客户端属性配置
     *
     * @param id 编号
     * @return 资产客户端属性配置
     */
    AssetClientAttrCfgDO getAssetClientAttrCfg(Long id);

    /**
     * 获得资产客户端属性配置分页
     *
     * @param pageReqVO 分页查询
     * @return 资产客户端属性配置分页
     */
    PageResult<AssetClientAttrCfgDO> getAssetClientAttrCfgPage(AssetClientAttrCfgPageReqVO pageReqVO);

    AssetClientAttrCfgImportRespVO importAssetClientAttrCfgList(List<AssetClientAttrCfgImportExcelVO> list, boolean updateSupport);

    List<AssetClientAttrCfgSimpleRespVO> getAssetClientAttrList();
}