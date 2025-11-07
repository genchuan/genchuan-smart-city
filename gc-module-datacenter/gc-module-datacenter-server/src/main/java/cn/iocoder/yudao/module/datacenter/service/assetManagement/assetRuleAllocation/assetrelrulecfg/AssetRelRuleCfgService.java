package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetrelrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgBatchUpdateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetrelrulecfg.AssetRelRuleCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产关联规则配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetRelRuleCfgService {

    /**
     * 创建资产关联规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetRelRuleCfg(@Valid AssetRelRuleCfgSaveReqVO createReqVO);

    /**
     * 更新资产关联规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetRelRuleCfg(@Valid AssetRelRuleCfgSaveReqVO updateReqVO);

    /**
     * 删除资产关联规则配置
     *
     * @param id 编号
     */
    void deleteAssetRelRuleCfg(Long id);

    /**
     * 获得资产关联规则配置
     *
     * @param id 编号
     * @return 资产关联规则配置
     */
    AssetRelRuleCfgDO getAssetRelRuleCfg(Long id);

    /**
     * 获得资产关联规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 资产关联规则配置分页
     */
    PageResult<AssetRelRuleCfgDO> getAssetRelRuleCfgPage(AssetRelRuleCfgPageReqVO pageReqVO);

    /**
     * 批量更新“是否必选”
     *
     * @param reqVO
     */
    void batchUpdateIsRequired(@Valid AssetRelRuleCfgBatchUpdateReqVO reqVO);
}