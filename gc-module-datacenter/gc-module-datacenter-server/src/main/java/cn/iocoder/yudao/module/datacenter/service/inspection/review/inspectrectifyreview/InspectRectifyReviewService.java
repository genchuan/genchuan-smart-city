package cn.iocoder.yudao.module.datacenter.service.inspection.review.inspectrectifyreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo.InspectRectifyReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.review.inspectrectifyreview.vo.InspectRectifyReviewSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.review.inspectrectifyreview.InspectRectifyReviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检整改结果复核 Service 接口
 *
 * @author zcq
 */
public interface InspectRectifyReviewService {

    /**
     * 创建巡查巡检整改结果复核
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectRectifyReview(@Valid InspectRectifyReviewSaveReqVO createReqVO);

    /**
     * 更新巡查巡检整改结果复核
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectRectifyReview(@Valid InspectRectifyReviewSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检整改结果复核
     *
     * @param id 编号
     */
    void deleteInspectRectifyReview(Long id);

    /**
     * 获得巡查巡检整改结果复核
     *
     * @param id 编号
     * @return 巡查巡检整改结果复核
     */
    InspectRectifyReviewDO getInspectRectifyReview(Long id);

    /**
     * 获得巡查巡检整改结果复核分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检整改结果复核分页
     */
    PageResult<InspectRectifyReviewDO> getInspectRectifyReviewPage(InspectRectifyReviewPageReqVO pageReqVO);

}