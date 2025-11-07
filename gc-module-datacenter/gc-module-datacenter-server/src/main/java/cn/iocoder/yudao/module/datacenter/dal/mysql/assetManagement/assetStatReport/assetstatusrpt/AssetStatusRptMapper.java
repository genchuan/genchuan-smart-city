package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetStatReport.assetstatusrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetstatusrpt.vo.AssetStatusRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetstatusrpt.AssetStatusRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产状态统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetStatusRptMapper extends BaseMapperX<AssetStatusRptDO> {

    default PageResult<AssetStatusRptDO> selectPage(AssetStatusRptPageReqVO reqVO) {

        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetStatusRptDO> queryWrapper = new LambdaQueryWrapperX<>();
        if ("assetCount".equals(reqVO.getOrderByColumn())) {
            // 资产数量排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetStatusRptDO::getAssetCount);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("mntPriority".equals(reqVO.getOrderByColumn())) {
            // 维护优先级
            String orderByClause = "FIELD(mnt_priority, 'high', 'middle', 'low')";
            if ("desc".equals(reqVO.getIsAsc())) {
                orderByClause += " DESC";
            }
            queryWrapper.last("ORDER BY " + orderByClause);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("assetRatio".equals(reqVO.getOrderByColumn())) {
            // 统计占比排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetStatusRptDO::getAssetRatio);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("statTime".equals(reqVO.getOrderByColumn())) {
            // 统计时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetStatusRptDO::getStatTime);
            return selectPage(reqVO, null, queryWrapper);
        }

        return selectPage(reqVO, new LambdaQueryWrapperX<AssetStatusRptDO>()
                .eqIfPresent(AssetStatusRptDO::getAssetStatusStatId, reqVO.getAssetStatusStatId())
                .eqIfPresent(AssetStatusRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AssetStatusRptDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(AssetStatusRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(AssetStatusRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(AssetStatusRptDO::getAssetType, reqVO.getAssetType())
                .eqIfPresent(AssetStatusRptDO::getAssetStatus, reqVO.getAssetStatus())
                .eqIfPresent(AssetStatusRptDO::getAssetCount, reqVO.getAssetCount())
                .eqIfPresent(AssetStatusRptDO::getAssetRatio, reqVO.getAssetRatio())
                .eqIfPresent(AssetStatusRptDO::getFaultReason, reqVO.getFaultReason())
                .eqIfPresent(AssetStatusRptDO::getMntPriority, reqVO.getMntPriority())
                .betweenIfPresent(AssetStatusRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(AssetStatusRptDO::getStatUser, reqVO.getStatUser())
                .eqIfPresent(AssetStatusRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(AssetStatusRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetStatusRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetStatusRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetStatusRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetStatusRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetStatusRptDO::getId));
    }

}