package cn.iocoder.yudao.module.ordertrade.service.ordermgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.ordermgmt.OfftimeParkOrderDO;

import java.util.List;

/**
 * OfftimeParkOrder Service 接口
 * @author genchuan
 */
public interface OfftimeParkOrderService {

    Long createOfftimeParkOrder(OfftimeParkOrderSaveReqVO createReqVO);

    void updateOfftimeParkOrder(OfftimeParkOrderSaveReqVO updateReqVO);

    void deleteOfftimeParkOrder(Long id);

    void deleteOfftimeParkOrderListByIds(List<Long> ids);

    OfftimeParkOrderDO getOfftimeParkOrder(Long id);

    PageResult<OfftimeParkOrderDO> getOfftimeParkOrderPage(OfftimeParkOrderPageReqVO pageReqVO);

    OfftimeParkOrderChartRespVO getOfftimeParkOrderChart(OfftimeParkOrderChartReqVO chartReqVO);

    /** Pay - 单条操作（IdReqVO） */

    void payOfftimeParkOrder(IdReqVO reqVO);

    /** Cancel - 单条操作（IdReqVO） */

    void cancelOfftimeParkOrder(IdReqVO reqVO);

    /** Refund - 单条操作（IdReqVO） */

    void refundOfftimeParkOrder(IdReqVO reqVO);

    /** Invoice - 单条操作（IdReqVO） */

    void invoiceOfftimeParkOrder(IdReqVO reqVO);
}
