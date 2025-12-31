package cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkordertemp;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkordertemp.vo.ParkOrderTempPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkordertemp.ParkOrderTempDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 临停订单 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkOrderTempMapper extends BaseMapperX<ParkOrderTempDO> {

    default PageResult<ParkOrderTempDO> selectPage(ParkOrderTempPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkOrderTempDO>()
                .eqIfPresent(ParkOrderTempDO::getRegionFullCode, reqVO.getRegionFullCode())
                .eqIfPresent(ParkOrderTempDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ParkOrderTempDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkOrderTempDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkOrderTempDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkOrderTempDO::getEntryId, reqVO.getEntryId())
                .eqIfPresent(ParkOrderTempDO::getExitId, reqVO.getExitId())
                .betweenIfPresent(ParkOrderTempDO::getParkingStartTime, reqVO.getParkingStartTime())
                .betweenIfPresent(ParkOrderTempDO::getParkingEndTime, reqVO.getParkingEndTime())
                .eqIfPresent(ParkOrderTempDO::getParkingDuration, reqVO.getParkingDuration())
                .eqIfPresent(ParkOrderTempDO::getFeeStrategyId, reqVO.getFeeStrategyId())
                .eqIfPresent(ParkOrderTempDO::getOriginalAmount, reqVO.getOriginalAmount())
                .eqIfPresent(ParkOrderTempDO::getDiscountAmount, reqVO.getDiscountAmount())
                .eqIfPresent(ParkOrderTempDO::getPayAmount, reqVO.getPayAmount())
                .eqIfPresent(ParkOrderTempDO::getOrderStatus, reqVO.getOrderStatus())
                .betweenIfPresent(ParkOrderTempDO::getPayTime, reqVO.getPayTime())
                .betweenIfPresent(ParkOrderTempDO::getCancelTime, reqVO.getCancelTime())
                .betweenIfPresent(ParkOrderTempDO::getRefundTime, reqVO.getRefundTime())
                .eqIfPresent(ParkOrderTempDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(ParkOrderTempDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkOrderTempDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkOrderTempDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkOrderTempDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkOrderTempDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkOrderTempDO::getId));
    }

}
