package cn.iocoder.yudao.module.ordertrade.service.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileBillDO;

import java.util.List;

public interface ReconcileBillService {

    Long createReconcileBill(ReconcileBillSaveReqVO createReqVO);

    void updateReconcileBill(ReconcileBillSaveReqVO updateReqVO);

    void deleteReconcileBill(Long id);

    ReconcileBillDO getReconcileBill(Long id);

    PageResult<ReconcileBillDO> getReconcileBillPage(ReconcileBillPageReqVO pageReqVO);

    void reconcileReconcileBill(IdReqVO reqVO);

    void batchReconcileReconcileBill(List<Long> ids);

    void confirmReconcileBill(Long id);

    void fixReconcileBill(IdReqVO reqVO);

    ReconcileBillChartRespVO getReconcileBillChart(ReconcileBillChartReqVO chartReqVO);
}
