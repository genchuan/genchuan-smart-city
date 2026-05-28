package cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentRecordPageReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentRecordRespVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentRulePageReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentRuleRespVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRecordDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AgentRecordMapper extends BaseMapperX<AgentRecordDO> {

   /* default PageResult<AgentRecordDO> selectPage(AgentRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AgentRecordDO>()
                .likeIfPresent(AgentRecordDO::getRecordNo, reqVO.getRecordNo())
                .eqIfPresent(AgentRecordDO::getMerchantId, reqVO.getMerchantId())
                .eqIfPresent(AgentRecordDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(AgentRecordDO::getStatus, reqVO.getStatus())
                .orderByDesc(AgentRecordDO::getId));
    }*/

    @Select("<script>" +
            "SELECT ar.*, pm.name AS merchantName, ao.order_no AS orderNo " +
            "FROM agent_record ar " +
            "LEFT JOIN merchant_info pm ON pm.id = ar.merchant_id AND pm.deleted = 0 " +
            "LEFT JOIN all_order ao ON ao.id = ar.order_id AND ao.deleted = 0 " +
            "WHERE ar.deleted = 0 " +
            "<if test='req.orderNo != null and req.orderNo != \"\"'>AND ao.order_no LIKE CONCAT('%', #{req.orderNo}, '%') </if>" +
            "<if test='req.merchantId != null'>AND ar.merchant_id = #{req.merchantId} </if>" +
            "<if test='req.merchantName != null and req.merchantName != \"\"'>AND pm.name LIKE CONCAT('%', #{req.merchantName}, '%') </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND ar.status = #{req.status} </if>" +
            "<if test='req.startTime != null'>AND ar.create_time &gt;= #{req.startTime} </if>" +
            "<if test='req.endTime != null'>AND ar.create_time &lt;= #{req.endTime} </if>" +
            "ORDER BY ar.id DESC" +
            "</script>")
    IPage<AgentRecordDO> selectPageWithDetails(Page<AgentRecordDO> page, @Param("req") AgentRecordPageReqVO reqVO);

    @Select("SELECT ar.*, pm.name AS merchantName, ao.order_no AS orderNo " +
            "FROM agent_record ar " +
            "LEFT JOIN merchant_info pm ON pm.id = ar.merchant_id AND pm.deleted = 0 " +
            "LEFT JOIN all_order ao ON ao.id = ar.order_id AND ao.deleted = 0 " +
            "WHERE ar.id = #{id} AND ar.deleted = 0")
    AgentRecordDO selectByIdWithDetails(@Param("id") Long id);


    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM agent_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT COUNT(*) FROM agent_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "</script>")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT COUNT(*) FROM agent_record WHERE deleted = 0 AND status = 'normal' " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "</script>")
    Long selectNormalCount(@Param("startTime") LocalDateTime startTime,
                           @Param("endTime") LocalDateTime endTime);

    @Select("<script>" +
            "SELECT COUNT(*) FROM agent_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "</script>")
    Long selectTotalCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);
}
