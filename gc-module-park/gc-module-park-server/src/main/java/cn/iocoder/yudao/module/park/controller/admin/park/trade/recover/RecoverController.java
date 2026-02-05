package cn.iocoder.yudao.module.park.controller.admin.park.trade.recover;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.GenerateArrearsQrCodeReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.recover.vo.PreDiscountAutoCalculateReqVO;
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

import java.math.BigDecimal;

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
    @PreAuthorize("@ss.hasPermission('park:recover:generate-arrears-qrcode')")
    public CommonResult<String> generateArrearsQrCode(@Valid @RequestBody GenerateArrearsQrCodeReqVO reqVO) {
        String respVO = recoverService.generateArrearsQrCode(reqVO);
        return success(respVO);
    }



    //1.优惠券前折扣
    @PostMapping("/pre-discount-auto-calculate")
    @Operation(summary = "追缴服务-优惠券前折扣")
    @PreAuthorize("@ss.hasPermission('park:recover:pre-discount-auto-calculate')")
    public CommonResult<BigDecimal> preDiscountAutoCalculate(@Valid @RequestBody PreDiscountAutoCalculateReqVO reqVO) {
        BigDecimal respVO = recoverService.preDiscountAutoCalculate(reqVO);
        return success(respVO);
    }


}
