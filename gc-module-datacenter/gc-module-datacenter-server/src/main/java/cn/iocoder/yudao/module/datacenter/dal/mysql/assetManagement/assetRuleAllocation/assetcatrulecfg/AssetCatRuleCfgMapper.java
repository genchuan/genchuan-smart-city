package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetRuleAllocation.assetcatrulecfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetRuleAllocation.assetcatrulecfg.vo.AssetCatRuleCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetRuleAllocation.assetcatrulecfg.AssetCatRuleCfgDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 资产分类规则配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetCatRuleCfgMapper extends BaseMapperX<AssetCatRuleCfgDO> {

    default PageResult<AssetCatRuleCfgDO> selectPage(AssetCatRuleCfgPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetCatRuleCfgDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("createdTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetCatRuleCfgDO::getCreatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("assetDom".equals(reqVO.getOrderByColumn())) {
            // 适用资产领域排序 - 使用自定义顺序：市政/交通/城管
            String orderByClause = "FIELD(asset_dom, '市政', '交通', '城管')";
            if ("desc".equals(reqVO.getIsAsc())) {
                orderByClause += " DESC";
            }
            queryWrapper.last("ORDER BY " + orderByClause);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 重新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetCatRuleCfgDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }

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

}