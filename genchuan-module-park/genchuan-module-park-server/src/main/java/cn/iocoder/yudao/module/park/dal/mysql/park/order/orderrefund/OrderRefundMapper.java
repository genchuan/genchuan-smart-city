package cn.iocoder.yudao.module.park.dal.mysql.park.order.orderrefund;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderrefund.vo.OrderRefundPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderrefund.OrderRefundDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退款订单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OrderRefundMapper extends BaseMapperX<OrderRefundDO> {

    default PageResult<OrderRefundDO> selectPage(OrderRefundPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderRefundDO>()
                .eqIfPresent(OrderRefundDO::getOriginalOrderId, reqVO.getOriginalOrderId())
                .eqIfPresent(OrderRefundDO::getOrderType, reqVO.getOrderType())
                .eqIfPresent(OrderRefundDO::getRefundAmount, reqVO.getRefundAmount())
                .eqIfPresent(OrderRefundDO::getRefundReason, reqVO.getRefundReason())
                .eqIfPresent(OrderRefundDO::getProofFiles, reqVO.getProofFiles())
                .eqIfPresent(OrderRefundDO::getApplyBy, reqVO.getApplyBy())
                .eqIfPresent(OrderRefundDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(OrderRefundDO::getApproveStatus, reqVO.getApproveStatus())
                .eqIfPresent(OrderRefundDO::getRefundStatus, reqVO.getRefundStatus())
                .betweenIfPresent(OrderRefundDO::getRefundTime, reqVO.getRefundTime())
                .betweenIfPresent(OrderRefundDO::getArrivalTime, reqVO.getArrivalTime())
                .betweenIfPresent(OrderRefundDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(OrderRefundDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderRefundDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(OrderRefundDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(OrderRefundDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(OrderRefundDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(OrderRefundDO::getId));
    }

}
