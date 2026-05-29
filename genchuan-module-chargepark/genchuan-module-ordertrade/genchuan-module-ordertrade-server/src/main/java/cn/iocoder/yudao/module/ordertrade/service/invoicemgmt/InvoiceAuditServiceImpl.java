package cn.iocoder.yudao.module.ordertrade.service.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.enums.InvoiceAuditStatusEnum;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceAuditDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt.InvoiceAuditMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class InvoiceAuditServiceImpl implements InvoiceAuditService {

    @Resource
    private InvoiceAuditMapper invoiceAuditMapper;

    @Override
    public Long createInvoiceAudit(InvoiceAuditSaveReqVO createReqVO) {
        InvoiceAuditDO obj = BeanUtils.toBean(createReqVO, InvoiceAuditDO.class);
        if (obj.getStatus() == null) {
            obj.setStatus(InvoiceAuditStatusEnum.PENDING.getValue());
        }
        if (obj.getApplyTime() == null) {
            obj.setApplyTime(LocalDateTime.now());
        }
        invoiceAuditMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateInvoiceAudit(InvoiceAuditSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        invoiceAuditMapper.updateById(BeanUtils.toBean(updateReqVO, InvoiceAuditDO.class));
    }

    @Override
    public void deleteInvoiceAudit(Long id) {
        validateExists(id);
        invoiceAuditMapper.deleteById(id);
    }

    @Override
    public InvoiceAuditDO getInvoiceAudit(Long id) {
        return invoiceAuditMapper.selectByIdWithInvoice(id);
    }

    @Override
    public PageResult<InvoiceAuditDO> getInvoiceAuditPage(InvoiceAuditPageReqVO pageReqVO) {
        Page<InvoiceAuditDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        var result = invoiceAuditMapper.selectPageWithApplicant(page, pageReqVO);
        return new PageResult<>(result.getRecords(), result.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void approveInvoiceAudit(IdReqVO reqVO) {
        InvoiceAuditDO audit = invoiceAuditMapper.selectById(reqVO.getId());
        if (audit == null) throw exception(INVOICE_AUDIT_NOT_EXISTS);
        if (!InvoiceAuditStatusEnum.PENDING.getValue().equals(audit.getStatus())) throw exception(INVOICE_AUDIT_STATUS_CANNOT_APPROVE);
        InvoiceAuditDO update = new InvoiceAuditDO();
        update.setId(reqVO.getId());
        update.setStatus(InvoiceAuditStatusEnum.APPROVED.getValue());
        update.setAuditorId(SecurityFrameworkUtils.getLoginUserId());
        update.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
        update.setAuditTime(LocalDateTime.now());
        update.setAuditResult(reqVO.getRemark() != null ? reqVO.getRemark() : "审核通过");
        invoiceAuditMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rejectInvoiceAudit(InvoiceAuditRejectReqVO reqVO) {
        InvoiceAuditDO audit = invoiceAuditMapper.selectById(reqVO.getId());
        if (audit == null) throw exception(INVOICE_AUDIT_NOT_EXISTS);
        if (!InvoiceAuditStatusEnum.PENDING.getValue().equals(audit.getStatus())) throw exception(INVOICE_AUDIT_STATUS_CANNOT_REJECT);
        InvoiceAuditDO update = new InvoiceAuditDO();
        update.setId(reqVO.getId());
        update.setStatus(InvoiceAuditStatusEnum.REJECTED.getValue());
        update.setAuditorId(SecurityFrameworkUtils.getLoginUserId());
        update.setAuditTime(LocalDateTime.now());
        update.setAuditResult(reqVO.getRejectReason());
        update.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
        invoiceAuditMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmInvoiceAudit(IdReqVO reqVO) {
        InvoiceAuditDO audit = invoiceAuditMapper.selectById(reqVO.getId());
        if (audit == null) throw exception(INVOICE_AUDIT_NOT_EXISTS);
        // 确认操作，业务自定义
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reapplyInvoiceAudit(IdReqVO reqVO) {
        InvoiceAuditDO audit = invoiceAuditMapper.selectById(reqVO.getId());
        if (audit == null) throw exception(INVOICE_AUDIT_NOT_EXISTS);
        InvoiceAuditDO update = new InvoiceAuditDO();
        update.setId(reqVO.getId());
        update.setStatus(InvoiceAuditStatusEnum.PENDING.getValue());
        update.setApplyTime(LocalDateTime.now());
        invoiceAuditMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchAuditInvoiceAudit(List<Long> ids) {
        ids.forEach(id -> {
            InvoiceAuditDO audit = invoiceAuditMapper.selectById(id);
            if (audit != null && InvoiceAuditStatusEnum.PENDING.getValue().equals(audit.getStatus())) {
                InvoiceAuditDO update = new InvoiceAuditDO();
                update.setId(id);
                update.setStatus(InvoiceAuditStatusEnum.APPROVED.getValue());
                update.setAuditorId(SecurityFrameworkUtils.getLoginUserId());
                update.setUpdater(SecurityFrameworkUtils.getLoginUserNickname());
                update.setAuditTime(LocalDateTime.now());
                invoiceAuditMapper.updateById(update);
            }
        });
    }

    @Override
    public InvoiceAuditChartRespVO getInvoiceAuditChart(InvoiceAuditChartReqVO chartReqVO) {
        InvoiceAuditChartRespVO resp = new InvoiceAuditChartRespVO();
        // 默认查全量，注释掉30天限制
        // LocalDateTime start = chartReqVO.getStartTime() != null ? chartReqVO.getStartTime() : LocalDateTime.now().minusDays(30);
        LocalDateTime start = chartReqVO.getStartTime();
        LocalDateTime end = chartReqVO.getEndTime() != null ? chartReqVO.getEndTime() : LocalDateTime.now();
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime now = LocalDateTime.now();

        resp.setTrendData(invoiceAuditMapper.selectTrend(start, end));

        InvoiceAuditChartRespVO.CardData card = new InvoiceAuditChartRespVO.CardData();
        card.setPendingCount(invoiceAuditMapper.selectPendingCount());

        Long approvedCount = invoiceAuditMapper.selectApprovedCount();
        Long totalCount = invoiceAuditMapper.selectTotalCount();
        if (totalCount != null && totalCount > 0) {
            card.setApproveRate(new BigDecimal(approvedCount).multiply(BigDecimal.valueOf(100))
                    .divide(new BigDecimal(totalCount), 1, RoundingMode.HALF_UP));
        } else {
            card.setApproveRate(BigDecimal.ZERO);
        }
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (invoiceAuditMapper.selectById(id) == null) throw exception(INVOICE_AUDIT_NOT_EXISTS);
    }
}
