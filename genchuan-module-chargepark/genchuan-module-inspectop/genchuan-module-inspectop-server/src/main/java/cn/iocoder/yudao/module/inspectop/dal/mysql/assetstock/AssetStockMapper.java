package cn.iocoder.yudao.module.inspectop.dal.mysql.assetstock;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.assetstock.AssetStockDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.assetstock.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 【新增方法】关联查询分页方法
     * 使用自定义SQL进行关联查询，返回包含场站名称和资产名称的结果
     *
     * @param page  MyBatis-Plus分页参数
     * @param reqVO 查询条件
     * @return 包含关联信息的分页结果
     */
    Page<AssetStockRespVO> selectPageWithJoin(@Param("page") Page<AssetStockRespVO> page,
                                              @Param("reqVO") AssetStockPageReqVO reqVO);


    /**
     * 查询库存趋势数据
     *
     * @param timeRange 时间范围
     * @return 库存趋势列表
     */
    List<AssetStockChartRespVO.TrendData> selectTrendData(@Param("timeRange") LocalDateTime[] timeRange);

    /**
     * 查询资产库存分布数据
     *
     * @param timeRange 时间范围
     * @return 库存分布列表
     */
    List<AssetStockChartRespVO.StockData> selectStockData(@Param("timeRange") LocalDateTime[] timeRange);

    /**
     * 查询卡片统计数据
     *
     * @param timeRange 时间范围
     * @return 卡片统计数据
     */
    AssetStockChartRespVO.CardData selectCardData(@Param("timeRange") LocalDateTime[] timeRange);

    /**
     * 根据ID查询库存（包含关联信息）
     *
     * @param id 库存ID
     * @return 包含关联信息的库存数据
     */
    AssetStockRespVO selectOneWithJoin(@Param("id") Long id);
}