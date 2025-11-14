package cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.devicespatialdata;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo.DeviceSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicespatialdata.DeviceSpatialDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备空间数据 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceSpatialDataMapper extends BaseMapperX<DeviceSpatialDataDO> {

    default PageResult<DeviceSpatialDataDO> selectPage(DeviceSpatialDataPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceSpatialDataDO>()
                .eqIfPresent(DeviceSpatialDataDO::getDeviceSpatialId, reqVO.getDeviceSpatialId())
                .eqIfPresent(DeviceSpatialDataDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceSpatialDataDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceSpatialDataDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceSpatialDataDO::getCoordSystem, reqVO.getCoordSystem())
                .eqIfPresent(DeviceSpatialDataDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(DeviceSpatialDataDO::getCoordY, reqVO.getCoordY())
                .eqIfPresent(DeviceSpatialDataDO::getElevation, reqVO.getElevation())
                .eqIfPresent(DeviceSpatialDataDO::getPositionAccuracy, reqVO.getPositionAccuracy())
                .eqIfPresent(DeviceSpatialDataDO::getSpatialDataFormat, reqVO.getSpatialDataFormat())
                .eqIfPresent(DeviceSpatialDataDO::getDataSource, reqVO.getDataSource())
                .betweenIfPresent(DeviceSpatialDataDO::getInputTime, reqVO.getInputTime())
                .eqIfPresent(DeviceSpatialDataDO::getInputUser, reqVO.getInputUser())
                .eqIfPresent(DeviceSpatialDataDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .eqIfPresent(DeviceSpatialDataDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(DeviceSpatialDataDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceSpatialDataDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceSpatialDataDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceSpatialDataDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceSpatialDataDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceSpatialDataDO::getId));
    }

}