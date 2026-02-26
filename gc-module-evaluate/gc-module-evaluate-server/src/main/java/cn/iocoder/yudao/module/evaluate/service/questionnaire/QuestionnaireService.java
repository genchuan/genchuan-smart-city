package cn.iocoder.yudao.module.evaluate.service.questionnaire;

import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo.QuestionnairePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.questionnaire.vo.QuestionnaireSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.questionnaire.QuestionnaireDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 问卷 Service 接口
 *
 * @author 亘川智城
 */
public interface QuestionnaireService {

    /**
     * 创建问卷
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createQuestionnaire(@Valid QuestionnaireSaveReqVO createReqVO);

    /**
     * 更新问卷
     *
     * @param updateReqVO 更新信息
     */
    void updateQuestionnaire(@Valid QuestionnaireSaveReqVO updateReqVO);

    /**
     * 删除问卷
     *
     * @param id 编号
     */
    void deleteQuestionnaire(Long id);

    /**
     * 获得问卷
     *
     * @param id 编号
     * @return 问卷
     */
    QuestionnaireDO getQuestionnaire(Long id);

    /**
     * 获得问卷分页
     *
     * @param pageReqVO 分页查询
     * @return 问卷分页
     */
    PageResult<QuestionnaireDO> getQuestionnairePage(QuestionnairePageReqVO pageReqVO);

}