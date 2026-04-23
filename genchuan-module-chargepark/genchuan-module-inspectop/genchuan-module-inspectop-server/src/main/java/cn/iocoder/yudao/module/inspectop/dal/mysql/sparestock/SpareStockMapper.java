package cn.iocoder.yudao.module.inspectop.dal.mysql.sparestock;

import java.util.*;
import org.apache.ibatis.annotations.Param;
import cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo.SpareStockChartRespVO;
import java.time.LocalDateTime;
import java.util.List;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.sparestock.SpareStockDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.sparestock.vo.*;

/**
 * 备件仓储 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface SpareStockMapper extends BaseMapperX<SpareStockDO> {

    default PageResult<SpareStockDO> selectPage(SpareStockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SpareStockDO>()
                .eqIfPresent(SpareStockDO::getSpareId, reqVO.getSpareId())
                .likeIfPresent(SpareStockDO::getSpareName, reqVO.getSpareName())
                .eqIfPresent(SpareStockDO::getCurrentStock, reqVO.getCurrentStock())
                .eqIfPresent(SpareStockDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SpareStockDO::getInTime, reqVO.getInTime())
                .betweenIfPresent(SpareStockDO::getOutTime, reqVO.getOutTime())
                .eqIfPresent(SpareStockDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(SpareStockDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(SpareStockDO::getCreator, reqVO.getCreator())
                .eqIfPresent(SpareStockDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(SpareStockDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(SpareStockDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(SpareStockDO::getId));
    }

    default SpareStockDO selectBySpareId(Long spareId) {
        return selectOne(SpareStockDO::getSpareId, spareId);
    }


    /**
     * 查询库存趋势数据
     *
     * @param timeRange 时间范围
     * @return 库存趋势列表
     */
    List<SpareStockChartRespVO.TrendData> selectTrendData(@Param("timeRange") LocalDateTime[] timeRange);

    /**
     * 查询备件库存分布数据
     *
     * @param timeRange 时间范围
     * @return 库存分布列表
     */
    List<SpareStockChartRespVO.StockData> selectStockData(@Param("timeRange") LocalDateTime[] timeRange);

    /**
     * 查询卡片统计数据
     *
     * @param timeRange 时间范围
     * @return 卡片统计数据
     */
    SpareStockChartRespVO.CardData selectCardData(@Param("timeRange") LocalDateTime[] timeRange);

}