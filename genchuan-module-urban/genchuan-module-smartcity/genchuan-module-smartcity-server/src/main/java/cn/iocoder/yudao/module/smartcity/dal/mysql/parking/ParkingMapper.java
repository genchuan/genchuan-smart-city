package cn.iocoder.yudao.module.smartcity.dal.mysql.parking;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parking.ParkingDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.parking.vo.*;

/**
 * 停车管理用户管理与服务 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface ParkingMapper extends BaseMapperX<ParkingDO> {

    default PageResult<ParkingDO> selectPage(ParkingPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkingDO>()
                .likeIfPresent(ParkingDO::getFullName, reqVO.getFullName())
                .eqIfPresent(ParkingDO::getGender, reqVO.getGender())
                .eqIfPresent(ParkingDO::getPhoneNumber, reqVO.getPhoneNumber())
                .eqIfPresent(ParkingDO::getEMail, reqVO.getEMail())
                .eqIfPresent(ParkingDO::getIdNumber, reqVO.getIdNumber())
                .betweenIfPresent(ParkingDO::getRegistrationTime, reqVO.getRegistrationTime())
                .eqIfPresent(ParkingDO::getCustomerType, reqVO.getCustomerType())
                .eqIfPresent(ParkingDO::getUserLevel, reqVO.getUserLevel())
                .eqIfPresent(ParkingDO::getPoints, reqVO.getPoints())
                .eqIfPresent(ParkingDO::getBalance, reqVO.getBalance())
                .eqIfPresent(ParkingDO::getAddress, reqVO.getAddress())
                .eqIfPresent(ParkingDO::getServiceEvaluation, reqVO.getServiceEvaluation())
                .betweenIfPresent(ParkingDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ParkingDO::getId));
    }

}