package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetspatialdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetspatialdata.vo.AssetSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetspatialdata.AssetSpatialDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产空间数据 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetSpatialDataMapper extends BaseMapperX<AssetSpatialDataDO> {

    default PageResult<AssetSpatialDataDO> selectPage(AssetSpatialDataPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetSpatialDataDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("inputTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetSpatialDataDO::getInputTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 更新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetSpatialDataDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }

        return selectPage(reqVO, new LambdaQueryWrapperX<AssetSpatialDataDO>()
                .eqIfPresent(AssetSpatialDataDO::getAssetSpatialId, reqVO.getAssetSpatialId())
                .likeIfPresent(AssetSpatialDataDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetSpatialDataDO::getRelAssetName, reqVO.getRelAssetName())
                .eqIfPresent(AssetSpatialDataDO::getCoordSystemType, reqVO.getCoordSystemType())
                .eqIfPresent(AssetSpatialDataDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(AssetSpatialDataDO::getCoordY, reqVO.getCoordY())
                .eqIfPresent(AssetSpatialDataDO::getElevation, reqVO.getElevation())
                .eqIfPresent(AssetSpatialDataDO::getBoundaryCoords, reqVO.getBoundaryCoords())
                .eqIfPresent(AssetSpatialDataDO::getSpatialDataSource, reqVO.getSpatialDataSource())
                .betweenIfPresent(AssetSpatialDataDO::getInputTime, reqVO.getInputTime())
                .eqIfPresent(AssetSpatialDataDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetSpatialDataDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetSpatialDataDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetSpatialDataDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetSpatialDataDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetSpatialDataDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetSpatialDataDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetSpatialDataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetSpatialDataDO::getId));
    }

}