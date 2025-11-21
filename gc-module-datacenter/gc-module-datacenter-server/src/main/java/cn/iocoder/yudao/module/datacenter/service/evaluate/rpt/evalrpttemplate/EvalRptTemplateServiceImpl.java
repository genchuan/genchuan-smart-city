package cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrpttemplate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo.EvalRptTemplatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpttemplate.vo.EvalRptTemplateSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpttemplate.EvalRptTemplateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rpt.evalrpttemplate.EvalRptTemplateMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 报告模板配置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalRptTemplateServiceImpl implements EvalRptTemplateService {

    @Resource
    private EvalRptTemplateMapper evalRptTemplateMapper;

    @Override
    public Long createEvalRptTemplate(EvalRptTemplateSaveReqVO createReqVO) {
        // 插入
        EvalRptTemplateDO evalRptTemplate = BeanUtils.toBean(createReqVO, EvalRptTemplateDO.class);
        evalRptTemplateMapper.insert(evalRptTemplate);
        // 返回
        return evalRptTemplate.getId();
    }

    @Override
    public void updateEvalRptTemplate(EvalRptTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalRptTemplateExists(updateReqVO.getId());
        // 更新
        EvalRptTemplateDO updateObj = BeanUtils.toBean(updateReqVO, EvalRptTemplateDO.class);
        evalRptTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalRptTemplate(Long id) {
        // 校验存在
        validateEvalRptTemplateExists(id);
        // 删除
        evalRptTemplateMapper.deleteById(id);
    }

    private void validateEvalRptTemplateExists(Long id) {
        if (evalRptTemplateMapper.selectById(id) == null) {
            throw exception(EVAL_RPT_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public EvalRptTemplateDO getEvalRptTemplate(Long id) {
        return evalRptTemplateMapper.selectById(id);
    }

    @Override
    public PageResult<EvalRptTemplateDO> getEvalRptTemplatePage(EvalRptTemplatePageReqVO pageReqVO) {
        return evalRptTemplateMapper.selectPage(pageReqVO);
    }

}