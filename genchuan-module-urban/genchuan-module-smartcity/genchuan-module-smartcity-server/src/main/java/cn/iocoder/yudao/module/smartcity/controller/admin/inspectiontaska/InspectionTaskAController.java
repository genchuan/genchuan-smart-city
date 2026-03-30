package cn.iocoder.yudao.module.smartcity.controller.admin.inspectiontaska;

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
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.smartcity.controller.admin.inspectiontaska.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectiontaska.InspectionTaskADO;
import cn.iocoder.yudao.module.smartcity.service.inspectiontaska.InspectionTaskAService;

@Tag(name = "管理后台 - 巡查任务")
@RestController
@RequestMapping("/smartcity/inspection-task-a")
@Validated
public class InspectionTaskAController {

    @Resource
    private InspectionTaskAService inspectionTaskAService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查任务")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-task-a:create')")
    public CommonResult<Long> createInspectionTaskA(@Valid @RequestBody InspectionTaskASaveReqVO createReqVO) {
        return success(inspectionTaskAService.createInspectionTaskA(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查任务")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-task-a:update')")
    public CommonResult<Boolean> updateInspectionTaskA(@Valid @RequestBody InspectionTaskASaveReqVO updateReqVO) {
        inspectionTaskAService.updateInspectionTaskA(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-task-a:delete')")
    public CommonResult<Boolean> deleteInspectionTaskA(@RequestParam("id") Long id) {
        inspectionTaskAService.deleteInspectionTaskA(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-task-a:query')")
    public CommonResult<InspectionTaskARespVO> getInspectionTaskA(@RequestParam("id") Long id) {
        InspectionTaskADO inspectionTaskA = inspectionTaskAService.getInspectionTaskA(id);
        return success(BeanUtils.toBean(inspectionTaskA, InspectionTaskARespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查任务分页")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-task-a:query')")
    public CommonResult<PageResult<InspectionTaskARespVO>> getInspectionTaskAPage(@Valid InspectionTaskAPageReqVO pageReqVO) {
        PageResult<InspectionTaskADO> pageResult = inspectionTaskAService.getInspectionTaskAPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionTaskARespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查任务 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-task-a:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionTaskAExcel(@Valid InspectionTaskAPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionTaskADO> list = inspectionTaskAService.getInspectionTaskAPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查任务.xls", "数据", InspectionTaskARespVO.class,
                        BeanUtils.toBean(list, InspectionTaskARespVO.class));
    }

}