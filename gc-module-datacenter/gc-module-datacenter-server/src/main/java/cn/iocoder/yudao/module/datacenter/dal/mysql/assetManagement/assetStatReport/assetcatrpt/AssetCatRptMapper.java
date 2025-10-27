package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetStatReport.assetcatrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetStatReport.assetcatrpt.vo.AssetCatRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetStatReport.assetcatrpt.AssetCatRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产分类统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetCatRptMapper extends BaseMapperX<AssetCatRptDO> {

    default PageResult<AssetCatRptDO> selectPage(AssetCatRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetCatRptDO>()
                .eqIfPresent(AssetCatRptDO::getAssetCatStatId, reqVO.getAssetCatStatId())
                .likeIfPresent(AssetCatRptDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(AssetCatRptDO::getStatCycleName, reqVO.getStatCycleName())
                .likeIfPresent(AssetCatRptDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(AssetCatRptDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(AssetCatRptDO::getAssetMajorId, reqVO.getAssetMajorId())
                .likeIfPresent(AssetCatRptDO::getAssetMajorName, reqVO.getAssetMajorName())
                .eqIfPresent(AssetCatRptDO::getAssetMidId, reqVO.getAssetMidId())
                .likeIfPresent(AssetCatRptDO::getAssetMidName, reqVO.getAssetMidName())
                .eqIfPresent(AssetCatRptDO::getAssetMinorId, reqVO.getAssetMinorId())
                .likeIfPresent(AssetCatRptDO::getAssetMinorName, reqVO.getAssetMinorName())
                .eqIfPresent(AssetCatRptDO::getTotalAssetCount, reqVO.getTotalAssetCount())
                .eqIfPresent(AssetCatRptDO::getEnabledAssetCount, reqVO.getEnabledAssetCount())
                .eqIfPresent(AssetCatRptDO::getDisabledAssetCount, reqVO.getDisabledAssetCount())
                .eqIfPresent(AssetCatRptDO::getNewAssetCount, reqVO.getNewAssetCount())
                .eqIfPresent(AssetCatRptDO::getUpdateAssetCount, reqVO.getUpdateAssetCount())
                .betweenIfPresent(AssetCatRptDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(AssetCatRptDO::getStatUser, reqVO.getStatUser())
                .eqIfPresent(AssetCatRptDO::getRptRemark, reqVO.getRptRemark())
                .eqIfPresent(AssetCatRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetCatRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetCatRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetCatRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetCatRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetCatRptDO::getId));
    }

}