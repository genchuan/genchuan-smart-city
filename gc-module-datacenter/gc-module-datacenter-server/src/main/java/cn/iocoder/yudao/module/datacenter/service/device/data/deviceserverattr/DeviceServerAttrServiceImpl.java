package cn.iocoder.yudao.module.datacenter.service.device.data.deviceserverattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr.vo.DeviceServerAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr.vo.DeviceServerAttrSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceserverattr.DeviceServerAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.deviceserverattr.DeviceServerAttrMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备服务器属性配置 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceServerAttrServiceImpl implements DeviceServerAttrService {

    @Resource
    private DeviceServerAttrMapper deviceServerAttrMapper;

    @Override
    public Long createDeviceServerAttr(DeviceServerAttrSaveReqVO createReqVO) {
        // 插入
        DeviceServerAttrDO deviceServerAttr = BeanUtils.toBean(createReqVO, DeviceServerAttrDO.class);
        deviceServerAttrMapper.insert(deviceServerAttr);
        // 返回
        return deviceServerAttr.getId();
    }

    @Override
    public void updateDeviceServerAttr(DeviceServerAttrSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceServerAttrExists(updateReqVO.getId());
        // 更新
        DeviceServerAttrDO updateObj = BeanUtils.toBean(updateReqVO, DeviceServerAttrDO.class);
        deviceServerAttrMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceServerAttr(Long id) {
        // 校验存在
        validateDeviceServerAttrExists(id);
        // 删除
        deviceServerAttrMapper.deleteById(id);
    }

    private void validateDeviceServerAttrExists(Long id) {
        if (deviceServerAttrMapper.selectById(id) == null) {
            throw exception(DEVICE_SERVER_ATTR_NOT_EXISTS);
        }
    }

    @Override
    public DeviceServerAttrDO getDeviceServerAttr(Long id) {
        return deviceServerAttrMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceServerAttrDO> getDeviceServerAttrPage(DeviceServerAttrPageReqVO pageReqVO) {
        return deviceServerAttrMapper.selectPage(pageReqVO);
    }

}