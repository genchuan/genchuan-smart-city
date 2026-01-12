package cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo.ParkPaymentProxyPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo.ParkPaymentProxyRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo.ParkPaymentProxySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkpaymentproxy.ParkPaymentProxyDO;
import cn.iocoder.yudao.module.industry.service.park.user.parkpaymentproxy.ParkPaymentProxyService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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


@Tag(name = "漳州停车管理 - 代付规则")
@RestController
@RequestMapping("/industry/park-payment-proxy")
@Validated
public class ParkPaymentProxyController {

    @Resource
    private ParkPaymentProxyService parkPaymentProxyService;

    @PostMapping("/create")
    @Operation(summary = "创建代付规则")
    @PreAuthorize("@ss.hasPermission('industry:park-payment-proxy:create')")
    public CommonResult<Long> createParkPaymentProxy(@Valid @RequestBody ParkPaymentProxySaveReqVO createReqVO) {
        return success(parkPaymentProxyService.createParkPaymentProxy(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新代付规则")
    @PreAuthorize("@ss.hasPermission('industry:park-payment-proxy:update')")
    public CommonResult<Boolean> updateParkPaymentProxy(@Valid @RequestBody ParkPaymentProxySaveReqVO updateReqVO) {
        parkPaymentProxyService.updateParkPaymentProxy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除代付规则")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-payment-proxy:delete')")
    public CommonResult<Boolean> deleteParkPaymentProxy(@RequestParam("id") Long id) {
        parkPaymentProxyService.deleteParkPaymentProxy(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得代付规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-payment-proxy:query')")
    public CommonResult<ParkPaymentProxyRespVO> getParkPaymentProxy(@RequestParam("id") Long id) {
        ParkPaymentProxyDO parkPaymentProxy = parkPaymentProxyService.getParkPaymentProxy(id);
        return success(BeanUtils.toBean(parkPaymentProxy, ParkPaymentProxyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得代付规则分页")
    @PreAuthorize("@ss.hasPermission('industry:park-payment-proxy:query')")
    public CommonResult<PageResult<ParkPaymentProxyRespVO>> getParkPaymentProxyPage(@Valid ParkPaymentProxyPageReqVO pageReqVO) {
        PageResult<ParkPaymentProxyDO> pageResult = parkPaymentProxyService.getParkPaymentProxyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkPaymentProxyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出代付规则 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-payment-proxy:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkPaymentProxyExcel(@Valid ParkPaymentProxyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkPaymentProxyDO> list = parkPaymentProxyService.getParkPaymentProxyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "代付规则.xls", "数据", ParkPaymentProxyRespVO.class,
                        BeanUtils.toBean(list, ParkPaymentProxyRespVO.class));
    }

}
