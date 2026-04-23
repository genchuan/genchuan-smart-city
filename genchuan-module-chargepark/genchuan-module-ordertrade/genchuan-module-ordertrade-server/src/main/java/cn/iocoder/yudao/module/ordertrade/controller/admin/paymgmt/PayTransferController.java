package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayTransferDO;
import cn.iocoder.yudao.module.ordertrade.service.paymgmt.PayTransferService;
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

@Tag(name = "订单交易 - 支付管理 - 转账订单")
@RestController
@RequestMapping("/ordertrade/pay-transfer")
@Validated
public class PayTransferController {

    @Resource
    private PayTransferService payTransferService;

    @GetMapping("/get")
    @Operation(summary = "获得转账订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<PayTransferRespVO> getPayTransfer(@RequestParam("id") Long id) {
        PayTransferDO obj = payTransferService.getPayTransfer(id);
        return success(BeanUtils.toBean(obj, PayTransferRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得转账订单分页列表")
    public CommonResult<PageResult<PayTransferRespVO>> getPayTransferPage(@Valid PayTransferPageReqVO pageReqVO) {
        PageResult<PayTransferDO> pageResult = payTransferService.getPayTransferPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PayTransferRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出转账订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPayTransferExcel(@Valid PayTransferPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PayTransferDO> list = payTransferService.getPayTransferPage(pageReqVO).getList();
        ExcelUtils.write(response, "转账订单.xls", "数据", PayTransferRespVO.class,
                BeanUtils.toBean(list, PayTransferRespVO.class));
    }

    @PutMapping("/execute")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "执行转账")
    public CommonResult<Boolean> executePayTransfer(@Valid @RequestBody IdReqVO reqVO) {
        payTransferService.executePayTransfer(reqVO);
        return success(true);
    }

    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "取消转账")
    public CommonResult<Boolean> cancelPayTransfer(@Valid @RequestBody IdReqVO reqVO) {
        payTransferService.cancelPayTransfer(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得转账订单统计图表数据")
    public CommonResult<PayTransferChartRespVO> getPayTransferChart(@Valid PayTransferChartReqVO chartReqVO) {
        return success(payTransferService.getPayTransferChart(chartReqVO));
    }
}
