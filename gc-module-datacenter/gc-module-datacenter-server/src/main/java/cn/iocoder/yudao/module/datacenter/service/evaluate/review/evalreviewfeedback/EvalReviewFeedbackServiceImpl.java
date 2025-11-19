package cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalreviewfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo.EvalReviewFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreviewfeedback.vo.EvalReviewFeedbackSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreviewfeedback.EvalReviewFeedbackDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.review.evalreviewfeedback.EvalReviewFeedbackMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 复核结果反馈 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalReviewFeedbackServiceImpl implements EvalReviewFeedbackService {

    @Resource
    private EvalReviewFeedbackMapper evalReviewFeedbackMapper;

    @Override
    public Long createEvalReviewFeedback(EvalReviewFeedbackSaveReqVO createReqVO) {
        // 插入
        EvalReviewFeedbackDO evalReviewFeedback = BeanUtils.toBean(createReqVO, EvalReviewFeedbackDO.class);
        evalReviewFeedbackMapper.insert(evalReviewFeedback);
        // 返回
        return evalReviewFeedback.getId();
    }

    @Override
    public void updateEvalReviewFeedback(EvalReviewFeedbackSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalReviewFeedbackExists(updateReqVO.getId());
        // 更新
        EvalReviewFeedbackDO updateObj = BeanUtils.toBean(updateReqVO, EvalReviewFeedbackDO.class);
        evalReviewFeedbackMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalReviewFeedback(Long id) {
        // 校验存在
        validateEvalReviewFeedbackExists(id);
        // 删除
        evalReviewFeedbackMapper.deleteById(id);
    }

    private void validateEvalReviewFeedbackExists(Long id) {
        if (evalReviewFeedbackMapper.selectById(id) == null) {
            throw exception(EVAL_REVIEW_FEEDBACK_NOT_EXISTS);
        }
    }

    @Override
    public EvalReviewFeedbackDO getEvalReviewFeedback(Long id) {
        return evalReviewFeedbackMapper.selectById(id);
    }

    @Override
    public PageResult<EvalReviewFeedbackDO> getEvalReviewFeedbackPage(EvalReviewFeedbackPageReqVO pageReqVO) {
        return evalReviewFeedbackMapper.selectPage(pageReqVO);
    }

}