package cn.iocoder.yudao.module.ordertrade.service.refundmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.RefundRecordDO;

import java.util.List;

/**
 * RefundRecord Service 接口
 * @author genchuan
 */
public interface RefundRecordService {

    Long createRefundRecord(RefundRecordSaveReqVO createReqVO);

    void updateRefundRecord(RefundRecordSaveReqVO updateReqVO);

    void deleteRefundRecord(Long id);

    void deleteRefundRecordListByIds(List<Long> ids);

    RefundRecordDO getRefundRecord(Long id);

    PageResult<RefundRecordDO> getRefundRecordPage(RefundRecordPageReqVO pageReqVO);

    RefundRecordChartRespVO getRefundRecordChart(RefundRecordChartReqVO chartReqVO);

    /** Check - 单条操作（IdReqVO） */

    void checkRefundRecord(IdReqVO reqVO);
}
