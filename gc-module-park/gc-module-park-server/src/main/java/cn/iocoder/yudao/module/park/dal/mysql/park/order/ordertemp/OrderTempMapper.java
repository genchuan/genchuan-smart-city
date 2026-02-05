package cn.iocoder.yudao.module.park.dal.mysql.park.order.ordertemp;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp.OrderTempDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 临停订单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface OrderTempMapper extends BaseMapperX<OrderTempDO> {

    default PageResult<OrderTempDO> selectPage(OrderTempPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderTempDO>()
                .eqIfPresent(OrderTempDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(OrderTempDO::getLotId, reqVO.getLotId())
                .eqIfPresent(OrderTempDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(OrderTempDO::getParkInputCarId, reqVO.getParkInputCarId())
                .betweenIfPresent(OrderTempDO::getEntryTime, reqVO.getEntryTime())
                .betweenIfPresent(OrderTempDO::getExitTime, reqVO.getExitTime())
                .eqIfPresent(OrderTempDO::getParkingDuration, reqVO.getParkingDuration())
                .eqIfPresent(OrderTempDO::getOriginalAmount, reqVO.getOriginalAmount())
                .eqIfPresent(OrderTempDO::getDiscountAmount, reqVO.getDiscountAmount())
                .eqIfPresent(OrderTempDO::getPayAmount, reqVO.getPayAmount())
                .eqIfPresent(OrderTempDO::getFeeStrategyId, reqVO.getFeeStrategyId())
                .eqIfPresent(OrderTempDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(OrderTempDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(OrderTempDO::getPayType, reqVO.getPayType())
                .eqIfPresent(OrderTempDO::getPaymentId, reqVO.getPaymentId())
                .betweenIfPresent(OrderTempDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(OrderTempDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderTempDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(OrderTempDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(OrderTempDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(OrderTempDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(OrderTempDO::getId));
    }

}
