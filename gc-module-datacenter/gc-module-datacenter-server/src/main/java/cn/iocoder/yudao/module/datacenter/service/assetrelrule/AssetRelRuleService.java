package cn.iocoder.yudao.module.datacenter.service.assetrelrule;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetrelrule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetrelrule.AssetRelRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 资产关联规则配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetRelRuleService {

    /**
     * 创建资产关联规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetRelRule(@Valid AssetRelRuleSaveReqVO createReqVO);

    /**
     * 更新资产关联规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetRelRule(@Valid AssetRelRuleSaveReqVO updateReqVO);

    /**
     * 删除资产关联规则配置
     *
     * @param id 编号
     */
    void deleteAssetRelRule(Long id);

    /**
     * 获得资产关联规则配置
     *
     * @param id 编号
     * @return 资产关联规则配置
     */
    AssetRelRuleDO getAssetRelRule(Long id);

    /**
     * 获得资产关联规则配置列表
     *
     * @param listReqVO 查询条件
     * @return 资产关联规则配置列表
     */
    List<AssetRelRuleDO> getAssetRelRuleList(AssetRelRuleListReqVO listReqVO);

}