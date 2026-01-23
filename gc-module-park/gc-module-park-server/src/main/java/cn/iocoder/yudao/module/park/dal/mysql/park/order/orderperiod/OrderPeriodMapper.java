package cn.iocoder.yudao.module.park.dal.mysql.park.order.orderperiod;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo.OrderPeriodPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderperiod.OrderPeriodDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 期卡订单 Mapper
 *
 * @author lxs
 */
@Mapper
public interface OrderPeriodMapper extends BaseMapperX<OrderPeriodDO> {

    default PageResult<OrderPeriodDO> selectPage(OrderPeriodPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderPeriodDO>()
                .eqIfPresent(OrderPeriodDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderPeriodDO::getCarId, reqVO.getCarId())
                .eqIfPresent(OrderPeriodDO::getPackageId, reqVO.getPackageId())
                .eqIfPresent(OrderPeriodDO::getLotIds, reqVO.getLotIds())
                .eqIfPresent(OrderPeriodDO::getOriginalPrice, reqVO.getOriginalPrice())
                .eqIfPresent(OrderPeriodDO::getPayAmount, reqVO.getPayAmount())
                .eqIfPresent(OrderPeriodDO::getDiscountAmount, reqVO.getDiscountAmount())
                .betweenIfPresent(OrderPeriodDO::getEffectTime, reqVO.getEffectTime())
                .betweenIfPresent(OrderPeriodDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(OrderPeriodDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(OrderPeriodDO::getPayStatus, reqVO.getPayStatus())
                .eqIfPresent(OrderPeriodDO::getPayType, reqVO.getPayType())
                .eqIfPresent(OrderPeriodDO::getPaymentId, reqVO.getPaymentId())
                .betweenIfPresent(OrderPeriodDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(OrderPeriodDO::getRemark, reqVO.getRemark())
                .eqIfPresent(OrderPeriodDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(OrderPeriodDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(OrderPeriodDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(OrderPeriodDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(OrderPeriodDO::getId));
    }

}
