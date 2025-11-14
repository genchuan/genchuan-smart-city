package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommonevtmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo.DomMonEvtMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo.DomMonEvtMonRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommonevtmonrpt.DomMonEvtMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.dommonevtmonrpt.DomMonEvtMonRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测事件分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomMonEvtMonRptServiceImpl implements DomMonEvtMonRptService {

    @Resource
    private DomMonEvtMonRptMapper domMonEvtMonRptMapper;

    @Override
    public Long createDomMonEvtMonRpt(DomMonEvtMonRptSaveReqVO createReqVO) {
        // 插入
        DomMonEvtMonRptDO domMonEvtMonRpt = BeanUtils.toBean(createReqVO, DomMonEvtMonRptDO.class);
        domMonEvtMonRptMapper.insert(domMonEvtMonRpt);
        // 返回
        return domMonEvtMonRpt.getId();
    }

    @Override
    public void updateDomMonEvtMonRpt(DomMonEvtMonRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomMonEvtMonRptExists(updateReqVO.getId());
        // 更新
        DomMonEvtMonRptDO updateObj = BeanUtils.toBean(updateReqVO, DomMonEvtMonRptDO.class);
        domMonEvtMonRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomMonEvtMonRpt(Long id) {
        // 校验存在
        validateDomMonEvtMonRptExists(id);
        // 删除
        domMonEvtMonRptMapper.deleteById(id);
    }

    private void validateDomMonEvtMonRptExists(Long id) {
        if (domMonEvtMonRptMapper.selectById(id) == null) {
            throw exception(DOM_MON_EVT_MON_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomMonEvtMonRptDO getDomMonEvtMonRpt(Long id) {
        return domMonEvtMonRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomMonEvtMonRptDO> getDomMonEvtMonRptPage(DomMonEvtMonRptPageReqVO pageReqVO) {
        return domMonEvtMonRptMapper.selectPage(pageReqVO);
    }

}