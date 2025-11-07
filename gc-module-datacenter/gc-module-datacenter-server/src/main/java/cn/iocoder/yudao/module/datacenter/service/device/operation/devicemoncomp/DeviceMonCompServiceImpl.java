package cn.iocoder.yudao.module.datacenter.service.device.operation.devicemoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo.DeviceMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo.DeviceMonCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicemoncomp.DeviceMonCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicemoncomp.DeviceMonCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备关联监测部件 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceMonCompServiceImpl implements DeviceMonCompService {

    @Resource
    private DeviceMonCompMapper deviceMonCompMapper;

    @Override
    public Long createDeviceMonComp(DeviceMonCompSaveReqVO createReqVO) {
        // 插入
        DeviceMonCompDO deviceMonComp = BeanUtils.toBean(createReqVO, DeviceMonCompDO.class);
        deviceMonCompMapper.insert(deviceMonComp);
        // 返回
        return deviceMonComp.getId();
    }

    @Override
    public void updateDeviceMonComp(DeviceMonCompSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceMonCompExists(updateReqVO.getId());
        // 更新
        DeviceMonCompDO updateObj = BeanUtils.toBean(updateReqVO, DeviceMonCompDO.class);
        deviceMonCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceMonComp(Long id) {
        // 校验存在
        validateDeviceMonCompExists(id);
        // 删除
        deviceMonCompMapper.deleteById(id);
    }

    private void validateDeviceMonCompExists(Long id) {
        if (deviceMonCompMapper.selectById(id) == null) {
            throw exception(DEVICE_MON_COMP_NOT_EXISTS);
        }
    }

    @Override
    public DeviceMonCompDO getDeviceMonComp(Long id) {
        return deviceMonCompMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceMonCompDO> getDeviceMonCompPage(DeviceMonCompPageReqVO pageReqVO) {
        return deviceMonCompMapper.selectPage(pageReqVO);
    }

}