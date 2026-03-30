package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingpoint;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingpoint.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingpoint.SamplingPointDO;
import cn.iocoder.yudao.module.waterdetection.service.samplingpoint.SamplingPointService;

@Tag(name = "管理后台 - 采样点规划")
@RestController
@RequestMapping("/waterdetection/sampling-point")
@Validated
public class SamplingPointController {

    @Resource
    private SamplingPointService samplingPointService;

    @PostMapping("/create")
    @Operation(summary = "创建采样点规划")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-point:create')")
    public CommonResult<Long> createSamplingPoint(@Valid @RequestBody SamplingPointSaveReqVO createReqVO) {
        return success(samplingPointService.createSamplingPoint(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采样点规划")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-point:update')")
    public CommonResult<Boolean> updateSamplingPoint(@Valid @RequestBody SamplingPointSaveReqVO updateReqVO) {
        samplingPointService.updateSamplingPoint(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采样点规划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-point:delete')")
    public CommonResult<Boolean> deleteSamplingPoint(@RequestParam("id") Long id) {
        samplingPointService.deleteSamplingPoint(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采样点规划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-point:query')")
    public CommonResult<SamplingPointRespVO> getSamplingPoint(@RequestParam("id") Long id) {
        SamplingPointDO samplingPoint = samplingPointService.getSamplingPoint(id);
        return success(BeanUtils.toBean(samplingPoint, SamplingPointRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采样点规划分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-point:query')")
    public CommonResult<PageResult<SamplingPointRespVO>> getSamplingPointPage(@Valid SamplingPointPageReqVO pageReqVO) {
        PageResult<SamplingPointDO> pageResult = samplingPointService.getSamplingPointPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SamplingPointRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采样点规划 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-point:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSamplingPointExcel(@Valid SamplingPointPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SamplingPointDO> list = samplingPointService.getSamplingPointPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "采样点规划.xls", "数据", SamplingPointRespVO.class,
                        BeanUtils.toBean(list, SamplingPointRespVO.class));
    }

}