package cn.iocoder.yudao.module.datacenter.service.device.deviceinfo;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.deviceinfo.DeviceInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.deviceinfo.DeviceInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.DEVICE_INFO_NOT_EXISTS;

/**
 * 设备信息 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Resource
    private DeviceInfoMapper deviceInfoMapper;

    @Override
    public Long createDeviceInfo(DeviceInfoSaveReqVO createReqVO) {
        // 插入
        DeviceInfoDO deviceInfo = BeanUtils.toBean(createReqVO, DeviceInfoDO.class);
        deviceInfoMapper.insert(deviceInfo);
        // 返回
        return deviceInfo.getId();
    }

    @Override
    public void updateDeviceInfo(DeviceInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceInfoExists(updateReqVO.getId());
        // 更新
        DeviceInfoDO updateObj = BeanUtils.toBean(updateReqVO, DeviceInfoDO.class);
        deviceInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceInfo(Long id) {
        // 校验存在
        validateDeviceInfoExists(id);
        // 删除
        deviceInfoMapper.deleteById(id);
    }

    private void validateDeviceInfoExists(Long id) {
        if (deviceInfoMapper.selectById(id) == null) {
            throw exception(DEVICE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public DeviceInfoDO getDeviceInfo(Long id) {
        return deviceInfoMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceInfoDO> getDeviceInfoPage(DeviceInfoPageReqVO pageReqVO) {
        return deviceInfoMapper.selectPage(pageReqVO);
    }

}