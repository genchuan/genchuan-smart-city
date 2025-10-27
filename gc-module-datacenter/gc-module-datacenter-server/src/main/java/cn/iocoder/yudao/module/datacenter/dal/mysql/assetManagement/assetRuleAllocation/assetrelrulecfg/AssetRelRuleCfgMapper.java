package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetrelrulecfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetrelrulecfg.vo.AssetRelRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetrelrulecfg.AssetRelRuleCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产关联规则配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetRelRuleCfgMapper extends BaseMapperX<AssetRelRuleCfgDO> {

    default PageResult<AssetRelRuleCfgDO> selectPage(AssetRelRuleCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetRelRuleCfgDO>()
                .eqIfPresent(AssetRelRuleCfgDO::getAssetRelRuleId, reqVO.getAssetRelRuleId())
                .eqIfPresent(AssetRelRuleCfgDO::getRelAssetCatId, reqVO.getRelAssetCatId())
                .likeIfPresent(AssetRelRuleCfgDO::getRelAssetCatName, reqVO.getRelAssetCatName())
                .eqIfPresent(AssetRelRuleCfgDO::getRelObjectType, reqVO.getRelObjectType())
                .likeIfPresent(AssetRelRuleCfgDO::getRelObjectName, reqVO.getRelObjectName())
                .eqIfPresent(AssetRelRuleCfgDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(AssetRelRuleCfgDO::getRelQuantityLimit, reqVO.getRelQuantityLimit())
                .eqIfPresent(AssetRelRuleCfgDO::getRelObjectSourceTable, reqVO.getRelObjectSourceTable())
                .eqIfPresent(AssetRelRuleCfgDO::getRelDesc, reqVO.getRelDesc())
                .eqIfPresent(AssetRelRuleCfgDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(AssetRelRuleCfgDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetRelRuleCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetRelRuleCfgDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetRelRuleCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetRelRuleCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetRelRuleCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetRelRuleCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetRelRuleCfgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetRelRuleCfgDO::getId));
    }

}