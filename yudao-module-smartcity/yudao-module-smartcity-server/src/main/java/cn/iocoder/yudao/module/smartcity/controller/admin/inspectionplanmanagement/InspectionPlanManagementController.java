package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionplanmanagement;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionplanmanagement.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionplanmanagement.InspectionPlanManagementDO;
import cn.iocoder.yudao.module.smartcity.service.inspectionplanmanagement.InspectionPlanManagementService;

@Tag(name = "管理后台 - 巡查计划管理")
@RestController
@RequestMapping("/smartcity/inspection-plan-management")
@Validated
public class InspectionPlanManagementController {

    @Resource
    private InspectionPlanManagementService inspectionPlanManagementService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查计划管理")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-plan-management:create')")
    public CommonResult<Long> createInspectionPlanManagement(@Valid @RequestBody InspectionPlanManagementSaveReqVO createReqVO) {
        return success(inspectionPlanManagementService.createInspectionPlanManagement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查计划管理")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-plan-management:update')")
    public CommonResult<Boolean> updateInspectionPlanManagement(@Valid @RequestBody InspectionPlanManagementSaveReqVO updateReqVO) {
        inspectionPlanManagementService.updateInspectionPlanManagement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查计划管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-plan-management:delete')")
    public CommonResult<Boolean> deleteInspectionPlanManagement(@RequestParam("id") Long id) {
        inspectionPlanManagementService.deleteInspectionPlanManagement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查计划管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-plan-management:query')")
    public CommonResult<InspectionPlanManagementRespVO> getInspectionPlanManagement(@RequestParam("id") Long id) {
        InspectionPlanManagementDO inspectionPlanManagement = inspectionPlanManagementService.getInspectionPlanManagement(id);
        return success(BeanUtils.toBean(inspectionPlanManagement, InspectionPlanManagementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查计划管理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-plan-management:query')")
    public CommonResult<PageResult<InspectionPlanManagementRespVO>> getInspectionPlanManagementPage(@Valid InspectionPlanManagementPageReqVO pageReqVO) {
        PageResult<InspectionPlanManagementDO> pageResult = inspectionPlanManagementService.getInspectionPlanManagementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionPlanManagementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查计划管理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-plan-management:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionPlanManagementExcel(@Valid InspectionPlanManagementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionPlanManagementDO> list = inspectionPlanManagementService.getInspectionPlanManagementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查计划管理.xls", "数据", InspectionPlanManagementRespVO.class,
                        BeanUtils.toBean(list, InspectionPlanManagementRespVO.class));
    }

}