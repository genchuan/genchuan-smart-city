package cn.iocoder.yudao.module.smartcity.service.assessmentandevaluation;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.assessmentandevaluation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.assessmentandevaluation.AssessmentAndEvaluationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.assessmentandevaluation.AssessmentAndEvaluationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 养护考核评价 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class AssessmentAndEvaluationServiceImpl implements AssessmentAndEvaluationService {

    @Resource
    private AssessmentAndEvaluationMapper assessmentAndEvaluationMapper;

    @Override
    public Long createAssessmentAndEvaluation(AssessmentAndEvaluationSaveReqVO createReqVO) {
        // 插入
        AssessmentAndEvaluationDO assessmentAndEvaluation = BeanUtils.toBean(createReqVO, AssessmentAndEvaluationDO.class);
        assessmentAndEvaluationMapper.insert(assessmentAndEvaluation);
        // 返回
        return assessmentAndEvaluation.getId();
    }

    @Override
    public void updateAssessmentAndEvaluation(AssessmentAndEvaluationSaveReqVO updateReqVO) {
        // 校验存在
        validateAssessmentAndEvaluationExists(updateReqVO.getId());
        // 更新
        AssessmentAndEvaluationDO updateObj = BeanUtils.toBean(updateReqVO, AssessmentAndEvaluationDO.class);
        assessmentAndEvaluationMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssessmentAndEvaluation(Long id) {
        // 校验存在
        validateAssessmentAndEvaluationExists(id);
        // 删除
        assessmentAndEvaluationMapper.deleteById(id);
    }

    private void validateAssessmentAndEvaluationExists(Long id) {
        if (assessmentAndEvaluationMapper.selectById(id) == null) {
            throw exception(ASSESSMENT_AND_EVALUATION_NOT_EXISTS);
        }
    }

    @Override
    public AssessmentAndEvaluationDO getAssessmentAndEvaluation(Long id) {
        return assessmentAndEvaluationMapper.selectById(id);
    }

    @Override
    public PageResult<AssessmentAndEvaluationDO> getAssessmentAndEvaluationPage(AssessmentAndEvaluationPageReqVO pageReqVO) {
        return assessmentAndEvaluationMapper.selectPage(pageReqVO);
    }

}