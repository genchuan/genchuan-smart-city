/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.planstatus;

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

import cn.iocoder.yudao.module.envirhealth.controller.admin.planstatus.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.planstatus.PlanStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.planstatus.PlanStatusService;

@Tag(name = "环境卫生管理 - 计划状态字典")
@RestController
@RequestMapping("/envirhealth/plan-status")
@Validated
public class PlanStatusController {

    @Resource
    private PlanStatusService planStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建计划状态字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:plan-status:create')")
    public CommonResult<Long> createPlanStatus(@Valid @RequestBody PlanStatusSaveReqVO createReqVO) {
        return success(planStatusService.createPlanStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新计划状态字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:plan-status:update')")
    public CommonResult<Boolean> updatePlanStatus(@Valid @RequestBody PlanStatusSaveReqVO updateReqVO) {
        planStatusService.updatePlanStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除计划状态字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:plan-status:delete')")
    public CommonResult<Boolean> deletePlanStatus(@RequestParam("id") Long id) {
        planStatusService.deletePlanStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得计划状态字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:plan-status:query')")
    public CommonResult<PlanStatusRespVO> getPlanStatus(@RequestParam("id") Long id) {
        PlanStatusDO planStatus = planStatusService.getPlanStatus(id);
        return success(BeanUtils.toBean(planStatus, PlanStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得计划状态字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:plan-status:query')")
    public CommonResult<PageResult<PlanStatusRespVO>> getPlanStatusPage(@Valid PlanStatusPageReqVO pageReqVO) {
        PageResult<PlanStatusDO> pageResult = planStatusService.getPlanStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PlanStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出计划状态字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:plan-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPlanStatusExcel(@Valid PlanStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PlanStatusDO> list = planStatusService.getPlanStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "计划状态字典.xls", "数据", PlanStatusRespVO.class,
                        BeanUtils.toBean(list, PlanStatusRespVO.class));
    }

}*/
