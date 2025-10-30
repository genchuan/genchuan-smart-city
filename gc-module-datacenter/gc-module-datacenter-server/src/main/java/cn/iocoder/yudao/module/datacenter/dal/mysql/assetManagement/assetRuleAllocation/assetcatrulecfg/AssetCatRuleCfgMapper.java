package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetcatrulecfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetcatrulecfg.AssetCatRuleCfgDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 资产分类规则配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetCatRuleCfgMapper extends BaseMapperX<AssetCatRuleCfgDO> {

    default PageResult<AssetCatRuleCfgDO> selectPage(AssetCatRuleCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetCatRuleCfgDO>()
                .eqIfPresent(AssetCatRuleCfgDO::getAssetCatRuleId, reqVO.getAssetCatRuleId())
                .likeIfPresent(AssetCatRuleCfgDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(AssetCatRuleCfgDO::getMajorCodeLength, reqVO.getMajorCodeLength())
                .eqIfPresent(AssetCatRuleCfgDO::getMidCodeLength, reqVO.getMidCodeLength())
                .eqIfPresent(AssetCatRuleCfgDO::getMinorCodeLength, reqVO.getMinorCodeLength())
                .eqIfPresent(AssetCatRuleCfgDO::getCodeGenLogic, reqVO.getCodeGenLogic())
                .eqIfPresent(AssetCatRuleCfgDO::getAssetDom, reqVO.getAssetDom())
                .eqIfPresent(AssetCatRuleCfgDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(AssetCatRuleCfgDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(AssetCatRuleCfgDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetCatRuleCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetCatRuleCfgDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetCatRuleCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetCatRuleCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetCatRuleCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetCatRuleCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetCatRuleCfgDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetCatRuleCfgDO::getId));
    }

    /**
     * 查询启用的资产分类规则列表（用于字典）
     *
     * @return 启用的资产分类规则列表
     */
    default List<AssetCatRuleCfgDO> selectEnabledList() {
        return selectList(new LambdaQueryWrapperX<AssetCatRuleCfgDO>()
                .eq(AssetCatRuleCfgDO::getEnableStatus, "1") // 状态为1表示启用
                .select(AssetCatRuleCfgDO::getAssetCatRuleId, AssetCatRuleCfgDO::getRuleName) // 只查询ID和规则名称
                .orderByAsc(AssetCatRuleCfgDO::getAssetCatRuleId)); // 按ID升序排列
    }
}