package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetarea;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetarea.vo.AssetAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetarea.AssetAreaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产关联行政区划 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetAreaMapper extends BaseMapperX<AssetAreaDO> {

    default PageResult<AssetAreaDO> selectPage(AssetAreaPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetAreaDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("relTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetAreaDO::getRelTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 重新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetAreaDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }

        return selectPage(reqVO, new LambdaQueryWrapperX<AssetAreaDO>()
                .eqIfPresent(AssetAreaDO::getAssetRelRegionId, reqVO.getAssetRelRegionId())
                .likeIfPresent(AssetAreaDO::getRelAssetId, reqVO.getRelAssetId())
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

}