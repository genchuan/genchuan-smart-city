package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayTransferDO;

public interface PayTransferService {

    PayTransferDO getPayTransfer(Long id);

    PageResult<PayTransferDO> getPayTransferPage(PayTransferPageReqVO pageReqVO);

    void executePayTransfer(IdReqVO reqVO);

    void cancelPayTransfer(IdReqVO reqVO);

    PayTransferChartRespVO getPayTransferChart(PayTransferChartReqVO chartReqVO);
}
