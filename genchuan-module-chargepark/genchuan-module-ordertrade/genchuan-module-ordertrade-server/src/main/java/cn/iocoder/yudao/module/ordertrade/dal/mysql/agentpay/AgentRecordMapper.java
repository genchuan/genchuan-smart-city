package cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentRecordPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AgentRecordMapper extends BaseMapperX<AgentRecordDO> {

    default PageResult<AgentRecordDO> selectPage(AgentRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AgentRecordDO>()
                .likeIfPresent(AgentRecordDO::getRecordNo, reqVO.getRecordNo())
                .eqIfPresent(AgentRecordDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(AgentRecordDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(AgentRecordDO::getStatus, reqVO.getStatus())
                .orderByDesc(AgentRecordDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM agent_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM agent_record WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM agent_record WHERE deleted = 0 AND status = 'normal'")
    Long selectNormalCount();

    @Select("SELECT COUNT(*) FROM agent_record WHERE deleted = 0")
    Long selectTotalCount();
}
