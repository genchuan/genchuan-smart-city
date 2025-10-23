package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetattrrule;

import java.util.*;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrule.vo.AssetAttrRuleListReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetattrrule.AssetAttrRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产属性规则配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetAttrRuleMapper extends BaseMapperX<AssetAttrRuleDO> {

    default List<AssetAttrRuleDO> selectList(AssetAttrRuleListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AssetAttrRuleDO>()
                .eqIfPresent(AssetAttrRuleDO::getParentId, reqVO.getParentId())
                .eqIfPresent(AssetAttrRuleDO::getAttrRuleId, reqVO.getAttrRuleId())
                .eqIfPresent(AssetAttrRuleDO::getAssetCategoryId, reqVO.getAssetCategoryId())
                .likeIfPresent(AssetAttrRuleDO::getAssetCategoryName, reqVO.getAssetCategoryName())
                .likeIfPresent(AssetAttrRuleDO::getAttrName, reqVO.getAttrName())
                .eqIfPresent(AssetAttrRuleDO::getAttrCode, reqVO.getAttrCode())
                .eqIfPresent(AssetAttrRuleDO::getDataType, reqVO.getDataType())
                .eqIfPresent(AssetAttrRuleDO::getFieldLength, reqVO.getFieldLength())
                .eqIfPresent(AssetAttrRuleDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(AssetAttrRuleDO::getUnit, reqVO.getUnit())
                .eqIfPresent(AssetAttrRuleDO::getValueRange, reqVO.getValueRange())
                .eqIfPresent(AssetAttrRuleDO::getDefaultValue, reqVO.getDefaultValue())
                .eqIfPresent(AssetAttrRuleDO::getAttrDesc, reqVO.getAttrDesc())
                .eqIfPresent(AssetAttrRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(AssetAttrRuleDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(AssetAttrRuleDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetAttrRuleDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetAttrRuleDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetAttrRuleDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(AssetAttrRuleDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(AssetAttrRuleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetAttrRuleDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetAttrRuleDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(AssetAttrRuleDO::getName, reqVO.getName())
                .orderByDesc(AssetAttrRuleDO::getId));
    }

	default AssetAttrRuleDO selectByParentIdAndName(Long parentId, String name) {
	    return selectOne(AssetAttrRuleDO::getParentId, parentId, AssetAttrRuleDO::getName, name);
	}

    default Long selectCountByParentId(Long parentId) {
        return selectCount(AssetAttrRuleDO::getParentId, parentId);
    }

}