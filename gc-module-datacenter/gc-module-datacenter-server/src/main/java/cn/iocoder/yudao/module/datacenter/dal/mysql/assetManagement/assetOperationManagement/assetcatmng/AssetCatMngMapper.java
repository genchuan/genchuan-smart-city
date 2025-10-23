package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetcatmng;

import java.util.*;

import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngListReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产分类管理 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetCatMngMapper extends BaseMapperX<AssetCatMngDO> {

    default List<AssetCatMngDO> selectList(AssetCatMngListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AssetCatMngDO>()
                .eqIfPresent(AssetCatMngDO::getParentId, reqVO.getParentId())
                .likeIfPresent(AssetCatMngDO::getName, reqVO.getName())
                .eqIfPresent(AssetCatMngDO::getAssetCatId, reqVO.getAssetCatId())
                .eqIfPresent(AssetCatMngDO::getRelCatRuleId, reqVO.getRelCatRuleId())
                .eqIfPresent(AssetCatMngDO::getAssetCatCode, reqVO.getAssetCatCode())
                .likeIfPresent(AssetCatMngDO::getAssetCatName, reqVO.getAssetCatName())
                .eqIfPresent(AssetCatMngDO::getCatLevel, reqVO.getCatLevel())
                .eqIfPresent(AssetCatMngDO::getParentCatId, reqVO.getParentCatId())
                .likeIfPresent(AssetCatMngDO::getParentCatName, reqVO.getParentCatName())
                .eqIfPresent(AssetCatMngDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(AssetCatMngDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(AssetCatMngDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(AssetCatMngDO::getCreatedTime, reqVO.getCreatedTime())
                .eqIfPresent(AssetCatMngDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetCatMngDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetCatMngDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetCatMngDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetCatMngDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetCatMngDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetCatMngDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetCatMngDO::getId));
    }

	default AssetCatMngDO selectByParentIdAndName(Long parentId, String name) {
	    return selectOne(AssetCatMngDO::getParentId, parentId, AssetCatMngDO::getName, name);
	}

    default Long selectCountByParentId(Long parentId) {
        return selectCount(AssetCatMngDO::getParentId, parentId);
    }

}