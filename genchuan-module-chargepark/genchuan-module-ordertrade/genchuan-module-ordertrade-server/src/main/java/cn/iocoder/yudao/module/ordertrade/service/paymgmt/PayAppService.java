package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayAppDO;

public interface PayAppService {

    Long createPayApp(PayAppSaveReqVO createReqVO);

    void updatePayApp(PayAppSaveReqVO updateReqVO);

    void deletePayApp(Long id);

    PayAppDO getPayApp(Long id);

    PageResult<PayAppDO> getPayAppPage(PayAppPageReqVO pageReqVO);

    void enablePayApp(Long id);

    void disablePayApp(Long id);

    PayAppChartRespVO getPayAppChart(PayAppChartReqVO chartReqVO);
}
