package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetOperationManagement.assetcatmng;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetOperationManagement.assetcatmng.vo.AssetCatMngPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetOperationManagement.assetcatmng.AssetCatMngDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产分类管理 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetCatMngMapper extends BaseMapperX<AssetCatMngDO> {

    default PageResult<AssetCatMngDO> selectPage(AssetCatMngPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetCatMngDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("createdTime".equals(reqVO.getOrderByColumn())) {
            // 创建时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetCatMngDO::getCreatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("updatedTime".equals(reqVO.getOrderByColumn())) {
            // 重新时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetCatMngDO::getUpdatedTime);
            return selectPage(reqVO, null, queryWrapper);
        }
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetCatMngDO>()
                .eqIfPresent(AssetCatMngDO::getAssetCatId, reqVO.getAssetCatId())
                .eqIfPresent(AssetCatMngDO::getRelCatRuleId, reqVO.getRelCatRuleId())
                .likeIfPresent(AssetCatMngDO::getAssetCatCode, reqVO.getAssetCatCode())
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

}