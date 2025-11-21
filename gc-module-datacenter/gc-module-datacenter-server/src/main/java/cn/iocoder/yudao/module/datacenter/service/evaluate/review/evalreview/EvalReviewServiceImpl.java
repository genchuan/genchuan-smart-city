package cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo.EvalReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo.EvalReviewSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreview.EvalReviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.review.evalreview.EvalReviewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 复核办理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalReviewServiceImpl implements EvalReviewService {

    @Resource
    private EvalReviewMapper evalReviewMapper;

    @Override
    public Long createEvalReview(EvalReviewSaveReqVO createReqVO) {
        // 插入
        EvalReviewDO evalReview = BeanUtils.toBean(createReqVO, EvalReviewDO.class);
        evalReviewMapper.insert(evalReview);
        // 返回
        return evalReview.getId();
    }

    @Override
    public void updateEvalReview(EvalReviewSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalReviewExists(updateReqVO.getId());
        // 更新
        EvalReviewDO updateObj = BeanUtils.toBean(updateReqVO, EvalReviewDO.class);
        evalReviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalReview(Long id) {
        // 校验存在
        validateEvalReviewExists(id);
        // 删除
        evalReviewMapper.deleteById(id);
    }

    private void validateEvalReviewExists(Long id) {
        if (evalReviewMapper.selectById(id) == null) {
            throw exception(EVAL_REVIEW_NOT_EXISTS);
        }
    }

    @Override
    public EvalReviewDO getEvalReview(Long id) {
        return evalReviewMapper.selectById(id);
    }

    @Override
    public PageResult<EvalReviewDO> getEvalReviewPage(EvalReviewPageReqVO pageReqVO) {
        return evalReviewMapper.selectPage(pageReqVO);
    }

}