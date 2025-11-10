package cn.iocoder.yudao.module.datacenter.service.device.data.deviceclientattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo.DeviceClientAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo.DeviceClientAttrSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceclientattr.DeviceClientAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.deviceclientattr.DeviceClientAttrMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备客户端属性配置 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceClientAttrServiceImpl implements DeviceClientAttrService {

    @Resource
    private DeviceClientAttrMapper deviceClientAttrMapper;

    @Override
    public Long createDeviceClientAttr(DeviceClientAttrSaveReqVO createReqVO) {
        // 插入
        DeviceClientAttrDO deviceClientAttr = BeanUtils.toBean(createReqVO, DeviceClientAttrDO.class);
        deviceClientAttrMapper.insert(deviceClientAttr);
        // 返回
        return deviceClientAttr.getId();
    }

    @Override
    public void updateDeviceClientAttr(DeviceClientAttrSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceClientAttrExists(updateReqVO.getId());
        // 更新
        DeviceClientAttrDO updateObj = BeanUtils.toBean(updateReqVO, DeviceClientAttrDO.class);
        deviceClientAttrMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceClientAttr(Long id) {
        // 校验存在
        validateDeviceClientAttrExists(id);
        // 删除
        deviceClientAttrMapper.deleteById(id);
    }

    private void validateDeviceClientAttrExists(Long id) {
        if (deviceClientAttrMapper.selectById(id) == null) {
            throw exception(DEVICE_CLIENT_ATTR_NOT_EXISTS);
        }
    }

    @Override
    public DeviceClientAttrDO getDeviceClientAttr(Long id) {
        return deviceClientAttrMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceClientAttrDO> getDeviceClientAttrPage(DeviceClientAttrPageReqVO pageReqVO) {
        return deviceClientAttrMapper.selectPage(pageReqVO);
    }

}