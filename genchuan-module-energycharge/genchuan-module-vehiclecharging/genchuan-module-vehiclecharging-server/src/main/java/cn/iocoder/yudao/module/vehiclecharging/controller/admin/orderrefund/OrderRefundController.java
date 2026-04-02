package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund;

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

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderrefund.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderrefund.OrderRefundDO;
import cn.iocoder.yudao.module.vehiclecharging.service.orderrefund.OrderRefundService;

@Tag(name = "管理后台 - 订单退款")
@RestController
@RequestMapping("/vehiclecharging/order-refund")
@Validated
public class OrderRefundController {

    @Resource
    private OrderRefundService orderRefundService;

    @PostMapping("/create")
    @Operation(summary = "创建订单退款")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-refund:create')")
    public CommonResult<Long> createOrderRefund(@Valid @RequestBody OrderRefundSaveReqVO createReqVO) {
        return success(orderRefundService.createOrderRefund(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单退款")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-refund:update')")
    public CommonResult<Boolean> updateOrderRefund(@Valid @RequestBody OrderRefundSaveReqVO updateReqVO) {
        orderRefundService.updateOrderRefund(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单退款")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-refund:delete')")
    public CommonResult<Boolean> deleteOrderRefund(@RequestParam("id") Long id) {
        orderRefundService.deleteOrderRefund(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除订单退款")
                @PreAuthorize("@ss.hasPermission('vehiclecharging:order-refund:delete')")
    public CommonResult<Boolean> deleteOrderRefundList(@RequestParam("ids") List<Long> ids) {
        orderRefundService.deleteOrderRefundListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单退款")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-refund:query')")
    public CommonResult<OrderRefundRespVO> getOrderRefund(@RequestParam("id") Long id) {
        OrderRefundDO orderRefund = orderRefundService.getOrderRefund(id);
        return success(BeanUtils.toBean(orderRefund, OrderRefundRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得订单退款分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-refund:query')")
    public CommonResult<PageResult<OrderRefundRespVO>> getOrderRefundPage(@Valid OrderRefundPageReqVO pageReqVO) {
        PageResult<OrderRefundDO> pageResult = orderRefundService.getOrderRefundPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrderRefundRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单退款 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-refund:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderRefundExcel(@Valid OrderRefundPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderRefundDO> list = orderRefundService.getOrderRefundPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订单退款.xls", "数据", OrderRefundRespVO.class,
                        BeanUtils.toBean(list, OrderRefundRespVO.class));
    }

}