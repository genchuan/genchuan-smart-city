package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetdatamng;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo.AssetDataMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdatamng.AssetDataMngDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产数据管理 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetDataMngMapper extends BaseMapperX<AssetDataMngDO> {

    default PageResult<AssetDataMngDO> selectPage(AssetDataMngPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetDataMngDO>()
                .eqIfPresent(AssetDataMngDO::getAssetDataId, reqVO.getAssetDataId())
                .likeIfPresent(AssetDataMngDO::getRelAssetCatId, reqVO.getRelAssetCatId())
                .likeIfPresent(AssetDataMngDO::getRelAssetCatName, reqVO.getRelAssetCatName())
                .likeIfPresent(AssetDataMngDO::getAssetCode, reqVO.getAssetCode())
                .likeIfPresent(AssetDataMngDO::getAssetName, reqVO.getAssetName())
                .eqIfPresent(AssetDataMngDO::getAssetStatus, reqVO.getAssetStatus())
                .betweenIfPresent(AssetDataMngDO::getInstallTime, reqVO.getInstallTime())
                .eqIfPresent(AssetDataMngDO::getServiceLife, reqVO.getServiceLife())
                .eqIfPresent(AssetDataMngDO::getAssetManager, reqVO.getAssetManager())
                .eqIfPresent(AssetDataMngDO::getAssetManagerTel, reqVO.getAssetManagerTel())
                .eqIfPresent(AssetDataMngDO::getAssetDesc, reqVO.getAssetDesc())
                .eqIfPresent(AssetDataMngDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(AssetDataMngDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetDataMngDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetDataMngDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetDataMngDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetDataMngDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetDataMngDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetDataMngDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetDataMngDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetDataMngDO::getId));
    }

}