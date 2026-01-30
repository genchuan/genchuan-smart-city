package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.assetextend;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo.AssetExtendPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.assetextend.AssetExtendDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产扩展 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface AssetExtendMapper extends BaseMapperX<AssetExtendDO> {

    default PageResult<AssetExtendDO> selectPage(AssetExtendPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetExtendDO>()
                .eqIfPresent(AssetExtendDO::getAssetType, reqVO.getAssetType())
                .likeIfPresent(AssetExtendDO::getAssetName, reqVO.getAssetName())
                .eqIfPresent(AssetExtendDO::getAssetCode, reqVO.getAssetCode())
                .eqIfPresent(AssetExtendDO::getAssetStatus, reqVO.getAssetStatus())
                .eqIfPresent(AssetExtendDO::getRegionCode, reqVO.getRegionCode())
                .eqIfPresent(AssetExtendDO::getAddress, reqVO.getAddress())
                .eqIfPresent(AssetExtendDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(AssetExtendDO::getLatitude, reqVO.getLatitude())
                .betweenIfPresent(AssetExtendDO::getAssetCreateTime, reqVO.getAssetCreateTime())
                .betweenIfPresent(AssetExtendDO::getAssetUpdateTime, reqVO.getAssetUpdateTime())
                .eqIfPresent(AssetExtendDO::getAssetRemark, reqVO.getAssetRemark())
                .betweenIfPresent(AssetExtendDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetExtendDO::getId));
    }

}