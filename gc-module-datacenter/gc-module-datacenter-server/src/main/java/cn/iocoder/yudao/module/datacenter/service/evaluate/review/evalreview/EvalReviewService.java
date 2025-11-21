package cn.iocoder.yudao.module.datacenter.service.evaluate.review.evalreview;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo.EvalReviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.review.evalreview.vo.EvalReviewSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.review.evalreview.EvalReviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 复核办理 Service 接口
 *
 * @author zcq
 */
public interface EvalReviewService {

    /**
     * 创建复核办理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvalReview(@Valid EvalReviewSaveReqVO createReqVO);

    /**
     * 更新复核办理
     *
     * @param updateReqVO 更新信息
     */
    void updateEvalReview(@Valid EvalReviewSaveReqVO updateReqVO);

    /**
     * 删除复核办理
     *
     * @param id 编号
     */
    void deleteEvalReview(Long id);

    /**
     * 获得复核办理
     *
     * @param id 编号
     * @return 复核办理
     */
    EvalReviewDO getEvalReview(Long id);

    /**
     * 获得复核办理分页
     *
     * @param pageReqVO 分页查询
     * @return 复核办理分页
     */
    PageResult<EvalReviewDO> getEvalReviewPage(EvalReviewPageReqVO pageReqVO);

}