package cn.iocoder.yudao.module.smartcity.service.monitoringdevice;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringdevice.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringdevice.MonitoringDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.monitoringdevice.MonitoringDeviceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 监测设备 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class MonitoringDeviceServiceImpl implements MonitoringDeviceService {

    @Resource
    private MonitoringDeviceMapper monitoringDeviceMapper;

    @Override
    public Long createMonitoringDevice(MonitoringDeviceSaveReqVO createReqVO) {
        // 插入
        MonitoringDeviceDO monitoringDevice = BeanUtils.toBean(createReqVO, MonitoringDeviceDO.class);
        monitoringDeviceMapper.insert(monitoringDevice);
        // 返回
        return monitoringDevice.getId();
    }

    @Override
    public void updateMonitoringDevice(MonitoringDeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateMonitoringDeviceExists(updateReqVO.getId());
        // 更新
        MonitoringDeviceDO updateObj = BeanUtils.toBean(updateReqVO, MonitoringDeviceDO.class);
        monitoringDeviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitoringDevice(Long id) {
        // 校验存在
        validateMonitoringDeviceExists(id);
        // 删除
        monitoringDeviceMapper.deleteById(id);
    }

    private void validateMonitoringDeviceExists(Long id) {
        if (monitoringDeviceMapper.selectById(id) == null) {
            throw exception(MONITORING_DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public MonitoringDeviceDO getMonitoringDevice(Long id) {
        return monitoringDeviceMapper.selectById(id);
    }

    @Override
    public PageResult<MonitoringDeviceDO> getMonitoringDevicePage(MonitoringDevicePageReqVO pageReqVO) {
        return monitoringDeviceMapper.selectPage(pageReqVO);
    }

}