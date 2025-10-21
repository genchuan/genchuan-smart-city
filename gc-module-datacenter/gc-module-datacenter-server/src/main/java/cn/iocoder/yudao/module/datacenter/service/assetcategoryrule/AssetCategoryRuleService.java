package cn.iocoder.yudao.module.datacenter.service.assetcategoryrule;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetcategoryrule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetcategoryrule.AssetCategoryRuleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 资产分类规则配置 Service 接口
 *
 * @author 亘川智城
 */
public interface AssetCategoryRuleService {

    /**
     * 创建资产分类规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssetCategoryRule(@Valid AssetCategoryRuleSaveReqVO createReqVO);

    /**
     * 更新资产分类规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateAssetCategoryRule(@Valid AssetCategoryRuleSaveReqVO updateReqVO);

    /**
     * 删除资产分类规则配置
     *
     * @param id 编号
     */
    void deleteAssetCategoryRule(Long id);

    /**
     * 获得资产分类规则配置
     *
     * @param id 编号
     * @return 资产分类规则配置
     */
    AssetCategoryRuleDO getAssetCategoryRule(Long id);

    /**
     * 获得资产分类规则配置列表
     *
     * @param listReqVO 查询条件
     * @return 资产分类规则配置列表
     */
    List<AssetCategoryRuleDO> getAssetCategoryRuleList(AssetCategoryRuleListReqVO listReqVO);

}