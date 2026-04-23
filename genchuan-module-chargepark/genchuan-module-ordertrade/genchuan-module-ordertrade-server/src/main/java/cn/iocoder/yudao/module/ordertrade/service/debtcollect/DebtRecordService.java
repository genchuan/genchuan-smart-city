package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.DebtRecordDO;

import java.util.List;

/**
 * DebtRecord Service 接口
 * @author genchuan
 */
public interface DebtRecordService {

    Long createDebtRecord(DebtRecordSaveReqVO createReqVO);

    void updateDebtRecord(DebtRecordSaveReqVO updateReqVO);

    void deleteDebtRecord(Long id);

    void deleteDebtRecordListByIds(List<Long> ids);

    DebtRecordDO getDebtRecord(Long id);

    PageResult<DebtRecordDO> getDebtRecordPage(DebtRecordPageReqVO pageReqVO);

    DebtRecordChartRespVO getDebtRecordChart(DebtRecordChartReqVO chartReqVO);

    /** StartCollect - 单条操作（IdReqVO） */

    void startCollectDebtRecord(IdReqVO reqVO);

    /** UpdateProgress - 单条操作（IdReqVO） */

    void updateProgressDebtRecord(IdReqVO reqVO);
}
