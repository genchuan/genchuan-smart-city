package cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.PayCallbackPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayCallbackDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface PayCallbackMapper extends BaseMapperX<PayCallbackDO> {

    default PageResult<PayCallbackDO> selectPage(PayCallbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PayCallbackDO>()
                .eqIfPresent(PayCallbackDO::getAppId, reqVO.getAppId())
                .eqIfPresent(PayCallbackDO::getType, reqVO.getType())
                .eqIfPresent(PayCallbackDO::getStatus, reqVO.getStatus())
                .orderByDesc(PayCallbackDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM pay_notify_task WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM pay_notify_task WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT COUNT(*) FROM pay_notify_task WHERE deleted = 0 AND status = 10 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodaySuccessCount(@Param("startTime") LocalDateTime startTime,
                                 @Param("endTime") LocalDateTime endTime);
}
