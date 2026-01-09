package cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.device;


import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.device.DeviceDO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DevicePageReqVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DeviceMapper extends BaseMapperX<DeviceDO> {

    default PageResult<DeviceDO> selectPage(DevicePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DeviceDO>()
                .eqIfPresent(DeviceDO::getTbTenantId, reqVO.getTbTenantId())
                .eqIfPresent(DeviceDO::getCustomerId, reqVO.getCustomerId())
                .likeIfPresent(DeviceDO::getName, reqVO.getName())
                .eqIfPresent(DeviceDO::getType, reqVO.getType())
                .eqIfPresent(DeviceDO::getLabel, reqVO.getLabel())
                .eqIfPresent(DeviceDO::getDeviceProfileId, reqVO.getDeviceProfileId())
                .eqIfPresent(DeviceDO::getDeviceData, reqVO.getDeviceData())
                .eqIfPresent(DeviceDO::getFirmwareId, reqVO.getFirmwareId())
                .eqIfPresent(DeviceDO::getSoftwareId, reqVO.getSoftwareId())
                .eqIfPresent(DeviceDO::getExternalId, reqVO.getExternalId())
                .eqIfPresent(DeviceDO::getVersion, reqVO.getVersion())
                .betweenIfPresent(DeviceDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(DeviceDO::getAttributes, reqVO.getAttributes())
                .eqIfPresent(DeviceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DeviceDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(DeviceDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(DeviceDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(DeviceDO::getId));
    }

    /**
     * 根据设备ID查询
     */
    DeviceDO selectByDeviceId(String deviceId);

}