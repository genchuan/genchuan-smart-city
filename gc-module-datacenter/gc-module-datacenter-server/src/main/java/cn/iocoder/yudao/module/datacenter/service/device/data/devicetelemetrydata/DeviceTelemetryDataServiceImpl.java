package cn.iocoder.yudao.module.datacenter.service.device.data.devicetelemetrydata;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo.DeviceTelemetryDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo.DeviceTelemetryDataSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicetelemetrydata.DeviceTelemetryDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.devicetelemetrydata.DeviceTelemetryDataMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备遥测数据 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceTelemetryDataServiceImpl implements DeviceTelemetryDataService {

    @Resource
    private DeviceTelemetryDataMapper deviceTelemetryDataMapper;

    @Override
    public Long createDeviceTelemetryData(DeviceTelemetryDataSaveReqVO createReqVO) {
        // 插入
        DeviceTelemetryDataDO deviceTelemetryData = BeanUtils.toBean(createReqVO, DeviceTelemetryDataDO.class);
        deviceTelemetryDataMapper.insert(deviceTelemetryData);
        // 返回
        return deviceTelemetryData.getId();
    }

    @Override
    public void updateDeviceTelemetryData(DeviceTelemetryDataSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceTelemetryDataExists(updateReqVO.getId());
        // 更新
        DeviceTelemetryDataDO updateObj = BeanUtils.toBean(updateReqVO, DeviceTelemetryDataDO.class);
        deviceTelemetryDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceTelemetryData(Long id) {
        // 校验存在
        validateDeviceTelemetryDataExists(id);
        // 删除
        deviceTelemetryDataMapper.deleteById(id);
    }

    private void validateDeviceTelemetryDataExists(Long id) {
        if (deviceTelemetryDataMapper.selectById(id) == null) {
            throw exception(DEVICE_TELEMETRY_DATA_NOT_EXISTS);
        }
    }

    @Override
    public DeviceTelemetryDataDO getDeviceTelemetryData(Long id) {
        return deviceTelemetryDataMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceTelemetryDataDO> getDeviceTelemetryDataPage(DeviceTelemetryDataPageReqVO pageReqVO) {
        return deviceTelemetryDataMapper.selectPage(pageReqVO);
    }

}