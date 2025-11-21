package cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalquestionnaire;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo.EvalQuestionnairePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo.EvalQuestionnaireSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalquestionnaire.EvalQuestionnaireDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.data.evalquestionnaire.EvalQuestionnaireMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 问卷调查管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalQuestionnaireServiceImpl implements EvalQuestionnaireService {

    @Resource
    private EvalQuestionnaireMapper evalQuestionnaireMapper;

    @Override
    public Long createEvalQuestionnaire(EvalQuestionnaireSaveReqVO createReqVO) {
        // 插入
        EvalQuestionnaireDO evalQuestionnaire = BeanUtils.toBean(createReqVO, EvalQuestionnaireDO.class);
        evalQuestionnaireMapper.insert(evalQuestionnaire);
        // 返回
        return evalQuestionnaire.getId();
    }

    @Override
    public void updateEvalQuestionnaire(EvalQuestionnaireSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalQuestionnaireExists(updateReqVO.getId());
        // 更新
        EvalQuestionnaireDO updateObj = BeanUtils.toBean(updateReqVO, EvalQuestionnaireDO.class);
        evalQuestionnaireMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalQuestionnaire(Long id) {
        // 校验存在
        validateEvalQuestionnaireExists(id);
        // 删除
        evalQuestionnaireMapper.deleteById(id);
    }

    private void validateEvalQuestionnaireExists(Long id) {
        if (evalQuestionnaireMapper.selectById(id) == null) {
            throw exception(EVAL_QUESTIONNAIRE_NOT_EXISTS);
        }
    }

    @Override
    public EvalQuestionnaireDO getEvalQuestionnaire(Long id) {
        return evalQuestionnaireMapper.selectById(id);
    }

    @Override
    public PageResult<EvalQuestionnaireDO> getEvalQuestionnairePage(EvalQuestionnairePageReqVO pageReqVO) {
        return evalQuestionnaireMapper.selectPage(pageReqVO);
    }

}