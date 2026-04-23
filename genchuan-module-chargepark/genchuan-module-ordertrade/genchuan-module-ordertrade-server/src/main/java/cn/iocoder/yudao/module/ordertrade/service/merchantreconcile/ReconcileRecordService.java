package cn.iocoder.yudao.module.ordertrade.service.merchantreconcile;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.merchantreconcile.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.merchantreconcile.ReconcileRecordDO;

public interface ReconcileRecordService {

    Long createReconcileRecord(ReconcileRecordSaveReqVO createReqVO);

    void updateReconcileRecord(ReconcileRecordSaveReqVO updateReqVO);

    void deleteReconcileRecord(Long id);

    ReconcileRecordDO getReconcileRecord(Long id);

    PageResult<ReconcileRecordDO> getReconcileRecordPage(ReconcileRecordPageReqVO pageReqVO);

    void checkReconcileRecord(IdReqVO reqVO);

    ReconcileRecordChartRespVO getReconcileRecordChart(ReconcileRecordChartReqVO chartReqVO);
}
