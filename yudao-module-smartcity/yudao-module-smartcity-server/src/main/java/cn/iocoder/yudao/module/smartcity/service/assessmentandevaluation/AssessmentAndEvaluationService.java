package cn.iocoder.yudao.module.smartcity.service.assessmentandevaluation;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.assessmentandevaluation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.assessmentandevaluation.AssessmentAndEvaluationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import jakarta.validation.Valid;

/**
 * 养护考核评价 Service 接口
 *
 * @author zcq
 */
public interface AssessmentAndEvaluationService {

    /**
     * 创建养护考核评价
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssessmentAndEvaluation(@Valid AssessmentAndEvaluationSaveReqVO createReqVO);

    /**
     * 更新养护考核评价
     *
     * @param updateReqVO 更新信息
     */
    void updateAssessmentAndEvaluation(@Valid AssessmentAndEvaluationSaveReqVO updateReqVO);

    /**
     * 删除养护考核评价
     *
     * @param id 编号
     */
    void deleteAssessmentAndEvaluation(Long id);

    /**
     * 获得养护考核评价
     *
     * @param id 编号
     * @return 养护考核评价
     */
    AssessmentAndEvaluationDO getAssessmentAndEvaluation(Long id);

    /**
     * 获得养护考核评价分页
     *
     * @param pageReqVO 分页查询
     * @return 养护考核评价分页
     */
    PageResult<AssessmentAndEvaluationDO> getAssessmentAndEvaluationPage(AssessmentAndEvaluationPageReqVO pageReqVO);

}