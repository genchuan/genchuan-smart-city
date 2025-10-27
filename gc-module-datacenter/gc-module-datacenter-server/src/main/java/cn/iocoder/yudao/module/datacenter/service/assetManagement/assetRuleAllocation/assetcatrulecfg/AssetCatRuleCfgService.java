package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetcatrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetcatrulecfg.AssetCatRuleCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产分类规则配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetCatRuleCfgService {

    /**
     * 创建资产分类规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetCatRuleCfg(@Valid AssetCatRuleCfgSaveReqVO createReqVO);

    /**
     * 更新资产分类规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetCatRuleCfg(@Valid AssetCatRuleCfgSaveReqVO updateReqVO);

    /**
     * 删除资产分类规则配置
     *
     * @param id 编号
     */
    void deleteAssetCatRuleCfg(Long id);

    /**
     * 获得资产分类规则配置
     *
     * @param id 编号
     * @return 资产分类规则配置
     */
    AssetCatRuleCfgDO getAssetCatRuleCfg(Long id);

    /**
     * 获得资产分类规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 资产分类规则配置分页
     */
    PageResult<AssetCatRuleCfgDO> getAssetCatRuleCfgPage(AssetCatRuleCfgPageReqVO pageReqVO);

}