package cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.TempParkOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.TempParkOrderDO;
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
 * TempParkOrder Mapper
 * @author genchuan
 */
@Mapper
public interface TempParkOrderMapper extends BaseMapperX<TempParkOrderDO> {

    IPage<TempParkOrderDO> selectPageJoinStation(IPage<TempParkOrderDO> page, @Param("req") TempParkOrderPageReqVO reqVO);

    @Select("SELECT o.*, s.name AS station_name FROM temp_park_order o LEFT JOIN station_info s ON o.station_id = s.id AND s.deleted = 0 WHERE o.id = #{id} AND o.deleted = 0")
    TempParkOrderDO selectByIdJoinStation(@Param("id") Long id);

    default PageResult<TempParkOrderDO> selectPage(TempParkOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TempParkOrderDO>()
                .likeIfPresent(TempParkOrderDO::getOrderNo, reqVO.getOrderNo())
                .likeIfPresent(TempParkOrderDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(TempParkOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TempParkOrderDO::getStationId, reqVO.getStationId())
                .geIfPresent(TempParkOrderDO::getCreateOrderTime, reqVO.getCreateOrderTimeStart())
                .leIfPresent(TempParkOrderDO::getCreateOrderTime, reqVO.getCreateOrderTimeEnd())
                .orderByDesc(TempParkOrderDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM temp_park_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT status, COUNT(*) AS count FROM temp_park_order WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM temp_park_order WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT IFNULL(SUM(amount), 0) FROM temp_park_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') " +
            "<if test='startTime != null'> AND pay_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND pay_time &lt;= #{endTime}   </if>" +
            "</script>")
    BigDecimal selectTodayRevenue(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM temp_park_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayPaidCount(@Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime);

}
