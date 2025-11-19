package cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalquestionnaire;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo.EvalQuestionnairePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalquestionnaire.vo.EvalQuestionnaireSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalquestionnaire.EvalQuestionnaireDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 问卷调查管理 Service 接口
 *
 * @author zcq
 */
public interface EvalQuestionnaireService {

    /**
     * 创建问卷调查管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalQuestionnaire(@Valid EvalQuestionnaireSaveReqVO createReqVO);

    /**
     * 更新问卷调查管理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalQuestionnaire(@Valid EvalQuestionnaireSaveReqVO updateReqVO);

    /**
     * 删除问卷调查管理
     *
     * @param id 编号
     */
    void deleteEvalQuestionnaire(Long id);

    /**
     * 获得问卷调查管理
     *
     * @param id 编号
     * @return 问卷调查管理
     */
    EvalQuestionnaireDO getEvalQuestionnaire(Long id);

    /**
     * 获得问卷调查管理分页
     *
     * @param pageReqVO 分页查询
     * @return 问卷调查管理分页
     */
    PageResult<EvalQuestionnaireDO> getEvalQuestionnairePage(EvalQuestionnairePageReqVO pageReqVO);

}