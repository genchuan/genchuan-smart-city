package cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.devicetelemetrydata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo.DeviceTelemetryDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicetelemetrydata.DeviceTelemetryDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备遥测数据 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceTelemetryDataMapper extends BaseMapperX<DeviceTelemetryDataDO> {

    default PageResult<DeviceTelemetryDataDO> selectPage(DeviceTelemetryDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceTelemetryDataDO>()
                .eqIfPresent(DeviceTelemetryDataDO::getTelemetryDataId, reqVO.getTelemetryDataId())
                .eqIfPresent(DeviceTelemetryDataDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceTelemetryDataDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceTelemetryDataDO::getDeviceCode, reqVO.getDeviceCode())
                .likeIfPresent(DeviceTelemetryDataDO::getTelemetryItemName, reqVO.getTelemetryItemName())
                .eqIfPresent(DeviceTelemetryDataDO::getTelemetryItemCode, reqVO.getTelemetryItemCode())
                .eqIfPresent(DeviceTelemetryDataDO::getTelemetryValue, reqVO.getTelemetryValue())
                .eqIfPresent(DeviceTelemetryDataDO::getDataType, reqVO.getDataType())
                .betweenIfPresent(DeviceTelemetryDataDO::getCollectTime, reqVO.getCollectTime())
                .eqIfPresent(DeviceTelemetryDataDO::getDataSource, reqVO.getDataSource())
                .eqIfPresent(DeviceTelemetryDataDO::getStorageCycle, reqVO.getStorageCycle())
                .eqIfPresent(DeviceTelemetryDataDO::getExpireStatus, reqVO.getExpireStatus())
                .eqIfPresent(DeviceTelemetryDataDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(DeviceTelemetryDataDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DeviceTelemetryDataDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceTelemetryDataDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceTelemetryDataDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceTelemetryDataDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceTelemetryDataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceTelemetryDataDO::getId));
    }

}