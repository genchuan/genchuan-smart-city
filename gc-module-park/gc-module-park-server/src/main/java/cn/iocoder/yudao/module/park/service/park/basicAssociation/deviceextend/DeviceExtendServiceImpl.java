package cn.iocoder.yudao.module.park.service.park.basicAssociation.deviceextend;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo.DeviceExtendPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo.DeviceExtendSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.deviceextend.DeviceExtendDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.deviceextend.DeviceExtendMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 设备扩展 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceExtendServiceImpl implements DeviceExtendService {

    @Resource
    private DeviceExtendMapper deviceExtendMapper;

    @Override
    public Long createDeviceExtend(DeviceExtendSaveReqVO createReqVO) {
        // 插入
        DeviceExtendDO deviceExtend = BeanUtils.toBean(createReqVO, DeviceExtendDO.class);
        deviceExtendMapper.insert(deviceExtend);
        // 返回
        return deviceExtend.getId();
    }

    @Override
    public void updateDeviceExtend(DeviceExtendSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceExtendExists(updateReqVO.getId());
        // 更新
        DeviceExtendDO updateObj = BeanUtils.toBean(updateReqVO, DeviceExtendDO.class);
        deviceExtendMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceExtend(Long id) {
        // 校验存在
        validateDeviceExtendExists(id);
        // 删除
        deviceExtendMapper.deleteById(id);
    }

    private void validateDeviceExtendExists(Long id) {
        if (deviceExtendMapper.selectById(id) == null) {
            throw exception(DEVICE_EXTEND_NOT_EXISTS);
        }
    }

    @Override
    public DeviceExtendDO getDeviceExtend(Long id) {
        return deviceExtendMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceExtendDO> getDeviceExtendPage(DeviceExtendPageReqVO pageReqVO) {
        return deviceExtendMapper.selectPage(pageReqVO);
    }

}