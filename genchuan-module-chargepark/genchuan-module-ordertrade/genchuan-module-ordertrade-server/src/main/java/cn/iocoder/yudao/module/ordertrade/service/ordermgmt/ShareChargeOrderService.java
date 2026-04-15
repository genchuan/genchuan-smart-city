package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.ShareChargeOrderDO;

import java.util.List;

/**
 * ShareChargeOrder Service 接口
 * @author genchuan
 */
public interface ShareChargeOrderService {

    Long createShareChargeOrder(ShareChargeOrderSaveReqVO createReqVO);

    void updateShareChargeOrder(ShareChargeOrderSaveReqVO updateReqVO);

    void deleteShareChargeOrder(Long id);

    void deleteShareChargeOrderListByIds(List<Long> ids);

    ShareChargeOrderDO getShareChargeOrder(Long id);

    PageResult<ShareChargeOrderDO> getShareChargeOrderPage(ShareChargeOrderPageReqVO pageReqVO);

    ShareChargeOrderChartRespVO getShareChargeOrderChart(ShareChargeOrderChartReqVO chartReqVO);

    /** ReturnOrder - 单条操作（IdReqVO） */

    void returnOrderShareChargeOrder(IdReqVO reqVO);

    /** Pay - 单条操作（IdReqVO） */

    void payShareChargeOrder(IdReqVO reqVO);

    /** Cancel - 单条操作（IdReqVO） */

    void cancelShareChargeOrder(IdReqVO reqVO);

    /** Refund - 单条操作（IdReqVO） */

    void refundShareChargeOrder(IdReqVO reqVO);

    /** Invoice - 单条操作（IdReqVO） */

    void invoiceShareChargeOrder(IdReqVO reqVO);
}
