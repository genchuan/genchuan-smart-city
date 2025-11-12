package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domdevicemonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo.DomDeviceMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo.DomDeviceMonRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domdevicemonrpt.DomDeviceMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domdevicemonrpt.DomDeviceMonRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 设备分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomDeviceMonRptServiceImpl implements DomDeviceMonRptService {

    @Resource
    private DomDeviceMonRptMapper domDeviceMonRptMapper;

    @Override
    public Long createDomDeviceMonRpt(DomDeviceMonRptSaveReqVO createReqVO) {
        // 插入
        DomDeviceMonRptDO domDeviceMonRpt = BeanUtils.toBean(createReqVO, DomDeviceMonRptDO.class);
        domDeviceMonRptMapper.insert(domDeviceMonRpt);
        // 返回
        return domDeviceMonRpt.getId();
    }

    @Override
    public void updateDomDeviceMonRpt(DomDeviceMonRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomDeviceMonRptExists(updateReqVO.getId());
        // 更新
        DomDeviceMonRptDO updateObj = BeanUtils.toBean(updateReqVO, DomDeviceMonRptDO.class);
        domDeviceMonRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomDeviceMonRpt(Long id) {
        // 校验存在
        validateDomDeviceMonRptExists(id);
        // 删除
        domDeviceMonRptMapper.deleteById(id);
    }

    private void validateDomDeviceMonRptExists(Long id) {
        if (domDeviceMonRptMapper.selectById(id) == null) {
            throw exception(DOM_DEVICE_MON_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomDeviceMonRptDO getDomDeviceMonRpt(Long id) {
        return domDeviceMonRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomDeviceMonRptDO> getDomDeviceMonRptPage(DomDeviceMonRptPageReqVO pageReqVO) {
        return domDeviceMonRptMapper.selectPage(pageReqVO);
    }

}