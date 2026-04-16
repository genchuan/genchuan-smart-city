package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderalarm;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderalarm.OrderAlarmDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 订单告警 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface OrderAlarmMapper extends BaseMapperX<OrderAlarmDO> {

    default PageResult<OrderAlarmDO> selectPage(OrderAlarmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderAlarmDO>()
                .eqIfPresent(OrderAlarmDO::getAlarmCode, reqVO.getAlarmCode())
                .eqIfPresent(OrderAlarmDO::getOrderCode, reqVO.getOrderCode())
                .eqIfPresent(OrderAlarmDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderAlarmDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(OrderAlarmDO::getAbnormalType, reqVO.getAbnormalType())
                .betweenIfPresent(OrderAlarmDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(OrderAlarmDO::getPileCode, reqVO.getPileCode())
                .eqIfPresent(OrderAlarmDO::getAlarmStatus, reqVO.getAlarmStatus())
                .eqIfPresent(OrderAlarmDO::getVerifyResult, reqVO.getVerifyResult())
                .eqIfPresent(OrderAlarmDO::getHandleMeasure, reqVO.getHandleMeasure())
                .betweenIfPresent(OrderAlarmDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(OrderAlarmDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderAlarmDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(OrderAlarmDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(OrderAlarmDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderAlarmDO::getId));
    }

    // ... (在 selectPage 方法后添加)

    /**
     * 获取订单告警图表统计数据
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 图表统计结果
     */
    @Select("<script>" +
            "SELECT " +
            "COUNT(*) as total_count, " +
            "SUM(CASE WHEN alarm_status IN ('2', '3') THEN 1 ELSE 0 END) as handled_count, " +
            "SUM(CASE WHEN alarm_status = '0' THEN 1 ELSE 0 END) as unverify_count, " +
            "SUM(CASE WHEN alarm_status = '1' THEN 1 ELSE 0 END) as verified_count, " +
            "SUM(CASE WHEN alarm_status = '2' THEN 1 ELSE 0 END) as handling_count, " +
            "SUM(CASE WHEN alarm_status = '3' THEN 1 ELSE 0 END) as completed_count " +
            "FROM order_alarm " +
            "WHERE deleted = 0 " +
            "<if test='startTime != null'>" +
            "   AND create_time >= #{startTime}" +
            "</if>" +
            "<if test='endTime != null'>" +
            "   AND create_time &lt;= #{endTime}" +
            "</if>" +
            "</script>")
    Map<String, Object> selectChartData(@Param("startTime") LocalDateTime startTime,
                                        @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT " +
            "DATE_FORMAT(create_time, '%Y-%m-%d') as date_str, " +
            "COUNT(*) as alarm_count, " +
            "SUM(CASE WHEN alarm_status IN ('2', '3') THEN 1 ELSE 0 END) as handle_count " +
            "FROM order_alarm " +
            "WHERE deleted = 0 " +
            "<if test='startTime != null'>" +
            "   AND create_time >= #{startTime}" +
            "</if>" +
            "<if test='endTime != null'>" +
            "   AND create_time &lt;= #{endTime}" +
            "</if>" +
            "GROUP BY date_str " +
            "ORDER BY date_str ASC" +
            "</script>")
    List<Map<String, Object>> selectLineChartData(@Param("startTime") LocalDateTime startTime,
                                                  @Param("endTime") LocalDateTime endTime);

    /**
     * 获取饼图数据（按异常类型分组）
     */
    @Select("<script>" +
            "SELECT " +
            "CASE abnormal_type " +
            "   WHEN '0' THEN '支付异常' " +
            "   WHEN '1' THEN '充电中断' " +
            "   WHEN '2' THEN '费率异常' " +
            "   ELSE '其他' END as name, " +
            "COUNT(*) as value " +
            "FROM order_alarm " +
            "WHERE deleted = 0 " +
            "<if test='startTime != null'>" +
            "   AND create_time >= #{startTime}" +
            "</if>" +
            "<if test='endTime != null'>" +
            "   AND create_time &lt;= #{endTime}" +
            "</if>" +
            "GROUP BY abnormal_type" +
            "</script>")
    List<Map<String, Object>> selectPieChartData(@Param("startTime") LocalDateTime startTime,
                                                 @Param("endTime") LocalDateTime endTime);

}