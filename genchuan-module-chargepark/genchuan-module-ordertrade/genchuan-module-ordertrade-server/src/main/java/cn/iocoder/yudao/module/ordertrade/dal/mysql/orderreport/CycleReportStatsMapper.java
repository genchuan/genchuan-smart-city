package cn.iocoder.yudao.module.ordertrade.dal.mysql.orderreport;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 周期报表统计 Mapper
 * 无独立数据库表，所有查询均基于 all_order / abnormal_order / arrear_record 等现有表实时聚合。
 */
@Mapper
public interface CycleReportStatsMapper {

    // ─── 订单量 ───────────────────────────────────────────────────────────────

    @Select("SELECT COUNT(*) FROM all_order WHERE deleted = 0 " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end}")
    Integer selectOrderCount(@Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end);

    @Select("SELECT COUNT(*) FROM all_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end}")
    Integer selectPaidOrderCount(@Param("start") LocalDateTime start,
                                 @Param("end") LocalDateTime end);

    // ─── 营收 ─────────────────────────────────────────────────────────────────

    @Select("SELECT IFNULL(SUM(amount), 0) FROM all_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') " +
            "AND pay_time >= #{start} AND pay_time <= #{end}")
    BigDecimal selectRevenue(@Param("start") LocalDateTime start,
                             @Param("end") LocalDateTime end);

    // ─── 退款 ─────────────────────────────────────────────────────────────────

    @Select("SELECT IFNULL(SUM(amount), 0) FROM all_order WHERE deleted = 0 " +
            "AND status = 'refunding' " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end}")
    BigDecimal selectRefundAmount(@Param("start") LocalDateTime start,
                                  @Param("end") LocalDateTime end);

    // ─── 借出量（两轮/共享充电） ──────────────────────────────────────────────

    @Select("SELECT COUNT(*) FROM all_order WHERE deleted = 0 " +
            "AND order_type IN ('bike_charge','share_charge') " +
            "AND status IN ('paid','completed','lending') " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end}")
    Integer selectLendCount(@Param("start") LocalDateTime start,
                            @Param("end") LocalDateTime end);

    // ─── 待处置异常数（全局，不按时间过滤） ──────────────────────────────────

    @Select("SELECT COUNT(*) FROM abnormal_order WHERE deleted = 0 AND status = 'unhandled'")
    Integer selectWaitHandleAbnormalCount();

    // ─── 追缴完成率 ───────────────────────────────────────────────────────────

    @Select("SELECT " +
            "  CASE WHEN COUNT(*) = 0 THEN 0 " +
            "  ELSE ROUND(SUM(CASE WHEN status='completed' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) END " +
            "FROM collect_track WHERE deleted = 0 " +
            "AND create_time >= #{start} AND create_time <= #{end}")
    BigDecimal selectCollectCompleteRate(@Param("start") LocalDateTime start,
                                         @Param("end") LocalDateTime end);

    // ─── 核算准确率 ───────────────────────────────────────────────────────────

    @Select("SELECT " +
            "  CASE WHEN COUNT(*) = 0 THEN 0 " +
            "  ELSE ROUND(SUM(CASE WHEN check_result='pass' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) END " +
            "FROM amount_check WHERE deleted = 0 " +
            "AND create_time >= #{start} AND create_time <= #{end}")
    BigDecimal selectCheckAccuracyRate(@Param("start") LocalDateTime start,
                                       @Param("end") LocalDateTime end);

    // ─── 图表：折线图趋势 ─────────────────────────────────────────────────────

    @Select("SELECT DATE_FORMAT(create_order_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM all_order WHERE deleted = 0 " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end} " +
            "GROUP BY DATE_FORMAT(create_order_time,'%Y-%m-%d') ORDER BY date")
    List<Map<String, Object>> selectOrderCountTrend(@Param("start") LocalDateTime start,
                                                    @Param("end") LocalDateTime end);

    @Select("SELECT DATE_FORMAT(pay_time,'%Y-%m-%d') AS date, IFNULL(SUM(amount),0) AS revenue " +
            "FROM all_order WHERE deleted = 0 AND status IN ('paid','completed') " +
            "AND pay_time >= #{start} AND pay_time <= #{end} " +
            "GROUP BY DATE_FORMAT(pay_time,'%Y-%m-%d') ORDER BY date")
    List<Map<String, Object>> selectRevenueTrend(@Param("start") LocalDateTime start,
                                                 @Param("end") LocalDateTime end);

    @Select("SELECT DATE_FORMAT(create_order_time,'%Y-%m-%d') AS date, IFNULL(SUM(amount),0) AS refundAmount " +
            "FROM all_order WHERE deleted = 0 AND status = 'refunding' " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end} " +
            "GROUP BY DATE_FORMAT(create_order_time,'%Y-%m-%d') ORDER BY date")
    List<Map<String, Object>> selectRefundTrend(@Param("start") LocalDateTime start,
                                                @Param("end") LocalDateTime end);

    @Select("SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM abnormal_order WHERE deleted = 0 " +
            "AND create_time >= #{start} AND create_time <= #{end} " +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date")
    List<Map<String, Object>> selectAbnormalTrend(@Param("start") LocalDateTime start,
                                                  @Param("end") LocalDateTime end);

    // ─── 图表：柱状图 / 饼图分布 ─────────────────────────────────────────────

    @Select("SELECT order_type AS name, COUNT(*) AS value FROM all_order WHERE deleted = 0 " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end} " +
            "GROUP BY order_type")
    List<Map<String, Object>> selectOrderTypeDistribution(@Param("start") LocalDateTime start,
                                                          @Param("end") LocalDateTime end);

    @Select("SELECT station_id AS name, COUNT(*) AS value FROM all_order WHERE deleted = 0 " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end} " +
            "GROUP BY station_id ORDER BY value DESC")
    List<Map<String, Object>> selectStationDistribution(@Param("start") LocalDateTime start,
                                                        @Param("end") LocalDateTime end);

    @Select("SELECT status AS name, COUNT(*) AS value FROM all_order WHERE deleted = 0 " +
            "AND create_order_time >= #{start} AND create_order_time <= #{end} " +
            "GROUP BY status")
    List<Map<String, Object>> selectStatusDistribution(@Param("start") LocalDateTime start,
                                                       @Param("end") LocalDateTime end);

    @Select("SELECT pay_method AS name, COUNT(*) AS value FROM all_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') " +
            "AND pay_time >= #{start} AND pay_time <= #{end} " +
            "GROUP BY pay_method")
    List<Map<String, Object>> selectPayMethodDistribution(@Param("start") LocalDateTime start,
                                                          @Param("end") LocalDateTime end);

    @Select("SELECT abnormal_type AS name, COUNT(*) AS value FROM abnormal_order WHERE deleted = 0 " +
            "AND create_time >= #{start} AND create_time <= #{end} " +
            "GROUP BY abnormal_type")
    List<Map<String, Object>> selectAbnormalTypeDistribution(@Param("start") LocalDateTime start,
                                                             @Param("end") LocalDateTime end);
}
