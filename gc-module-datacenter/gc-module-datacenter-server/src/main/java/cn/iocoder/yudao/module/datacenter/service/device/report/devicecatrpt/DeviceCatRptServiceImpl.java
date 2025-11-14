package cn.iocoder.yudao.module.datacenter.service.device.report.devicecatrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo.DeviceCatRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo.DeviceCatRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.devicecatrpt.DeviceCatRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.device.report.devicecatrpt.DeviceCatRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备分类统计 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class DeviceCatRptServiceImpl implements DeviceCatRptService {

    @Resource
    private DeviceCatRptMapper deviceCatRptMapper;

    @Override
    public Long createDeviceCatRpt(DeviceCatRptSaveReqVO createReqVO) {
        // 插入
        DeviceCatRptDO deviceCatRpt = BeanUtils.toBean(createReqVO, DeviceCatRptDO.class);
        deviceCatRptMapper.insert(deviceCatRpt);
        // 返回
        return deviceCatRpt.getId();
    }

    @Override
    public void updateDeviceCatRpt(DeviceCatRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceCatRptExists(updateReqVO.getId());
        // 更新
        DeviceCatRptDO updateObj = BeanUtils.toBean(updateReqVO, DeviceCatRptDO.class);
        deviceCatRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDeviceCatRpt(Long id) {
        // 校验存在
        validateDeviceCatRptExists(id);
        // 删除
        deviceCatRptMapper.deleteById(id);
    }

    private void validateDeviceCatRptExists(Long id) {
        if (deviceCatRptMapper.selectById(id) == null) {
            throw exception(DEVICE_CAT_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DeviceCatRptDO getDeviceCatRpt(Long id) {
        return deviceCatRptMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceCatRptDO> getDeviceCatRptPage(DeviceCatRptPageReqVO pageReqVO) {
        return deviceCatRptMapper.selectPage(pageReqVO);
    }

}