package cn.iocoder.yudao.module.evaluate.service.questionnaire;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo.QuestionnairePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo.QuestionnaireSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.questionnaire.QuestionnaireDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.questionnaire.QuestionnaireMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.QUESTIONNAIRE_NOT_EXISTS;

/**
 * 问卷 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @Override
    public Long createQuestionnaire(QuestionnaireSaveReqVO createReqVO) {
        // 插入
        QuestionnaireDO questionnaire = BeanUtils.toBean(createReqVO, QuestionnaireDO.class);
        questionnaireMapper.insert(questionnaire);
        // 返回
        return questionnaire.getId();
    }

    @Override
    public void updateQuestionnaire(QuestionnaireSaveReqVO updateReqVO) {
        // 校验存在
        validateQuestionnaireExists(updateReqVO.getId());
        // 更新
        QuestionnaireDO updateObj = BeanUtils.toBean(updateReqVO, QuestionnaireDO.class);
        questionnaireMapper.updateById(updateObj);
    }

    @Override
    public void deleteQuestionnaire(Long id) {
        // 校验存在
        validateQuestionnaireExists(id);
        // 删除
        questionnaireMapper.deleteById(id);
    }

    private void validateQuestionnaireExists(Long id) {
        if (questionnaireMapper.selectById(id) == null) {
            throw exception(QUESTIONNAIRE_NOT_EXISTS);
        }
    }

    @Override
    public QuestionnaireDO getQuestionnaire(Long id) {
        return questionnaireMapper.selectById(id);
    }

    @Override
    public PageResult<QuestionnaireDO> getQuestionnairePage(QuestionnairePageReqVO pageReqVO) {
        return questionnaireMapper.selectPage(pageReqVO);
    }

}