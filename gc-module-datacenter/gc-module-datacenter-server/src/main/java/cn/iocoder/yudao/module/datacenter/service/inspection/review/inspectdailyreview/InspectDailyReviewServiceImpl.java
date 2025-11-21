package cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectdailyreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo.InspectDailyReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo.InspectDailyReviewSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectdailyreview.InspectDailyReviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.review.inspectdailyreview.InspectDailyReviewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检日常结果复核 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectDailyReviewServiceImpl implements InspectDailyReviewService {

    @Resource
    private InspectDailyReviewMapper inspectDailyReviewMapper;

    @Override
    public Long createInspectDailyReview(InspectDailyReviewSaveReqVO createReqVO) {
        // 插入
        InspectDailyReviewDO inspectDailyReview = BeanUtils.toBean(createReqVO, InspectDailyReviewDO.class);
        inspectDailyReviewMapper.insert(inspectDailyReview);
        // 返回
        return inspectDailyReview.getId();
    }

    @Override
    public void updateInspectDailyReview(InspectDailyReviewSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectDailyReviewExists(updateReqVO.getId());
        // 更新
        InspectDailyReviewDO updateObj = BeanUtils.toBean(updateReqVO, InspectDailyReviewDO.class);
        inspectDailyReviewMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectDailyReview(Long id) {
        // 校验存在
        validateInspectDailyReviewExists(id);
        // 删除
        inspectDailyReviewMapper.deleteById(id);
    }

    private void validateInspectDailyReviewExists(Long id) {
        if (inspectDailyReviewMapper.selectById(id) == null) {
            throw exception(INSPECT_DAILY_REVIEW_NOT_EXISTS);
        }
    }

    @Override
    public InspectDailyReviewDO getInspectDailyReview(Long id) {
        return inspectDailyReviewMapper.selectById(id);
    }

    @Override
    public PageResult<InspectDailyReviewDO> getInspectDailyReviewPage(InspectDailyReviewPageReqVO pageReqVO) {
        return inspectDailyReviewMapper.selectPage(pageReqVO);
    }

}