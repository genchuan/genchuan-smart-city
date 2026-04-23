package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceListDO;
import cn.iocoder.yudao.module.ordertrade.service.invoicemgmt.InvoiceListService;
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

@Tag(name = "订单交易 - 电子发票 - 发票列表")
@RestController
@RequestMapping("/ordertrade/invoice-list")
@Validated
public class InvoiceListController {

    @Resource
    private InvoiceListService invoiceListService;

    @PostMapping("/create")
    @Operation(summary = "创建发票")
    public CommonResult<Long> createInvoiceList(@Valid @RequestBody InvoiceListSaveReqVO createReqVO) {
        return success(invoiceListService.createInvoiceList(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新发票")
    public CommonResult<Boolean> updateInvoiceList(@Valid @RequestBody InvoiceListSaveReqVO updateReqVO) {
        invoiceListService.updateInvoiceList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除发票")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteInvoiceList(@RequestParam("id") Long id) {
        invoiceListService.deleteInvoiceList(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得发票详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<InvoiceListRespVO> getInvoiceList(@RequestParam("id") Long id) {
        InvoiceListDO obj = invoiceListService.getInvoiceList(id);
        return success(BeanUtils.toBean(obj, InvoiceListRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得发票分页列表")
    public CommonResult<PageResult<InvoiceListRespVO>> getInvoiceListPage(@Valid InvoiceListPageReqVO pageReqVO) {
        PageResult<InvoiceListDO> pageResult = invoiceListService.getInvoiceListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InvoiceListRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出发票 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInvoiceListExcel(@Valid InvoiceListPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InvoiceListDO> list = invoiceListService.getInvoiceListPage(pageReqVO).getList();
        ExcelUtils.write(response, "发票列表.xls", "数据", InvoiceListRespVO.class,
                BeanUtils.toBean(list, InvoiceListRespVO.class));
    }

    @PostMapping("/audit-pass")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "通过发票审核")
    public CommonResult<Boolean> approveInvoiceList(@Valid @RequestBody IdReqVO reqVO) {
        invoiceListService.approveInvoiceList(reqVO);
        return success(true);
    }

    @PostMapping("/audit-reject")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "驳回发票审核")
    public CommonResult<Boolean> rejectInvoiceList(@Valid @RequestBody IdReqVO reqVO) {
        invoiceListService.rejectInvoiceList(reqVO);
        return success(true);
    }

    @PostMapping("/invoice")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "开票")
    public CommonResult<Boolean> invoiceInvoiceList(@Valid @RequestBody IdReqVO reqVO) {
        invoiceListService.invoiceInvoiceList(reqVO);
        return success(true);
    }

    @PostMapping("/push")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "推送发票")
    public CommonResult<Boolean> pushInvoiceList(@Valid @RequestBody IdReqVO reqVO) {
        invoiceListService.pushInvoiceList(reqVO);
        return success(true);
    }

    @GetMapping("/download")
    @Operation(summary = "下载发票")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<String> downloadInvoiceList(@RequestParam("id") Long id) {
        return success(invoiceListService.downloadInvoiceList(id));
    }

    @PostMapping("/reapply")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "重新申请发票")
    public CommonResult<Boolean> reapplyInvoiceList(@Valid @RequestBody IdReqVO reqVO) {
        invoiceListService.reapplyInvoiceList(reqVO);
        return success(true);
    }

    @PostMapping("/batch-invoice")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "批量开票")
    public CommonResult<Boolean> batchInvoiceList(@RequestBody List<Long> ids) {
        invoiceListService.batchInvoiceList(ids);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得发票统计图表数据")
    public CommonResult<InvoiceListChartRespVO> getInvoiceListChart(@Valid InvoiceListChartReqVO chartReqVO) {
        return success(invoiceListService.getInvoiceListChart(chartReqVO));
    }
}
