package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.CarChargeOrderDO;

import java.util.List;

/**
 * CarChargeOrder Service 接口
 * @author genchuan
 */
public interface CarChargeOrderService {

    Long createCarChargeOrder(CarChargeOrderSaveReqVO createReqVO);

    void updateCarChargeOrder(CarChargeOrderSaveReqVO updateReqVO);

    void deleteCarChargeOrder(Long id);

    void deleteCarChargeOrderListByIds(List<Long> ids);

    CarChargeOrderDO getCarChargeOrder(Long id);

    PageResult<CarChargeOrderDO> getCarChargeOrderPage(CarChargeOrderPageReqVO pageReqVO);

    CarChargeOrderChartRespVO getCarChargeOrderChart(CarChargeOrderChartReqVO chartReqVO);

    /** Stop - 单条操作（IdReqVO） */

    void stopCarChargeOrder(IdReqVO reqVO);

    /** Pay - 单条操作（IdReqVO） */

    void payCarChargeOrder(IdReqVO reqVO);

    /** Cancel - 单条操作（IdReqVO） */

    void cancelCarChargeOrder(IdReqVO reqVO);

    /** Refund - 单条操作（IdReqVO） */

    void refundCarChargeOrder(IdReqVO reqVO);

    /** Invoice - 单条操作（InvoiceOrderReqVO） */

    void invoiceCarChargeOrder(InvoiceOrderReqVO reqVO);
}
