package cn.iocoder.yudao.module.envirhealth.service.user.assessment;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessment.AssessmentPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessment.AssessmentSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AssessmentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 考核 Service 接口
 *
 * @author 芋道源码
 */
public interface AssessmentService {

    /**
     * 创建考核
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAssessment(@Valid AssessmentSaveReqVO createReqVO);

    /**
     * 更新考核
     *
     * @param updateReqVO 更新信息
     */
    void updateAssessment(@Valid AssessmentSaveReqVO updateReqVO);

    /**
     * 删除考核
     *
     * @param id 编号
     */
    void deleteAssessment(Long id);

    /**
     * 获得考核
     *
     * @param id 编号
     * @return 考核
     */
    AssessmentDO getAssessment(Long id);

    /**
     * 获得考核分页
     *
     * @param pageReqVO 分页查询
     * @return 考核分页
     */
    PageResult<AssessmentDO> getAssessmentPage(AssessmentPageReqVO pageReqVO);

}