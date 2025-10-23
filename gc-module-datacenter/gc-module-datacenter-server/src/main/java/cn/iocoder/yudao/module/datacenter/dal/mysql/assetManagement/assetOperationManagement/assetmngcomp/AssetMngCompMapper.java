package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetmngcomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetmngcomp.vo.AssetMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetmngcomp.AssetMngCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产关联管理部件 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetMngCompMapper extends BaseMapperX<AssetMngCompDO> {

    default PageResult<AssetMngCompDO> selectPage(AssetMngCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetMngCompDO>()
                .eqIfPresent(AssetMngCompDO::getAssetRelMngCompId, reqVO.getAssetRelMngCompId())
                .eqIfPresent(AssetMngCompDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetMngCompDO::getRelAssetName, reqVO.getRelAssetName())
                .eqIfPresent(AssetMngCompDO::getMngCompId, reqVO.getMngCompId())
                .eqIfPresent(AssetMngCompDO::getMngCompCode, reqVO.getMngCompCode())
                .likeIfPresent(AssetMngCompDO::getMngCompName, reqVO.getMngCompName())
                .betweenIfPresent(AssetMngCompDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(AssetMngCompDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetMngCompDO::getRelDesc, reqVO.getRelDesc())
                .eqIfPresent(AssetMngCompDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetMngCompDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetMngCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetMngCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetMngCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetMngCompDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetMngCompDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetMngCompDO::getId));
    }

}