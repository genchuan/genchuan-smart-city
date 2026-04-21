package cn.iocoder.yudao.module.ordertrade.service.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceAuditDO;

import java.util.List;

public interface InvoiceAuditService {

    Long createInvoiceAudit(InvoiceAuditSaveReqVO createReqVO);

    void updateInvoiceAudit(InvoiceAuditSaveReqVO updateReqVO);

    void deleteInvoiceAudit(Long id);

    InvoiceAuditDO getInvoiceAudit(Long id);

    PageResult<InvoiceAuditDO> getInvoiceAuditPage(InvoiceAuditPageReqVO pageReqVO);

    void approveInvoiceAudit(IdReqVO reqVO);

    void rejectInvoiceAudit(IdReqVO reqVO);

    void confirmInvoiceAudit(IdReqVO reqVO);

    void reapplyInvoiceAudit(IdReqVO reqVO);

    void batchAuditInvoiceAudit(List<Long> ids);

    InvoiceAuditChartRespVO getInvoiceAuditChart(InvoiceAuditChartReqVO chartReqVO);
}
