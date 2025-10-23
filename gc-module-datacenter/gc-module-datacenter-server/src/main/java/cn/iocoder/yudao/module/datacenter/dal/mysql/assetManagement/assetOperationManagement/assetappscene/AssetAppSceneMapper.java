package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetappscene;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetappscene.vo.AssetAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetappscene.AssetAppSceneDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产关联应用场景 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetAppSceneMapper extends BaseMapperX<AssetAppSceneDO> {

    default PageResult<AssetAppSceneDO> selectPage(AssetAppScenePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetAppSceneDO>()
                .eqIfPresent(AssetAppSceneDO::getAssetRelAppSceneId, reqVO.getAssetRelAppSceneId())
                .eqIfPresent(AssetAppSceneDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetAppSceneDO::getRelAssetName, reqVO.getRelAssetName())
                .eqIfPresent(AssetAppSceneDO::getAppSceneId, reqVO.getAppSceneId())
                .likeIfPresent(AssetAppSceneDO::getAppSceneCode, reqVO.getAppSceneCode())
                .likeIfPresent(AssetAppSceneDO::getAppSceneName, reqVO.getAppSceneName())
                .betweenIfPresent(AssetAppSceneDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(AssetAppSceneDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetAppSceneDO::getRelDesc, reqVO.getRelDesc())
                .eqIfPresent(AssetAppSceneDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetAppSceneDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetAppSceneDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetAppSceneDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetAppSceneDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetAppSceneDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetAppSceneDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetAppSceneDO::getId));
    }

}