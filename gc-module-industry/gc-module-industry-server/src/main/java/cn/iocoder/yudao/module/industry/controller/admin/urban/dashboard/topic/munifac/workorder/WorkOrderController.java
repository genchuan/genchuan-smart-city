package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderCreateReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.workorder.vo.WorkOrderRespVO;

import cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.workorder.WorkOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 市政设施专题-派发工单")
@RestController
@RequestMapping("/industry/work-order")
@Validated
public class WorkOrderController {
    @Resource
    private WorkOrderService workOrderService;

    @GetMapping("/get")
    @Operation(summary = "获得市政设施专题-获取工单")
    @PreAuthorize("@ss.hasPermission('industry:work-order:query')")
    public CommonResult<WorkOrderRespVO> getWorkOrder(
            @Valid WorkOrderQueryReqVO workOrderQueryReqVO
    ) {
        WorkOrderRespVO workOrderRespVO = workOrderService.getWorkOrder(workOrderQueryReqVO);
        return success(workOrderRespVO);
    }

    @PostMapping("/create")
    @Operation(summary = "获得市政设施专题-派发工单")
    @PreAuthorize("@ss.hasPermission('industry:work-order:query')")
    public CommonResult<Long> getWorkOrder(
            @Valid WorkOrderCreateReqVO workOrderCreateReqVO
    ) {
        Long workOrderId = workOrderService.createWorkOrder(workOrderCreateReqVO);
        return success(workOrderId);
    }
}
