package cn.iocoder.yudao.module.datacenter.service.device.operation.devicearea;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo.DeviceAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo.DeviceAreaSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicearea.DeviceAreaDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicearea.DeviceAreaMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备关联行政区划 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceAreaServiceImpl implements DeviceAreaService {

    @Resource
    private DeviceAreaMapper deviceAreaMapper;

    @Override
    public Long createDeviceArea(DeviceAreaSaveReqVO createReqVO) {
        // 插入
        DeviceAreaDO deviceArea = BeanUtils.toBean(createReqVO, DeviceAreaDO.class);
        deviceAreaMapper.insert(deviceArea);
        // 返回
        return deviceArea.getId();
    }

    @Override
    public void updateDeviceArea(DeviceAreaSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceAreaExists(updateReqVO.getId());
        // 更新
        DeviceAreaDO updateObj = BeanUtils.toBean(updateReqVO, DeviceAreaDO.class);
        deviceAreaMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceArea(Long id) {
        // 校验存在
        validateDeviceAreaExists(id);
        // 删除
        deviceAreaMapper.deleteById(id);
    }

    private void validateDeviceAreaExists(Long id) {
        if (deviceAreaMapper.selectById(id) == null) {
            throw exception(DEVICE_AREA_NOT_EXISTS);
        }
    }

    @Override
    public DeviceAreaDO getDeviceArea(Long id) {
        return deviceAreaMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceAreaDO> getDeviceAreaPage(DeviceAreaPageReqVO pageReqVO) {
        return deviceAreaMapper.selectPage(pageReqVO);
    }

}