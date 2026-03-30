package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingassignment;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingassignment.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingassignment.SamplingAssignmentDO;
import cn.iocoder.yudao.module.waterdetection.service.samplingassignment.SamplingAssignmentService;

@Tag(name = "管理后台 - 采样人员分配")
@RestController
@RequestMapping("/waterdetection/sampling-assignment")
@Validated
public class SamplingAssignmentController {

    @Resource
    private SamplingAssignmentService samplingAssignmentService;

    @PostMapping("/create")
    @Operation(summary = "创建采样人员分配")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-assignment:create')")
    public CommonResult<Long> createSamplingAssignment(@Valid @RequestBody SamplingAssignmentSaveReqVO createReqVO) {
        return success(samplingAssignmentService.createSamplingAssignment(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采样人员分配")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-assignment:update')")
    public CommonResult<Boolean> updateSamplingAssignment(@Valid @RequestBody SamplingAssignmentSaveReqVO updateReqVO) {
        samplingAssignmentService.updateSamplingAssignment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采样人员分配")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-assignment:delete')")
    public CommonResult<Boolean> deleteSamplingAssignment(@RequestParam("id") Long id) {
        samplingAssignmentService.deleteSamplingAssignment(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采样人员分配")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-assignment:query')")
    public CommonResult<SamplingAssignmentRespVO> getSamplingAssignment(@RequestParam("id") Long id) {
        SamplingAssignmentDO samplingAssignment = samplingAssignmentService.getSamplingAssignment(id);
        return success(BeanUtils.toBean(samplingAssignment, SamplingAssignmentRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采样人员分配分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-assignment:query')")
    public CommonResult<PageResult<SamplingAssignmentRespVO>> getSamplingAssignmentPage(@Valid SamplingAssignmentPageReqVO pageReqVO) {
        PageResult<SamplingAssignmentDO> pageResult = samplingAssignmentService.getSamplingAssignmentPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SamplingAssignmentRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采样人员分配 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-assignment:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSamplingAssignmentExcel(@Valid SamplingAssignmentPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SamplingAssignmentDO> list = samplingAssignmentService.getSamplingAssignmentPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "采样人员分配.xls", "数据", SamplingAssignmentRespVO.class,
                        BeanUtils.toBean(list, SamplingAssignmentRespVO.class));
    }

}