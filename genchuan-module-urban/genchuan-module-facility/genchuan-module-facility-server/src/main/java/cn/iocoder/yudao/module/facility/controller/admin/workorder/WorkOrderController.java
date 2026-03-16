package cn.iocoder.yudao.module.facility.controller.admin.workorder;

import cn.iocoder.yudao.module.facility.controller.admin.workorder.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.workorder.WorkOrderDO;
import cn.iocoder.yudao.module.facility.service.workorder.WorkOrderService;
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
import org.springframework.web.multipart.MultipartFile;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 工单")
@RestController
@RequestMapping("/facility/work-order")
@Validated
public class WorkOrderController {

    @Resource
    private WorkOrderService workOrderService;

    @PostMapping("/upload-work-order-file")
    @Operation(summary = "上传工单资料")
    @PreAuthorize("@ss.hasPermission('facility:work-order:upload-work-order-file')")
    public CommonResult<UploadWorkOrderFileRespVO> uploadWorkOrderFile(
            @RequestPart("file") MultipartFile file,
            @Valid @ModelAttribute UploadWorkOrderFileReqVO reqVO) {
        UploadWorkOrderFileRespVO respVO = workOrderService.uploadWorkOrderFile(reqVO,file);
        return success(respVO);
    }


    @PostMapping("/supervise-overtime")
    @Operation(summary = "超时督办")
    @PreAuthorize("@ss.hasPermission('facility:work-order:supervise-overtime')")
    public CommonResult<Boolean> superviseOvertime(@Valid @RequestBody SuperviseOvertimeReqVO reqVO) {
        Boolean successFlag = workOrderService.superviseOvertime(reqVO);
        return success(successFlag);
    }
    @PostMapping("/update-process-status")
    @Operation(summary = "更新进度")
    @PreAuthorize("@ss.hasPermission('facility:work-order:update-process-status')")
    public CommonResult<Boolean> updateProcessStatus(@Valid @RequestBody UpdateProcessStatusReqVO reqVO) {
        Boolean successFlag = workOrderService.updateProcessStatus(reqVO);
        return success(successFlag);
    }
    @PostMapping("/reassign-work-order")
    @Operation(summary = "调整派单对象")
    @PreAuthorize("@ss.hasPermission('facility:work-order:reassign-work-order')")
    public CommonResult<Boolean> reassignWorkOrder(@Valid @RequestBody ReassignWorkOrderReqVO reqVO) {
        Boolean successFlag = workOrderService.reassignWorkOrder(reqVO);
        return success(successFlag);
    }
    @PostMapping("/batch-remind")
    @Operation(summary = "批量提醒")
    @PreAuthorize("@ss.hasPermission('facility:work-order:batch-remind')")
    public CommonResult<Long> BatchRemind(@Valid @RequestBody BatchRemindReqVO reqVO) {
        Long successCount = workOrderService.batchRemind(reqVO);
        return success(successCount);
    }
    @GetMapping("/page-complete")
    @Operation(summary = "完整获得工单分页")
    @PreAuthorize("@ss.hasPermission('facility:work-order:query')")
    public CommonResult<PageResult<WorkOrderRespVO>> getCompleteWorkOrderPage(@Valid WorkOrderPageReqVO pageReqVO) {
        PageResult<WorkOrderRespVO> pageResult = workOrderService.getCompleteWorkOrderPage(pageReqVO);
        return success(pageResult);
    }
    //派发订单
    @PostMapping("/create")
    @Operation(summary = "创建工单")
    @PreAuthorize("@ss.hasPermission('facility:work-order:create')")
    public CommonResult<Long> createWorkOrder(@Valid @RequestBody WorkOrderSaveReqVO createReqVO) {
        return success(workOrderService.createWorkOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新工单")
    @PreAuthorize("@ss.hasPermission('facility:work-order:update')")
    public CommonResult<Boolean> updateWorkOrder(@Valid @RequestBody WorkOrderUpdateReqVO updateReqVO) {
        workOrderService.updateWorkOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除工单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:work-order:delete')")
    public CommonResult<Boolean> deleteWorkOrder(@RequestParam("id") Long id) {
        workOrderService.deleteWorkOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得工单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:work-order:query')")
    public CommonResult<WorkOrderRespVO> getWorkOrder(@RequestParam("id") Long id) {
        WorkOrderDO workOrder = workOrderService.getWorkOrder(id);
        return success(BeanUtils.toBean(workOrder, WorkOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "(不用)获得工单分页")
    @PreAuthorize("@ss.hasPermission('facility:work-order:query')")
    public CommonResult<PageResult<WorkOrderRespVO>> getWorkOrderPage(@Valid WorkOrderPageReqVO pageReqVO) {
        PageResult<WorkOrderDO> pageResult = workOrderService.getWorkOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WorkOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出工单 Excel")
    @PreAuthorize("@ss.hasPermission('facility:work-order:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWorkOrderExcel(@Valid WorkOrderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WorkOrderDO> list = workOrderService.getWorkOrderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "工单.xls", "数据", WorkOrderRespVO.class,
                        BeanUtils.toBean(list, WorkOrderRespVO.class));
    }

}
