package cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.invoicemgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.invoicemgmt.InvoiceConfigDO;
import cn.iocoder.yudao.module.ordertrade.service.invoicemgmt.InvoiceConfigService;
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

@Tag(name = "订单交易 - 电子发票 - 发票配置")
@RestController
@RequestMapping("/ordertrade/invoice-config")
@Validated
public class InvoiceConfigController {

    @Resource
    private InvoiceConfigService invoiceConfigService;

    @PostMapping("/create")
    @Operation(summary = "创建发票配置")
    public CommonResult<Long> createInvoiceConfig(@Valid @RequestBody InvoiceConfigSaveReqVO createReqVO) {
        return success(invoiceConfigService.createInvoiceConfig(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新发票配置")
    public CommonResult<Boolean> updateInvoiceConfig(@Valid @RequestBody InvoiceConfigSaveReqVO updateReqVO) {
        invoiceConfigService.updateInvoiceConfig(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除发票配置")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteInvoiceConfig(@RequestParam("id") Long id) {
        invoiceConfigService.deleteInvoiceConfig(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得发票配置详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<InvoiceConfigRespVO> getInvoiceConfig(@RequestParam("id") Long id) {
        InvoiceConfigDO obj = invoiceConfigService.getInvoiceConfig(id);
        return success(BeanUtils.toBean(obj, InvoiceConfigRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得发票配置分页列表")
    public CommonResult<PageResult<InvoiceConfigRespVO>> getInvoiceConfigPage(@Valid InvoiceConfigPageReqVO pageReqVO) {
        PageResult<InvoiceConfigDO> pageResult = invoiceConfigService.getInvoiceConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InvoiceConfigRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出发票配置 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInvoiceConfigExcel(@Valid InvoiceConfigPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InvoiceConfigDO> list = invoiceConfigService.getInvoiceConfigPage(pageReqVO).getList();
        ExcelUtils.write(response, "发票配置.xls", "数据", InvoiceConfigRespVO.class,
                BeanUtils.toBean(list, InvoiceConfigRespVO.class));
    }

    @PutMapping("/enable")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "生效发票配置")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> enableInvoiceConfig(@RequestParam("id") Long id) {
        invoiceConfigService.enableInvoiceConfig(id);
        return success(true);
    }

    @PutMapping("/disable")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "禁用发票配置")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> disableInvoiceConfig(@RequestParam("id") Long id) {
        invoiceConfigService.disableInvoiceConfig(id);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得发票配置统计图表数据")
    public CommonResult<InvoiceConfigChartRespVO> getInvoiceConfigChart(@Valid InvoiceConfigChartReqVO chartReqVO) {
        return success(invoiceConfigService.getInvoiceConfigChart(chartReqVO));
    }
}
