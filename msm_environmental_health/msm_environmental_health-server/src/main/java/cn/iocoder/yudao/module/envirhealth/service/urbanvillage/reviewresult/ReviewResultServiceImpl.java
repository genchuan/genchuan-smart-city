package cn.iocoder.yudao.module.envirhealth.service.urbanvillage.reviewresult;

import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.urbanvillage.vo.reviewresult.ReviewResultSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.urbanvillage.ReviewResultDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.urbanvillage.ReviewResultMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 复核结果字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ReviewResultServiceImpl implements ReviewResultService {

    @Resource
    private ReviewResultMapper reviewResultMapper;

    @Override
    public Long createReviewResult(ReviewResultSaveReqVO createReqVO) {
        // 插入
        ReviewResultDO reviewResult = BeanUtils.toBean(createReqVO, ReviewResultDO.class);
        reviewResultMapper.insert(reviewResult);
        // 返回
        return reviewResult.getId();
    }

    @Override
    public void updateReviewResult(ReviewResultSaveReqVO updateReqVO) {
        // 校验存在
        validateReviewResultExists(updateReqVO.getId());
        // 更新
        ReviewResultDO updateObj = BeanUtils.toBean(updateReqVO, ReviewResultDO.class);
        reviewResultMapper.updateById(updateObj);
    }

    @Override
    public void deleteReviewResult(Long id) {
        // 校验存在
        validateReviewResultExists(id);
        // 删除
        reviewResultMapper.deleteById(id);
    }

    private void validateReviewResultExists(Long id) {
        if (reviewResultMapper.selectById(id) == null) {
            throw exception(REVIEW_RESULT_NOT_EXISTS);
        }
    }

    @Override
    public ReviewResultDO getReviewResult(Long id) {
        return reviewResultMapper.selectById(id);
    }

    @Override
    public PageResult<ReviewResultDO> getReviewResultPage(ReviewResultPageReqVO pageReqVO) {
        return reviewResultMapper.selectPage(pageReqVO);
    }

}