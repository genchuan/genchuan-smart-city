package cn.iocoder.yudao.module.datacenter.service.device.data.devicespatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo.DeviceSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo.DeviceSpatialDataSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicespatialdata.DeviceSpatialDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.devicespatialdata.DeviceSpatialDataMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备空间数据 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceSpatialDataServiceImpl implements DeviceSpatialDataService {

    @Resource
    private DeviceSpatialDataMapper deviceSpatialDataMapper;

    @Override
    public Long createDeviceSpatialData(DeviceSpatialDataSaveReqVO createReqVO) {
        // 插入
        DeviceSpatialDataDO deviceSpatialData = BeanUtils.toBean(createReqVO, DeviceSpatialDataDO.class);
        deviceSpatialDataMapper.insert(deviceSpatialData);
        // 返回
        return deviceSpatialData.getId();
    }

    @Override
    public void updateDeviceSpatialData(DeviceSpatialDataSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceSpatialDataExists(updateReqVO.getId());
        // 更新
        DeviceSpatialDataDO updateObj = BeanUtils.toBean(updateReqVO, DeviceSpatialDataDO.class);
        deviceSpatialDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceSpatialData(Long id) {
        // 校验存在
        validateDeviceSpatialDataExists(id);
        // 删除
        deviceSpatialDataMapper.deleteById(id);
    }

    private void validateDeviceSpatialDataExists(Long id) {
        if (deviceSpatialDataMapper.selectById(id) == null) {
            throw exception(DEVICE_SPATIAL_DATA_NOT_EXISTS);
        }
    }

    @Override
    public DeviceSpatialDataDO getDeviceSpatialData(Long id) {
        return deviceSpatialDataMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceSpatialDataDO> getDeviceSpatialDataPage(DeviceSpatialDataPageReqVO pageReqVO) {
        return deviceSpatialDataMapper.selectPage(pageReqVO);
    }

}