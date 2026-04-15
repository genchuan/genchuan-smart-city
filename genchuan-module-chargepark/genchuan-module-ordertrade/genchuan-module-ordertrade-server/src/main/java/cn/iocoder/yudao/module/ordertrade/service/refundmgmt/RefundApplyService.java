package cn.iocoder.yudao.module.ordertrade.service.refundmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundApplyDO;

import java.util.List;

/**
 * RefundApply Service 接口
 * @author genchuan
 */
public interface RefundApplyService {

    Long createRefundApply(RefundApplySaveReqVO createReqVO);

    void updateRefundApply(RefundApplySaveReqVO updateReqVO);

    void deleteRefundApply(Long id);

    void deleteRefundApplyListByIds(List<Long> ids);

    RefundApplyDO getRefundApply(Long id);

    PageResult<RefundApplyDO> getRefundApplyPage(RefundApplyPageReqVO pageReqVO);

    RefundApplyChartRespVO getRefundApplyChart(RefundApplyChartReqVO chartReqVO);

    /** Approve - 单条操作（IdReqVO） */

    void approveRefundApply(IdReqVO reqVO);

    /** Reject - 单条操作（IdReqVO） */

    void rejectRefundApply(IdReqVO reqVO);

    /** Execute - 单条操作（IdReqVO） */

    void executeRefundApply(IdReqVO reqVO);

    /** Reapply - 单条操作（IdReqVO） */

    void reapplyRefundApply(IdReqVO reqVO);

    /** BatchAudit - 批量操作（IdsReqVO） */

    void batchAuditRefundApply(IdsReqVO reqVO);
}
