package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.TempParkOrderDO;

import java.util.List;

/**
 * TempParkOrder Service 接口
 * @author genchuan
 */
public interface TempParkOrderService {

    Long createTempParkOrder(TempParkOrderSaveReqVO createReqVO);

    void updateTempParkOrder(TempParkOrderSaveReqVO updateReqVO);

    void deleteTempParkOrder(Long id);

    void deleteTempParkOrderListByIds(List<Long> ids);

    TempParkOrderDO getTempParkOrder(Long id);

    PageResult<TempParkOrderDO> getTempParkOrderPage(TempParkOrderPageReqVO pageReqVO);

    TempParkOrderChartRespVO getTempParkOrderChart(TempParkOrderChartReqVO chartReqVO);

    /** Pay - 单条操作（IdReqVO） */

    void payTempParkOrder(IdReqVO reqVO);

    /** Cancel - 单条操作（IdReqVO） */

    void cancelTempParkOrder(IdReqVO reqVO);

    /** Refund - 单条操作（IdReqVO） */

    void refundTempParkOrder(IdReqVO reqVO);

    /** Invoice - 单条操作（IdReqVO） */

    void invoiceTempParkOrder(IdReqVO reqVO);
}
