package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo.DispatchPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo.DispatchPlanRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo.DispatchPlanSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchplan.DispatchPlanDO;
import cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatchplan.DispatchPlanService;

@Tag(name = "管理后台 - 调度方案")
@RestController
@RequestMapping("/datacenter/dispatch-plan")
@Validated
public class DispatchPlanController {

    @Resource
    private DispatchPlanService dispatchPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建调度方案")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-plan:create')")
    public CommonResult<Long> createDispatchPlan(@Valid @RequestBody DispatchPlanSaveReqVO createReqVO) {
        return success(dispatchPlanService.createDispatchPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新调度方案")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-plan:update')")
    public CommonResult<Boolean> updateDispatchPlan(@Valid @RequestBody DispatchPlanSaveReqVO updateReqVO) {
        dispatchPlanService.updateDispatchPlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除调度方案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-plan:delete')")
    public CommonResult<Boolean> deleteDispatchPlan(@RequestParam("id") Long id) {
        dispatchPlanService.deleteDispatchPlan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得调度方案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-plan:query')")
    public CommonResult<DispatchPlanRespVO> getDispatchPlan(@RequestParam("id") Long id) {
        DispatchPlanDO dispatchPlan = dispatchPlanService.getDispatchPlan(id);
        return success(BeanUtils.toBean(dispatchPlan, DispatchPlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得调度方案分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-plan:query')")
    public CommonResult<PageResult<DispatchPlanRespVO>> getDispatchPlanPage(@Valid DispatchPlanPageReqVO pageReqVO) {
        PageResult<DispatchPlanDO> pageResult = dispatchPlanService.getDispatchPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DispatchPlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出调度方案 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dispatch-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDispatchPlanExcel(@Valid DispatchPlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DispatchPlanDO> list = dispatchPlanService.getDispatchPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "调度方案.xls", "数据", DispatchPlanRespVO.class,
                        BeanUtils.toBean(list, DispatchPlanRespVO.class));
    }

}