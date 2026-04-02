package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.orderlist;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderlist.OrderListDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo.*;

/**
 * 订单列表 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OrderListMapper extends BaseMapperX<OrderListDO> {

    default PageResult<OrderListDO> selectPage(OrderListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderListDO>()
                .eqIfPresent(OrderListDO::getOrderCode, reqVO.getOrderCode())
                .eqIfPresent(OrderListDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderListDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(OrderListDO::getPileCode, reqVO.getPileCode())
                .betweenIfPresent(OrderListDO::getChargeTime, reqVO.getChargeTime())
                .eqIfPresent(OrderListDO::getChargeAmount, reqVO.getChargeAmount())
                .eqIfPresent(OrderListDO::getChargeMoney, reqVO.getChargeMoney())
                .eqIfPresent(OrderListDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(OrderListDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(OrderListDO::getPayType, reqVO.getPayType())
                .eqIfPresent(OrderListDO::getStopReason, reqVO.getStopReason())
                .eqIfPresent(OrderListDO::getEvaluate, reqVO.getEvaluate())
                .betweenIfPresent(OrderListDO::getEvaluateTime, reqVO.getEvaluateTime())
                .betweenIfPresent(OrderListDO::getCancelTime, reqVO.getCancelTime())
                .eqIfPresent(OrderListDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderListDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(OrderListDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(OrderListDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderListDO::getId));
    }

}