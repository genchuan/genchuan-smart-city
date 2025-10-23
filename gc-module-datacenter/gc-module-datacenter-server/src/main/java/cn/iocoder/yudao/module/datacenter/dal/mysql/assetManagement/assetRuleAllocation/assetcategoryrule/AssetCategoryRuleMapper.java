package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetcategoryrule;

import java.util.*;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcategoryrule.vo.AssetCategoryRuleListReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetcategoryrule.AssetCategoryRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产分类规则配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetCategoryRuleMapper extends BaseMapperX<AssetCategoryRuleDO> {

    default List<AssetCategoryRuleDO> selectList(AssetCategoryRuleListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AssetCategoryRuleDO>()
                .eqIfPresent(AssetCategoryRuleDO::getParentId, reqVO.getParentId())
                .likeIfPresent(AssetCategoryRuleDO::getName, reqVO.getName())
                .eqIfPresent(AssetCategoryRuleDO::getCategoryRuleId, reqVO.getCategoryRuleId())
                .eqIfPresent(AssetCategoryRuleDO::getParentCategoryRuleId, reqVO.getParentCategoryRuleId())
                .eqIfPresent(AssetCategoryRuleDO::getCategoryLevel, reqVO.getCategoryLevel())
                .eqIfPresent(AssetCategoryRuleDO::getCategoryCode, reqVO.getCategoryCode())
                .likeIfPresent(AssetCategoryRuleDO::getCategoryName, reqVO.getCategoryName())
                .eqIfPresent(AssetCategoryRuleDO::getCategoryDesc, reqVO.getCategoryDesc())
                .eqIfPresent(AssetCategoryRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(AssetCategoryRuleDO::getCreatedUser, reqVO.getCreatedUser())
                .betweenIfPresent(AssetCategoryRuleDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetCategoryRuleDO::getUpdatedUser, reqVO.getUpdatedUser())
                .betweenIfPresent(AssetCategoryRuleDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetCategoryRuleDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(AssetCategoryRuleDO::getExtCategory2, reqVO.getExtCategory2())
                .betweenIfPresent(AssetCategoryRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetCategoryRuleDO::getId));
    }

	default AssetCategoryRuleDO selectByParentIdAndName(Long parentId, String name) {
	    return selectOne(AssetCategoryRuleDO::getParentId, parentId, AssetCategoryRuleDO::getName, name);
	}

    default Long selectCountByParentId(Long parentId) {
        return selectCount(AssetCategoryRuleDO::getParentId, parentId);
    }

}