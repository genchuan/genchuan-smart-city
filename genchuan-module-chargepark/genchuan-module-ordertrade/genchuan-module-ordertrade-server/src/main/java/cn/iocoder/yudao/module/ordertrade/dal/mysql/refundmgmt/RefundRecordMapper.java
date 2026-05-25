package cn.iocoder.yudao.module.ordertrade.dal.mysql.refundmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.RefundRecordPageReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundRecordDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * RefundRecord Mapper
 * @author genchuan
 */
@Mapper
public interface RefundRecordMapper extends BaseMapperX<RefundRecordDO> {

    IPage<RefundRecordDO> selectPageJoinApplyAndOrder(IPage<RefundRecordDO> page, @Param("req") RefundRecordPageReqVO reqVO);

    @Select("SELECT r.*, a.apply_no AS apply_no, o.order_no AS order_no " +
            "FROM refund_record r " +
            "LEFT JOIN refund_apply a ON r.apply_id = a.id AND a.deleted = 0 " +
            "LEFT JOIN all_order o ON r.order_id = o.id AND o.deleted = 0 " +
            "WHERE r.id = #{id} AND r.deleted = 0")
    RefundRecordDO selectByIdJoinApplyAndOrder(@Param("id") Long id);

    default PageResult<RefundRecordDO> selectPage(RefundRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RefundRecordDO>()
                .eqIfPresent(RefundRecordDO::getApplyId, reqVO.getApplyId())
                .eqIfPresent(RefundRecordDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(RefundRecordDO::getStatus, reqVO.getStatus())
                .geIfPresent(RefundRecordDO::getRefundTime, reqVO.getRefundTimeStart())
                .leIfPresent(RefundRecordDO::getRefundTime, reqVO.getRefundTimeEnd())
                .orderByDesc(RefundRecordDO::getId));
    }

    @Select("<script>" +
            "SELECT DATE_FORMAT(refund_time,'%Y-%m-%d') AS date, COUNT(*) AS count " +
            "FROM refund_record WHERE deleted = 0 " +
            "<if test='startTime != null'> AND refund_time &gt;= #{startTime} </if>" +
            "<if test='endTime != null'>   AND refund_time &lt;= #{endTime}   </if>" +
            "GROUP BY DATE_FORMAT(refund_time,'%Y-%m-%d') ORDER BY date" +
            "</script>")
    List<Map<String, Object>> selectTrend(@Param("startTime") LocalDateTime startTime,
                                          @Param("endTime") LocalDateTime endTime);

    @Select("SELECT status, COUNT(*) AS count FROM refund_record WHERE deleted = 0 GROUP BY status")
    List<Map<String, Object>> selectGroupByStatus();

    @Select("SELECT COUNT(*) FROM refund_record WHERE deleted = 0 AND refund_time BETWEEN #{startTime} AND #{endTime}")
    Long selectTodayCount(@Param("startTime") LocalDateTime startTime,
                          @Param("endTime") LocalDateTime endTime);


    @Select("<script>" +
            "SELECT COUNT(*) FROM refund_record WHERE deleted = 0 " +
            "<if test='status != null'> AND status = #{status} </if>" +
            "</script>")
    Long selectCountByStatus(@Param("status") String status);

    @Select("SELECT IFNULL(SUM(refund_amount), 0) FROM refund_record WHERE deleted = 0")
    BigDecimal selectTotalRefundAmount();

}
