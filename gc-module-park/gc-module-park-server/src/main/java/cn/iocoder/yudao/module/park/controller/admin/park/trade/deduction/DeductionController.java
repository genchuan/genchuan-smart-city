package cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeAmountRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeDurationReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.trade.deduction.vo.CalculateChargeDurationRespVO;
import cn.iocoder.yudao.module.park.service.park.trade.deduction.DeductionService;
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

@Tag(name = "管理后台 - 扣费服务")
@RestController
@RequestMapping("/park/deduction")
@Validated
public class DeductionController {
    @Resource
    private DeductionService deductionService;
    //计算收费时长，TODO 此controller是方便测试，后续只有一个扣费controller
    @PostMapping("/calculate-charge-duration")
    @Operation(summary = "扣费服务-计算收费时长(分钟)")
    @PreAuthorize("@ss.hasPermission('park:deduction:calculate-charge-duration')")
    public CommonResult<CalculateChargeDurationRespVO> calculateChargeDuration(@Valid @RequestBody CalculateChargeDurationReqVO reqVO) {
        CalculateChargeDurationRespVO respVO = deductionService.calculateChargeDuration(reqVO);
        return success(respVO);
    }

    //计算收费价格，TODO 此controller是方便测试，后续只有一个扣费controller
    @PostMapping("/calculate-charge-amount")
    @Operation(summary = "扣费服务-计算收费金额")
    @PreAuthorize("@ss.hasPermission('park:deduction:calculate-charge-amount')")
    public CommonResult<CalculateChargeAmountRespVO> calculateChargeAmount(@Valid @RequestBody CalculateChargeAmountReqVO reqVO) {
        CalculateChargeAmountRespVO respVO = deductionService.calculateChargeAmount(reqVO);
        return success(respVO);
    }


}
