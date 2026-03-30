package cn.iocoder.yudao.module.envirhealth.service.dictionary.reviewstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.reviewstatus.vo.ReviewStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.reviewstatus.vo.ReviewStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.ReviewStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.ReviewStatusMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.REVIEW_STATUS_NOT_EXISTS;

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

    @Override
    public List<OptionVO> getReviewStatusOptions() {

        List<ReviewStatusDO> list;
        list = reviewStatusMapper.selectList(
                new LambdaQueryWrapperX<ReviewStatusDO>()
                        .eq(ReviewStatusDO::getDeleted, 0)
                        .orderByDesc(ReviewStatusDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, reviewStatusDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(reviewStatusDO.getReviewStatusName());
            vo.setValue(reviewStatusDO.getReviewStatusId());
            return vo;
        });
    }
}