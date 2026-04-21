package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayOrderDO;

public interface PayOrderService {

    PayOrderDO getPayOrder(Long id);

    PageResult<PayOrderDO> getPayOrderPage(PayOrderPageReqVO pageReqVO);

    void payPayOrder(IdReqVO reqVO);

    void refundPayOrder(IdReqVO reqVO);

    void cancelPayOrder(IdReqVO reqVO);

    PayOrderChartRespVO getPayOrderChart(PayOrderChartReqVO chartReqVO);
}
