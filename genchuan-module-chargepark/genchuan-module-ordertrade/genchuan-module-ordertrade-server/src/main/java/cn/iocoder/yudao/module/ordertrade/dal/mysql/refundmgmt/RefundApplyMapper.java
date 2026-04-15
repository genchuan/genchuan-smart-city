package cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.RefundApplyPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * RefundApply Mapper
 * @author genchuan
 */
@Mapper
public interface RefundApplyMapper extends BaseMapperX<RefundApplyDO> {

    default PageResult<RefundApplyDO> selectPage(RefundApplyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RefundApplyDO>()
                .eqIfPresent(RefundApplyDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(RefundApplyDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RefundApplyDO::getApplicantId, reqVO.getApplicantId())
                .betweenIfPresent(RefundApplyDO::getApplyTime, reqVO.getApplyTime())
                .orderByDesc(RefundApplyDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(create_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM refund_apply WHERE deleted = 0 " +
            "<if test='startTime != null'> AND create_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND create_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT status, COUNT(*) AS count FROM refund_apply WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM refund_apply WHERE deleted = 0 AND create_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT COUNT(*) FROM refund_apply WHERE deleted = 0 " +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</script>")
    Long selectCountByStatus(@Param("status") String status);

}
