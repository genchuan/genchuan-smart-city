package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommoncompmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo.DomMonCompMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo.DomMonCompMonRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommoncompmonrpt.DomMonCompMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.dommoncompmonrpt.DomMonCompMonRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测部件分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomMonCompMonRptServiceImpl implements DomMonCompMonRptService {

    @Resource
    private DomMonCompMonRptMapper domMonCompMonRptMapper;

    @Override
    public Long createDomMonCompMonRpt(DomMonCompMonRptSaveReqVO createReqVO) {
        // 插入
        DomMonCompMonRptDO domMonCompMonRpt = BeanUtils.toBean(createReqVO, DomMonCompMonRptDO.class);
        domMonCompMonRptMapper.insert(domMonCompMonRpt);
        // 返回
        return domMonCompMonRpt.getId();
    }

    @Override
    public void updateDomMonCompMonRpt(DomMonCompMonRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomMonCompMonRptExists(updateReqVO.getId());
        // 更新
        DomMonCompMonRptDO updateObj = BeanUtils.toBean(updateReqVO, DomMonCompMonRptDO.class);
        domMonCompMonRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomMonCompMonRpt(Long id) {
        // 校验存在
        validateDomMonCompMonRptExists(id);
        // 删除
        domMonCompMonRptMapper.deleteById(id);
    }

    private void validateDomMonCompMonRptExists(Long id) {
        if (domMonCompMonRptMapper.selectById(id) == null) {
            throw exception(DOM_MON_COMP_MON_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomMonCompMonRptDO getDomMonCompMonRpt(Long id) {
        return domMonCompMonRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomMonCompMonRptDO> getDomMonCompMonRptPage(DomMonCompMonRptPageReqVO pageReqVO) {
        return domMonCompMonRptMapper.selectPage(pageReqVO);
    }

}