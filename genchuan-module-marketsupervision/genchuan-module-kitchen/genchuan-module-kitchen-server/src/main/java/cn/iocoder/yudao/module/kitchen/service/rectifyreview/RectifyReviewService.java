package cn.iocoder.yudao.module.kitchen.service.rectifyreview;

import java.io.OutputStream;
import java.util.*;

import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.*;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.cancel.CancelReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo.issue.IssueReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.rectifyreview.RectifyReviewDO;
import jakarta.servlet.ServletOutputStream;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 整改通知书复审台账 Service 接口
 *
 * @author 亘川智城
 */
public interface RectifyReviewService {

    /**
     * 创建整改通知书复审台账
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRectifyReview(@Valid RectifyReviewSaveReqVO createReqVO);

    /**
     * 更新整改通知书复审台账
     *
     * @param updateReqVO 更新信息
     */
    void updateRectifyReview(@Valid RectifyReviewSaveReqVO updateReqVO);

    /**
     * 删除整改通知书复审台账
     *
     * @param id 编号
     */
    void deleteRectifyReview(Long id);

    /**
     * 获得整改通知书复审台账
     *
     * @param id 编号
     * @return 整改通知书复审台账
     */
    RectifyReviewDO getRectifyReview(Long id);

    /**
     * 获得整改通知书复审台账分页
     *
     * @param pageReqVO 分页查询
     * @return 整改通知书复审台账分页
     */
    PageResult<RectifyReviewDO> getRectifyReviewPage(RectifyReviewPageReqVO pageReqVO);

    PageResult<RectifyReviewLedgerRespVO> getRectifyReviewLedgerPage(RectifyReviewLedgerPageReqVO reqVO);



    List<RectifyReviewLedgerRespVO> getByIds(List<Long> ledgerIds);


    PageResult<RectifyEvidenceVO> getBatchEvidence(BatchEvidenceRequestVO reqVO);




    Long reviewIssue(IssueReqVO reqVO);

    Long reviewCancel(CancelReqVO reqVO);
}
