package cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.PayRefundPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayRefundDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface PayRefundMapper extends BaseMapperX<PayRefundDO> {

    default PageResult<PayRefundDO> selectPage(PayRefundPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PayRefundDO>()
                .eqIfPresent(PayRefundDO::getAppId, reqVO.getAppId())
                .eqIfPresent(PayRefundDO::getChannelCode, reqVO.getChannelCode())
                .eqIfPresent(PayRefundDO::getStatus, reqVO.getStatus())
                .orderByDesc(PayRefundDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM pay_refund WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM pay_refund WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM pay_refund WHERE deleted = 0 AND status = 20 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodaySuccessCount(@Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime);
}
