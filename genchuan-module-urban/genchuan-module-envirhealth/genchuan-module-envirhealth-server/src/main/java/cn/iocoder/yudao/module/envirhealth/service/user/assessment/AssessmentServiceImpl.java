package cn.iocoder.yudao.module.envirhealth.service.user.assessment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessment.AssessmentPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessment.AssessmentSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AssessmentDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.AssessmentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.ASSESSMENT_NOT_EXISTS;

/**
 * 考核 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AssessmentServiceImpl implements AssessmentService {

    @Resource
    private AssessmentMapper assessmentMapper;

    @Override
    public Long createAssessment(AssessmentSaveReqVO createReqVO) {
        // 插入
        AssessmentDO assessment = BeanUtils.toBean(createReqVO, AssessmentDO.class);
        assessmentMapper.insert(assessment);
        // 返回
        return assessment.getId();
    }

    @Override
    public void updateAssessment(AssessmentSaveReqVO updateReqVO) {
        // 校验存在
        validateAssessmentExists(updateReqVO.getId());
        // 更新
        AssessmentDO updateObj = BeanUtils.toBean(updateReqVO, AssessmentDO.class);
        assessmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteAssessment(Long id) {
        // 校验存在
        validateAssessmentExists(id);
        // 删除
        assessmentMapper.deleteById(id);
    }

    private void validateAssessmentExists(Long id) {
        if (assessmentMapper.selectById(id) == null) {
            throw exception(ASSESSMENT_NOT_EXISTS);
        }
    }

    @Override
    public AssessmentDO getAssessment(Long id) {
        return assessmentMapper.selectById(id);
    }

    @Override
    public PageResult<AssessmentDO> getAssessmentPage(AssessmentPageReqVO pageReqVO) {
        return assessmentMapper.selectPage(pageReqVO);
    }

}