package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayWalletDO;

public interface PayWalletService {

    PayWalletDO getPayWallet(Long id);

    PageResult<PayWalletDO> getPayWalletPage(PayWalletPageReqVO pageReqVO);

    void rechargePayWallet(IdReqVO reqVO);

    void withdrawPayWallet(IdReqVO reqVO);

    void unfreezePayWallet(IdReqVO reqVO);

    PayWalletChartRespVO getPayWalletChart(PayWalletChartReqVO chartReqVO);
}
