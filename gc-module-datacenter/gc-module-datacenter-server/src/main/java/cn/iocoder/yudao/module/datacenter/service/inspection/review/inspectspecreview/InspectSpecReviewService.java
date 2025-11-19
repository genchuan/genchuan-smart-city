package cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectspecreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo.InspectSpecReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectspecreview.vo.InspectSpecReviewSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectspecreview.InspectSpecReviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检专项结果复核 Service 接口
 *
 * @author zcq
 */
public interface InspectSpecReviewService {

    /**
     * 创建巡查巡检专项结果复核
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectSpecReview(@Valid InspectSpecReviewSaveReqVO createReqVO);

    /**
     * 更新巡查巡检专项结果复核
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectSpecReview(@Valid InspectSpecReviewSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检专项结果复核
     *
     * @param id 编号
     */
    void deleteInspectSpecReview(Long id);

    /**
     * 获得巡查巡检专项结果复核
     *
     * @param id 编号
     * @return 巡查巡检专项结果复核
     */
    InspectSpecReviewDO getInspectSpecReview(Long id);

    /**
     * 获得巡查巡检专项结果复核分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检专项结果复核分页
     */
    PageResult<InspectSpecReviewDO> getInspectSpecReviewPage(InspectSpecReviewPageReqVO pageReqVO);

}