package cn.iocoder.yudao.module.datacenter.dal.mysql.device.status.deviceofflinealert;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo.DeviceOfflineAlertPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.deviceofflinealert.DeviceOfflineAlertDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备离线预警 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceOfflineAlertMapper extends BaseMapperX<DeviceOfflineAlertDO> {

    default PageResult<DeviceOfflineAlertDO> selectPage(DeviceOfflineAlertPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceOfflineAlertDO>()
                .eqIfPresent(DeviceOfflineAlertDO::getOfflineAlertId, reqVO.getOfflineAlertId())
                .eqIfPresent(DeviceOfflineAlertDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceOfflineAlertDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceOfflineAlertDO::getDeviceCode, reqVO.getDeviceCode())
                .betweenIfPresent(DeviceOfflineAlertDO::getOfflineStartTime, reqVO.getOfflineStartTime())
                .eqIfPresent(DeviceOfflineAlertDO::getOfflineEndure, reqVO.getOfflineEndure())
                .eqIfPresent(DeviceOfflineAlertDO::getAlertLevel, reqVO.getAlertLevel())
                .eqIfPresent(DeviceOfflineAlertDO::getAlertUserId, reqVO.getAlertUserId())
                .likeIfPresent(DeviceOfflineAlertDO::getAlertUserName, reqVO.getAlertUserName())
                .betweenIfPresent(DeviceOfflineAlertDO::getAlertPushTime, reqVO.getAlertPushTime())
                .eqIfPresent(DeviceOfflineAlertDO::getAlertStatus, reqVO.getAlertStatus())
                .eqIfPresent(DeviceOfflineAlertDO::getHandleUserId, reqVO.getHandleUserId())
                .likeIfPresent(DeviceOfflineAlertDO::getHandleUserName, reqVO.getHandleUserName())
                .betweenIfPresent(DeviceOfflineAlertDO::getHandleTime, reqVO.getHandleTime())
                .eqIfPresent(DeviceOfflineAlertDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(DeviceOfflineAlertDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(DeviceOfflineAlertDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(DeviceOfflineAlertDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceOfflineAlertDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceOfflineAlertDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceOfflineAlertDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceOfflineAlertDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceOfflineAlertDO::getId));
    }

}