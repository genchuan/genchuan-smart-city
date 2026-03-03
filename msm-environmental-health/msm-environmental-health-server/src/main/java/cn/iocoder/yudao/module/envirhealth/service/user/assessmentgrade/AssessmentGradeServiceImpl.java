package cn.iocoder.yudao.module.envirhealth.service.user.assessmentgrade;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessmentgrade.AssessmentGradePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessmentgrade.AssessmentGradeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AssessmentGradeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.AssessmentGradeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 考核等级字典表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AssessmentGradeServiceImpl implements AssessmentGradeService {

    @Resource
    private AssessmentGradeMapper assessmentGradeMapper;

    @Override
    public Long createAssessmentGrade(AssessmentGradeSaveReqVO createReqVO) {
        // 插入
        AssessmentGradeDO assessmentGrade = BeanUtils.toBean(createReqVO, AssessmentGradeDO.class);
        assessmentGradeMapper.insert(assessmentGrade);
        // 返回
        return assessmentGrade.getId();
    }

    @Override
    public void updateAssessmentGrade(AssessmentGradeSaveReqVO updateReqVO) {
        // 校验存在
        validateAssessmentGradeExists(updateReqVO.getId());
        // 更新
        AssessmentGradeDO updateObj = BeanUtils.toBean(updateReqVO, AssessmentGradeDO.class);
        assessmentGradeMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssessmentGrade(Long id) {
        // 校验存在
        validateAssessmentGradeExists(id);
        // 删除
        assessmentGradeMapper.deleteById(id);
    }

    private void validateAssessmentGradeExists(Long id) {
        if (assessmentGradeMapper.selectById(id) == null) {
            throw exception(ASSESSMENT_GRADE_NOT_EXISTS);
        }
    }

    @Override
    public AssessmentGradeDO getAssessmentGrade(Long id) {
        return assessmentGradeMapper.selectById(id);
    }

    @Override
    public PageResult<AssessmentGradeDO> getAssessmentGradePage(AssessmentGradePageReqVO pageReqVO) {
        return assessmentGradeMapper.selectPage(pageReqVO);
    }

}