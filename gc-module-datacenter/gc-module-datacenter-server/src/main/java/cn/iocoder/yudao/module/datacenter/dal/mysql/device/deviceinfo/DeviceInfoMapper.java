package cn.iocoder.yudao.module.datacenter.dal.mysql.device.deviceinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.deviceinfo.DeviceInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo.vo.*;

/**
 * 设备信息 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceInfoMapper extends BaseMapperX<DeviceInfoDO> {

    default PageResult<DeviceInfoDO> selectPage(DeviceInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceInfoDO>()
                .eqIfPresent(DeviceInfoDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceInfoDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceInfoDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceInfoDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(DeviceInfoDO::getDeviceModel, reqVO.getDeviceModel())
                .eqIfPresent(DeviceInfoDO::getDeviceSpec, reqVO.getDeviceSpec())
                .eqIfPresent(DeviceInfoDO::getManufacturer, reqVO.getManufacturer())
                .betweenIfPresent(DeviceInfoDO::getProductionDate, reqVO.getProductionDate())
                .betweenIfPresent(DeviceInfoDO::getPurchaseDate, reqVO.getPurchaseDate())
                .betweenIfPresent(DeviceInfoDO::getInstallDate, reqVO.getInstallDate())
                .eqIfPresent(DeviceInfoDO::getWarrantyPeriod, reqVO.getWarrantyPeriod())
                .eqIfPresent(DeviceInfoDO::getDeviceStatus, reqVO.getDeviceStatus())
                .eqIfPresent(DeviceInfoDO::getRunStatus, reqVO.getRunStatus())
                .betweenIfPresent(DeviceInfoDO::getLastOnlineTime, reqVO.getLastOnlineTime())
                .eqIfPresent(DeviceInfoDO::getDeviceMajorId, reqVO.getDeviceMajorId())
                .likeIfPresent(DeviceInfoDO::getDeviceMajorName, reqVO.getDeviceMajorName())
                .eqIfPresent(DeviceInfoDO::getDeviceMidId, reqVO.getDeviceMidId())
                .likeIfPresent(DeviceInfoDO::getDeviceMidName, reqVO.getDeviceMidName())
                .eqIfPresent(DeviceInfoDO::getDeviceMinorId, reqVO.getDeviceMinorId())
                .likeIfPresent(DeviceInfoDO::getDeviceMinorName, reqVO.getDeviceMinorName())
                .eqIfPresent(DeviceInfoDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(DeviceInfoDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(DeviceInfoDO::getGridId, reqVO.getGridId())
                .likeIfPresent(DeviceInfoDO::getGridName, reqVO.getGridName())
                .eqIfPresent(DeviceInfoDO::getGridCode, reqVO.getGridCode())
                .eqIfPresent(DeviceInfoDO::getMonCompId, reqVO.getMonCompId())
                .likeIfPresent(DeviceInfoDO::getMonCompName, reqVO.getMonCompName())
                .eqIfPresent(DeviceInfoDO::getMonCompCode, reqVO.getMonCompCode())
                .eqIfPresent(DeviceInfoDO::getAppSceneId, reqVO.getAppSceneId())
                .likeIfPresent(DeviceInfoDO::getAppSceneName, reqVO.getAppSceneName())
                .eqIfPresent(DeviceInfoDO::getAppSceneCode, reqVO.getAppSceneCode())
                .eqIfPresent(DeviceInfoDO::getAssetId, reqVO.getAssetId())
                .likeIfPresent(DeviceInfoDO::getAssetName, reqVO.getAssetName())
                .eqIfPresent(DeviceInfoDO::getAssetCode, reqVO.getAssetCode())
                .eqIfPresent(DeviceInfoDO::getMainDeviceId, reqVO.getMainDeviceId())
                .likeIfPresent(DeviceInfoDO::getMainDeviceName, reqVO.getMainDeviceName())
                .eqIfPresent(DeviceInfoDO::getMainDeviceCode, reqVO.getMainDeviceCode())
                .eqIfPresent(DeviceInfoDO::getInstallLocation, reqVO.getInstallLocation())
                .eqIfPresent(DeviceInfoDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(DeviceInfoDO::getCoordY, reqVO.getCoordY())
                .eqIfPresent(DeviceInfoDO::getElevation, reqVO.getElevation())
                .eqIfPresent(DeviceInfoDO::getPositionAccuracy, reqVO.getPositionAccuracy())
                .eqIfPresent(DeviceInfoDO::getIpAddress, reqVO.getIpAddress())
                .eqIfPresent(DeviceInfoDO::getMacAddress, reqVO.getMacAddress())
                .eqIfPresent(DeviceInfoDO::getSerialNumber, reqVO.getSerialNumber())
                .eqIfPresent(DeviceInfoDO::getFirmwareVersion, reqVO.getFirmwareVersion())
                .eqIfPresent(DeviceInfoDO::getSoftwareVersion, reqVO.getSoftwareVersion())
                .eqIfPresent(DeviceInfoDO::getCommunicationProtocol, reqVO.getCommunicationProtocol())
                .eqIfPresent(DeviceInfoDO::getDataReportInterval, reqVO.getDataReportInterval())
                .eqIfPresent(DeviceInfoDO::getSamplingFrequency, reqVO.getSamplingFrequency())
                .eqIfPresent(DeviceInfoDO::getVoltageLevel, reqVO.getVoltageLevel())
                .eqIfPresent(DeviceInfoDO::getPowerSupply, reqVO.getPowerSupply())
                .eqIfPresent(DeviceInfoDO::getBatteryCapacity, reqVO.getBatteryCapacity())
                .eqIfPresent(DeviceInfoDO::getBatteryLevel, reqVO.getBatteryLevel())
                .eqIfPresent(DeviceInfoDO::getSignalStrength, reqVO.getSignalStrength())
                .eqIfPresent(DeviceInfoDO::getEnvironmentTemp, reqVO.getEnvironmentTemp())
                .eqIfPresent(DeviceInfoDO::getEnvironmentHumidity, reqVO.getEnvironmentHumidity())
                .eqIfPresent(DeviceInfoDO::getMaintenanceCycle, reqVO.getMaintenanceCycle())
                .betweenIfPresent(DeviceInfoDO::getLastMaintenanceDate, reqVO.getLastMaintenanceDate())
                .betweenIfPresent(DeviceInfoDO::getNextMaintenanceDate, reqVO.getNextMaintenanceDate())
                .eqIfPresent(DeviceInfoDO::getMaintenanceOrg, reqVO.getMaintenanceOrg())
                .eqIfPresent(DeviceInfoDO::getMaintenancePerson, reqVO.getMaintenancePerson())
                .eqIfPresent(DeviceInfoDO::getMaintenancePhone, reqVO.getMaintenancePhone())
                .eqIfPresent(DeviceInfoDO::getRemark, reqVO.getRemark())
                .eqIfPresent(DeviceInfoDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceInfoDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceInfoDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceInfoDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceInfoDO::getId));
    }

}