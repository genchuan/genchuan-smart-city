package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.decisionanalysis;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.CycleReportDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Mapper
public interface CycleReportMapper extends BaseMapperX<CycleReportDO> {

    default PageResult<CycleReportDO> selectPage(CycleReportPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CycleReportDO>()
                .eqIfPresent(CycleReportDO::getReportCycle, reqVO.getReportCycle())
                .geIfPresent(CycleReportDO::getStatStartTime, reqVO.getStatStartTime())
                .leIfPresent(CycleReportDO::getStatEndTime, reqVO.getStatEndTime())
                .eqIfPresent(CycleReportDO::getGenerateStatus, reqVO.getGenerateStatus())
                .eqIfPresent(CycleReportDO::getTenantId, reqVO.getTenantId())
                .orderByDesc(CycleReportDO::getId));
    }

    // ========== 基于 13 张业务表的聚合查询 ==========

    @Select("SELECT COUNT(*) FROM activity_config WHERE deleted = 0 " +
            "AND start_time >= #{startTime} AND end_time <= #{endTime} AND tenant_id = #{tenantId}")
    Integer selectActivityCount(@Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime,
                                @Param("tenantId") Long tenantId);

    @Select("SELECT COUNT(DISTINCT user_id) FROM point_lottery WHERE deleted = 0 " +
            "AND create_time >= #{startTime} AND create_time <= #{endTime} AND tenant_id = #{tenantId}")
    Integer selectJoinUserCount(@Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime,
                                @Param("tenantId") Long tenantId);

    @Select("SELECT COUNT(*) FROM point_lottery WHERE deleted = 0 " +
            "AND create_time >= #{startTime} AND create_time <= #{endTime} AND tenant_id = #{tenantId}")
    Integer selectLotteryCount(@Param("startTime") LocalDateTime startTime,
                               @Param("endTime") LocalDateTime endTime,
                               @Param("tenantId") Long tenantId);

    @Select("SELECT IFNULL(COUNT(*) FILTER (WHERE is_win = 1), 0) FROM point_lottery WHERE deleted = 0 " +
            "AND create_time >= #{startTime} AND create_time <= #{endTime} AND tenant_id = #{tenantId}")
    Integer selectWinCount(@Param("startTime") LocalDateTime startTime,
                           @Param("endTime") LocalDateTime endTime,
                           @Param("tenantId") Long tenantId);

    @Select("SELECT COUNT(*) FROM receive_record WHERE deleted = 0 " +
            "AND receive_time >= #{startTime} AND receive_time <= #{endTime} AND tenant_id = #{tenantId}")
    Integer selectCouponSendCount(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime,
                                  @Param("tenantId") Long tenantId);

    @Select("SELECT COUNT(*) FROM receive_record WHERE deleted = 0 AND verify_time IS NOT NULL " +
            "AND receive_time >= #{startTime} AND receive_time <= #{endTime} AND tenant_id = #{tenantId}")
    Integer selectCouponVerifyCount(@Param("startTime") LocalDateTime startTime,
                                    @Param("endTime") LocalDateTime endTime,
                                    @Param("tenantId") Long tenantId);

    @Select("SELECT COUNT(*) FROM card_order WHERE deleted = 0 " +
            "AND pay_status IN ('已支付','已完成') " +
            "AND pay_time >= #{startTime} AND pay_time <= #{endTime} AND tenant_id = #{tenantId}")
    Integer selectCardOrderCount(@Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime,
                                 @Param("tenantId") Long tenantId);

    @Select("SELECT IFNULL(SUM(pay_amount), 0) FROM card_order WHERE deleted = 0 " +
            "AND pay_status IN ('已支付','已完成') " +
            "AND pay_time >= #{startTime} AND pay_time <= #{endTime} AND tenant_id = #{tenantId}")
    BigDecimal selectRevenue(@Param("startTime") LocalDateTime startTime,
                             @Param("endTime") LocalDateTime endTime,
                             @Param("tenantId") Long tenantId);

    @Select("SELECT COUNT(*) FROM exchange_order WHERE deleted = 0 " +
            "AND create_time >= #{startTime} AND create_time <= #{endTime} AND tenant_id = #{tenantId}")
    Integer selectExchangeCount(@Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime,
                                @Param("tenantId") Long tenantId);

    @Select("SELECT IFNULL(SUM(stock), 0) FROM stock_control WHERE deleted = 0 AND tenant_id = #{tenantId}")
    Integer selectTotalStock(@Param("tenantId") Long tenantId);

    @Select("SELECT COUNT(*) FROM stock_control WHERE deleted = 0 AND warn_status = '预警' AND tenant_id = #{tenantId}")
    Integer selectWarnStockCount(@Param("tenantId") Long tenantId);

}
