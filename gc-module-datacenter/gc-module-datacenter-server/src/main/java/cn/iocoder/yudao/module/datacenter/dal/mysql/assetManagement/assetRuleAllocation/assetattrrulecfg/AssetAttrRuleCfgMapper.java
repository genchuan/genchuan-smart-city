package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetattrrulecfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetattrrulecfg.vo.AssetAttrRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetattrrulecfg.AssetAttrRuleCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产属性规则配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetAttrRuleCfgMapper extends BaseMapperX<AssetAttrRuleCfgDO> {

    default PageResult<AssetAttrRuleCfgDO> selectPage(AssetAttrRuleCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetAttrRuleCfgDO>()
                .eqIfPresent(AssetAttrRuleCfgDO::getAssetAttrRuleId, reqVO.getAssetAttrRuleId())
                .eqIfPresent(AssetAttrRuleCfgDO::getRelAssetCatId, reqVO.getRelAssetCatId())
                .likeIfPresent(AssetAttrRuleCfgDO::getRelAssetCatName, reqVO.getRelAssetCatName())
                .likeIfPresent(AssetAttrRuleCfgDO::getAssetAttrName, reqVO.getAssetAttrName())
                .eqIfPresent(AssetAttrRuleCfgDO::getAssetAttrCode, reqVO.getAssetAttrCode())
                .eqIfPresent(AssetAttrRuleCfgDO::getAttrDataType, reqVO.getAttrDataType())
                .eqIfPresent(AssetAttrRuleCfgDO::getAttrLength, reqVO.getAttrLength())
                .eqIfPresent(AssetAttrRuleCfgDO::getAttrValueRange, reqVO.getAttrValueRange())
                .eqIfPresent(AssetAttrRuleCfgDO::getIsRequired, reqVO.getIsRequired())
                .eqIfPresent(AssetAttrRuleCfgDO::getAttrDesc, reqVO.getAttrDesc())
                .eqIfPresent(AssetAttrRuleCfgDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(AssetAttrRuleCfgDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetAttrRuleCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetAttrRuleCfgDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetAttrRuleCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetAttrRuleCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetAttrRuleCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetAttrRuleCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetAttrRuleCfgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetAttrRuleCfgDO::getId));
    }

}