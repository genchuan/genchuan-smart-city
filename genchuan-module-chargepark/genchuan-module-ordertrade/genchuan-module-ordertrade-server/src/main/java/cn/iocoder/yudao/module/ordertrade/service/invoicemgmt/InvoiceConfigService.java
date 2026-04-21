package cn.iocoder.yudao.module.ordertrade.service.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceConfigDO;

public interface InvoiceConfigService {

    Long createInvoiceConfig(InvoiceConfigSaveReqVO createReqVO);

    void updateInvoiceConfig(InvoiceConfigSaveReqVO updateReqVO);

    void deleteInvoiceConfig(Long id);

    InvoiceConfigDO getInvoiceConfig(Long id);

    PageResult<InvoiceConfigDO> getInvoiceConfigPage(InvoiceConfigPageReqVO pageReqVO);

    void enableInvoiceConfig(Long id);

    void disableInvoiceConfig(Long id);

    InvoiceConfigChartRespVO getInvoiceConfigChart(InvoiceConfigChartReqVO chartReqVO);
}
