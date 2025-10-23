package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetgrid;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetgrid.vo.AssetGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetgrid.AssetGridDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产关联网格 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetGridMapper extends BaseMapperX<AssetGridDO> {

    default PageResult<AssetGridDO> selectPage(AssetGridPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetGridDO>()
                .eqIfPresent(AssetGridDO::getAssetRelGridId, reqVO.getAssetRelGridId())
                .eqIfPresent(AssetGridDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetGridDO::getRelAssetName, reqVO.getRelAssetName())
                .eqIfPresent(AssetGridDO::getGridCode, reqVO.getGridCode())
                .likeIfPresent(AssetGridDO::getGridName, reqVO.getGridName())
                .eqIfPresent(AssetGridDO::getGridLevel, reqVO.getGridLevel())
                .betweenIfPresent(AssetGridDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(AssetGridDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetGridDO::getRelDesc, reqVO.getRelDesc())
                .eqIfPresent(AssetGridDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetGridDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetGridDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetGridDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetGridDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetGridDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetGridDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetGridDO::getId));
    }

}