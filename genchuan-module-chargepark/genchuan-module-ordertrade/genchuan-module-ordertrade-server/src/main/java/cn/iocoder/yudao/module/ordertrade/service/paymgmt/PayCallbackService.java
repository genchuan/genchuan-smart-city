package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayCallbackDO;

public interface PayCallbackService {

    PayCallbackDO getPayCallback(Long id);

    PageResult<PayCallbackDO> getPayCallbackPage(PayCallbackPageReqVO pageReqVO);

    void processPayCallback(IdReqVO reqVO);

    void repushPayCallback(IdReqVO reqVO);

    PayCallbackChartRespVO getPayCallbackChart(PayCallbackChartReqVO chartReqVO);
}
