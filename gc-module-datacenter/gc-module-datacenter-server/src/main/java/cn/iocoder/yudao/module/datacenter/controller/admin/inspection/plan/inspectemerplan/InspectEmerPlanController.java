package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo.InspectEmerPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo.InspectEmerPlanRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo.InspectEmerPlanSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.plan.inspectemerplan.InspectEmerPlanDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.plan.inspectemerplan.InspectEmerPlanService;

@Tag(name = "管理后台 - 应急巡查计划信息")
@RestController
@RequestMapping("/datacenter/inspect-emer-plan")
@Validated
public class InspectEmerPlanController {

    @Resource
    private InspectEmerPlanService inspectEmerPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建应急巡查计划信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-plan:create')")
    public CommonResult<Long> createInspectEmerPlan(@Valid @RequestBody InspectEmerPlanSaveReqVO createReqVO) {
        return success(inspectEmerPlanService.createInspectEmerPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应急巡查计划信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-plan:update')")
    public CommonResult<Boolean> updateInspectEmerPlan(@Valid @RequestBody InspectEmerPlanSaveReqVO updateReqVO) {
        inspectEmerPlanService.updateInspectEmerPlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应急巡查计划信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-plan:delete')")
    public CommonResult<Boolean> deleteInspectEmerPlan(@RequestParam("id") Long id) {
        inspectEmerPlanService.deleteInspectEmerPlan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应急巡查计划信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-plan:query')")
    public CommonResult<InspectEmerPlanRespVO> getInspectEmerPlan(@RequestParam("id") Long id) {
        InspectEmerPlanDO inspectEmerPlan = inspectEmerPlanService.getInspectEmerPlan(id);
        return success(BeanUtils.toBean(inspectEmerPlan, InspectEmerPlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应急巡查计划信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-plan:query')")
    public CommonResult<PageResult<InspectEmerPlanRespVO>> getInspectEmerPlanPage(@Valid InspectEmerPlanPageReqVO pageReqVO) {
        PageResult<InspectEmerPlanDO> pageResult = inspectEmerPlanService.getInspectEmerPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectEmerPlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应急巡查计划信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-emer-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectEmerPlanExcel(@Valid InspectEmerPlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectEmerPlanDO> list = inspectEmerPlanService.getInspectEmerPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应急巡查计划信息.xls", "数据", InspectEmerPlanRespVO.class,
                        BeanUtils.toBean(list, InspectEmerPlanRespVO.class));
    }

}