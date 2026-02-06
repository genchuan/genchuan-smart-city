package cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.asset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo.AssetPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.asset.AssetDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产-thingsboard Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface AssetMapper extends BaseMapperX<AssetDO> {

    default PageResult<AssetDO> selectPage(AssetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetDO>()
                .eqIfPresent(AssetDO::getAssetCode, reqVO.getAssetCode())
                .likeIfPresent(AssetDO::getAssetName, reqVO.getAssetName())
                .eqIfPresent(AssetDO::getAssetType, reqVO.getAssetType())
                .eqIfPresent(AssetDO::getRegionCode, reqVO.getRegionCode())
                .eqIfPresent(AssetDO::getAssetStatus, reqVO.getAssetStatus())
                .betweenIfPresent(AssetDO::getEntryTime, reqVO.getEntryTime())
                .betweenIfPresent(AssetDO::getAssetCreateTime, reqVO.getAssetCreateTime())
                .betweenIfPresent(AssetDO::getAssetUpdateTime, reqVO.getAssetUpdateTime())
                .eqIfPresent(AssetDO::getAssetRemark, reqVO.getAssetRemark())
                .betweenIfPresent(AssetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetDO::getId));
    }

}
