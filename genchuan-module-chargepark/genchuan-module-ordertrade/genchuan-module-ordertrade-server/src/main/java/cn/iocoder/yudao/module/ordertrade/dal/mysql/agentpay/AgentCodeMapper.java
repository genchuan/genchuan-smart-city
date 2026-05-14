package cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentCodePageReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentCodeRespVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentCodeDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AgentCodeMapper extends BaseMapperX<AgentCodeDO> {


    @Select("<script>" +
            "SELECT pm.name as merchant_name, agr.name as rule_name, ar.* " +
            "FROM agent_code ar " +
            "LEFT JOIN merchant_info pm ON pm.id = ar.merchant_id AND pm.deleted = 0 " +
            "LEFT JOIN agent_rule agr ON agr.id = ar.rule_id AND agr.deleted = 0 " +
            "WHERE ar.deleted = 0  " +
            "<if test='req.merchantName != null and req.merchantName != \"\"'>AND pm.name LIKE CONCAT('%', #{req.merchantName}, '%') </if>" +
            "<if test='req.ruleName != null and req.ruleName != \"\"'>AND agr.name LIKE CONCAT('%', #{req.ruleName}, '%') </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND ar.status = #{req.status} </if>" +
            "<if test='req.startTime != null'>AND ar.create_order_time &gt;= #{req.startTime} </if>" +
            "<if test='req.endTime != null'>AND ar.create_order_time &lt;= #{req.endTime} </if>" +
            "ORDER BY ar.id DESC " +
            "</script>")
    IPage<AgentCodeRespVO>  selectPageWithMerchant(Page<AgentCodeRespVO>page, @Param("req") AgentCodePageReqVO reqVO);

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM agent_code WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM agent_code WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectGeneratedCount(@Param("startTime") LocalDateTime startTime,
                              @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM agent_code WHERE deleted = 0 AND status = 'used'")
    Long selectUsedCount();

    @Select("SELECT COUNT(*) FROM agent_code WHERE deleted = 0")
    Long selectTotalCount();
}
