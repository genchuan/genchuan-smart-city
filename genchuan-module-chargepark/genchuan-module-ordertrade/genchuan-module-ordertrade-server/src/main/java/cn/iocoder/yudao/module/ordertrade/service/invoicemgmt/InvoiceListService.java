package cn.iocoder.yudao.module.ordertrade.service.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;

import java.util.List;

public interface InvoiceListService {

    Long createInvoiceList(InvoiceListSaveReqVO createReqVO);

    void updateInvoiceList(InvoiceListSaveReqVO updateReqVO);

    void deleteInvoiceList(Long id);

    InvoiceListDO getInvoiceList(Long id);

    PageResult<InvoiceListDO> getInvoiceListPage(InvoiceListPageReqVO pageReqVO);

    void approveInvoiceList(IdReqVO reqVO);

    void rejectInvoiceList(IdReqVO reqVO);

    void invoiceInvoiceList(IdReqVO reqVO);

    void pushInvoiceList(IdReqVO reqVO);

    String downloadInvoiceList(Long id);

    void reapplyInvoiceList(IdReqVO reqVO);

    void batchInvoiceList(List<Long> ids);

    InvoiceListChartRespVO getInvoiceListChart(InvoiceListChartReqVO chartReqVO);
}
