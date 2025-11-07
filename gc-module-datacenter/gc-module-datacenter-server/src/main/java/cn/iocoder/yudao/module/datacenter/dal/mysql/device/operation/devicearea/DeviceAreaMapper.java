package cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicearea;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo.DeviceAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicearea.DeviceAreaDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备关联行政区划 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceAreaMapper extends BaseMapperX<DeviceAreaDO> {

    default PageResult<DeviceAreaDO> selectPage(DeviceAreaPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceAreaDO>()
                .eqIfPresent(DeviceAreaDO::getDeviceRelRegionId, reqVO.getDeviceRelRegionId())
                .eqIfPresent(DeviceAreaDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceAreaDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceAreaDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceAreaDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(DeviceAreaDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(DeviceAreaDO::getRegionLevel, reqVO.getRegionLevel())
                .betweenIfPresent(DeviceAreaDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(DeviceAreaDO::getRelUser, reqVO.getRelUser())
                .eqIfPresent(DeviceAreaDO::getRelStatus, reqVO.getRelStatus())
                .betweenIfPresent(DeviceAreaDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(DeviceAreaDO::getAdjustUser, reqVO.getAdjustUser())
                .eqIfPresent(DeviceAreaDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(DeviceAreaDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceAreaDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceAreaDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceAreaDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceAreaDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceAreaDO::getId));
    }

}