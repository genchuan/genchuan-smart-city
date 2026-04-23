package cn.iocoder.yudao.module.ordertrade.service.refundmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.AmountCheckDO;

import java.util.List;

/**
 * AmountCheck Service 接口
 * @author genchuan
 */
public interface AmountCheckService {

    Long createAmountCheck(AmountCheckSaveReqVO createReqVO);

    void updateAmountCheck(AmountCheckSaveReqVO updateReqVO);

    void deleteAmountCheck(Long id);

    void deleteAmountCheckListByIds(List<Long> ids);

    AmountCheckDO getAmountCheck(Long id);

    PageResult<AmountCheckDO> getAmountCheckPage(AmountCheckPageReqVO pageReqVO);

    AmountCheckChartRespVO getAmountCheckChart(AmountCheckChartReqVO chartReqVO);

    /** Check - 单条操作（IdReqVO） */

    void checkAmountCheck(IdReqVO reqVO);

    /** Confirm - 单条操作（IdReqVO） */

    void confirmAmountCheck(IdReqVO reqVO);

    /** DoCheck - 批量操作（IdsReqVO） */

    void doCheckAmountCheck(IdsReqVO reqVO);
}
