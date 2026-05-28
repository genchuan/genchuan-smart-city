package cn.iocoder.yudao.module.ordertrade.dal.mysql.agentpay;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.AgentOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentOrderDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface AgentOrderMapper extends BaseMapperX<AgentOrderDO> {

    @Select("<script>" +
            "SELECT ao.*, mi.name AS merchant_name " +
            "FROM agent_order ao " +
            "LEFT JOIN merchant_info mi ON mi.id = ao.merchant_id AND mi.deleted = 0 " +
            "WHERE ao.deleted = 0 " +
            "<if test='req.orderNo != null and req.orderNo != \"\"'>AND ao.order_no LIKE CONCAT('%', #{req.orderNo}, '%') </if>" +
            "<if test='req.merchantId != null'>AND ao.merchant_id = #{req.merchantId} </if>" +
            "<if test='req.merchantName != null and req.merchantName != \"\"'>AND mi.name LIKE CONCAT('%', #{req.merchantName}, '%') </if>" +
            "<if test='req.carNo != null and req.carNo != \"\"'>AND ao.car_no LIKE CONCAT('%', #{req.carNo}, '%') </if>" +
            "<if test='req.status != null and req.status != \"\"'>AND ao.status = #{req.status} </if>" +
            "<if test='req.payType != null and req.payType != \"\"'>AND ao.pay_type = #{req.payType} </if>" +
            "ORDER BY ao.id DESC" +
            "</script>")
    IPage<AgentOrderDO> selectPageWithMerchant(Page<AgentOrderDO> page, @Param("req") AgentOrderPageReqVO reqVO);

    @Select("SELECT ao.*, mi.name AS merchant_name " +
            "FROM agent_order ao " +
            "LEFT JOIN merchant_info mi ON mi.id = ao.merchant_id AND mi.deleted = 0 " +
            "WHERE ao.id = #{id} AND ao.deleted = 0")
    AgentOrderDO selectByIdWithMerchant(@Param("id") Long id);

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
