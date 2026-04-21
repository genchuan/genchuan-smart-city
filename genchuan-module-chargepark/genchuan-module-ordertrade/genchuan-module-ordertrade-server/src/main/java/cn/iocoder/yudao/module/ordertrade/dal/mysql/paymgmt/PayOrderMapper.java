package cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.PayOrderPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface PayOrderMapper extends BaseMapperX<PayOrderDO> {

    default PageResult<PayOrderDO> selectPage(PayOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PayOrderDO>()
                .eqIfPresent(PayOrderDO::getAppId, reqVO.getAppId())
                .eqIfPresent(PayOrderDO::getChannelCode, reqVO.getChannelCode())
                .eqIfPresent(PayOrderDO::getStatus, reqVO.getStatus())
                .likeIfPresent(PayOrderDO::getMerchantOrderId, reqVO.getMerchantOrderId())
                .orderByDesc(PayOrderDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM pay_order WHERE 1=1 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT channel_code, COUNT(*) AS count FROM pay_order GROUP BY channel_code")
    List<Map<String, Object>> selectGroupByChannel();

    @Select("SELECT COUNT(*) FROM pay_order WHERE create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM pay_order WHERE status = 10 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodaySuccessCount(@Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime);
}
