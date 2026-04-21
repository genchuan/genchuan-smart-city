package cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentRulePageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRuleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AgentRuleMapper extends BaseMapperX<AgentRuleDO> {

    default PageResult<AgentRuleDO> selectPage(AgentRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AgentRuleDO>()
                .likeIfPresent(AgentRuleDO::getName, reqVO.getName())
                .eqIfPresent(AgentRuleDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(AgentRuleDO::getAgentType, reqVO.getAgentType())
                .eqIfPresent(AgentRuleDO::getStatus, reqVO.getStatus())
                .orderByDesc(AgentRuleDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM agent_rule WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT agent_type, COUNT(*) AS count FROM agent_rule WHERE deleted = 0 GROUP BY agent_type")
    List<Map<String, Object>> selectGroupByAgentType();

    @Select("SELECT COUNT(*) FROM agent_rule WHERE deleted = 0 AND status = 'enabled'")
    Long selectEnabledCount();

    @Select("SELECT COUNT(*) FROM agent_rule WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);
}
