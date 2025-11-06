package cn.iocoder.yudao.module.datacenter.service.device.operation.devicegrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo.DeviceGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo.DeviceGridSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicegrid.DeviceGridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.operation.devicegrid.DeviceGridMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备关联网格 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceGridServiceImpl implements DeviceGridService {

    @Resource
    private DeviceGridMapper deviceGridMapper;

    @Override
    public Long createDeviceGrid(DeviceGridSaveReqVO createReqVO) {
        // 插入
        DeviceGridDO deviceGrid = BeanUtils.toBean(createReqVO, DeviceGridDO.class);
        deviceGridMapper.insert(deviceGrid);
        // 返回
        return deviceGrid.getId();
    }

    @Override
    public void updateDeviceGrid(DeviceGridSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceGridExists(updateReqVO.getId());
        // 更新
        DeviceGridDO updateObj = BeanUtils.toBean(updateReqVO, DeviceGridDO.class);
        deviceGridMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceGrid(Long id) {
        // 校验存在
        validateDeviceGridExists(id);
        // 删除
        deviceGridMapper.deleteById(id);
    }

    private void validateDeviceGridExists(Long id) {
        if (deviceGridMapper.selectById(id) == null) {
            throw exception(DEVICE_GRID_NOT_EXISTS);
        }
    }

    @Override
    public DeviceGridDO getDeviceGrid(Long id) {
        return deviceGridMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceGridDO> getDeviceGridPage(DeviceGridPageReqVO pageReqVO) {
        return deviceGridMapper.selectPage(pageReqVO);
    }

}