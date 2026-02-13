package cn.iocoder.yudao.module.park.controller.admin.park.trade.recover;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.*;
import cn.iocoder.yudao.module.park.service.park.trade.recover.RecoverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "漳州停车管理后台 - 追缴服务")
@RestController
@RequestMapping("/park/recover")
@Validated
public class RecoverController {

    @Resource
    private RecoverService recoverService;

    //1.生成欠费支付二维码
    @PostMapping("/generate-arrears-qrcode")
    @Operation(summary = "追缴服务-生成欠费支付二维码")
//    @PreAuthorize("@ss.hasPermission('park:recover:generate-arrears-qrcode')")
    public CommonResult<String> generateArrearsQrCode(@Valid @RequestBody GenerateArrearsQrCodeReqVO reqVO) {
        String respVO = recoverService.generateArrearsQrCode(reqVO);
        return success(respVO);
    }



    //1.优惠券前折扣
    @PostMapping("/pre-discount-auto-calculate")
    @Operation(summary = "追缴服务-优惠券前折扣")
//    @PreAuthorize("@ss.hasPermission('park:recover:pre-discount-auto-calculate')")
    public CommonResult<PreDiscountAutoCalculateRespVO> preDiscountAutoCalculate(@Valid @RequestBody PreDiscountAutoCalculateReqVO reqVO) {
        PreDiscountAutoCalculateRespVO respVO = recoverService.preDiscountAutoCalculate(reqVO);
        return success(respVO);
    }

    @PostMapping("/wallet-pay-order-temp")
    @Operation(summary = "追缴服务-临停订单钱包支付")
//    @PreAuthorize("@ss.hasPermission('park:recover:wallet-pay-order-temp')")
    public CommonResult<WalletPayOrderTempRespVO> walletPayOrderTemp(@Valid @RequestBody WalletPayOrderTempReqVO reqVO) {
        WalletPayOrderTempRespVO respVO=recoverService.walletPayOrderTemp(reqVO);
        return success(respVO);
    }

    //取消使用，用这个walletPayOrderTemp
    @PostMapping("/wallet-pay")
    @Operation(summary = "追缴服务-钱包支付")
//    @PreAuthorize("@ss.hasPermission('park:recover:wallet-pay')")
    public CommonResult<RecoverWalletPayRespVO> walletPay(@Valid @RequestBody RecoverWalletPayReqVO reqVO) {
        RecoverWalletPayRespVO respVO=recoverService.walletPay(reqVO);
        return success(respVO);
    }



}
