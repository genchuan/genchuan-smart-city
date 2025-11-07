package cn.iocoder.yudao.module.smartcity.dal.mysql.vehicleinformation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.vehicleinformation.VehicleInformationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.vehicleinformation.vo.*;

/**
 * 车辆信息 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface VehicleInformationMapper extends BaseMapperX<VehicleInformationDO> {

    default PageResult<VehicleInformationDO> selectPage(VehicleInformationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleInformationDO>()
                .eqIfPresent(VehicleInformationDO::getLicensePlateNumber, reqVO.getLicensePlateNumber())
                .eqIfPresent(VehicleInformationDO::getVehicleBrand, reqVO.getVehicleBrand())
                .eqIfPresent(VehicleInformationDO::getModel, reqVO.getModel())
                .eqIfPresent(VehicleInformationDO::getCarOwnerPhoneNumber, reqVO.getCarOwnerPhoneNumber())
                .eqIfPresent(VehicleInformationDO::getVehicleColor, reqVO.getVehicleColor())
                .eqIfPresent(VehicleInformationDO::getSeats, reqVO.getSeats())
                .eqIfPresent(VehicleInformationDO::getDateOfProduction, reqVO.getDateOfProduction())
                .eqIfPresent(VehicleInformationDO::getPurchasePrice, reqVO.getPurchasePrice())
                .eqIfPresent(VehicleInformationDO::getNatureOfUse, reqVO.getNatureOfUse())
                .eqIfPresent(VehicleInformationDO::getAnnualInspectionPeriod, reqVO.getAnnualInspectionPeriod())
                .eqIfPresent(VehicleInformationDO::getPeriodOfInsurance, reqVO.getPeriodOfInsurance())
                .eqIfPresent(VehicleInformationDO::getTypesOfInsurance, reqVO.getTypesOfInsurance())
                .eqIfPresent(VehicleInformationDO::getEnvironmentalStandards, reqVO.getEnvironmentalStandards())
                .eqIfPresent(VehicleInformationDO::getVehicleUsage, reqVO.getVehicleUsage())
                .betweenIfPresent(VehicleInformationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VehicleInformationDO::getId));
    }

}