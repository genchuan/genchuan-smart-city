package cn.iocoder.yudao.module.datacenter.service.device.status.devicerealtimestatus;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo.DeviceRealTimeStatusPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo.DeviceRealTimeStatusSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.devicerealtimestatus.DeviceRealTimeStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.status.devicerealtimestatus.DeviceRealTimeStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备实时状态 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceRealTimeStatusServiceImpl implements DeviceRealTimeStatusService {

    @Resource
    private DeviceRealTimeStatusMapper deviceRealTimeStatusMapper;

    @Override
    public Long createDeviceRealTimeStatus(DeviceRealTimeStatusSaveReqVO createReqVO) {
        // 插入
        DeviceRealTimeStatusDO deviceRealTimeStatus = BeanUtils.toBean(createReqVO, DeviceRealTimeStatusDO.class);
        deviceRealTimeStatusMapper.insert(deviceRealTimeStatus);
        // 返回
        return deviceRealTimeStatus.getId();
    }

    @Override
    public void updateDeviceRealTimeStatus(DeviceRealTimeStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceRealTimeStatusExists(updateReqVO.getId());
        // 更新
        DeviceRealTimeStatusDO updateObj = BeanUtils.toBean(updateReqVO, DeviceRealTimeStatusDO.class);
        deviceRealTimeStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceRealTimeStatus(Long id) {
        // 校验存在
        validateDeviceRealTimeStatusExists(id);
        // 删除
        deviceRealTimeStatusMapper.deleteById(id);
    }

    private void validateDeviceRealTimeStatusExists(Long id) {
        if (deviceRealTimeStatusMapper.selectById(id) == null) {
            throw exception(DEVICE_REAL_TIME_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public DeviceRealTimeStatusDO getDeviceRealTimeStatus(Long id) {
        return deviceRealTimeStatusMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceRealTimeStatusDO> getDeviceRealTimeStatusPage(DeviceRealTimeStatusPageReqVO pageReqVO) {
        return deviceRealTimeStatusMapper.selectPage(pageReqVO);
    }

}