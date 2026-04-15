package cn.iocoder.yudao.module.ordertrade.dal.mysql.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.AbnormalOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AbnormalOrderDO;
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

    default PageResult<AbnormalOrderDO> selectPage(AbnormalOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AbnormalOrderDO>()
                .eqIfPresent(AbnormalOrderDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(AbnormalOrderDO::getOrderType, reqVO.getOrderType())
                .eqIfPresent(AbnormalOrderDO::getAbnormalType, reqVO.getAbnormalType())
                .eqIfPresent(AbnormalOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AbnormalOrderDO::getStationId, reqVO.getStationId())
                .betweenIfPresent(AbnormalOrderDO::getIdentifyTime, reqVO.getIdentifyTime())
                .orderByDesc(AbnormalOrderDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM abnormal_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT status, COUNT(*) AS count FROM abnormal_order WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM abnormal_order WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT COUNT(*) FROM abnormal_order WHERE deleted = 0 " +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</script>")
    Long selectCountByStatus(@Param("status") String status);

}
