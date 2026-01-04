package cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkorderperiod;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderperiod.vo.ParkOrderPeriodPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderperiod.ParkOrderPeriodDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 期卡订单 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkOrderPeriodMapper extends BaseMapperX<ParkOrderPeriodDO> {

    default PageResult<ParkOrderPeriodDO> selectPage(ParkOrderPeriodPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkOrderPeriodDO>()
                .eqIfPresent(ParkOrderPeriodDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ParkOrderPeriodDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkOrderPeriodDO::getPeriodCardId, reqVO.getPeriodCardId())
                .eqIfPresent(ParkOrderPeriodDO::getLotIds, reqVO.getLotIds())
                .betweenIfPresent(ParkOrderPeriodDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkOrderPeriodDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkOrderPeriodDO::getCardStatus, reqVO.getCardStatus())
                .eqIfPresent(ParkOrderPeriodDO::getOriginalAmount, reqVO.getOriginalAmount())
                .eqIfPresent(ParkOrderPeriodDO::getDiscountAmount, reqVO.getDiscountAmount())
                .eqIfPresent(ParkOrderPeriodDO::getPayAmount, reqVO.getPayAmount())
                .eqIfPresent(ParkOrderPeriodDO::getOrderStatus, reqVO.getOrderStatus())
                .betweenIfPresent(ParkOrderPeriodDO::getPayTime, reqVO.getPayTime())
                .betweenIfPresent(ParkOrderPeriodDO::getCancelTime, reqVO.getCancelTime())
                .betweenIfPresent(ParkOrderPeriodDO::getRefundTime, reqVO.getRefundTime())
                .eqIfPresent(ParkOrderPeriodDO::getRenewFlag, reqVO.getRenewFlag())
                .eqIfPresent(ParkOrderPeriodDO::getPrevOrderId, reqVO.getPrevOrderId())
                .eqIfPresent(ParkOrderPeriodDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ParkOrderPeriodDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkOrderPeriodDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkOrderPeriodDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkOrderPeriodDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkOrderPeriodDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkOrderPeriodDO::getId));
    }

}
