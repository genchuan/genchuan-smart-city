package cn.iocoder.yudao.module.envirhealth.service.user.reviewstatus;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.reviewstatus.ReviewStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.reviewstatus.ReviewStatusSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ReviewStatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.ReviewStatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 审核状态字典表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ReviewStatusServiceImpl implements ReviewStatusService {

    @Resource
    private ReviewStatusMapper reviewStatusMapper;

    @Override
    public Long createReviewStatus(ReviewStatusSaveReqVO createReqVO) {
        // 插入
        ReviewStatusDO reviewStatus = BeanUtils.toBean(createReqVO, ReviewStatusDO.class);
        reviewStatusMapper.insert(reviewStatus);
        // 返回
        return reviewStatus.getId();
    }

    @Override
    public void updateReviewStatus(ReviewStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateReviewStatusExists(updateReqVO.getId());
        // 更新
        ReviewStatusDO updateObj = BeanUtils.toBean(updateReqVO, ReviewStatusDO.class);
        reviewStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteReviewStatus(Long id) {
        // 校验存在
        validateReviewStatusExists(id);
        // 删除
        reviewStatusMapper.deleteById(id);
    }

    private void validateReviewStatusExists(Long id) {
        if (reviewStatusMapper.selectById(id) == null) {
            throw exception(REVIEW_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public ReviewStatusDO getReviewStatus(Long id) {
        return reviewStatusMapper.selectById(id);
    }

    @Override
    public PageResult<ReviewStatusDO> getReviewStatusPage(ReviewStatusPageReqVO pageReqVO) {
        return reviewStatusMapper.selectPage(pageReqVO);
    }

}