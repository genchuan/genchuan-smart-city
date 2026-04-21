package cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.BikeChargeOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.BikeChargeOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * BikeChargeOrder Mapper
 * @author genchuan
 */
@Mapper
public interface BikeChargeOrderMapper extends BaseMapperX<BikeChargeOrderDO> {

    default PageResult<BikeChargeOrderDO> selectPage(BikeChargeOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BikeChargeOrderDO>()
                .likeIfPresent(BikeChargeOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(BikeChargeOrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(BikeChargeOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(BikeChargeOrderDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(BikeChargeOrderDO::getCreateOrderTime, reqVO.getCreateOrderTime())
                .orderByDesc(BikeChargeOrderDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM bike_charge_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT status, COUNT(*) AS count FROM bike_charge_order WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM bike_charge_order WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT IFNULL(SUM(amount), 0) FROM bike_charge_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') " +
            "<if test='startTime != null'> AND pay_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND pay_time &lt;= #{endTime}   </if>" +
            "</script>")
    BigDecimal selectTodayRevenue(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM bike_charge_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayPaidCount(@Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT IFNULL(SUM(charge_quantity), 0) FROM bike_charge_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "</script>")
    BigDecimal selectTodayChargeQuantity(@Param("startTime") LocalDateTime startTime,
                                         @Param("endTime") LocalDateTime endTime);

}
