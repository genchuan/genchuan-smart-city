package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceAuditDO;
import cn.iocoder.yudao.module.ordertrade.service.invoicemgmt.InvoiceAuditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "订单交易 - 电子发票 - 开票审核")
@RestController
@RequestMapping("/ordertrade/invoice-audit")
@Validated
public class InvoiceAuditController {
    @Resource
    private InvoiceAuditService invoiceAuditService;

    @PostMapping("/create")
    @Operation(summary = "创建开票审核")
    public CommonResult<Long> createInvoiceAudit(@Valid @RequestBody InvoiceAuditSaveReqVO createReqVO) {
        return success(invoiceAuditService.createInvoiceAudit(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新开票审核")
    public CommonResult<Boolean> updateInvoiceAudit(@Valid @RequestBody InvoiceAuditSaveReqVO updateReqVO) {
        invoiceAuditService.updateInvoiceAudit(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除开票审核")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteInvoiceAudit(@RequestParam("id") Long id) {
        invoiceAuditService.deleteInvoiceAudit(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得开票审核详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<InvoiceAuditRespVO> getInvoiceAudit(@RequestParam("id") Long id) {
        InvoiceAuditDO obj = invoiceAuditService.getInvoiceAudit(id);
        return success(BeanUtils.toBean(obj, InvoiceAuditRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得开票审核分页列表")
    public CommonResult<PageResult<InvoiceAuditRespVO>> getInvoiceAuditPage(@Valid InvoiceAuditPageReqVO pageReqVO) {
        PageResult<InvoiceAuditDO> pageResult = invoiceAuditService.getInvoiceAuditPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InvoiceAuditRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出开票审核 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInvoiceAuditExcel(@Valid InvoiceAuditPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InvoiceAuditDO> list = invoiceAuditService.getInvoiceAuditPage(pageReqVO).getList();
        ExcelUtils.write(response, "开票审核.xls", "数据", InvoiceAuditRespVO.class,
                BeanUtils.toBean(list, InvoiceAuditRespVO.class));
    }

    @PostMapping("/audit-pass")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "通过开票审核")
    public CommonResult<Boolean> approveInvoiceAudit(@Valid @RequestBody IdReqVO reqVO) {
        invoiceAuditService.approveInvoiceAudit(reqVO);
        return success(true);
    }

    @PostMapping("/audit-reject")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "驳回开票审核")
    public CommonResult<Boolean> rejectInvoiceAudit(@Valid @RequestBody InvoiceAuditRejectReqVO reqVO) {
        invoiceAuditService.rejectInvoiceAudit(reqVO);
        return success(true);
    }

    @PostMapping("/confirm")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "确认开票审核")
    public CommonResult<Boolean> confirmInvoiceAudit(@Valid @RequestBody IdReqVO reqVO) {
        invoiceAuditService.confirmInvoiceAudit(reqVO);
        return success(true);
    }

    @PostMapping("/reapply")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "重新申请开票审核")
    public CommonResult<Boolean> reapplyInvoiceAudit(@Valid @RequestBody IdReqVO reqVO) {
        invoiceAuditService.reapplyInvoiceAudit(reqVO);
        return success(true);
    }

    @PostMapping("/batch-audit")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "批量审核")
    public CommonResult<Boolean> batchAuditInvoiceAudit(@RequestBody List<Long> ids) {
        invoiceAuditService.batchAuditInvoiceAudit(ids);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得开票审核统计图表数据")
    public CommonResult<InvoiceAuditChartRespVO> getInvoiceAuditChart(@Valid InvoiceAuditChartReqVO chartReqVO) {
        return success(invoiceAuditService.getInvoiceAuditChart(chartReqVO));
    }
}
