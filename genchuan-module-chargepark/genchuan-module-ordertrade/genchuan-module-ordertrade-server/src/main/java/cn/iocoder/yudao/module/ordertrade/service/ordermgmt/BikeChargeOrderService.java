package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.BikeChargeOrderDO;

import java.util.List;

/**
 * BikeChargeOrder Service 接口
 * @author genchuan
 */
public interface BikeChargeOrderService {

    Long createBikeChargeOrder(BikeChargeOrderSaveReqVO createReqVO);

    void updateBikeChargeOrder(BikeChargeOrderSaveReqVO updateReqVO);

    void deleteBikeChargeOrder(Long id);

    void deleteBikeChargeOrderListByIds(List<Long> ids);

    BikeChargeOrderDO getBikeChargeOrder(Long id);

    PageResult<BikeChargeOrderDO> getBikeChargeOrderPage(BikeChargeOrderPageReqVO pageReqVO);

    BikeChargeOrderChartRespVO getBikeChargeOrderChart(BikeChargeOrderChartReqVO chartReqVO);

    /** Stop - 单条操作（IdReqVO） */

    void stopBikeChargeOrder(IdReqVO reqVO);

    /** Pay - 单条操作（IdReqVO） */

    void payBikeChargeOrder(IdReqVO reqVO);

    /** Cancel - 单条操作（IdReqVO） */

    void cancelBikeChargeOrder(IdReqVO reqVO);

    /** Refund - 单条操作（IdReqVO） */

    void refundBikeChargeOrder(IdReqVO reqVO);

    /** Invoice - 单条操作（InvoiceOrderReqVO） */

    void invoiceBikeChargeOrder(InvoiceOrderReqVO reqVO);
}
