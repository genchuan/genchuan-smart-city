package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivdevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo.DomDivDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo.DomDivDeviceSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivdevice.DomDivDeviceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regional.domdivdevice.DomDivDeviceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备分域 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDivDeviceServiceImpl implements DomDivDeviceService {

    @Resource
    private DomDivDeviceMapper domDivDeviceMapper;

    @Override
    public Long createDomDivDevice(DomDivDeviceSaveReqVO createReqVO) {
        // 插入
        DomDivDeviceDO domDivDevice = BeanUtils.toBean(createReqVO, DomDivDeviceDO.class);
        domDivDeviceMapper.insert(domDivDevice);
        // 返回
        return domDivDevice.getId();
    }

    @Override
    public void updateDomDivDevice(DomDivDeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDivDeviceExists(updateReqVO.getId());
        // 更新
        DomDivDeviceDO updateObj = BeanUtils.toBean(updateReqVO, DomDivDeviceDO.class);
        domDivDeviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDivDevice(Long id) {
        // 校验存在
        validateDomDivDeviceExists(id);
        // 删除
        domDivDeviceMapper.deleteById(id);
    }

    private void validateDomDivDeviceExists(Long id) {
        if (domDivDeviceMapper.selectById(id) == null) {
            throw exception(DOM_DIV_DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public DomDivDeviceDO getDomDivDevice(Long id) {
        return domDivDeviceMapper.selectById(id);
    }

    @Override
    public PageResult<DomDivDeviceDO> getDomDivDevicePage(DomDivDevicePageReqVO pageReqVO) {
        return domDivDeviceMapper.selectPage(pageReqVO);
    }

}