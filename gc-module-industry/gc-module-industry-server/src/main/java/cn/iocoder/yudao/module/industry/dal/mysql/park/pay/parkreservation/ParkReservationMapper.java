package cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkreservation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation.vo.ParkReservationPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreservation.ParkReservationDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 停车预约服务 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkReservationMapper extends BaseMapperX<ParkReservationDO> {

    default PageResult<ParkReservationDO> selectPage(ParkReservationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkReservationDO>()
                .eqIfPresent(ParkReservationDO::getReservationId, reqVO.getReservationId())
                .eqIfPresent(ParkReservationDO::getReservationNo, reqVO.getReservationNo())
                .eqIfPresent(ParkReservationDO::getUserId, reqVO.getUserId())
                .likeIfPresent(ParkReservationDO::getUserName, reqVO.getUserName())
                .eqIfPresent(ParkReservationDO::getUserPhone, reqVO.getUserPhone())
                .eqIfPresent(ParkReservationDO::getParkLotId, reqVO.getParkLotId())
                .likeIfPresent(ParkReservationDO::getParkLotName, reqVO.getParkLotName())
                .eqIfPresent(ParkReservationDO::getBerthId, reqVO.getBerthId())
                .eqIfPresent(ParkReservationDO::getBerthNo, reqVO.getBerthNo())
                .betweenIfPresent(ParkReservationDO::getReserveStartTime, reqVO.getReserveStartTime())
                .betweenIfPresent(ParkReservationDO::getReserveEndTime, reqVO.getReserveEndTime())
                .eqIfPresent(ParkReservationDO::getReserveStatus, reqVO.getReserveStatus())
                .eqIfPresent(ParkReservationDO::getVerifyMethod, reqVO.getVerifyMethod())
                .eqIfPresent(ParkReservationDO::getPayAmount, reqVO.getPayAmount())
                .eqIfPresent(ParkReservationDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ParkReservationDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ParkReservationDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(ParkReservationDO::getCreateTime, reqVO.getCreateTime())
                .likeIfPresent(ParkReservationDO::getRegionName, reqVO.getRegionName())
                .likeIfPresent(ParkReservationDO::getGridName, reqVO.getGridName())
                .eqIfPresent(ParkReservationDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkReservationDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(ParkReservationDO::getId));
    }

}
