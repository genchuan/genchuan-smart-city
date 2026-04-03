package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist;

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

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderlist.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderlist.OrderListDO;
import cn.iocoder.yudao.module.vehiclecharging.service.orderlist.OrderListService;

@Tag(name = "管理后台 - 订单列表")
@RestController
@RequestMapping("/vehiclecharging/order-list")
@Validated
public class OrderListController {

    @Resource
    private OrderListService orderListService;

    @GetMapping("/page")
    @Operation(summary = "获得订单分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:query')")
    public CommonResult<PageResult<OrderListRespVO>> getOrderListPage(@Valid OrderListPageReqVO pageReqVO) {
        PageResult<OrderListDO> pageResult = orderListService.getOrderListPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrderListRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出订单 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderListExcel(@Valid OrderListExportReqVO exportReqVO,
                                     HttpServletResponse response) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderListDO> list = orderListService.getOrderListPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订单列表.xls", "数据", OrderListRespVO.class,
                BeanUtils.toBean(list, OrderListRespVO.class));
    }

    @GetMapping("/batchGet")
    @Operation(summary = "批量获得订单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:query')")
    public CommonResult<List<OrderListRespVO>> batchGetOrderList(@RequestParam("ids") List<Long> ids) {
        List<OrderListDO> orderListDOList = orderListService.getOrderListByIds(ids);
        List<OrderListRespVO> respVOList = BeanUtils.toBean(orderListDOList, OrderListRespVO.class);
        return success(respVOList);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:query')")
    public CommonResult<OrderListRespVO> getOrderList(@RequestParam("id") Long id) {
        OrderListDO orderList = orderListService.getOrderList(id);
        return success(BeanUtils.toBean(orderList, OrderListRespVO.class));
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消订单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:cancel')")
    public CommonResult<Boolean> cancelOrderList(@Valid @RequestBody OrderListCancelReqVO cancelReqVO) {
        orderListService.updateOrderList(cancelReqVO);
        return success(true);
    }

    @PutMapping("/payRemind")
    @Operation(summary = "支付提醒")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:payRemind')")
    public CommonResult<Boolean> payRemind(@Valid @RequestBody OrderListPayRemindReqVO reqVO) {
        return success(orderListService.payRemind(reqVO.getId()));
    }

//    @PostMapping("/refundApply")
//    @Operation(summary = "退款申请")
//    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:refundApply')")
//    public CommonResult<Boolean> refundApply(@Valid @RequestBody OrderListRefundApplyReqVO reqVO) {
//        return success(orderListService.refundApply(reqVO));
//    }

    @PutMapping("/evaluate")
    @Operation(summary = "评价订单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:evaluate')")
    public CommonResult<Boolean> evaluateOrderList(@Valid @RequestBody OrderListEvaluateReqVO reqVO) {
        return success(orderListService.evaluateOrderList(reqVO));
    }

    @PutMapping("/stopCharge")
    @Operation(summary = "终止充电")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order_list:stopCharge')")
    public CommonResult<Boolean> stopCharge(@Valid @RequestBody OrderListStopChargeReqVO reqVO) {
        return success(orderListService.stopCharge(reqVO));
    }

//    @PostMapping("/create")
//    @Operation(summary = "创建订单列表")
//    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:create')")
//    public CommonResult<Long> createOrderList(@Valid @RequestBody OrderListSaveReqVO createReqVO) {
//        return success(orderListService.createOrderList(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新订单列表")
//    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:update')")
//    public CommonResult<Boolean> updateOrderList(@Valid @RequestBody OrderListSaveReqVO updateReqVO) {
//        orderListService.updateOrderList(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除订单列表")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:delete')")
//    public CommonResult<Boolean> deleteOrderList(@RequestParam("id") Long id) {
//        orderListService.deleteOrderList(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除订单列表")
//                @PreAuthorize("@ss.hasPermission('vehiclecharging:order-list:delete')")
//    public CommonResult<Boolean> deleteOrderListList(@RequestParam("ids") List<Long> ids) {
//        orderListService.deleteOrderListListByIds(ids);
//        return success(true);
//    }

}