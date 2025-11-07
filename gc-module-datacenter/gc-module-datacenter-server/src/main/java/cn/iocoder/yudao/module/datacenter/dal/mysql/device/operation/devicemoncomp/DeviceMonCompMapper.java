package cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicemoncomp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo.DeviceMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicemoncomp.DeviceMonCompDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备关联监测部件 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface DeviceMonCompMapper extends BaseMapperX<DeviceMonCompDO> {

    default PageResult<DeviceMonCompDO> selectPage(DeviceMonCompPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceMonCompDO>()
                .eqIfPresent(DeviceMonCompDO::getDeviceRelMonCompId, reqVO.getDeviceRelMonCompId())
                .eqIfPresent(DeviceMonCompDO::getDeviceId, reqVO.getDeviceId())
                .likeIfPresent(DeviceMonCompDO::getDeviceName, reqVO.getDeviceName())
                .eqIfPresent(DeviceMonCompDO::getDeviceCode, reqVO.getDeviceCode())
                .eqIfPresent(DeviceMonCompDO::getMonCompId, reqVO.getMonCompId())
                .likeIfPresent(DeviceMonCompDO::getMonCompName, reqVO.getMonCompName())
                .eqIfPresent(DeviceMonCompDO::getMonCompCode, reqVO.getMonCompCode())
                .betweenIfPresent(DeviceMonCompDO::getRelTime, reqVO.getRelTime())
                .eqIfPresent(DeviceMonCompDO::getRelUser, reqVO.getRelUser())
                .eqIfPresent(DeviceMonCompDO::getRelStatus, reqVO.getRelStatus())
                .betweenIfPresent(DeviceMonCompDO::getAdjustTime, reqVO.getAdjustTime())
                .eqIfPresent(DeviceMonCompDO::getAdjustUser, reqVO.getAdjustUser())
                .eqIfPresent(DeviceMonCompDO::getAdjustReason, reqVO.getAdjustReason())
                .eqIfPresent(DeviceMonCompDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DeviceMonCompDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DeviceMonCompDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceMonCompDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(DeviceMonCompDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DeviceMonCompDO::getId));
    }

}