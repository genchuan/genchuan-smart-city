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
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetRelRuleCfgDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("createdTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetRelRuleCfgDO::getCreatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 重新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetRelRuleCfgDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("relQuantityLimit".equals(reqVO.getOrderByColumn())) {
            // 重新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetRelRuleCfgDO::getRelQuantityLimit);
            return selectPage(reqVO, null, queryWrapper);
        }

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