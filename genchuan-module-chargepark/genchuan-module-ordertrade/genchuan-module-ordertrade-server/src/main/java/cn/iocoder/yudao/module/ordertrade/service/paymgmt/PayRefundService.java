package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayRefundDO;

public interface PayRefundService {

    PayRefundDO getPayRefund(Long id);

    PageResult<PayRefundDO> getPayRefundPage(PayRefundPageReqVO pageReqVO);

    void executePayRefund(IdReqVO reqVO);

    void cancelPayRefund(IdReqVO reqVO);

    PayRefundChartRespVO getPayRefundChart(PayRefundChartReqVO chartReqVO);
}
