package cn.iocoder.yudao.module.inspectop.dal.mysql.assetstock;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetstock.AssetStockDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo.*;

/**
 * 库存管理 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface AssetStockMapper extends BaseMapperX<AssetStockDO> {

    default PageResult<AssetStockDO> selectPage(AssetStockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssetStockDO>()
                .eqIfPresent(AssetStockDO::getAssetId, reqVO.getAssetId())
                .eqIfPresent(AssetStockDO::getCurrentStock, reqVO.getCurrentStock())
                .eqIfPresent(AssetStockDO::getWarnThreshold, reqVO.getWarnThreshold())
                .eqIfPresent(AssetStockDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AssetStockDO::getStationId, reqVO.getStationId())
                .eqIfPresent(AssetStockDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AssetStockDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(AssetStockDO::getCreator, reqVO.getCreator())
                .eqIfPresent(AssetStockDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(AssetStockDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(AssetStockDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(AssetStockDO::getId));
    }

}