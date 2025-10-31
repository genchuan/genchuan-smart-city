package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetDataMng.assetshareattrcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgImportExcelVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgImportRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetshareattrcfg.vo.AssetShareAttrCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetshareattrcfg.AssetShareAttrCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import java.util.List;

/**
 * 资产共享属性配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetShareAttrCfgService {

    /**
     * 创建资产共享属性配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetShareAttrCfg(@Valid AssetShareAttrCfgSaveReqVO createReqVO);

    /**
     * 更新资产共享属性配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetShareAttrCfg(@Valid AssetShareAttrCfgSaveReqVO updateReqVO);

    /**
     * 删除资产共享属性配置
     *
     * @param id 编号
     */
    void deleteAssetShareAttrCfg(Long id);

    /**
     * 获得资产共享属性配置
     *
     * @param id 编号
     * @return 资产共享属性配置
     */
    AssetShareAttrCfgDO getAssetShareAttrCfg(Long id);

    /**
     * 获得资产共享属性配置分页
     *
     * @param pageReqVO 分页查询
     * @return 资产共享属性配置分页
     */
    PageResult<AssetShareAttrCfgDO> getAssetShareAttrCfgPage(AssetShareAttrCfgPageReqVO pageReqVO);

    AssetShareAttrCfgImportRespVO importExcel(List<AssetShareAttrCfgImportExcelVO> list, boolean updateSupport);
}