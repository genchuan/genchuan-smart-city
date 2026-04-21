package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayWalletDO;
import cn.iocoder.yudao.module.ordertrade.service.paymgmt.PayWalletService;
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

@Tag(name = "订单交易 - 支付管理 - 钱包管理")
@RestController
@RequestMapping("/ordertrade/pay-wallet")
@Validated
public class PayWalletController {

    @Resource
    private PayWalletService payWalletService;

    @GetMapping("/get")
    @Operation(summary = "获得钱包详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<PayWalletRespVO> getPayWallet(@RequestParam("id") Long id) {
        PayWalletDO obj = payWalletService.getPayWallet(id);
        return success(BeanUtils.toBean(obj, PayWalletRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得钱包分页列表")
    public CommonResult<PageResult<PayWalletRespVO>> getPayWalletPage(@Valid PayWalletPageReqVO pageReqVO) {
        PageResult<PayWalletDO> pageResult = payWalletService.getPayWalletPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PayWalletRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出钱包 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPayWalletExcel(@Valid PayWalletPageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PayWalletDO> list = payWalletService.getPayWalletPage(pageReqVO).getList();
        ExcelUtils.write(response, "钱包管理.xls", "数据", PayWalletRespVO.class,
                BeanUtils.toBean(list, PayWalletRespVO.class));
    }

    @PutMapping("/recharge")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "充值")
    public CommonResult<Boolean> rechargePayWallet(@Valid @RequestBody IdReqVO reqVO) {
        payWalletService.rechargePayWallet(reqVO);
        return success(true);
    }

    @PutMapping("/withdraw")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "提现")
    public CommonResult<Boolean> withdrawPayWallet(@Valid @RequestBody IdReqVO reqVO) {
        payWalletService.withdrawPayWallet(reqVO);
        return success(true);
    }

    @PutMapping("/unfreeze")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "解冻钱包")
    public CommonResult<Boolean> unfreezePayWallet(@Valid @RequestBody IdReqVO reqVO) {
        payWalletService.unfreezePayWallet(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得钱包统计图表数据")
    public CommonResult<PayWalletChartRespVO> getPayWalletChart(@Valid PayWalletChartReqVO chartReqVO) {
        return success(payWalletService.getPayWalletChart(chartReqVO));
    }
}
