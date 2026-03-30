package cn.iocoder.yudao.module.envirhealth.service.user.assessmentgrade;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessmentgrade.AssessmentGradePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessmentgrade.AssessmentGradeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AssessmentGradeDO;
import jakarta.validation.Valid;

/**
 * 考核等级字典表 Service 接口
 *
 * @author 芋道源码
 */
public interface AssessmentGradeService {

    /**
     * 创建考核等级字典表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssessmentGrade(@Valid AssessmentGradeSaveReqVO createReqVO);

    /**
     * 更新考核等级字典表
     *
     * @param updateReqVO 更新信息
     */
    void updateAssessmentGrade(@Valid AssessmentGradeSaveReqVO updateReqVO);

    /**
     * 删除考核等级字典表
     *
     * @param id 编号
     */
    void deleteAssessmentGrade(Long id);

    /**
     * 获得考核等级字典表
     *
     * @param id 编号
     * @return 考核等级字典表
     */
    AssessmentGradeDO getAssessmentGrade(Long id);

    /**
     * 获得考核等级字典表分页
     *
     * @param pageReqVO 分页查询
     * @return 考核等级字典表分页
     */
    PageResult<AssessmentGradeDO> getAssessmentGradePage(AssessmentGradePageReqVO pageReqVO);

}