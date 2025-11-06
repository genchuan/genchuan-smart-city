package cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicedevice;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo.DeviceDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicedevice.DeviceDeviceDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备关联设备 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceDeviceMapper extends BaseMapperX<DeviceDeviceDO> {

    default PageResult<DeviceDeviceDO> selectPage(DeviceDevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceDeviceDO>()
                .eqIfPresent(DeviceDeviceDO::getDeviceRelDeviceId, reqVO.getDeviceRelDeviceId())
                .eqIfPresent(DeviceDeviceDO::getMainDeviceId, reqVO.getMainDeviceId())
                .likeIfPresent(DeviceDeviceDO::getMainDeviceName, reqVO.getMainDeviceName())
                .eqIfPresent(DeviceDeviceDO::getMainDeviceCode, reqVO.getMainDeviceCode())
                .eqIfPresent(DeviceDeviceDO::getSlaveDeviceId, reqVO.getSlaveDeviceId())
                .likeIfPresent(DeviceDeviceDO::getSlaveDeviceName, reqVO.getSlaveDeviceName())
                .eqIfPresent(DeviceDeviceDO::getSlaveDeviceCode, reqVO.getSlaveDeviceCode())
                .eqIfPresent(DeviceDeviceDO::getRelType, reqVO.getRelType())
                .betweenIfPresent(DeviceDeviceDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(DeviceDeviceDO::getRelUser, reqVO.getRelUser())
                .eqIfPresent(DeviceDeviceDO::getRelStatus, reqVO.getRelStatus())
                .betweenIfPresent(DeviceDeviceDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(DeviceDeviceDO::getAdjustUser, reqVO.getAdjustUser())
                .eqIfPresent(DeviceDeviceDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(DeviceDeviceDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceDeviceDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceDeviceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceDeviceDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceDeviceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceDeviceDO::getId));
    }

}