package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommngcompmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo.DomMngCompMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo.DomMngCompMonRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngcompmonrpt.DomMngCompMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.dommngcompmonrpt.DomMngCompMonRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomMngCompMonRptServiceImpl implements DomMngCompMonRptService {

    @Resource
    private DomMngCompMonRptMapper domMngCompMonRptMapper;

    @Override
    public Long createDomMngCompMonRpt(DomMngCompMonRptSaveReqVO createReqVO) {
        // 插入
        DomMngCompMonRptDO domMngCompMonRpt = BeanUtils.toBean(createReqVO, DomMngCompMonRptDO.class);
        domMngCompMonRptMapper.insert(domMngCompMonRpt);
        // 返回
        return domMngCompMonRpt.getId();
    }

    @Override
    public void updateDomMngCompMonRpt(DomMngCompMonRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomMngCompMonRptExists(updateReqVO.getId());
        // 更新
        DomMngCompMonRptDO updateObj = BeanUtils.toBean(updateReqVO, DomMngCompMonRptDO.class);
        domMngCompMonRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomMngCompMonRpt(Long id) {
        // 校验存在
        validateDomMngCompMonRptExists(id);
        // 删除
        domMngCompMonRptMapper.deleteById(id);
    }

    private void validateDomMngCompMonRptExists(Long id) {
        if (domMngCompMonRptMapper.selectById(id) == null) {
            throw exception(DOM_MNG_COMP_MON_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomMngCompMonRptDO getDomMngCompMonRpt(Long id) {
        return domMngCompMonRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomMngCompMonRptDO> getDomMngCompMonRptPage(DomMngCompMonRptPageReqVO pageReqVO) {
        return domMngCompMonRptMapper.selectPage(pageReqVO);
    }

}