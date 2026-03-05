package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkchargereservation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo.ParkChargeReservationPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkchargereservation.ParkChargeReservationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充电预约 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkChargeReservationMapper extends BaseMapperX<ParkChargeReservationDO> {

    default PageResult<ParkChargeReservationDO> selectPage(ParkChargeReservationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkChargeReservationDO>()
                .eqIfPresent(ParkChargeReservationDO::getReservationNo, reqVO.getReservationNo())
                .eqIfPresent(ParkChargeReservationDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkChargeReservationDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkChargeReservationDO::getChargePileId, reqVO.getChargePileId())
                .betweenIfPresent(ParkChargeReservationDO::getReserveDate, reqVO.getReserveDate())
                .betweenIfPresent(ParkChargeReservationDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkChargeReservationDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkChargeReservationDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkChargeReservationDO::getActualChargeTime, reqVO.getActualChargeTime())
                .eqIfPresent(ParkChargeReservationDO::getChargeAmount, reqVO.getChargeAmount())
                .eqIfPresent(ParkChargeReservationDO::getChargeFee, reqVO.getChargeFee())
                .betweenIfPresent(ParkChargeReservationDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkChargeReservationDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkChargeReservationDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkChargeReservationDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkChargeReservationDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkChargeReservationDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkChargeReservationDO::getId));
    }

}
