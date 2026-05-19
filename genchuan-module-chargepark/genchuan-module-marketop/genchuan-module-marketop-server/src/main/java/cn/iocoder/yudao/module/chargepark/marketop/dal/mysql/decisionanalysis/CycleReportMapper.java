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
import java.util.List;
import java.util.Map;

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

    // ========== 基于 13 张业务表的聚合查询（时间参数为空则统计全部） ==========

    @Select("<script>" +
            "SELECT COUNT(*) FROM point_activity WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'> AND create_time &lt;= #{endTime} </if>" +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    Integer selectActivityCount(@Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime,
                                @Param("tenantId") Long tenantId);

    @Select("<script>" +
            "SELECT COUNT(DISTINCT user_id) FROM point_lottery WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'> AND create_time &lt;= #{endTime} </if>" +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    Integer selectJoinUserCount(@Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime,
                                @Param("tenantId") Long tenantId);

    @Select("<script>" +
            "SELECT COUNT(*) FROM point_lottery WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'> AND create_time &lt;= #{endTime} </if>" +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    Integer selectLotteryCount(@Param("startTime") LocalDateTime startTime,
                               @Param("endTime") LocalDateTime endTime,
                               @Param("tenantId") Long tenantId);

    @Select("<script>" +
            "SELECT COUNT(*) FROM point_lottery WHERE deleted = 0 AND prize_id > 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'> AND create_time &lt;= #{endTime} </if>" +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    Integer selectWinCount(@Param("startTime") LocalDateTime startTime,
                           @Param("endTime") LocalDateTime endTime,
                           @Param("tenantId") Long tenantId);

//    @Select("<script>" +
//            "SELECT COUNT(*) FROM receive_record WHERE deleted = 0 " +
//            "<if test='startTime != null'> AND receive_time &gt;= #{startTime} </if>" +
//            "<if test='endTime != null'> AND receive_time &lt;= #{endTime} </if>" +
//            "AND tenant_id = #{tenantId}" +
//            "</script>")
//    Integer selectCouponSendCount(@Param("startTime") LocalDateTime startTime,
//                                  @Param("endTime") LocalDateTime endTime,
//                                  @Param("tenantId") Long tenantId);

    @Select("<script>" +
            "SELECT COUNT(*) FROM coupon_mgmt WHERE deleted = 0 " +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    Integer selectCouponSendCount(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime,
                                  @Param("tenantId") Long tenantId);

    @Select("<script>" +
            "SELECT COUNT(*) FROM receive_record WHERE deleted = 0 AND verify_time IS NOT NULL " +
            "<if test='startTime != null'> AND receive_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'> AND receive_time &lt;= #{endTime} </if>" +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    Integer selectCouponVerifyCount(@Param("startTime") LocalDateTime startTime,
                                    @Param("endTime") LocalDateTime endTime,
                                    @Param("tenantId") Long tenantId);

    @Select("<script>" +
            "SELECT COUNT(*) FROM card_order WHERE deleted = 0 " +
           // "AND pay_status IN ('已支付','已完成') " +
            "<if test='startTime != null'> AND pay_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'> AND pay_time &lt;= #{endTime} </if>" +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    Integer selectCardOrderCount(@Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime,
                                 @Param("tenantId") Long tenantId);

    @Select("<script>" +
            "SELECT IFNULL(SUM(amount), 0) FROM card_order WHERE deleted = 0 " +
            //"AND pay_status IN ('已支付','已完成') " +
            "<if test='startTime != null'> AND pay_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'> AND pay_time &lt;= #{endTime} </if>" +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    BigDecimal selectRevenue(@Param("startTime") LocalDateTime startTime,
                             @Param("endTime") LocalDateTime endTime,
                             @Param("tenantId") Long tenantId);

    @Select("<script>" +
            "SELECT COUNT(*) FROM exchange_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'> AND create_time &lt;= #{endTime} </if>" +
            "AND tenant_id = #{tenantId}" +
            "</script>")
    Integer selectExchangeCount(@Param("startTime") LocalDateTime startTime,
                                @Param("endTime") LocalDateTime endTime,
                                @Param("tenantId") Long tenantId);

    @Select("SELECT IFNULL(SUM(current_stock), 0) FROM stock_control WHERE deleted = 0 AND tenant_id = #{tenantId}")
    Integer selectTotalStock(@Param("tenantId") Long tenantId);

    @Select("SELECT COUNT(*) FROM stock_control WHERE deleted = 0 AND warn_status = '1' AND tenant_id = #{tenantId}")
    Integer selectWarnStockCount(@Param("tenantId") Long tenantId);

    // ========== 图表扩展查询 ==========

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM point_activity WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) ORDER BY DATE(create_time) ASC")
    List<java.util.Map<String, Object>> selectPointActivityCountByDay(@Param("startTime") LocalDateTime startTime);

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM point_lottery WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) ORDER BY DATE(create_time) ASC")
    List<java.util.Map<String, Object>> selectPointLotteryCountByDay(@Param("startTime") LocalDateTime startTime);

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM receive_record WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) ORDER BY DATE(create_time) ASC")
    List<java.util.Map<String, Object>> selectReceiveRecordCountByDay(@Param("startTime") LocalDateTime startTime);

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM card_order WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) ORDER BY DATE(create_time) ASC")
    List<java.util.Map<String, Object>> selectCardOrderCountByDay(@Param("startTime") LocalDateTime startTime);

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM exchange_order WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) ORDER BY DATE(create_time) ASC")
    List<java.util.Map<String, Object>> selectExchangeOrderCountByDay(@Param("startTime") LocalDateTime startTime);

    @Select("SELECT DATE(create_time) AS date, COUNT(*) AS count " +
            "FROM stock_control WHERE deleted = 0 AND create_time >= #{startTime} " +
            "GROUP BY DATE(create_time) ORDER BY DATE(create_time) ASC")
    List<java.util.Map<String, Object>> selectStockControlCountByDay(@Param("startTime") LocalDateTime startTime);

    // ========== barData 按type分类统计 ==========

    @Select("SELECT type, COUNT(*) AS count FROM activity_config WHERE deleted = 0 GROUP BY type")
    List<java.util.Map<String, Object>> selectActivityConfigTypeCount();

    @Select("SELECT type, COUNT(*) AS count FROM point_activity WHERE deleted = 0 GROUP BY type")
    List<java.util.Map<String, Object>> selectPointActivityTypeCount();

    @Select("SELECT type, COUNT(*) AS count FROM coupon_mgmt WHERE deleted = 0 GROUP BY type")
    List<java.util.Map<String, Object>> selectCouponMgmtTypeCount();

    @Select("SELECT type, COUNT(*) AS count FROM prize_mgmt WHERE deleted = 0 GROUP BY type")
    List<java.util.Map<String, Object>> selectPrizeMgmtTypeCount();

    @Select("SELECT type, COUNT(*) AS count FROM card_config WHERE deleted = 0 GROUP BY type")
    List<java.util.Map<String, Object>> selectCardConfigTypeCount();

    @Select("SELECT eo.category_id AS categoryId, ec.name AS name, COUNT(*) AS count " +
            "FROM exchange_order eo LEFT JOIN exchange_category ec ON eo.category_id = ec.id " +
            "WHERE eo.deleted = 0 GROUP BY eo.category_id, ec.name")
    List<java.util.Map<String, Object>> selectExchangeCategoryTypeCount();

    // ========== pieData 按type统计占比 ==========

    @Select("SELECT type, COUNT(*) AS count FROM rule_config WHERE deleted = 0 GROUP BY type")
    List<java.util.Map<String, Object>> selectRuleConfigTypeCount();

    @Select("SELECT type, COUNT(*) AS count FROM package_config WHERE deleted = 0 GROUP BY type")
    List<java.util.Map<String, Object>> selectPackageConfigTypeCount();

    @Select("SELECT type, COUNT(*) AS count FROM activity_config WHERE deleted = 0 GROUP BY type")
    List<java.util.Map<String, Object>> selectActivityConfigTypeCountForPie();

}
