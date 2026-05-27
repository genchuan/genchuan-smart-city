package cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.AbnormalOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AbnormalOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * AbnormalOrder Mapper
 * @author genchuan
 */
@Mapper
public interface AbnormalOrderMapper extends BaseMapperX<AbnormalOrderDO> {

    IPage<AbnormalOrderDO> selectPageJoinStation(IPage<AbnormalOrderDO> page, @Param("req") AbnormalOrderPageReqVO reqVO);

    @Select("SELECT o.*, s.name AS stationName FROM abnormal_order o LEFT JOIN station_info s ON o.station_id = s.id AND s.deleted = 0 WHERE o.id = #{id} AND o.deleted = 0")
    AbnormalOrderDO selectByIdJoinStation(@Param("id") Long id);

    default PageResult<AbnormalOrderDO> selectPage(AbnormalOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AbnormalOrderDO>()
                .eqIfPresent(AbnormalOrderDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(AbnormalOrderDO::getOrderType, reqVO.getOrderType())
                .eqIfPresent(AbnormalOrderDO::getAbnormalType, reqVO.getAbnormalType())
                .eqIfPresent(AbnormalOrderDO::getStatus, reqVO.getStatus())
                .geIfPresent(AbnormalOrderDO::getIdentifyTime, reqVO.getIdentifyTimeStart())
                .leIfPresent(AbnormalOrderDO::getIdentifyTime, reqVO.getIdentifyTimeEnd())
                .orderByDesc(AbnormalOrderDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(identify_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM abnormal_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND identify_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND identify_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(identify_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT abnormal_type AS type, COUNT(*) AS count FROM abnormal_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND identify_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND identify_time &lt;= #{endTime}   </if>" +
            "GROUP BY abnormal_type" +
            "</script>")
    List<Map<String, Object>> selectGroupByType(@Param("startTime") LocalDateTime startTime,
                                                @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM abnormal_order WHERE deleted = 0 AND identify_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT COUNT(*) FROM abnormal_order WHERE deleted = 0 " +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</script>")
    Long selectCountByStatus(@Param("status") String status);

}
