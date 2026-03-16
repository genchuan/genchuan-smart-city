package cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder.vo.RoadWorkOrderPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.road.roadworkorder.vo.RoadWorkOrderPageRespVO;
import cn.iocoder.yudao.module.facility.service.road.roadworkorder.RoadWorkOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 道路工单")
@RestController
@RequestMapping("/facility/road-work-order")
@Validated
public class RoadWorkOrderController {

    @Resource
    private RoadWorkOrderService roadworkOrderService;

    @GetMapping("/page")
    @Operation(summary = "获得工单分页")
    @PreAuthorize("@ss.hasPermission('facility:road-work-order:query')")
    public CommonResult<PageResult<RoadWorkOrderPageRespVO>> getWorkOrderPage(@Valid RoadWorkOrderPageReqVO pageReqVO) {
        PageResult<RoadWorkOrderPageRespVO> pageResult = roadworkOrderService.getWorkOrderPage(pageReqVO);
        return success(pageResult);
    }

//    @PostMapping("/create")
//    @Operation(summary = "创建工单")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:create')")
//    public CommonResult<Long> createWorkOrder(@Valid @RequestBody WorkOrderSaveReqVO createReqVO) {
//        return success(workOrderService.createWorkOrder(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @Operation(summary = "更新工单")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:update')")
//    public CommonResult<Boolean> updateWorkOrder(@Valid @RequestBody WorkOrderSaveReqVO updateReqVO) {
//        workOrderService.updateWorkOrder(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除工单")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('facility:work-order:delete')")
//    public CommonResult<Boolean> deleteWorkOrder(@RequestParam("id") Long id) {
//        workOrderService.deleteWorkOrder(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @Operation(summary = "获得工单")
//    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:query')")
//    public CommonResult<WorkOrderRespVO> getWorkOrder(@RequestParam("id") Long id) {
//        WorkOrderDO workOrder = workOrderService.getWorkOrder(id);
//        return success(BeanUtils.toBean(workOrder, WorkOrderRespVO.class));
//    }
//
//    @GetMapping("/page")
//    @Operation(summary = "获得工单分页")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:query')")
//    public CommonResult<PageResult<WorkOrderRespVO>> getWorkOrderPage(@Valid WorkOrderPageReqVO pageReqVO) {
//        PageResult<WorkOrderDO> pageResult = workOrderService.getWorkOrderPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, WorkOrderRespVO.class));
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出工单 Excel")
//    @PreAuthorize("@ss.hasPermission('facility:work-order:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportWorkOrderExcel(@Valid WorkOrderPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<WorkOrderDO> list = workOrderService.getWorkOrderPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "工单.xls", "数据", WorkOrderRespVO.class,
//                        BeanUtils.toBean(list, WorkOrderRespVO.class));
//    }

}
