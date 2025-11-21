package cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectspecreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo.InspectSpecReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo.InspectSpecReviewSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectspecreview.InspectSpecReviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.review.inspectspecreview.InspectSpecReviewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检专项结果复核 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectSpecReviewServiceImpl implements InspectSpecReviewService {

    @Resource
    private InspectSpecReviewMapper inspectSpecReviewMapper;

    @Override
    public Long createInspectSpecReview(InspectSpecReviewSaveReqVO createReqVO) {
        // 插入
        InspectSpecReviewDO inspectSpecReview = BeanUtils.toBean(createReqVO, InspectSpecReviewDO.class);
        inspectSpecReviewMapper.insert(inspectSpecReview);
        // 返回
        return inspectSpecReview.getId();
    }

    @Override
    public void updateInspectSpecReview(InspectSpecReviewSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectSpecReviewExists(updateReqVO.getId());
        // 更新
        InspectSpecReviewDO updateObj = BeanUtils.toBean(updateReqVO, InspectSpecReviewDO.class);
        inspectSpecReviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectSpecReview(Long id) {
        // 校验存在
        validateInspectSpecReviewExists(id);
        // 删除
        inspectSpecReviewMapper.deleteById(id);
    }

    private void validateInspectSpecReviewExists(Long id) {
        if (inspectSpecReviewMapper.selectById(id) == null) {
            throw exception(INSPECT_SPEC_REVIEW_NOT_EXISTS);
        }
    }

    @Override
    public InspectSpecReviewDO getInspectSpecReview(Long id) {
        return inspectSpecReviewMapper.selectById(id);
    }

    @Override
    public PageResult<InspectSpecReviewDO> getInspectSpecReviewPage(InspectSpecReviewPageReqVO pageReqVO) {
        return inspectSpecReviewMapper.selectPage(pageReqVO);
    }

}