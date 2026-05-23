package cn.iocoder.yudao.module.ordertrade.service.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt.InvoiceListMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class InvoiceListServiceImpl implements InvoiceListService {

    @Resource
    private InvoiceListMapper invoiceListMapper;

    @Override
    public Long createInvoiceList(InvoiceListSaveReqVO createReqVO) {
        InvoiceListDO obj = BeanUtils.toBean(createReqVO, InvoiceListDO.class);
        obj.setInvoiceNo("INV" + UUID.randomUUID().toString().replace("-", "").substring(0, 14).toUpperCase());
        if (obj.getStatus() == null) {
            obj.setStatus("pending_audit");
        }
        invoiceListMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateInvoiceList(InvoiceListSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        invoiceListMapper.updateById(BeanUtils.toBean(updateReqVO, InvoiceListDO.class));
    }

    @Override
    public void deleteInvoiceList(Long id) {
        validateExists(id);
        invoiceListMapper.deleteById(id);
    }

    @Override
    public InvoiceListDO getInvoiceList(Long id) {
        return invoiceListMapper.selectByIdWithDetails(id);
    }

    @Override
    public PageResult<InvoiceListDO> getInvoiceListPage(InvoiceListPageReqVO pageReqVO) {
        return invoiceListMapper.selectPageWithDetails(pageReqVO);
    }

    @Override
    public List<InvoiceListDO> getInvoiceListExport(InvoiceListPageReqVO pageReqVO) {
        return invoiceListMapper.selectListWithDetails(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveInvoiceList(IdReqVO reqVO) {
        InvoiceListDO invoice = invoiceListMapper.selectById(reqVO.getId());
        if (invoice == null) throw exception(INVOICE_LIST_NOT_EXISTS);
        if (!"pending_audit".equals(invoice.getStatus())) throw exception(INVOICE_LIST_STATUS_CANNOT_APPROVE);
        InvoiceListDO update = new InvoiceListDO();
        update.setId(reqVO.getId());
        update.setStatus("pending_invoice");
        update.setAuditorId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        invoiceListMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectInvoiceList(IdReqVO reqVO) {
        InvoiceListDO invoice = invoiceListMapper.selectById(reqVO.getId());
        if (invoice == null) throw exception(INVOICE_LIST_NOT_EXISTS);
        if (!"pending".equals(invoice.getStatus())) throw exception(INVOICE_LIST_STATUS_CANNOT_REJECT);
        InvoiceListDO update = new InvoiceListDO();
        update.setId(reqVO.getId());
        update.setStatus("rejected");
        update.setAuditorId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        invoiceListMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invoiceInvoiceList(IdReqVO reqVO) {
        InvoiceListDO invoice = invoiceListMapper.selectById(reqVO.getId());
        if (invoice == null) throw exception(INVOICE_LIST_NOT_EXISTS);
        if (!"pending_invoice".equals(invoice.getStatus())) throw exception(INVOICE_LIST_STATUS_CANNOT_INVOICE);
        InvoiceListDO update = new InvoiceListDO();
        update.setId(reqVO.getId());
        update.setStatus("invoiced");
        update.setInvoiceTime(LocalDateTime.now());
        invoiceListMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pushInvoiceList(IdReqVO reqVO) {
        InvoiceListDO invoice = invoiceListMapper.selectById(reqVO.getId());
        if (invoice == null) throw exception(INVOICE_LIST_NOT_EXISTS);
        InvoiceListDO update = new InvoiceListDO();
        update.setId(reqVO.getId());
        update.setPushTime(LocalDateTime.now());
        invoiceListMapper.updateById(update);
    }

    @Override
    public String downloadInvoiceList(Long id) {
        InvoiceListDO invoice = invoiceListMapper.selectById(id);
        if (invoice == null) throw exception(INVOICE_LIST_NOT_EXISTS);
        if (!"invoiced".equals(invoice.getStatus())) throw exception(INVOICE_LIST_STATUS_NOT_INVOICED);
        if (invoice.getDownloadUrl() == null || invoice.getDownloadUrl().isEmpty()) {
            throw exception(INVOICE_LIST_DOWNLOAD_URL_NOT_EXISTS);
        }
        return invoice.getDownloadUrl();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reapplyInvoiceList(IdReqVO reqVO) {
        InvoiceListDO invoice = invoiceListMapper.selectById(reqVO.getId());
        if (invoice == null) throw exception(INVOICE_LIST_NOT_EXISTS);
        InvoiceListDO update = new InvoiceListDO();
        update.setId(reqVO.getId());
        update.setStatus("pending_audit");
        invoiceListMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInvoiceList(List<Long> ids) {
        ids.forEach(id -> {
            InvoiceListDO invoice = invoiceListMapper.selectById(id);
            if (invoice != null && "pending_invoice".equals(invoice.getStatus())) {
                InvoiceListDO update = new InvoiceListDO();
                update.setId(id);
                update.setStatus("invoiced");
                update.setInvoiceTime(LocalDateTime.now());
                invoiceListMapper.updateById(update);
            }
        });
    }

    @Override
    public InvoiceListChartRespVO getInvoiceListChart(InvoiceListChartReqVO chartReqVO) {
        InvoiceListChartRespVO resp = new InvoiceListChartRespVO();
        LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(invoiceListMapper.selectTrend(start, end));

        InvoiceListChartRespVO.CardData card = new InvoiceListChartRespVO.CardData();
        card.setTodayInvoiceCount(invoiceListMapper.selectTodayCount(todayStart, now));

        Long invoicedCount = invoiceListMapper.selectInvoicedCount();
        Long totalCount = invoiceListMapper.selectTotalCount();
        if (totalCount != null && totalCount > 0) {
            card.setSuccessRate(new BigDecimal(invoicedCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setSuccessRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (invoiceListMapper.selectById(id) == null) throw exception(INVOICE_LIST_NOT_EXISTS);
    }
}
