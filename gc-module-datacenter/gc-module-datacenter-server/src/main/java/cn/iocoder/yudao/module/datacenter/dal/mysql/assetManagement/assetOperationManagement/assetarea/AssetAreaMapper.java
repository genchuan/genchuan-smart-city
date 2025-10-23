package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetarea;

import java.util.*;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaListReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetarea.AssetAreaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产关联行政区划 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetAreaMapper extends BaseMapperX<AssetAreaDO> {

    default List<AssetAreaDO> selectList(AssetAreaListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AssetAreaDO>()
                .eqIfPresent(AssetAreaDO::getParentId, reqVO.getParentId())
                .likeIfPresent(AssetAreaDO::getName, reqVO.getName())
                .eqIfPresent(AssetAreaDO::getAssetRelRegionId, reqVO.getAssetRelRegionId())
                .eqIfPresent(AssetAreaDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetAreaDO::getRelAssetName, reqVO.getRelAssetName())
                .eqIfPresent(AssetAreaDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(AssetAreaDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(AssetAreaDO::getRegionLevel, reqVO.getRegionLevel())
                .betweenIfPresent(AssetAreaDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(AssetAreaDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetAreaDO::getRelDesc, reqVO.getRelDesc())
                .eqIfPresent(AssetAreaDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetAreaDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetAreaDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetAreaDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetAreaDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetAreaDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetAreaDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetAreaDO::getId));
    }

	default AssetAreaDO selectByParentIdAndName(Long parentId, String name) {
	    return selectOne(AssetAreaDO::getParentId, parentId, AssetAreaDO::getName, name);
	}

    default Long selectCountByParentId(Long parentId) {
        return selectCount(AssetAreaDO::getParentId, parentId);
    }

}