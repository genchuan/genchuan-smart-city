package cn.iocoder.yudao.module.ordertrade.service.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileBillDO;

public interface ReconcileBillService {

    Long createReconcileBill(ReconcileBillSaveReqVO createReqVO);

    void updateReconcileBill(ReconcileBillSaveReqVO updateReqVO);

    void deleteReconcileBill(Long id);

    ReconcileBillDO getReconcileBill(Long id);

    PageResult<ReconcileBillDO> getReconcileBillPage(ReconcileBillPageReqVO pageReqVO);

    void confirmReconcileBill(Long id);

    void disputeReconcileBill(Long id, String reason);

    void resolveReconcileBill(Long id);

    ReconcileBillChartRespVO getReconcileBillChart(ReconcileBillChartReqVO chartReqVO);
}
