package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.cardorder.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardorder.CardOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "管理后台 - 卡种订单")
@RestController
@RequestMapping("/marketop/card-order")
public class CardOrderController {

    @Resource
    private CardOrderService cardOrderService;

    @GetMapping("/page")
    @Operation(summary = "获得卡种订单分页")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<PageResult<CardOrderRespVO>> getPage(CardOrderPageReqVO reqVO) {
        PageResult<CardOrderDO> pageResult = cardOrderService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, CardOrderRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得卡种订单详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<CardOrderRespVO> get(@RequestParam("id") Long id) {
        CardOrderDO cardOrder = cardOrderService.get(id);
        return CommonResult.success(BeanUtils.toBean(cardOrder, CardOrderRespVO.class));
    }

    @PutMapping("/pay")
    @Operation(summary = "支付卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:update')")
    public CommonResult<Boolean> pay(@RequestParam("id") Long id) {
        cardOrderService.pay(id);
        return CommonResult.success(true);
    }

    @PutMapping("/activate")
    @Operation(summary = "激活卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:update')")
    public CommonResult<Boolean> activate(@RequestParam("id") Long id) {
        cardOrderService.activate(id);
        return CommonResult.success(true);
    }

    @PutMapping("/invoice")
    @Operation(summary = "开票卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:update')")
    public CommonResult<Boolean> invoice(@RequestParam("id") Long id) {
        cardOrderService.invoice(id);
        return CommonResult.success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:update')")
    public CommonResult<Boolean> cancel(@RequestParam("id") Long id) {
        cardOrderService.cancel(id);
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public void export(CardOrderPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<CardOrderDO> pageResult = cardOrderService.getPage(reqVO);
        List<CardOrderRespVO> list = BeanUtils.toBean(pageResult.getList(), CardOrderRespVO.class);
        ExcelUtils.write(response, "卡种订单.xlsx", "数据", CardOrderRespVO.class, list);
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出卡种订单")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public void batchExport(@RequestParam("ids") List<Long> ids, HttpServletResponse response) throws IOException {
        // TODO: 实现按ID批量查询并导出
        List<CardOrderRespVO> voList = java.util.Collections.emptyList();
        ExcelUtils.write(response, "卡种订单(批量).xlsx", "数据", CardOrderRespVO.class, voList);
    }

    @GetMapping("/chart")
    @Operation(summary = "卡种订单图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:card-order:query')")
    public CommonResult<CardOrderChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(cardOrderService.getChart(timeRange));
    }

}
