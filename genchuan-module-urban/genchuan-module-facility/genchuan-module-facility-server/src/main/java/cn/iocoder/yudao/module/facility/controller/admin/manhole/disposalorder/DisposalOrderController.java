package cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder;

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

import cn.iocoder.yudao.module.facility.controller.admin.manhole.disposalorder.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import cn.iocoder.yudao.module.facility.service.manhole.disposalorder.DisposalOrderService;

@Tag(name = "管理后台 - 处置工单")
@RestController
@RequestMapping("/disposal/order")
@Validated
public class DisposalOrderController {

    @Resource
    private DisposalOrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建处置工单")
    @PreAuthorize("@ss.hasPermission('disposal:order:create')")
    public CommonResult<Long> createOrder(@Valid @RequestBody DisposalOrderSaveReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新处置工单")
    @PreAuthorize("@ss.hasPermission('disposal:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody DisposalOrderSaveReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除处置工单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('disposal:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得处置工单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('disposal:order:query')")
    public CommonResult<DisposalOrderRespVO> getOrder(@RequestParam("id") Long id) {
        DisposalOrderDO order = orderService.getOrder(id);
        return success(BeanUtils.toBean(order, DisposalOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得处置工单分页")
    @PreAuthorize("@ss.hasPermission('disposal:order:query')")
    public CommonResult<PageResult<DisposalOrderRespVO>> getOrderPage(@Valid DisposalOrderPageReqVO pageReqVO) {
        PageResult<DisposalOrderDO> pageResult = orderService.getOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DisposalOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出处置工单 Excel")
    @PreAuthorize("@ss.hasPermission('disposal:order:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderExcel(@Valid DisposalOrderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DisposalOrderDO> list = orderService.getOrderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "处置工单.xls", "数据", DisposalOrderRespVO.class,
                        BeanUtils.toBean(list, DisposalOrderRespVO.class));
    }

    @GetMapping("order/page")
    @Operation(summary = "窨井盖维修工单分页查询")
    public CommonResult<PageResult<ManholeCoverRepairOrderPageRespVO>> page(ManholeCoverRepairOrderPageReqVO reqVO) {
        return CommonResult.success(orderService.page(reqVO));
    }

    @PostMapping("/add")
    @Operation(summary = "窨井盖维修工单新增")
    public CommonResult<ManholeCoverRepairOrderAddRespVO> addRepairOrder(
            @Validated @RequestBody ManholeCoverRepairOrderAddReqVO reqVO) {
        ManholeCoverRepairOrderAddRespVO respVO = orderService.addRepairOrder(reqVO);
        return CommonResult.success(respVO);
    }

}