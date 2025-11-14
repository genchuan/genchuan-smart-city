package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommngmatterrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt.vo.DomMngMatterRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt.vo.DomMngMatterRptSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngmatterrpt.DomMngMatterRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monitor.regionalReport.dommngmatterrpt.DomMngMatterRptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理事项分域监测报表 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class DomMngMatterRptServiceImpl implements DomMngMatterRptService {

    @Resource
    private DomMngMatterRptMapper domMngMatterRptMapper;

    @Override
    public Long createDomMngMatterRpt(DomMngMatterRptSaveReqVO createReqVO) {
        // 插入
        DomMngMatterRptDO domMngMatterRpt = BeanUtils.toBean(createReqVO, DomMngMatterRptDO.class);
        domMngMatterRptMapper.insert(domMngMatterRpt);
        // 返回
        return domMngMatterRpt.getId();
    }

    @Override
    public void updateDomMngMatterRpt(DomMngMatterRptSaveReqVO updateReqVO) {
        // 校验存在
        validateDomMngMatterRptExists(updateReqVO.getId());
        // 更新
        DomMngMatterRptDO updateObj = BeanUtils.toBean(updateReqVO, DomMngMatterRptDO.class);
        domMngMatterRptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomMngMatterRpt(Long id) {
        // 校验存在
        validateDomMngMatterRptExists(id);
        // 删除
        domMngMatterRptMapper.deleteById(id);
    }

    private void validateDomMngMatterRptExists(Long id) {
        if (domMngMatterRptMapper.selectById(id) == null) {
            throw exception(DOM_MNG_MATTER_RPT_NOT_EXISTS);
        }
    }

    @Override
    public DomMngMatterRptDO getDomMngMatterRpt(Long id) {
        return domMngMatterRptMapper.selectById(id);
    }

    @Override
    public PageResult<DomMngMatterRptDO> getDomMngMatterRptPage(DomMngMatterRptPageReqVO pageReqVO) {
        return domMngMatterRptMapper.selectPage(pageReqVO);
    }

}