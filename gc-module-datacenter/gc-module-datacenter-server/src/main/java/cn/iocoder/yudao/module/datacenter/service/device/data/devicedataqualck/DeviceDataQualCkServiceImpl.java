package cn.iocoder.yudao.module.datacenter.service.device.data.devicedataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo.DeviceDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo.DeviceDataQualCkSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicedataqualck.DeviceDataQualCkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.data.devicedataqualck.DeviceDataQualCkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备数据质量检查 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceDataQualCkServiceImpl implements DeviceDataQualCkService {

    @Resource
    private DeviceDataQualCkMapper deviceDataQualCkMapper;

    @Override
    public Long createDeviceDataQualCk(DeviceDataQualCkSaveReqVO createReqVO) {
        // 插入
        DeviceDataQualCkDO deviceDataQualCk = BeanUtils.toBean(createReqVO, DeviceDataQualCkDO.class);
        deviceDataQualCkMapper.insert(deviceDataQualCk);
        // 返回
        return deviceDataQualCk.getId();
    }

    @Override
    public void updateDeviceDataQualCk(DeviceDataQualCkSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceDataQualCkExists(updateReqVO.getId());
        // 更新
        DeviceDataQualCkDO updateObj = BeanUtils.toBean(updateReqVO, DeviceDataQualCkDO.class);
        deviceDataQualCkMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceDataQualCk(Long id) {
        // 校验存在
        validateDeviceDataQualCkExists(id);
        // 删除
        deviceDataQualCkMapper.deleteById(id);
    }

    private void validateDeviceDataQualCkExists(Long id) {
        if (deviceDataQualCkMapper.selectById(id) == null) {
            throw exception(DEVICE_DATA_QUAL_CK_NOT_EXISTS);
        }
    }

    @Override
    public DeviceDataQualCkDO getDeviceDataQualCk(Long id) {
        return deviceDataQualCkMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceDataQualCkDO> getDeviceDataQualCkPage(DeviceDataQualCkPageReqVO pageReqVO) {
        return deviceDataQualCkMapper.selectPage(pageReqVO);
    }

}