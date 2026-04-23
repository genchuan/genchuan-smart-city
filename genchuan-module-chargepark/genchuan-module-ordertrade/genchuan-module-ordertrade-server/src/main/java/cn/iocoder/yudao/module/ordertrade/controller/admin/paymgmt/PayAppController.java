package cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayAppDO;
import cn.iocoder.yudao.module.ordertrade.service.paymgmt.PayAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "订单交易 - 支付管理 - 应用信息")
@RestController
@RequestMapping("/ordertrade/pay-app")
@Validated
public class PayAppController {

    @Resource
    private PayAppService payAppService;

    @PostMapping("/create")
    @Operation(summary = "创建支付应用")
    public CommonResult<Long> createPayApp(@Valid @RequestBody PayAppSaveReqVO createReqVO) {
        return success(payAppService.createPayApp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新支付应用")
    public CommonResult<Boolean> updatePayApp(@Valid @RequestBody PayAppSaveReqVO updateReqVO) {
        payAppService.updatePayApp(updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得支付应用详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<PayAppRespVO> getPayApp(@RequestParam("id") Long id) {
        PayAppDO obj = payAppService.getPayApp(id);
        return success(BeanUtils.toBean(obj, PayAppRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得支付应用分页列表")
   /* @PreAuthorize("@ss.hasPermission('ordertrade:pay-app:query')")*/
    public CommonResult<PageResult<PayAppRespVO>> getPayAppPage(@Valid PayAppPageReqVO pageReqVO) {
        PageResult<PayAppDO> pageResult = payAppService.getPayAppPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PayAppRespVO.class));
    }

    @PutMapping("/enable")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "生效支付应用")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> enablePayApp(@RequestParam("id") Long id) {
        payAppService.enablePayApp(id);
        return success(true);
    }

    @PutMapping("/disable")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "禁用支付应用")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> disablePayApp(@RequestParam("id") Long id) {
        payAppService.disablePayApp(id);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得支付应用统计图表数据")
    public CommonResult<PayAppChartRespVO> getPayAppChart(@Valid PayAppChartReqVO chartReqVO) {
        return success(payAppService.getPayAppChart(chartReqVO));
    }
}
