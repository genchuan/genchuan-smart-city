package cn.iocoder.yudao.module.datacenter.service.device.data.deviceshareattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr.vo.DeviceShareAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr.vo.DeviceShareAttrSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceshareattr.DeviceShareAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.deviceshareattr.DeviceShareAttrMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备共享属性配置 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceShareAttrServiceImpl implements DeviceShareAttrService {

    @Resource
    private DeviceShareAttrMapper deviceShareAttrMapper;

    @Override
    public Long createDeviceShareAttr(DeviceShareAttrSaveReqVO createReqVO) {
        // 插入
        DeviceShareAttrDO deviceShareAttr = BeanUtils.toBean(createReqVO, DeviceShareAttrDO.class);
        deviceShareAttrMapper.insert(deviceShareAttr);
        // 返回
        return deviceShareAttr.getId();
    }

    @Override
    public void updateDeviceShareAttr(DeviceShareAttrSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceShareAttrExists(updateReqVO.getId());
        // 更新
        DeviceShareAttrDO updateObj = BeanUtils.toBean(updateReqVO, DeviceShareAttrDO.class);
        deviceShareAttrMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceShareAttr(Long id) {
        // 校验存在
        validateDeviceShareAttrExists(id);
        // 删除
        deviceShareAttrMapper.deleteById(id);
    }

    private void validateDeviceShareAttrExists(Long id) {
        if (deviceShareAttrMapper.selectById(id) == null) {
            throw exception(DEVICE_SHARE_ATTR_NOT_EXISTS);
        }
    }

    @Override
    public DeviceShareAttrDO getDeviceShareAttr(Long id) {
        return deviceShareAttrMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceShareAttrDO> getDeviceShareAttrPage(DeviceShareAttrPageReqVO pageReqVO) {
        return deviceShareAttrMapper.selectPage(pageReqVO);
    }

}