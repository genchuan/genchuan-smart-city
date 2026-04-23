package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.decisionanalysis;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.MarketOpReportChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.marketopreport.vo.MarketOpReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.MarketOpReportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MarketOpReportMapper extends BaseMapperX<MarketOpReportDO> {

    default PageResult<MarketOpReportDO> selectPage(MarketOpReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MarketOpReportDO>()
                .eqIfPresent(MarketOpReportDO::getType, reqVO.getType())
                .betweenIfPresent(MarketOpReportDO::getStartTime, reqVO.getTimeRange())
                .orderByDesc(MarketOpReportDO::getId));
    }

    // ========== @Select 聚合查询 ==========

    @Select("SELECT IFNULL(SUM(join_count), 0) FROM point_activity " +
            "WHERE deleted = 0 AND start_time >= #{startTime} AND end_time <= #{endTime}")
    Integer selectActivityJoinCount(@Param("startTime") LocalDateTime startTime,
                                    @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM receive_record " +
            "WHERE deleted = 0 AND verify_time IS NOT NULL " +
            "AND receive_time >= #{startTime} AND receive_time <= #{endTime}")
    Integer selectCouponVerifiedCount(@Param("startTime") LocalDateTime startTime,
                                       @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM receive_record " +
            "WHERE deleted = 0 AND receive_time >= #{startTime} AND receive_time <= #{endTime}")
    Integer selectCouponReceivedCount(@Param("startTime") LocalDateTime startTime,
                                       @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM card_order " +
            "WHERE deleted = 0 AND pay_status IN ('已支付','已完成') " +
            "AND pay_time >= #{startTime} AND pay_time <= #{endTime}")
    Integer selectCardSaleCount(@Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM point_activity " +
            "WHERE deleted = 0 AND start_time >= #{startTime} AND end_time <= #{endTime}")
    Integer selectActivityTotalCount(@Param("startTime") LocalDateTime startTime,
                                      @Param("endTime") LocalDateTime endTime);

    // ========== XML 聚合查询 ==========

    List<MarketOpReportChartRespVO.TrendItem> selectTrendList(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("timeScale") String timeScale);

    List<MarketOpReportChartRespVO.EffectItem> selectEffectList(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime);

}
