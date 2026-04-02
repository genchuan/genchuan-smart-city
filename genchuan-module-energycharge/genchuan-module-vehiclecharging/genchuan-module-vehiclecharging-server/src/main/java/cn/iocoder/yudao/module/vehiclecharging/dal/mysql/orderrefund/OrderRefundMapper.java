package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderrefund;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderrefund.OrderRefundDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 订单退款 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface OrderRefundMapper extends BaseMapperX<OrderRefundDO> {

    default PageResult<OrderRefundDO> selectPage(OrderRefundPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderRefundDO>()
                .eqIfPresent(OrderRefundDO::getRefundCode, reqVO.getRefundCode())
                .eqIfPresent(OrderRefundDO::getOrderCode, reqVO.getOrderCode())
                .eqIfPresent(OrderRefundDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderRefundDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(OrderRefundDO::getRefundAmount, reqVO.getRefundAmount())
                .eqIfPresent(OrderRefundDO::getRefundReason, reqVO.getRefundReason())
                .eqIfPresent(OrderRefundDO::getRefundStatus, reqVO.getRefundStatus())
                .eqIfPresent(OrderRefundDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(OrderRefundDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(OrderRefundDO::getAuditRemark, reqVO.getAuditRemark())
                .betweenIfPresent(OrderRefundDO::getRefundTime, reqVO.getRefundTime())
                .eqIfPresent(OrderRefundDO::getRefundChannel, reqVO.getRefundChannel())
                .eqIfPresent(OrderRefundDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderRefundDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(OrderRefundDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(OrderRefundDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderRefundDO::getId));
    }

    default List<OrderRefundDO> selectByIdsAndRefundStatus(@Param("ids") Collection<Long> ids,
                                                           @Param("refundStatus") String refundStatus) {
        return selectList(new LambdaQueryWrapperX<OrderRefundDO>()
                .in(OrderRefundDO::getId, ids)
                .eq(OrderRefundDO::getRefundStatus, refundStatus));
    }

    List<OrderRefundChartRespVO.LineData> selectLineDataByDate(@Param("req") OrderRefundChartReqVO chartReqVO);

    List<OrderRefundChartRespVO.PieData> selectPieDataByStatus();

    OrderRefundChartRespVO.CardData selectCardData();

}