package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarndevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo.EarlyWarnDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo.EarlyWarnDeviceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarndevice.EarlyWarnDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarndevice.EarlyWarnDeviceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 按设备分域预警告警统计 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EarlyWarnDeviceServiceImpl implements EarlyWarnDeviceService {

    @Resource
    private EarlyWarnDeviceMapper earlyWarnDeviceMapper;

    @Override
    public Long createEarlyWarnDevice(EarlyWarnDeviceSaveReqVO createReqVO) {
        // 插入
        EarlyWarnDeviceDO earlyWarnDevice = BeanUtils.toBean(createReqVO, EarlyWarnDeviceDO.class);
        earlyWarnDeviceMapper.insert(earlyWarnDevice);
        // 返回
        return earlyWarnDevice.getId();
    }

    @Override
    public void updateEarlyWarnDevice(EarlyWarnDeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateEarlyWarnDeviceExists(updateReqVO.getId());
        // 更新
        EarlyWarnDeviceDO updateObj = BeanUtils.toBean(updateReqVO, EarlyWarnDeviceDO.class);
        earlyWarnDeviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteEarlyWarnDevice(Long id) {
        // 校验存在
        validateEarlyWarnDeviceExists(id);
        // 删除
        earlyWarnDeviceMapper.deleteById(id);
    }

    private void validateEarlyWarnDeviceExists(Long id) {
        if (earlyWarnDeviceMapper.selectById(id) == null) {
            throw exception(EARLY_WARN_DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public EarlyWarnDeviceDO getEarlyWarnDevice(Long id) {
        return earlyWarnDeviceMapper.selectById(id);
    }

    @Override
    public PageResult<EarlyWarnDeviceDO> getEarlyWarnDevicePage(EarlyWarnDevicePageReqVO pageReqVO) {
        return earlyWarnDeviceMapper.selectPage(pageReqVO);
    }

}