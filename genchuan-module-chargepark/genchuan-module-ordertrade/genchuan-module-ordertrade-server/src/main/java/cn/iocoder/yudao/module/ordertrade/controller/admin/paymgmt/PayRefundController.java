package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayRefundDO;
import cn.iocoder.yudao.module.ordertrade.service.paymgmt.PayRefundService;
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

@Tag(name = "订单交易 - 支付管理 - 退款订单")
@RestController
@RequestMapping("/ordertrade/pay-refund")
@Validated
public class PayRefundController {

    @Resource
    private PayRefundService payRefundService;

    @GetMapping("/get")
    @Operation(summary = "获得退款订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<PayRefundRespVO> getPayRefund(@RequestParam("id") Long id) {
        PayRefundDO obj = payRefundService.getPayRefund(id);
        return success(BeanUtils.toBean(obj, PayRefundRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得退款订单分页列表")
    public CommonResult<PageResult<PayRefundRespVO>> getPayRefundPage(@Valid PayRefundPageReqVO pageReqVO) {
        PageResult<PayRefundDO> pageResult = payRefundService.getPayRefundPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PayRefundRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出退款订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPayRefundExcel(@Valid PayRefundPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PayRefundDO> list = payRefundService.getPayRefundPage(pageReqVO).getList();
        ExcelUtils.write(response, "退款订单.xls", "数据", PayRefundRespVO.class,
                BeanUtils.toBean(list, PayRefundRespVO.class));
    }

    @PutMapping("/execute")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "执行退款")
    public CommonResult<Boolean> executePayRefund(@Valid @RequestBody IdReqVO reqVO) {
        payRefundService.executePayRefund(reqVO);
        return success(true);
    }

    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "取消退款")
    public CommonResult<Boolean> cancelPayRefund(@Valid @RequestBody IdReqVO reqVO) {
        payRefundService.cancelPayRefund(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得退款订单统计图表数据")
    public CommonResult<PayRefundChartRespVO> getPayRefundChart(@Valid PayRefundChartReqVO chartReqVO) {
        return success(payRefundService.getPayRefundChart(chartReqVO));
    }
}
