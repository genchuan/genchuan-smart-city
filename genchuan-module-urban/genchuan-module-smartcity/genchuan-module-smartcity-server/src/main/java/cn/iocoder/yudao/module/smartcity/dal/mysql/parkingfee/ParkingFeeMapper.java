package cn.iocoder.yudao.module.smartcity.dal.mysql.parkingfee;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parkingfee.ParkingFeeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.parkingfee.vo.*;

/**
 * 停车收费管理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ParkingFeeMapper extends BaseMapperX<ParkingFeeDO> {

    default PageResult<ParkingFeeDO> selectPage(ParkingFeePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkingFeeDO>()
                .eqIfPresent(ParkingFeeDO::getLicensePlateNumber, reqVO.getLicensePlateNumber())
                .betweenIfPresent(ParkingFeeDO::getAdmissionTime, reqVO.getAdmissionTime())
                .betweenIfPresent(ParkingFeeDO::getExitTime, reqVO.getExitTime())
                .eqIfPresent(ParkingFeeDO::getParkingDuration, reqVO.getParkingDuration())
                .eqIfPresent(ParkingFeeDO::getFeeAmount, reqVO.getFeeAmount())
                .eqIfPresent(ParkingFeeDO::getPaymentMethod, reqVO.getPaymentMethod())
                .eqIfPresent(ParkingFeeDO::getActualReceivedAmount, reqVO.getActualReceivedAmount())
                .eqIfPresent(ParkingFeeDO::getParkingSpaceNumber, reqVO.getParkingSpaceNumber())
                .betweenIfPresent(ParkingFeeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkingFeeDO::getId));
    }

}