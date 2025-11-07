package cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicegrid;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo.DeviceGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicegrid.DeviceGridDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备关联网格 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceGridMapper extends BaseMapperX<DeviceGridDO> {

    default PageResult<DeviceGridDO> selectPage(DeviceGridPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceGridDO>()
                .eqIfPresent(DeviceGridDO::getDeviceRelGridId, reqVO.getDeviceRelGridId())
                .eqIfPresent(DeviceGridDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceGridDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceGridDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceGridDO::getGridId, reqVO.getGridId())
                .likeIfPresent(DeviceGridDO::getGridName, reqVO.getGridName())
                .eqIfPresent(DeviceGridDO::getGridCode, reqVO.getGridCode())
                .betweenIfPresent(DeviceGridDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(DeviceGridDO::getRelUser, reqVO.getRelUser())
                .eqIfPresent(DeviceGridDO::getRelStatus, reqVO.getRelStatus())
                .betweenIfPresent(DeviceGridDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(DeviceGridDO::getAdjustUser, reqVO.getAdjustUser())
                .eqIfPresent(DeviceGridDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(DeviceGridDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceGridDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceGridDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceGridDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceGridDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceGridDO::getId));
    }

}