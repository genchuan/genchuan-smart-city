package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domadminmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt.vo.DomAdminMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt.vo.DomAdminMonRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domadminmonrpt.DomAdminMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.domadminmonrpt.DomAdminMonRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 行政区划分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomAdminMonRptServiceImpl implements DomAdminMonRptService {

    @Resource
    private DomAdminMonRptMapper domAdminMonRptMapper;

    @Override
    public Long createDomAdminMonRpt(DomAdminMonRptSaveReqVO createReqVO) {
        // 插入
        DomAdminMonRptDO domAdminMonRpt = BeanUtils.toBean(createReqVO, DomAdminMonRptDO.class);
        domAdminMonRptMapper.insert(domAdminMonRpt);
        // 返回
        return domAdminMonRpt.getId();
    }

    @Override
    public void updateDomAdminMonRpt(DomAdminMonRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomAdminMonRptExists(updateReqVO.getId());
        // 更新
        DomAdminMonRptDO updateObj = BeanUtils.toBean(updateReqVO, DomAdminMonRptDO.class);
        domAdminMonRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomAdminMonRpt(Long id) {
        // 校验存在
        validateDomAdminMonRptExists(id);
        // 删除
        domAdminMonRptMapper.deleteById(id);
    }

    private void validateDomAdminMonRptExists(Long id) {
        if (domAdminMonRptMapper.selectById(id) == null) {
            throw exception(DOM_ADMIN_MON_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomAdminMonRptDO getDomAdminMonRpt(Long id) {
        return domAdminMonRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomAdminMonRptDO> getDomAdminMonRptPage(DomAdminMonRptPageReqVO pageReqVO) {
        return domAdminMonRptMapper.selectPage(pageReqVO);
    }

}