package cn.iocoder.yudao.module.datacenter.service.thingsboard;


import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.DeviceMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.Dao.DeviceTbDao;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.DeviceDO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DeviceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.id.EntityId;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.DEVICE_NOT_EXISTS;


/**
 * 设备 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private DeviceTbDao deviceTbDao;

    @Override
    public String createDevice(DeviceSaveReqVO createReqVO) {
        // 插入
        DeviceDO device = BeanUtils.toBean(createReqVO, DeviceDO.class);
        deviceMapper.insert(device);

        // 返回
        return device.getId();
    }

    @Override
    public void updateDevice(DeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceExists(updateReqVO.getId());
        // 更新
        DeviceDO updateObj = BeanUtils.toBean(updateReqVO, DeviceDO.class);
        deviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteDevice(String id) {
        // 校验存在
        validateDeviceExists(id);
        // 删除
        deviceMapper.deleteById(id);
    }

    @Override
    public void deleteDeviceListByIds(List<String> ids) {
        // 删除
        deviceMapper.deleteByIds(ids);
    }


    private void validateDeviceExists(String id) {
        if (deviceMapper.selectById(id) == null) {
            throw exception(DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceInfo getDevice(String id) {
        return deviceTbDao.getDeviceInfoById(id);
    }

    @Override
    public PageResult<Device> getDevicePage(DevicePageReqVO pageReqVO) {
        return deviceTbDao.getDevicePage(pageReqVO);
    }

    @Override
    public List<AttributeKvEntry> getAttributeKvEntries(String id) {
        return deviceTbDao.getAttributeKvEntries(id);
    }

}