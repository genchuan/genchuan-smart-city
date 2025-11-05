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
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetAppSceneDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("relTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetAppSceneDO::getRelTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 重新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetAppSceneDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }

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