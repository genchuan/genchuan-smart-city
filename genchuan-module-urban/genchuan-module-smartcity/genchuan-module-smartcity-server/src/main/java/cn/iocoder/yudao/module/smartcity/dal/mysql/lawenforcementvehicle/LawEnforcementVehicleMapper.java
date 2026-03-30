package cn.iocoder.yudao.module.smartcity.dal.mysql.lawenforcementvehicle;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawenforcementvehicle.LawEnforcementVehicleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawenforcementvehicle.vo.*;

/**
 * 执法车辆管理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface LawEnforcementVehicleMapper extends BaseMapperX<LawEnforcementVehicleDO> {

    default PageResult<LawEnforcementVehicleDO> selectPage(LawEnforcementVehiclePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LawEnforcementVehicleDO>()
                .eqIfPresent(LawEnforcementVehicleDO::getLicensePlateNumber, reqVO.getLicensePlateNumber())
                .eqIfPresent(LawEnforcementVehicleDO::getVehicleBrand, reqVO.getVehicleBrand())
                .eqIfPresent(LawEnforcementVehicleDO::getModel, reqVO.getModel())
                .eqIfPresent(LawEnforcementVehicleDO::getVehicleColor, reqVO.getVehicleColor())
                .eqIfPresent(LawEnforcementVehicleDO::getVin, reqVO.getVin())
                .eqIfPresent(LawEnforcementVehicleDO::getEngineNo, reqVO.getEngineNo())
                .betweenIfPresent(LawEnforcementVehicleDO::getPurchaseTime, reqVO.getPurchaseTime())
                .betweenIfPresent(LawEnforcementVehicleDO::getRegistrationDate, reqVO.getRegistrationDate())
                .eqIfPresent(LawEnforcementVehicleDO::getBelongingDepartment, reqVO.getBelongingDepartment())
                .eqIfPresent(LawEnforcementVehicleDO::getNatureOfUse, reqVO.getNatureOfUse())
                .eqIfPresent(LawEnforcementVehicleDO::getVehicleUsage, reqVO.getVehicleUsage())
                .eqIfPresent(LawEnforcementVehicleDO::getVehicleStatus, reqVO.getVehicleStatus())
                .betweenIfPresent(LawEnforcementVehicleDO::getAnnualInspectionDate, reqVO.getAnnualInspectionDate())
                .eqIfPresent(LawEnforcementVehicleDO::getInsuranceDeadline, reqVO.getInsuranceDeadline())
                .eqIfPresent(LawEnforcementVehicleDO::getTypesOfInsurance, reqVO.getTypesOfInsurance())
                .likeIfPresent(LawEnforcementVehicleDO::getDriverName, reqVO.getDriverName())
                .eqIfPresent(LawEnforcementVehicleDO::getDriverContactInformation, reqVO.getDriverContactInformation())
                .eqIfPresent(LawEnforcementVehicleDO::getMileage, reqVO.getMileage())
                .eqIfPresent(LawEnforcementVehicleDO::getMaintenanceRecord, reqVO.getMaintenanceRecord())
                .eqIfPresent(LawEnforcementVehicleDO::getMaintenanceRecords, reqVO.getMaintenanceRecords())
                .eqIfPresent(LawEnforcementVehicleDO::getRefuelingRecord, reqVO.getRefuelingRecord())
                .eqIfPresent(LawEnforcementVehicleDO::getViolationRecords, reqVO.getViolationRecords())
                .betweenIfPresent(LawEnforcementVehicleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LawEnforcementVehicleDO::getId));
    }

}