package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayOrderDO;
import cn.iocoder.yudao.module.ordertrade.service.paymgmt.PayOrderService;
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

@Tag(name = "订单交易 - 支付管理 - 支付订单")
@RestController
@RequestMapping("/ordertrade/pay-order")
@Validated
public class PayOrderController {

    @Resource
    private PayOrderService payOrderService;

    @GetMapping("/get")
    @Operation(summary = "获得支付订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<PayOrderRespVO> getPayOrder(@RequestParam("id") Long id) {
        PayOrderDO obj = payOrderService.getPayOrder(id);
        return success(BeanUtils.toBean(obj, PayOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得支付订单分页列表")
    public CommonResult<PageResult<PayOrderRespVO>> getPayOrderPage(@Valid PayOrderPageReqVO pageReqVO) {
        PageResult<PayOrderDO> pageResult = payOrderService.getPayOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PayOrderRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出支付订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPayOrderExcel(@Valid PayOrderPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PayOrderDO> list = payOrderService.getPayOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "支付订单.xls", "数据", PayOrderRespVO.class,
                BeanUtils.toBean(list, PayOrderRespVO.class));
    }

    @PutMapping("/pay")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "支付")
    public CommonResult<Boolean> payPayOrder(@Valid @RequestBody IdReqVO reqVO) {
        payOrderService.payPayOrder(reqVO);
        return success(true);
    }

    @PutMapping("/refund")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "退款")
    public CommonResult<Boolean> refundPayOrder(@Valid @RequestBody IdReqVO reqVO) {
        payOrderService.refundPayOrder(reqVO);
        return success(true);
    }

    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "取消支付订单")
    public CommonResult<Boolean> cancelPayOrder(@Valid @RequestBody IdReqVO reqVO) {
        payOrderService.cancelPayOrder(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得支付订单统计图表数据")
    public CommonResult<PayOrderChartRespVO> getPayOrderChart(@Valid PayOrderChartReqVO chartReqVO) {
        return success(payOrderService.getPayOrderChart(chartReqVO));
    }
}
