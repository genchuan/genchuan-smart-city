package cn.iocoder.yudao.module.datacenter.dal.mysql.device.status.devicerealtimestatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo.DeviceRealTimeStatusPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.devicerealtimestatus.DeviceRealTimeStatusDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备实时状态 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceRealTimeStatusMapper extends BaseMapperX<DeviceRealTimeStatusDO> {

    default PageResult<DeviceRealTimeStatusDO> selectPage(DeviceRealTimeStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceRealTimeStatusDO>()
                .eqIfPresent(DeviceRealTimeStatusDO::getRealTimeStatusId, reqVO.getRealTimeStatusId())
                .eqIfPresent(DeviceRealTimeStatusDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceRealTimeStatusDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceRealTimeStatusDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceRealTimeStatusDO::getRunStatus, reqVO.getRunStatus())
                .betweenIfPresent(DeviceRealTimeStatusDO::getLastRptTime, reqVO.getLastRptTime())
                .eqIfPresent(DeviceRealTimeStatusDO::getRealTimeParam1, reqVO.getRealTimeParam1())
                .eqIfPresent(DeviceRealTimeStatusDO::getRealTimeParam2, reqVO.getRealTimeParam2())
                .betweenIfPresent(DeviceRealTimeStatusDO::getStatusUpdateTime, reqVO.getStatusUpdateTime())
                .eqIfPresent(DeviceRealTimeStatusDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(DeviceRealTimeStatusDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(DeviceRealTimeStatusDO::getGridId, reqVO.getGridId())
                .likeIfPresent(DeviceRealTimeStatusDO::getGridName, reqVO.getGridName())
                .eqIfPresent(DeviceRealTimeStatusDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceRealTimeStatusDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceRealTimeStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceRealTimeStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceRealTimeStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceRealTimeStatusDO::getId));
    }

}