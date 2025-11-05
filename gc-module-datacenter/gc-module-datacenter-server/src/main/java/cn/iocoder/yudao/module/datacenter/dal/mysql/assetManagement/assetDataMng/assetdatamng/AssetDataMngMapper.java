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

        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetDataMngDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("createdTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetDataMngDO::getCreatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 更新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetDataMngDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("installTime".equals(reqVO.getOrderByColumn())) {
            // 安装时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetDataMngDO::getInstallTime);
            return selectPage(reqVO, null, queryWrapper);
        }

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