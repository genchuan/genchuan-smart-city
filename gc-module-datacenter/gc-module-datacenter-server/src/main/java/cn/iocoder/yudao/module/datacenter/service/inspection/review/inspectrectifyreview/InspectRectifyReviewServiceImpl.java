package cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectrectifyreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo.InspectRectifyReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo.InspectRectifyReviewSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectrectifyreview.InspectRectifyReviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.review.inspectrectifyreview.InspectRectifyReviewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检整改结果复核 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectRectifyReviewServiceImpl implements InspectRectifyReviewService {

    @Resource
    private InspectRectifyReviewMapper inspectRectifyReviewMapper;

    @Override
    public Long createInspectRectifyReview(InspectRectifyReviewSaveReqVO createReqVO) {
        // 插入
        InspectRectifyReviewDO inspectRectifyReview = BeanUtils.toBean(createReqVO, InspectRectifyReviewDO.class);
        inspectRectifyReviewMapper.insert(inspectRectifyReview);
        // 返回
        return inspectRectifyReview.getId();
    }

    @Override
    public void updateInspectRectifyReview(InspectRectifyReviewSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectRectifyReviewExists(updateReqVO.getId());
        // 更新
        InspectRectifyReviewDO updateObj = BeanUtils.toBean(updateReqVO, InspectRectifyReviewDO.class);
        inspectRectifyReviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectRectifyReview(Long id) {
        // 校验存在
        validateInspectRectifyReviewExists(id);
        // 删除
        inspectRectifyReviewMapper.deleteById(id);
    }

    private void validateInspectRectifyReviewExists(Long id) {
        if (inspectRectifyReviewMapper.selectById(id) == null) {
            throw exception(INSPECT_RECTIFY_REVIEW_NOT_EXISTS);
        }
    }

    @Override
    public InspectRectifyReviewDO getInspectRectifyReview(Long id) {
        return inspectRectifyReviewMapper.selectById(id);
    }

    @Override
    public PageResult<InspectRectifyReviewDO> getInspectRectifyReviewPage(InspectRectifyReviewPageReqVO pageReqVO) {
        return inspectRectifyReviewMapper.selectPage(pageReqVO);
    }

}