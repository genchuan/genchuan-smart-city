package cn.iocoder.yudao.module.datacenter.service.device.operation.devicedevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo.DeviceDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo.DeviceDeviceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicedevice.DeviceDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicedevice.DeviceDeviceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备关联设备 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceDeviceServiceImpl implements DeviceDeviceService {

    @Resource
    private DeviceDeviceMapper deviceDeviceMapper;

    @Override
    public Long createDeviceDevice(DeviceDeviceSaveReqVO createReqVO) {
        // 插入
        DeviceDeviceDO deviceDevice = BeanUtils.toBean(createReqVO, DeviceDeviceDO.class);
        deviceDeviceMapper.insert(deviceDevice);
        // 返回
        return deviceDevice.getId();
    }

    @Override
    public void updateDeviceDevice(DeviceDeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceDeviceExists(updateReqVO.getId());
        // 更新
        DeviceDeviceDO updateObj = BeanUtils.toBean(updateReqVO, DeviceDeviceDO.class);
        deviceDeviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceDevice(Long id) {
        // 校验存在
        validateDeviceDeviceExists(id);
        // 删除
        deviceDeviceMapper.deleteById(id);
    }

    private void validateDeviceDeviceExists(Long id) {
        if (deviceDeviceMapper.selectById(id) == null) {
            throw exception(DEVICE_DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceDeviceDO getDeviceDevice(Long id) {
        return deviceDeviceMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceDeviceDO> getDeviceDevicePage(DeviceDevicePageReqVO pageReqVO) {
        return deviceDeviceMapper.selectPage(pageReqVO);
    }

}