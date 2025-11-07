package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetattrrulecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgBatchUpdateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetattrrulecfg.AssetAttrRuleCfgDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产属性规则配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetAttrRuleCfgService {

    /**
     * 创建资产属性规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetAttrRuleCfg(@Valid AssetAttrRuleCfgSaveReqVO createReqVO);

    /**
     * 更新资产属性规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetAttrRuleCfg(@Valid AssetAttrRuleCfgSaveReqVO updateReqVO);

    /**
     * 删除资产属性规则配置
     *
     * @param id 编号
     */
    void deleteAssetAttrRuleCfg(Long id);

    /**
     * 获得资产属性规则配置
     *
     * @param id 编号
     * @return 资产属性规则配置
     */
    AssetAttrRuleCfgDO getAssetAttrRuleCfg(Long id);

    /**
     * 获得资产属性规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 资产属性规则配置分页
     */
    PageResult<AssetAttrRuleCfgDO> getAssetAttrRuleCfgPage(AssetAttrRuleCfgPageReqVO pageReqVO);

    /**
     * 批量更新“是否必选”
     *
     * @param reqVO
     */
    void batchUpdateIsRequired(@Valid AssetAttrRuleCfgBatchUpdateReqVO reqVO);

}