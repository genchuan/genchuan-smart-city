package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayCallbackDO;
import cn.iocoder.yudao.module.ordertrade.service.paymgmt.PayCallbackService;
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

@Tag(name = "订单交易 - 支付管理 - 回调通知")
@RestController
@RequestMapping("/ordertrade/pay-callback")
@Validated
public class PayCallbackController {

    @Resource
    private PayCallbackService payCallbackService;

    @GetMapping("/get")
    @Operation(summary = "获得回调通知详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<PayCallbackRespVO> getPayCallback(@RequestParam("id") Long id) {
        PayCallbackDO obj = payCallbackService.getPayCallback(id);
        return success(BeanUtils.toBean(obj, PayCallbackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得回调通知分页列表")
    public CommonResult<PageResult<PayCallbackRespVO>> getPayCallbackPage(@Valid PayCallbackPageReqVO pageReqVO) {
        PageResult<PayCallbackDO> pageResult = payCallbackService.getPayCallbackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PayCallbackRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出回调通知 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPayCallbackExcel(@Valid PayCallbackPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PayCallbackDO> list = payCallbackService.getPayCallbackPage(pageReqVO).getList();
        ExcelUtils.write(response, "回调通知.xls", "数据", PayCallbackRespVO.class,
                BeanUtils.toBean(list, PayCallbackRespVO.class));
    }

    @PutMapping("/retry")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "重推回调通知")
    public CommonResult<Boolean> retryPayCallback(@Valid @RequestBody IdReqVO reqVO) {
        payCallbackService.retryPayCallback(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得回调通知统计图表数据")
    public CommonResult<PayCallbackChartRespVO> getPayCallbackChart(@Valid PayCallbackChartReqVO chartReqVO) {
        return success(payCallbackService.getPayCallbackChart(chartReqVO));
    }
}
