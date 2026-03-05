package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkreservation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkreservation.vo.ParkReservationPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkreservation.ParkReservationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 停车预约 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkReservationMapper extends BaseMapperX<ParkReservationDO> {

    default PageResult<ParkReservationDO> selectPage(ParkReservationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkReservationDO>()
                .eqIfPresent(ParkReservationDO::getReservationNo, reqVO.getReservationNo())
                .eqIfPresent(ParkReservationDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkReservationDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkReservationDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkReservationDO::getSpaceId, reqVO.getSpaceId())
                .betweenIfPresent(ParkReservationDO::getReserveDate, reqVO.getReserveDate())
                .betweenIfPresent(ParkReservationDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ParkReservationDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(ParkReservationDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ParkReservationDO::getVerifyTime, reqVO.getVerifyTime())
                .eqIfPresent(ParkReservationDO::getVerifyBy, reqVO.getVerifyBy())
                .betweenIfPresent(ParkReservationDO::getCancelTime, reqVO.getCancelTime())
                .eqIfPresent(ParkReservationDO::getCancelReason, reqVO.getCancelReason())
                .betweenIfPresent(ParkReservationDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkReservationDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkReservationDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkReservationDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkReservationDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkReservationDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkReservationDO::getId));
    }

}
