package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspectionstatistics.vo.InspectionStatisticsSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspectionstatistics.InspectionStatisticsDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.inspectionstatistics.InspectionStatisticsService;

@Tag(name = "管理后台 - 环卫考核统计结果")
@RestController
@RequestMapping("/datacenter/inspection-statistics")
@Validated
public class InspectionStatisticsController {

    @Resource
    private InspectionStatisticsService inspectionStatisticsService;

    @PostMapping("/create")
    @Operation(summary = "创建环卫考核统计结果")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-statistics:create')")
    public CommonResult<Long> createInspectionStatistics(@Valid @RequestBody InspectionStatisticsSaveReqVO createReqVO) {
        return success(inspectionStatisticsService.createInspectionStatistics(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新环卫考核统计结果")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-statistics:update')")
    public CommonResult<Boolean> updateInspectionStatistics(@Valid @RequestBody InspectionStatisticsSaveReqVO updateReqVO) {
        inspectionStatisticsService.updateInspectionStatistics(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除环卫考核统计结果")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-statistics:delete')")
    public CommonResult<Boolean> deleteInspectionStatistics(@RequestParam("id") Long id) {
        inspectionStatisticsService.deleteInspectionStatistics(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得环卫考核统计结果")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-statistics:query')")
    public CommonResult<InspectionStatisticsRespVO> getInspectionStatistics(@RequestParam("id") Long id) {
        InspectionStatisticsDO inspectionStatistics = inspectionStatisticsService.getInspectionStatistics(id);
        return success(BeanUtils.toBean(inspectionStatistics, InspectionStatisticsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得环卫考核统计结果分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-statistics:query')")
    public CommonResult<PageResult<InspectionStatisticsRespVO>> getInspectionStatisticsPage(@Valid InspectionStatisticsPageReqVO pageReqVO) {
        PageResult<InspectionStatisticsDO> pageResult = inspectionStatisticsService.getInspectionStatisticsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionStatisticsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出环卫考核统计结果 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspection-statistics:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionStatisticsExcel(@Valid InspectionStatisticsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionStatisticsDO> list = inspectionStatisticsService.getInspectionStatisticsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "环卫考核统计结果.xls", "数据", InspectionStatisticsRespVO.class,
                        BeanUtils.toBean(list, InspectionStatisticsRespVO.class));
    }

}