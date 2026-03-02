package cn.iocoder.yudao.module.park.dal.mysql.park.resource.parkinglotinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo.ParkingLotInfoPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.parkinglotinfo.ParkingLotInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 停车场信息管理 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkingLotInfoMapper extends BaseMapperX<ParkingLotInfoDO> {

    default PageResult<ParkingLotInfoDO> selectPage(ParkingLotInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkingLotInfoDO>()
                .eqIfPresent(ParkingLotInfoDO::getLotId, reqVO.getLotId())
                .likeIfPresent(ParkingLotInfoDO::getLotName, reqVO.getLotName())
                .eqIfPresent(ParkingLotInfoDO::getLotType, reqVO.getLotType())
                .eqIfPresent(ParkingLotInfoDO::getRegion, reqVO.getRegion())
                .eqIfPresent(ParkingLotInfoDO::getTotalSpaces, reqVO.getTotalSpaces())
                .eqIfPresent(ParkingLotInfoDO::getAvailableSpaces, reqVO.getAvailableSpaces())
                .eqIfPresent(ParkingLotInfoDO::getLotStatus, reqVO.getLotStatus())
                .eqIfPresent(ParkingLotInfoDO::getFeeStandard, reqVO.getFeeStandard())
                .eqIfPresent(ParkingLotInfoDO::getBusinessHours, reqVO.getBusinessHours())
                .eqIfPresent(ParkingLotInfoDO::getOperator, reqVO.getOperator())
                .eqIfPresent(ParkingLotInfoDO::getContactPerson, reqVO.getContactPerson())
                .eqIfPresent(ParkingLotInfoDO::getContactPhone, reqVO.getContactPhone())
                .eqIfPresent(ParkingLotInfoDO::getAreaCode, reqVO.getAreaCode())
                .betweenIfPresent(ParkingLotInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkingLotInfoDO::getId));
    }

}