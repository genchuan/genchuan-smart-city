package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangeorder.ExchangeOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Tag(name = "管理后台 - 兑换订单")
@RestController
@RequestMapping("/marketop/exchange-order")
public class ExchangeOrderController {

    @Resource
    private ExchangeOrderService exchangeOrderService;

    @GetMapping("/page")
    @Operation(summary = "获得兑换订单分页")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<PageResult<ExchangeOrderRespVO>> getPage(ExchangeOrderPageReqVO reqVO) {
        PageResult<ExchangeOrderDO> pageResult = exchangeOrderService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, ExchangeOrderRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得兑换订单详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<ExchangeOrderRespVO> get(@RequestParam("id") Long id) {
        ExchangeOrderDO exchangeOrder = exchangeOrderService.get(id);
        return CommonResult.success(BeanUtils.toBean(exchangeOrder, ExchangeOrderRespVO.class));
    }

    @PutMapping("/pay")
    @Operation(summary = "支付兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:pay')")
    public CommonResult<Boolean> pay(@RequestParam("id") Long id) {
        exchangeOrderService.pay(id);
        return CommonResult.success(true);
    }

    @PutMapping("/deliver")
    @Operation(summary = "发货兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:deliver')")
    public CommonResult<Boolean> deliver(@Valid @RequestBody ExchangeOrderDeliverReqVO reqVO) {
        exchangeOrderService.deliver(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:cancel')")
    public CommonResult<Boolean> cancel(@RequestParam("id") Long id) {
        exchangeOrderService.cancel(id);
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public void export(ExchangeOrderPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ExchangeOrderDO> pageResult = exchangeOrderService.getPage(reqVO);
        List<ExchangeOrderRespVO> list = BeanUtils.toBean(pageResult.getList(), ExchangeOrderRespVO.class);
        ExcelUtils.write(response, "兑换订单.xlsx", "数据", ExchangeOrderRespVO.class, list);
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public void batchExport(@RequestParam(value = "ids", required = false) List<Long> ids,
                            ExchangeOrderPageReqVO reqVO, HttpServletResponse response) throws IOException {
        List<ExchangeOrderDO> list;
        if (ids != null && !ids.isEmpty()) {
            // TODO: 实现批量按ID查询
            list = Collections.emptyList();
        } else {
            reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
            list = exchangeOrderService.getPage(reqVO).getList();
        }
        ExcelUtils.write(response, "兑换订单.xlsx", "数据", ExchangeOrderRespVO.class,
                BeanUtils.toBean(list, ExchangeOrderRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "兑换订单统计图表")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<ExchangeOrderChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(exchangeOrderService.getChart(timeRange));
    }

}
