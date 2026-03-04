package cn.iocoder.yudao.module.facility.service.sysdevice;

import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.SysDevicePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.SysDeviceSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysdevice.SysDeviceDO;
import cn.iocoder.yudao.module.facility.dal.mysql.sysdevice.SysDeviceMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.*;

/**
 * 设备 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SysDeviceServiceImpl implements SysDeviceService {

    @Resource
    private SysDeviceMapper sysDeviceMapper;

    @Override
    public Long createSysDevice(SysDeviceSaveReqVO createReqVO) {
        // 插入
        SysDeviceDO sysDevice = BeanUtils.toBean(createReqVO, SysDeviceDO.class);
        sysDeviceMapper.insert(sysDevice);
        // 返回
        return sysDevice.getId();
    }

    @Override
    public void updateSysDevice(SysDeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateSysDeviceExists(updateReqVO.getId());
        // 更新
        SysDeviceDO updateObj = BeanUtils.toBean(updateReqVO, SysDeviceDO.class);
        sysDeviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteSysDevice(Long id) {
        // 校验存在
        validateSysDeviceExists(id);
        // 删除
        sysDeviceMapper.deleteById(id);
    }

    private void validateSysDeviceExists(Long id) {
        if (sysDeviceMapper.selectById(id) == null) {
            throw exception(SYS_DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public SysDeviceDO getSysDevice(Long id) {
        return sysDeviceMapper.selectById(id);
    }

    @Override
    public PageResult<SysDeviceDO> getSysDevicePage(SysDevicePageReqVO pageReqVO) {
        return sysDeviceMapper.selectPage(pageReqVO);
    }

}
