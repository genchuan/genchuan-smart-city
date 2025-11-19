package cn.iocoder.yudao.module.datacenter.service.evaluate.task.evaltasktemplate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo.EvalTaskTemplatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltasktemplate.vo.EvalTaskTemplateSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltasktemplate.EvalTaskTemplateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.task.evaltasktemplate.EvalTaskTemplateMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 任务模板管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalTaskTemplateServiceImpl implements EvalTaskTemplateService {

    @Resource
    private EvalTaskTemplateMapper evalTaskTemplateMapper;

    @Override
    public Long createEvalTaskTemplate(EvalTaskTemplateSaveReqVO createReqVO) {
        // 插入
        EvalTaskTemplateDO evalTaskTemplate = BeanUtils.toBean(createReqVO, EvalTaskTemplateDO.class);
        evalTaskTemplateMapper.insert(evalTaskTemplate);
        // 返回
        return evalTaskTemplate.getId();
    }

    @Override
    public void updateEvalTaskTemplate(EvalTaskTemplateSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalTaskTemplateExists(updateReqVO.getId());
        // 更新
        EvalTaskTemplateDO updateObj = BeanUtils.toBean(updateReqVO, EvalTaskTemplateDO.class);
        evalTaskTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalTaskTemplate(Long id) {
        // 校验存在
        validateEvalTaskTemplateExists(id);
        // 删除
        evalTaskTemplateMapper.deleteById(id);
    }

    private void validateEvalTaskTemplateExists(Long id) {
        if (evalTaskTemplateMapper.selectById(id) == null) {
            throw exception(EVAL_TASK_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public EvalTaskTemplateDO getEvalTaskTemplate(Long id) {
        return evalTaskTemplateMapper.selectById(id);
    }

    @Override
    public PageResult<EvalTaskTemplateDO> getEvalTaskTemplatePage(EvalTaskTemplatePageReqVO pageReqVO) {
        return evalTaskTemplateMapper.selectPage(pageReqVO);
    }

}