package cn.iocoder.yudao.module.waterdetection.controller.admin.samplingfrequency;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingfrequency.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingfrequency.SamplingFrequencyDO;
import cn.iocoder.yudao.module.waterdetection.service.samplingfrequency.SamplingFrequencyService;

@Tag(name = "管理后台 - 采样频率设置")
@RestController
@RequestMapping("/waterdetection/sampling-frequency")
@Validated
public class SamplingFrequencyController {

    @Resource
    private SamplingFrequencyService samplingFrequencyService;

    @PostMapping("/create")
    @Operation(summary = "创建采样频率设置")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-frequency:create')")
    public CommonResult<Long> createSamplingFrequency(@Valid @RequestBody SamplingFrequencySaveReqVO createReqVO) {
        return success(samplingFrequencyService.createSamplingFrequency(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新采样频率设置")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-frequency:update')")
    public CommonResult<Boolean> updateSamplingFrequency(@Valid @RequestBody SamplingFrequencySaveReqVO updateReqVO) {
        samplingFrequencyService.updateSamplingFrequency(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除采样频率设置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-frequency:delete')")
    public CommonResult<Boolean> deleteSamplingFrequency(@RequestParam("id") Long id) {
        samplingFrequencyService.deleteSamplingFrequency(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得采样频率设置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-frequency:query')")
    public CommonResult<SamplingFrequencyRespVO> getSamplingFrequency(@RequestParam("id") Long id) {
        SamplingFrequencyDO samplingFrequency = samplingFrequencyService.getSamplingFrequency(id);
        return success(BeanUtils.toBean(samplingFrequency, SamplingFrequencyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得采样频率设置分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-frequency:query')")
    public CommonResult<PageResult<SamplingFrequencyRespVO>> getSamplingFrequencyPage(@Valid SamplingFrequencyPageReqVO pageReqVO) {
        PageResult<SamplingFrequencyDO> pageResult = samplingFrequencyService.getSamplingFrequencyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SamplingFrequencyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出采样频率设置 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:sampling-frequency:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSamplingFrequencyExcel(@Valid SamplingFrequencyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SamplingFrequencyDO> list = samplingFrequencyService.getSamplingFrequencyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "采样频率设置.xls", "数据", SamplingFrequencyRespVO.class,
                        BeanUtils.toBean(list, SamplingFrequencyRespVO.class));
    }

}