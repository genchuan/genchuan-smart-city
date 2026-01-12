package cn.iocoder.yudao.module.industry.dal.mysql.park.through.carparking;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo.ParkCarParkingPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carparking.ParkCarParkingDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 在停车辆 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkCarParkingMapper extends BaseMapperX<ParkCarParkingDO> {

    default PageResult<ParkCarParkingDO> selectPage(ParkCarParkingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkCarParkingDO>()
                .eqIfPresent(ParkCarParkingDO::getParkingId, reqVO.getParkingId())
                .eqIfPresent(ParkCarParkingDO::getEntryId, reqVO.getEntryId())
                .eqIfPresent(ParkCarParkingDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkCarParkingDO::getLotId, reqVO.getLotId())
                .eqIfPresent(ParkCarParkingDO::getSpaceId, reqVO.getSpaceId())
                .betweenIfPresent(ParkCarParkingDO::getParkingTime, reqVO.getParkingTime())
                .eqIfPresent(ParkCarParkingDO::getParkingStatus, reqVO.getParkingStatus())
                .eqIfPresent(ParkCarParkingDO::getSuspiciousReason, reqVO.getSuspiciousReason())
                .eqIfPresent(ParkCarParkingDO::getAbnormalReason, reqVO.getAbnormalReason())
                .betweenIfPresent(ParkCarParkingDO::getParkingUpdateTime, reqVO.getParkingUpdateTime())
                .eqIfPresent(ParkCarParkingDO::getParkingRemark, reqVO.getParkingRemark())
                .betweenIfPresent(ParkCarParkingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkCarParkingDO::getId));
    }

}