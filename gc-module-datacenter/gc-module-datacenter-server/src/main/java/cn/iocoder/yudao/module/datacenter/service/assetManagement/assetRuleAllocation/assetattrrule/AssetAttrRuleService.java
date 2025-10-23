package cn.iocoder.yudao.module.datacenter.service.assetManagement.assetRuleAllocation.assetattrrule;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrule.vo.AssetAttrRuleListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrule.vo.AssetAttrRuleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetattrrule.AssetAttrRuleDO;

/**
 * 资产属性规则配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetAttrRuleService {

    /**
     * 创建资产属性规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetAttrRule(@Valid AssetAttrRuleSaveReqVO createReqVO);

    /**
     * 更新资产属性规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetAttrRule(@Valid AssetAttrRuleSaveReqVO updateReqVO);

    /**
     * 删除资产属性规则配置
     *
     * @param id 编号
     */
    void deleteAssetAttrRule(Long id);

    /**
     * 获得资产属性规则配置
     *
     * @param id 编号
     * @return 资产属性规则配置
     */
    AssetAttrRuleDO getAssetAttrRule(Long id);

    /**
     * 获得资产属性规则配置列表
     *
     * @param listReqVO 查询条件
     * @return 资产属性规则配置列表
     */
    List<AssetAttrRuleDO> getAssetAttrRuleList(AssetAttrRuleListReqVO listReqVO);

}