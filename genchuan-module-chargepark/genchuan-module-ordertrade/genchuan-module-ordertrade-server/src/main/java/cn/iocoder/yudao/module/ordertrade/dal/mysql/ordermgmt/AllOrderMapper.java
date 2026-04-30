package cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.AllOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AllOrderDO;
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
 * AllOrder Mapper
 * @author genchuan
 */
@Mapper
public interface AllOrderMapper extends BaseMapperX<AllOrderDO> {

    IPage<AllOrderDO> selectPageJoinStation(IPage<AllOrderDO> page, @Param("req") AllOrderPageReqVO reqVO);

    @Select("SELECT o.*, s.name AS station_name FROM all_order o LEFT JOIN station_info s ON o.station_id = s.id AND s.deleted = 0 WHERE o.id = #{id} AND o.deleted = 0")
    AllOrderDO selectByIdJoinStation(@Param("id") Long id);

    default PageResult<AllOrderDO> selectPage(AllOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AllOrderDO>()
                .likeIfPresent(AllOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(AllOrderDO::getOrderType, reqVO.getOrderType())
                .likeIfPresent(AllOrderDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(AllOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AllOrderDO::getStationId, reqVO.getStationId())
                .geIfPresent(AllOrderDO::getCreateOrderTime, reqVO.getCreateOrderTimeStart())
                .leIfPresent(AllOrderDO::getCreateOrderTime, reqVO.getCreateOrderTimeEnd())
                .geIfPresent(AllOrderDO::getPayTime, reqVO.getPayTimeStart())
                .leIfPresent(AllOrderDO::getPayTime, reqVO.getPayTimeEnd())
                .orderByDesc(AllOrderDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM all_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT status, COUNT(*) AS count FROM all_order WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM all_order WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT IFNULL(SUM(amount), 0) FROM all_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') " +
            "<if test='startTime != null'> AND pay_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND pay_time &lt;= #{endTime}   </if>" +
            "</script>")
    BigDecimal selectTodayRevenue(@Param("startTime") LocalDateTime startTime,
                                  @Param("endTime") LocalDateTime endTime);



    @Select("SELECT COUNT(*) FROM all_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayPaidCount(@Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime);

}
