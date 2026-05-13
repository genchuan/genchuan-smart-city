package cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.OfftimeParkOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.OfftimeParkOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * OfftimeParkOrder Mapper
 * @author genchuan
 */
@Mapper
public interface OfftimeParkOrderMapper extends BaseMapperX<OfftimeParkOrderDO> {

    IPage<OfftimeParkOrderDO> selectPageJoinStation(IPage<OfftimeParkOrderDO> page, @Param("req") OfftimeParkOrderPageReqVO reqVO);

    @Select("SELECT o.*, s.name AS station_name FROM offtime_park_order o LEFT JOIN station_info s ON o.station_id = s.id AND s.deleted = 0 WHERE o.id = #{id} AND o.deleted = 0")
    OfftimeParkOrderDO selectByIdJoinStation(@Param("id") Long id);

    default PageResult<OfftimeParkOrderDO> selectPage(OfftimeParkOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OfftimeParkOrderDO>()
                .likeIfPresent(OfftimeParkOrderDO::getOrderNo, reqVO.getOrderNo())
                .likeIfPresent(OfftimeParkOrderDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(OfftimeParkOrderDO::getStatus, reqVO.getStatus())
                .geIfPresent(OfftimeParkOrderDO::getCreateOrderTime, reqVO.getCreateOrderTimeStart())
                .leIfPresent(OfftimeParkOrderDO::getCreateOrderTime, reqVO.getCreateOrderTimeEnd())
                .orderByDesc(OfftimeParkOrderDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_order_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM offtime_park_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_order_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_order_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_order_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT IFNULL(s.name, CONCAT('场站', o.station_id)) AS station, COUNT(*) AS count " +
            "FROM offtime_park_order o " +
            "LEFT JOIN station_info s ON o.station_id = s.id AND s.deleted = 0 " +
            "WHERE o.deleted = 0 " +
            "<if test='startTime != null'> AND o.create_order_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND o.create_order_time &lt;= #{endTime}   </if>" +
            "GROUP BY o.station_id ORDER BY count DESC" +
            "</script>")
    List<Map<String, Object>> selectGroupByStation(@Param("startTime") LocalDateTime startTime,
                                                   @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM offtime_park_order WHERE deleted = 0 AND create_order_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT IFNULL(SUM(amount), 0) FROM offtime_park_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') " +
            "<if test='startTime != null'> AND pay_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND pay_time &lt;= #{endTime}   </if>" +
            "</script>")
    BigDecimal selectTodayRevenue(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM offtime_park_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') AND create_order_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayPaidCount(@Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime);

}
