package cn.iocoder.yudao.module.ordertrade.service.debtcollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.DebtIdentifyDO;

import java.util.List;

/**
 * DebtIdentify Service 接口
 * @author genchuan
 */
public interface DebtIdentifyService {

    Long createDebtIdentify(DebtIdentifySaveReqVO createReqVO);

    void updateDebtIdentify(DebtIdentifySaveReqVO updateReqVO);

    void deleteDebtIdentify(Long id);

    void deleteDebtIdentifyListByIds(List<Long> ids);

    DebtIdentifyDO getDebtIdentify(Long id);

    PageResult<DebtIdentifyDO> getDebtIdentifyPage(DebtIdentifyPageReqVO pageReqVO);

    DebtIdentifyChartRespVO getDebtIdentifyChart(DebtIdentifyChartReqVO chartReqVO);

    /** Identify - 单条操作（IdReqVO） */

    void identifyDebtIdentify(IdReqVO reqVO);

    /** Mark - 单条操作（IdReqVO） */

    void markDebtIdentify(IdReqVO reqVO);

    /** BatchIdentify - 批量操作（IdsReqVO） */

    void batchIdentifyDebtIdentify(IdsReqVO reqVO);
}
