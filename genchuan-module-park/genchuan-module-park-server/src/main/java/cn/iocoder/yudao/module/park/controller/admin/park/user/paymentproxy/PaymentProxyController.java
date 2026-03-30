package cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo.PaymentProxyPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo.PaymentProxyRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.paymentproxy.vo.PaymentProxySaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.paymentproxy.PaymentProxyDO;
import cn.iocoder.yudao.module.park.service.park.user.paymentproxy.PaymentProxyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 代付规则")
@RestController
@RequestMapping("/park/payment-proxy")
@Validated
public class PaymentProxyController {

    @Resource
    private PaymentProxyService paymentProxyService;

    @PostMapping("/create")
    @Operation(summary = "创建代付规则")
    @PreAuthorize("@ss.hasPermission('park:payment-proxy:create')")
    public CommonResult<Long> createPaymentProxy(@Valid @RequestBody PaymentProxySaveReqVO createReqVO) {
        return success(paymentProxyService.createPaymentProxy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新代付规则")
    @PreAuthorize("@ss.hasPermission('park:payment-proxy:update')")
    public CommonResult<Boolean> updatePaymentProxy(@Valid @RequestBody PaymentProxySaveReqVO updateReqVO) {
        paymentProxyService.updatePaymentProxy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除代付规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:payment-proxy:delete')")
    public CommonResult<Boolean> deletePaymentProxy(@RequestParam("id") Long id) {
        paymentProxyService.deletePaymentProxy(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得代付规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:payment-proxy:query')")
    public CommonResult<PaymentProxyRespVO> getPaymentProxy(@RequestParam("id") Long id) {
        PaymentProxyDO paymentProxy = paymentProxyService.getPaymentProxy(id);
        return success(BeanUtils.toBean(paymentProxy, PaymentProxyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得代付规则分页")
    @PreAuthorize("@ss.hasPermission('park:payment-proxy:query')")
    public CommonResult<PageResult<PaymentProxyRespVO>> getPaymentProxyPage(@Valid PaymentProxyPageReqVO pageReqVO) {
        PageResult<PaymentProxyDO> pageResult = paymentProxyService.getPaymentProxyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PaymentProxyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出代付规则 Excel")
    @PreAuthorize("@ss.hasPermission('park:payment-proxy:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPaymentProxyExcel(@Valid PaymentProxyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PaymentProxyDO> list = paymentProxyService.getPaymentProxyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "代付规则.xls", "数据", PaymentProxyRespVO.class,
                        BeanUtils.toBean(list, PaymentProxyRespVO.class));
    }

}
