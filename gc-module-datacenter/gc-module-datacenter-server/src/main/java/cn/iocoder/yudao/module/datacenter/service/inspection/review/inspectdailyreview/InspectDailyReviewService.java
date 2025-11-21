package cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectdailyreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo.InspectDailyReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectdailyreview.vo.InspectDailyReviewSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectdailyreview.InspectDailyReviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检日常结果复核 Service 接口
 *
 * @author zcq
 */
public interface InspectDailyReviewService {

    /**
     * 创建巡查巡检日常结果复核
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectDailyReview(@Valid InspectDailyReviewSaveReqVO createReqVO);

    /**
     * 更新巡查巡检日常结果复核
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectDailyReview(@Valid InspectDailyReviewSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检日常结果复核
     *
     * @param id 编号
     */
    void deleteInspectDailyReview(Long id);

    /**
     * 获得巡查巡检日常结果复核
     *
     * @param id 编号
     * @return 巡查巡检日常结果复核
     */
    InspectDailyReviewDO getInspectDailyReview(Long id);

    /**
     * 获得巡查巡检日常结果复核分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检日常结果复核分页
     */
    PageResult<InspectDailyReviewDO> getInspectDailyReviewPage(InspectDailyReviewPageReqVO pageReqVO);

}