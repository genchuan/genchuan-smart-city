package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo.InspectDailyPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo.InspectDailyPlanRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectdailyplan.vo.InspectDailyPlanSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectdailyplan.InspectDailyPlanDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.plan.inspectdailyplan.InspectDailyPlanService;

@Tag(name = "管理后台 - 日常巡查计划信息")
@RestController
@RequestMapping("/datacenter/inspect-daily-plan")
@Validated
public class InspectDailyPlanController {

    @Resource
    private InspectDailyPlanService inspectDailyPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建日常巡查计划信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-plan:create')")
    public CommonResult<Long> createInspectDailyPlan(@Valid @RequestBody InspectDailyPlanSaveReqVO createReqVO) {
        return success(inspectDailyPlanService.createInspectDailyPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新日常巡查计划信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-plan:update')")
    public CommonResult<Boolean> updateInspectDailyPlan(@Valid @RequestBody InspectDailyPlanSaveReqVO updateReqVO) {
        inspectDailyPlanService.updateInspectDailyPlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除日常巡查计划信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-plan:delete')")
    public CommonResult<Boolean> deleteInspectDailyPlan(@RequestParam("id") Long id) {
        inspectDailyPlanService.deleteInspectDailyPlan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得日常巡查计划信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-plan:query')")
    public CommonResult<InspectDailyPlanRespVO> getInspectDailyPlan(@RequestParam("id") Long id) {
        InspectDailyPlanDO inspectDailyPlan = inspectDailyPlanService.getInspectDailyPlan(id);
        return success(BeanUtils.toBean(inspectDailyPlan, InspectDailyPlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得日常巡查计划信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-plan:query')")
    public CommonResult<PageResult<InspectDailyPlanRespVO>> getInspectDailyPlanPage(@Valid InspectDailyPlanPageReqVO pageReqVO) {
        PageResult<InspectDailyPlanDO> pageResult = inspectDailyPlanService.getInspectDailyPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectDailyPlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出日常巡查计划信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-daily-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectDailyPlanExcel(@Valid InspectDailyPlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectDailyPlanDO> list = inspectDailyPlanService.getInspectDailyPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "日常巡查计划信息.xls", "数据", InspectDailyPlanRespVO.class,
                        BeanUtils.toBean(list, InspectDailyPlanRespVO.class));
    }

}