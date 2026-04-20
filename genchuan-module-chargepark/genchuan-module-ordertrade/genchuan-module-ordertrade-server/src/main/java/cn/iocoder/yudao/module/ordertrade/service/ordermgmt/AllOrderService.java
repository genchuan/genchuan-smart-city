package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.AllOrderDO;

import java.util.List;

/**
 * AllOrder Service 接口
 * @author genchuan
 */
public interface AllOrderService {

    Long createAllOrder(AllOrderSaveReqVO createReqVO);

    void updateAllOrder(AllOrderSaveReqVO updateReqVO);

    void deleteAllOrder(Long id);

    void deleteAllOrderListByIds(List<Long> ids);

    AllOrderDO getAllOrder(Long id);

    PageResult<AllOrderDO> getAllOrderPage(AllOrderPageReqVO pageReqVO);

    AllOrderChartRespVO getAllOrderChart(AllOrderChartReqVO chartReqVO);

    /** Pay - 单条操作（IdReqVO） */

    void payAllOrder(IdReqVO reqVO);

    /** Cancel - 单条操作（IdReqVO） */

    void cancelAllOrder(IdReqVO reqVO);

    /** Refund - 单条操作（IdReqVO） */

    void refundAllOrder(IdReqVO reqVO);

    /** Invoice - 单条操作（IdReqVO） */

    void invoiceAllOrder(IdReqVO reqVO);
}
