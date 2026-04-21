package cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AgentOrderMapper extends BaseMapperX<AgentOrderDO> {

    default PageResult<AgentOrderDO> selectPage(AgentOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AgentOrderDO>()
                .likeIfPresent(AgentOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(AgentOrderDO::getMerchantId, reqVO.getMerchantId())
                .likeIfPresent(AgentOrderDO::getCarNo, reqVO.getCarNo())
                .eqIfPresent(AgentOrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AgentOrderDO::getPayType, reqVO.getPayType())
                .orderByDesc(AgentOrderDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM agent_order WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM agent_order WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT IFNULL(SUM(amount), 0) FROM agent_order WHERE deleted = 0 " +
            "AND status IN ('paid','completed') " +
            "<if test='startTime != null'> AND pay_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND pay_time &lt;= #{endTime}   </if>" +
            "</script>")
    BigDecimal selectTodayAmount(@Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime);
}
