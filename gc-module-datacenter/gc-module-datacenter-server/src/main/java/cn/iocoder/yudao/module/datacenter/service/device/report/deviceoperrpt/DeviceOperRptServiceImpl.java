package cn.iocoder.yudao.module.datacenter.service.device.report.deviceoperrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo.DeviceOperRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo.DeviceOperRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.deviceoperrpt.DeviceOperRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.report.deviceoperrpt.DeviceOperRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备运行状态统计 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceOperRptServiceImpl implements DeviceOperRptService {

    @Resource
    private DeviceOperRptMapper deviceOperRptMapper;

    @Override
    public Long createDeviceOperRpt(DeviceOperRptSaveReqVO createReqVO) {
        // 插入
        DeviceOperRptDO deviceOperRpt = BeanUtils.toBean(createReqVO, DeviceOperRptDO.class);
        deviceOperRptMapper.insert(deviceOperRpt);
        // 返回
        return deviceOperRpt.getId();
    }

    @Override
    public void updateDeviceOperRpt(DeviceOperRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceOperRptExists(updateReqVO.getId());
        // 更新
        DeviceOperRptDO updateObj = BeanUtils.toBean(updateReqVO, DeviceOperRptDO.class);
        deviceOperRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceOperRpt(Long id) {
        // 校验存在
        validateDeviceOperRptExists(id);
        // 删除
        deviceOperRptMapper.deleteById(id);
    }

    private void validateDeviceOperRptExists(Long id) {
        if (deviceOperRptMapper.selectById(id) == null) {
            throw exception(DEVICE_OPER_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DeviceOperRptDO getDeviceOperRpt(Long id) {
        return deviceOperRptMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceOperRptDO> getDeviceOperRptPage(DeviceOperRptPageReqVO pageReqVO) {
        return deviceOperRptMapper.selectPage(pageReqVO);
    }

}