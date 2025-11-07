package cn.iocoder.yudao.module.smartcity.controller.admin.inspectioninspectionplan;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.inspectioninspectionplan.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectioninspectionplan.InspectionInspectionPlanDO;
import cn.iocoder.yudao.module.smartcity.service.inspectioninspectionplan.InspectionInspectionPlanService;

@Tag(name = "管理后台 - 巡查计划")
@RestController
@RequestMapping("/smartcity/inspection-inspection-plan")
@Validated
public class InspectionInspectionPlanController {

    @Resource
    private InspectionInspectionPlanService inspectionInspectionPlanService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查计划")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-inspection-plan:create')")
    public CommonResult<Long> createInspectionInspectionPlan(@Valid @RequestBody InspectionInspectionPlanSaveReqVO createReqVO) {
        return success(inspectionInspectionPlanService.createInspectionInspectionPlan(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查计划")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-inspection-plan:update')")
    public CommonResult<Boolean> updateInspectionInspectionPlan(@Valid @RequestBody InspectionInspectionPlanSaveReqVO updateReqVO) {
        inspectionInspectionPlanService.updateInspectionInspectionPlan(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-inspection-plan:delete')")
    public CommonResult<Boolean> deleteInspectionInspectionPlan(@RequestParam("id") Long id) {
        inspectionInspectionPlanService.deleteInspectionInspectionPlan(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-inspection-plan:query')")
    public CommonResult<InspectionInspectionPlanRespVO> getInspectionInspectionPlan(@RequestParam("id") Long id) {
        InspectionInspectionPlanDO inspectionInspectionPlan = inspectionInspectionPlanService.getInspectionInspectionPlan(id);
        return success(BeanUtils.toBean(inspectionInspectionPlan, InspectionInspectionPlanRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查计划分页")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-inspection-plan:query')")
    public CommonResult<PageResult<InspectionInspectionPlanRespVO>> getInspectionInspectionPlanPage(@Valid InspectionInspectionPlanPageReqVO pageReqVO) {
        PageResult<InspectionInspectionPlanDO> pageResult = inspectionInspectionPlanService.getInspectionInspectionPlanPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionInspectionPlanRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查计划 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-inspection-plan:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionInspectionPlanExcel(@Valid InspectionInspectionPlanPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionInspectionPlanDO> list = inspectionInspectionPlanService.getInspectionInspectionPlanPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查计划.xls", "数据", InspectionInspectionPlanRespVO.class,
                        BeanUtils.toBean(list, InspectionInspectionPlanRespVO.class));
    }

}