package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetasset;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetasset.vo.AssetAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetasset.AssetAssetDO;

import org.apache.ibatis.annotations.Mapper;



/**
 * 资产关联资产 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetAssetMapper extends BaseMapperX<AssetAssetDO> {

    default PageResult<AssetAssetDO> selectPage(AssetAssetPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetAssetDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("relTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetAssetDO::getRelTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetAssetDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetAssetDO>()
                .eqIfPresent(AssetAssetDO::getAssetRelAssetId, reqVO.getAssetRelAssetId())
                .likeIfPresent(AssetAssetDO::getSlaveAssetId, reqVO.getSlaveAssetId())
                .likeIfPresent(AssetAssetDO::getSlaveAssetName, reqVO.getSlaveAssetName())
                .likeIfPresent(AssetAssetDO::getMasterAssetId, reqVO.getMasterAssetId())
                .likeIfPresent(AssetAssetDO::getMasterAssetName, reqVO.getMasterAssetName())
                .eqIfPresent(AssetAssetDO::getRelType, reqVO.getRelType())
                .betweenIfPresent(AssetAssetDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(AssetAssetDO::getOperUser, reqVO.getOperUser())
                .eqIfPresent(AssetAssetDO::getRelDesc, reqVO.getRelDesc())
                .eqIfPresent(AssetAssetDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(AssetAssetDO::getUpdatedTime, reqVO.getUpdatedTime())
                .eqIfPresent(AssetAssetDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetAssetDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetAssetDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetAssetDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetAssetDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetAssetDO::getId));
    }

}