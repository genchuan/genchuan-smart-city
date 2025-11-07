package cn.iocoder.yudao.module.datacenter.dal.mysql.assetManagement.assetDataMng.assetdataqualck;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo.AssetDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.assetManagement.assetDataMng.assetdataqualck.AssetDataQualCkDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资产数据质量检查 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AssetDataQualCkMapper extends BaseMapperX<AssetDataQualCkDO> {

    default PageResult<AssetDataQualCkDO> selectPage(AssetDataQualCkPageReqVO reqVO) {
        // 构建完整的查询条件
        LambdaQueryWrapperX<AssetDataQualCkDO> queryWrapper = new LambdaQueryWrapperX<>();
        // 处理特殊排序逻辑
        if ("ckTime".equals(reqVO.getOrderByColumn())) {
            // 检测时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetDataQualCkDO::getCkTime);
            return selectPage(reqVO, null, queryWrapper);
        }else if ("handleTime".equals(reqVO.getOrderByColumn())) {
            // 处理时间排序
            queryWrapper.orderBy(true, "asc".equals(reqVO.getIsAsc()), AssetDataQualCkDO::getHandleTime);
            return selectPage(reqVO, null, queryWrapper);
        }
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetDataQualCkDO>()
                .eqIfPresent(AssetDataQualCkDO::getAssetDataQualId, reqVO.getAssetDataQualId())
                .likeIfPresent(AssetDataQualCkDO::getRelAssetId, reqVO.getRelAssetId())
                .likeIfPresent(AssetDataQualCkDO::getRelAssetName, reqVO.getRelAssetName())
                .eqIfPresent(AssetDataQualCkDO::getCkDataType, reqVO.getCkDataType())
                .likeIfPresent(AssetDataQualCkDO::getCkItemName, reqVO.getCkItemName())
                .eqIfPresent(AssetDataQualCkDO::getCkItemCode, reqVO.getCkItemCode())
                .eqIfPresent(AssetDataQualCkDO::getCkResult, reqVO.getCkResult())
                .eqIfPresent(AssetDataQualCkDO::getErrorDesc, reqVO.getErrorDesc())
                .betweenIfPresent(AssetDataQualCkDO::getCkTime, reqVO.getCkTime())
                .eqIfPresent(AssetDataQualCkDO::getCkMethod, reqVO.getCkMethod())
                .eqIfPresent(AssetDataQualCkDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(AssetDataQualCkDO::getHandleUser, reqVO.getHandleUser())
                .betweenIfPresent(AssetDataQualCkDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(AssetDataQualCkDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(AssetDataQualCkDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(AssetDataQualCkDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssetDataQualCkDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(AssetDataQualCkDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssetDataQualCkDO::getId));
    }

}