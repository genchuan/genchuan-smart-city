package cn.iocoder.yudao.module.ordertrade.service.invoicemgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceConfigDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.invoicemgmt.InvoiceConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class InvoiceConfigServiceImpl implements InvoiceConfigService {

    @Resource
    private InvoiceConfigMapper invoiceConfigMapper;

    @Override
    public Long createInvoiceConfig(InvoiceConfigSaveReqVO createReqVO) {
        InvoiceConfigDO obj = BeanUtils.toBean(createReqVO, InvoiceConfigDO.class);
        if (obj.getStatus() == null) {
            obj.setStatus("pending");
        }
        invoiceConfigMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updateInvoiceConfig(InvoiceConfigSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        invoiceConfigMapper.updateById(BeanUtils.toBean(updateReqVO, InvoiceConfigDO.class));
    }

    @Override
    public void deleteInvoiceConfig(Long id) {
        validateExists(id);
        invoiceConfigMapper.deleteById(id);
    }

    @Override
    public InvoiceConfigDO getInvoiceConfig(Long id) {
        return invoiceConfigMapper.selectById(id);
    }

    @Override
    public PageResult<InvoiceConfigDO> getInvoiceConfigPage(InvoiceConfigPageReqVO pageReqVO) {
        return invoiceConfigMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableInvoiceConfig(Long id) {
        InvoiceConfigDO config = invoiceConfigMapper.selectById(id);
        if (config == null) throw exception(INVOICE_CONFIG_NOT_EXISTS);
        if ("enabled".equals(config.getStatus())) throw exception(INVOICE_CONFIG_STATUS_CANNOT_ENABLE);
        InvoiceConfigDO update = new InvoiceConfigDO();
        update.setId(id);
        update.setStatus("enabled");
        update.setAuditTime(LocalDateTime.now());
        invoiceConfigMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableInvoiceConfig(Long id) {
        InvoiceConfigDO config = invoiceConfigMapper.selectById(id);
        if (config == null) throw exception(INVOICE_CONFIG_NOT_EXISTS);
        if ("disabled".equals(config.getStatus())) throw exception(INVOICE_CONFIG_STATUS_CANNOT_DISABLE);
        InvoiceConfigDO update = new InvoiceConfigDO();
        update.setId(id);
        update.setStatus("disabled");
        invoiceConfigMapper.updateById(update);
    }

    @Override
    public InvoiceConfigChartRespVO getInvoiceConfigChart(InvoiceConfigChartReqVO chartReqVO) {
        InvoiceConfigChartRespVO resp = new InvoiceConfigChartRespVO();
        resp.setCategoryData(invoiceConfigMapper.selectGroupByCategory());
        InvoiceConfigChartRespVO.CardData card = new InvoiceConfigChartRespVO.CardData();
        card.setEnabledCount(invoiceConfigMapper.selectEnabledCount());
        resp.setCardData(card);
        return resp;
    }

    private void validateExists(Long id) {
        if (invoiceConfigMapper.selectById(id) == null) throw exception(INVOICE_CONFIG_NOT_EXISTS);
    }
}
