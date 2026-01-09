package cn.iocoder.yudao.module.smartcity.service.questionclassification;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.questionclassification.vo.QuestionClassificationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.questionclassification.vo.QuestionClassificationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.questionclassification.QuestionClassificationDO;
import jakarta.validation.Valid;

/**
 * 问题录入 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface QuestionClassificationService {

    /**
     * 创建问题录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createQuestionClassification(@Valid QuestionClassificationSaveReqVO createReqVO);

    /**
     * 更新问题录入
     *
     * @param updateReqVO 更新信息
     */
    void updateQuestionClassification(@Valid QuestionClassificationSaveReqVO updateReqVO);

    /**
     * 删除问题录入
     *
     * @param id 编号
     */
    void deleteQuestionClassification(Long id);

    /**
     * 获得问题录入
     *
     * @param id 编号
     * @return 问题录入
     */
    QuestionClassificationDO getQuestionClassification(Long id);

    /**
     * 获得问题录入分页
     *
     * @param pageReqVO 分页查询
     * @return 问题录入分页
     */
    PageResult<QuestionClassificationDO> getQuestionClassificationPage(QuestionClassificationPageReqVO pageReqVO);

}