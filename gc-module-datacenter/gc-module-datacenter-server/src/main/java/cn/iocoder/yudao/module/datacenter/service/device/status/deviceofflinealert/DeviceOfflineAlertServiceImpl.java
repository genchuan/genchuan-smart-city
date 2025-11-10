package cn.iocoder.yudao.module.datacenter.service.device.status.deviceofflinealert;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo.DeviceOfflineAlertPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo.DeviceOfflineAlertSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.deviceofflinealert.DeviceOfflineAlertDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.status.deviceofflinealert.DeviceOfflineAlertMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备离线预警 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceOfflineAlertServiceImpl implements DeviceOfflineAlertService {

    @Resource
    private DeviceOfflineAlertMapper deviceOfflineAlertMapper;

    @Override
    public Long createDeviceOfflineAlert(DeviceOfflineAlertSaveReqVO createReqVO) {
        // 插入
        DeviceOfflineAlertDO deviceOfflineAlert = BeanUtils.toBean(createReqVO, DeviceOfflineAlertDO.class);
        deviceOfflineAlertMapper.insert(deviceOfflineAlert);
        // 返回
        return deviceOfflineAlert.getId();
    }

    @Override
    public void updateDeviceOfflineAlert(DeviceOfflineAlertSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceOfflineAlertExists(updateReqVO.getId());
        // 更新
        DeviceOfflineAlertDO updateObj = BeanUtils.toBean(updateReqVO, DeviceOfflineAlertDO.class);
        deviceOfflineAlertMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceOfflineAlert(Long id) {
        // 校验存在
        validateDeviceOfflineAlertExists(id);
        // 删除
        deviceOfflineAlertMapper.deleteById(id);
    }

    private void validateDeviceOfflineAlertExists(Long id) {
        if (deviceOfflineAlertMapper.selectById(id) == null) {
            throw exception(DEVICE_OFFLINE_ALERT_NOT_EXISTS);
        }
    }

    @Override
    public DeviceOfflineAlertDO getDeviceOfflineAlert(Long id) {
        return deviceOfflineAlertMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceOfflineAlertDO> getDeviceOfflineAlertPage(DeviceOfflineAlertPageReqVO pageReqVO) {
        return deviceOfflineAlertMapper.selectPage(pageReqVO);
    }

}