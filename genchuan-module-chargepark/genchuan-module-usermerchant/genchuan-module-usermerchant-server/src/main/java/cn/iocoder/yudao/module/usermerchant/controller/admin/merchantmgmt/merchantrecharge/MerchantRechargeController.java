package cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantrecharge.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantrecharge.MerchantRechargeDO;
import cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantrecharge.MerchantRechargeService;

@Tag(name = "管理后台 - 商户充值")
@RestController
@RequestMapping("/usermerchant/merchant-recharge")
@Validated
public class MerchantRechargeController {

    @Resource
    private MerchantRechargeService merchantRechargeService;

    @GetMapping("/page")
    @Operation(summary = "获得商户充值分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:query')")
    public CommonResult<PageResult<MerchantRechargePageRespVO>> getMerchantRechargePage(@Valid MerchantRechargePageReqVO pageReqVO) {
        PageResult<MerchantRechargeDO> pageResult = merchantRechargeService.getMerchantRechargePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MerchantRechargePageRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出商户充值")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMerchantRechargeExcel(@Valid MerchantRechargePageReqVO pageReqVO,
                                            HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MerchantRechargeDO> list = merchantRechargeService.getMerchantRechargePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "商户充值.xls", "数据", MerchantRechargeExportRespVO.class,
                BeanUtils.toBean(list, MerchantRechargeExportRespVO.class));
    }

    @PutMapping("/pay")
    @Operation(summary = "商户支付")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:pay')")
    public CommonResult<Boolean> payMerchantRecharge(@Valid @RequestBody MerchantRechargePayReqVO payReqVO) {
        return success(merchantRechargeService.payMerchantRecharge(payReqVO));
    }

    @PutMapping("/confirm")
    @Operation(summary = "支付确认")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:confirm')")
    public CommonResult<Boolean> confirmMerchantRecharge(@Valid @RequestBody MerchantRechargePayReqVO payReqVO) {
        return success(merchantRechargeService.cashMerchantRecharge(payReqVO,"确认"));
    }

    @PutMapping("/cancel")
    @Operation(summary = "支付取消")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:cancel')")
    public CommonResult<Boolean> cancelMerchantRecharge(@Valid @RequestBody MerchantRechargePayReqVO payReqVO) {
        return success(merchantRechargeService.cashMerchantRecharge(payReqVO,"取消"));
    }

    @GetMapping("/chart")
    @Operation(summary = "商户充值统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:query')")
    public CommonResult<MerchantRechargeChartRespVO> getMerchantRechargeChart(@Valid MerchantRechargeChartReqVO chartReqVO) {
        return success(merchantRechargeService.getMerchantRechargeChart(chartReqVO));
    }

    @PostMapping("/debug-create")
    @Operation(summary = "模拟支付订单")
    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:create')")
    public CommonResult<Boolean> createMerchantRecharge(@Valid @RequestBody MerchantRechargeCreateReqVO createReqVO) {
        return success(merchantRechargeService.createMerchantRecharge(createReqVO));
    }

//    @PutMapping("/update")
//    @Operation(summary = "更新商户充值")
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:update')")
//    public CommonResult<Boolean> updateMerchantRecharge(@Valid @RequestBody MerchantRechargeSaveReqVO updateReqVO) {
//        merchantRechargeService.updateMerchantRecharge(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除商户充值")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:delete')")
//    public CommonResult<Boolean> deleteMerchantRecharge(@RequestParam("id") Long id) {
//        merchantRechargeService.deleteMerchantRecharge(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除商户充值")
//                @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:delete')")
//    public CommonResult<Boolean> deleteMerchantRechargeList(@RequestParam("ids") List<Long> ids) {
//        merchantRechargeService.deleteMerchantRechargeListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得商户充值")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('usermerchant:merchant-recharge:query')")
//    public CommonResult<MerchantRechargePageRespVO> getMerchantRecharge(@RequestParam("id") Long id) {
//        MerchantRechargeDO merchantRecharge = merchantRechargeService.getMerchantRecharge(id);
//        return success(BeanUtils.toBean(merchantRecharge, MerchantRechargePageRespVO.class));
//    }

}