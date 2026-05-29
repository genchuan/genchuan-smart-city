package cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentRulePageReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentRuleRespVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRuleDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AgentRuleMapper extends BaseMapperX<AgentRuleDO> {

    default AgentRuleDO selectByMerchantIdAndAgentType(Long merchantId, String agentType, Long excludeId) {
        return selectOne(new LambdaQueryWrapperX<AgentRuleDO>()
                .eq(AgentRuleDO::getMerchantId, merchantId)
                .eq(AgentRuleDO::getAgentType, agentType)
                .neIfPresent(AgentRuleDO::getId, excludeId));
    }

    @Select("<script>" +
            "SELECT ar.*, pm.name as merchantName " +
            "FROM agent_rule ar " +
            "LEFT JOIN merchant_info pm ON pm.id = ar.merchant_id AND pm.deleted = 0 " +
            "WHERE ar.deleted = 0 " +
            "<if test='req.name != null and req.name != \"\"'>AND ar.name LIKE CONCAT('%', #{req.name}, '%') </if>" +
            "<if test='req.merchantId != null'>AND ar.merchant_id = #{req.merchantId} </if>" +
            "<if test='req.merchantName != null and req.merchantName != \"\"'>AND pm.name LIKE CONCAT('%', #{req.merchantName}, '%') </if>" +
            "<if test='req.agentType != null and req.agentType != \"\"'>AND ar.agent_type = #{req.agentType} </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND ar.status = #{req.status} </if>" +
            "<if test='req.startTime != null'>AND ar.create_time &gt;= #{req.startTime} </if>" +
            "<if test='req.endTime != null'>AND ar.create_time &lt;= #{req.endTime} </if>" +
            "ORDER BY ar.id DESC" +
            "</script>")
    IPage<AgentRuleRespVO> selectPageWithMerchant(Page<AgentRuleRespVO> page, @Param("req") AgentRulePageReqVO reqVO);

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
