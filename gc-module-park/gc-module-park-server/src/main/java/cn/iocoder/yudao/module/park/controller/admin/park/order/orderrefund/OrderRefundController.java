package cn.iocoder.yudao.module.park.controller.admin.park.order.orderrefund;

import cn.iocoder.yudao.module.park.controller.admin.park.order.orderrefund.vo.OrderRefundPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderrefund.vo.OrderRefundRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderrefund.vo.OrderRefundSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderrefund.OrderRefundDO;
import cn.iocoder.yudao.module.park.service.park.order.orderrefund.OrderRefundService;
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


@Tag(name = "管理后台 - 退款订单")
@RestController
@RequestMapping("/park/order-refund")
@Validated
public class OrderRefundController {

    @Resource
    private OrderRefundService orderRefundService;

    @PostMapping("/create")
    @Operation(summary = "创建退款订单")
    @PreAuthorize("@ss.hasPermission('park:order-refund:create')")
    public CommonResult<Long> createOrderRefund(@Valid @RequestBody OrderRefundSaveReqVO createReqVO) {
        return success(orderRefundService.createOrderRefund(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新退款订单")
    @PreAuthorize("@ss.hasPermission('park:order-refund:update')")
    public CommonResult<Boolean> updateOrderRefund(@Valid @RequestBody OrderRefundSaveReqVO updateReqVO) {
        orderRefundService.updateOrderRefund(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除退款订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:order-refund:delete')")
    public CommonResult<Boolean> deleteOrderRefund(@RequestParam("id") Long id) {
        orderRefundService.deleteOrderRefund(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得退款订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:order-refund:query')")
    public CommonResult<OrderRefundRespVO> getOrderRefund(@RequestParam("id") Long id) {
        OrderRefundDO orderRefund = orderRefundService.getOrderRefund(id);
        return success(BeanUtils.toBean(orderRefund, OrderRefundRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得退款订单分页")
    @PreAuthorize("@ss.hasPermission('park:order-refund:query')")
    public CommonResult<PageResult<OrderRefundRespVO>> getOrderRefundPage(@Valid OrderRefundPageReqVO pageReqVO) {
        PageResult<OrderRefundDO> pageResult = orderRefundService.getOrderRefundPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrderRefundRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出退款订单 Excel")
    @PreAuthorize("@ss.hasPermission('park:order-refund:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderRefundExcel(@Valid OrderRefundPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderRefundDO> list = orderRefundService.getOrderRefundPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "退款订单.xls", "数据", OrderRefundRespVO.class,
                        BeanUtils.toBean(list, OrderRefundRespVO.class));
    }

}
