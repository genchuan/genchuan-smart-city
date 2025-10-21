package cn.iocoder.yudao.module.datacenter.dal.mysql.assetrelrule;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetrelrule.AssetRelRuleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetrelrule.vo.*;

/**
 * 资产关联规则配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetRelRuleMapper extends BaseMapperX<AssetRelRuleDO> {

    default List<AssetRelRuleDO> selectList(AssetRelRuleListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AssetRelRuleDO>()
                .eqIfPresent(AssetRelRuleDO::getParentId, reqVO.getParentId())
                .likeIfPresent(AssetRelRuleDO::getName, reqVO.getName())
                .eqIfPresent(AssetRelRuleDO::getRelRuleId, reqVO.getRelRuleId())
                .eqIfPresent(AssetRelRuleDO::getAssetCategoryId, reqVO.getAssetCategoryId())
                .likeIfPresent(AssetRelRuleDO::getAssetCategoryName, reqVO.getAssetCategoryName())
                .eqIfPresent(AssetRelRuleDO::getRelObjType, reqVO.getRelObjType())
                .eqIfPresent(AssetRelRuleDO::getRelObjId, reqVO.getRelObjId())
                .likeIfPresent(AssetRelRuleDO::getRelObjName, reqVO.getRelObjName())
                .eqIfPresent(AssetRelRuleDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(AssetRelRuleDO::getRelCheckRule, reqVO.getRelCheckRule())
                .eqIfPresent(AssetRelRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(AssetRelRuleDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(AssetRelRuleDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetRelRuleDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetRelRuleDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetRelRuleDO::getExtCategory1, reqVO.getExtCategory1())
                .eqIfPresent(AssetRelRuleDO::getExtCategory2, reqVO.getExtCategory2())
                .eqIfPresent(AssetRelRuleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetRelRuleDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetRelRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetRelRuleDO::getId));
    }

	default AssetRelRuleDO selectByParentIdAndName(Long parentId, String name) {
	    return selectOne(AssetRelRuleDO::getParentId, parentId, AssetRelRuleDO::getName, name);
	}

    default Long selectCountByParentId(Long parentId) {
        return selectCount(AssetRelRuleDO::getParentId, parentId);
    }

}