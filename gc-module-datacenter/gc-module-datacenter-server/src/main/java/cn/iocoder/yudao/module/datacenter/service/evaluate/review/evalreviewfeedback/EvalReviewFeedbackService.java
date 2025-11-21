package cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalreviewfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo.EvalReviewFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo.EvalReviewFeedbackSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreviewfeedback.EvalReviewFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 复核结果反馈 Service 接口
 *
 * @author zcq
 */
public interface EvalReviewFeedbackService {

    /**
     * 创建复核结果反馈
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalReviewFeedback(@Valid EvalReviewFeedbackSaveReqVO createReqVO);

    /**
     * 更新复核结果反馈
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalReviewFeedback(@Valid EvalReviewFeedbackSaveReqVO updateReqVO);

    /**
     * 删除复核结果反馈
     *
     * @param id 编号
     */
    void deleteEvalReviewFeedback(Long id);

    /**
     * 获得复核结果反馈
     *
     * @param id 编号
     * @return 复核结果反馈
     */
    EvalReviewFeedbackDO getEvalReviewFeedback(Long id);

    /**
     * 获得复核结果反馈分页
     *
     * @param pageReqVO 分页查询
     * @return 复核结果反馈分页
     */
    PageResult<EvalReviewFeedbackDO> getEvalReviewFeedbackPage(EvalReviewFeedbackPageReqVO pageReqVO);

}