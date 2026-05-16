package cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.inspectop.controller.admin.cyclereport.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.inspectop.service.cyclereport.CycleReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "巡查巡检 - 巡检运维报表")
@RestController
@RequestMapping("/inspectop/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @GetMapping("/page")
    @Operation(summary = "获得巡检运维报表存储分页")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:query')")
    public CommonResult<PageResult<CycleReportRespVO>> getCycleReportPage(@Valid CycleReportPageReqVO pageReqVO) {
        PageResult<CycleReportDO> pageResult = cycleReportService.getCycleReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CycleReportRespVO.class));
    }

    @PostMapping("/generate")
    @Operation(summary = "实时生成巡检运维报表")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:create')")
    public CommonResult<CycleReportRespVO> generateCycleReport(@Valid @RequestBody CycleReportGenerateReqVO generateReqVO) {
        CycleReportRespVO respVO = cycleReportService.generateCycleReport(generateReqVO);
        return success(respVO);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取巡检运维报表图表数据")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:query')")
    public CommonResult<CycleReportChartRespVO> getCycleReportChart(@Valid CycleReportChartReqVO reqVO) {
        CycleReportChartRespVO respVO = cycleReportService.getCycleReportChart(reqVO);
        return success(respVO);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检运维报表存储 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleReportExcel(@Valid CycleReportPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<CycleReportDO> pageResult = cycleReportService.getCycleReportPage(pageReqVO);
        List<CycleReportDO> list = pageResult.getList();

        // 转换为 Excel VO 列表
        List<CycleReportExcelVO> excelList = new ArrayList<>();
        for (CycleReportDO reportDO : list) {
            excelList.add(convertToExcelVO(reportDO));
        }

        // 导出 Excel
        ExcelUtils.write(response, "巡检运维报表.xls", "数据", CycleReportExcelVO.class, excelList);

        // 批量增加导出次数
        for (CycleReportDO reportDO : list) {
            try {
                cycleReportService.incrementExportCount(reportDO.getId());
            } catch (Exception e) {
                // 某个报表增加次数失败，不影响其他报表
            }
        }
    }

    @GetMapping("/export-excel/{id}")
    @Operation(summary = "导出单条巡检运维报表 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:cycle-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleReportExcelById(@Parameter(description = "报表主键 ID", required = true, example = "1")
                                           @PathVariable("id") Long id,
                                           HttpServletResponse response) throws IOException {
        // 1. 根据ID查询单条报表数据
        CycleReportDO reportDO = cycleReportService.getCycleReport(id);
        if (reportDO == null) {
            response.sendError(HttpStatus.NOT_FOUND.value(), "报表不存在");
            return;
        }

        // 2. 转换为 Excel VO
        CycleReportExcelVO excelVO = convertToExcelVO(reportDO);

        // 3. 导出 Excel
        String fileName = String.format("巡检运维报表_%s_%s.xls",
                excelVO.getStationName(),
                excelVO.getReportCycle());

        try {
            // 导出Excel
            ExcelUtils.write(response, fileName, "报表详情", CycleReportExcelVO.class,
                    Collections.singletonList(excelVO));

            // 4. 导出成功后，增加导出次数
            cycleReportService.incrementExportCount(id);

        } catch (IOException e) {
            // 如果导出失败，不增加导出次数
            throw e;
        }
    }

    /**
     * 将 DO 转换为 Excel VO
     * 注意：此方法隐藏了 stationId 字段
     */
    private CycleReportExcelVO convertToExcelVO(CycleReportDO reportDO) {
        if (reportDO == null) {
            return null;
        }

        CycleReportExcelVO excelVO = new CycleReportExcelVO();

        // 使用 BeanUtils 复制相同字段名的属性
        excelVO.setId(reportDO.getId());
        excelVO.setReportCycle(reportDO.getReportCycle());
        excelVO.setStationName(reportDO.getStationName());
        excelVO.setStatTimeStart(reportDO.getStatTimeStart());
        excelVO.setStatTimeEnd(reportDO.getStatTimeEnd());
        excelVO.setNormalDeviceNum(reportDO.getNormalDeviceNum());
        excelVO.setAbnormalDeviceNum(reportDO.getAbnormalDeviceNum());
        excelVO.setInspectTaskNum(reportDO.getInspectTaskNum());
        excelVO.setTaskCompleteRate(reportDO.getTaskCompleteRate());
        excelVO.setOilWaitHandleNum(reportDO.getOilWaitHandleNum());
        excelVO.setOilHandleCompleteRate(reportDO.getOilHandleCompleteRate());
        excelVO.setInspectUserOnlineNum(reportDO.getInspectUserOnlineNum());
        excelVO.setAssetNormalNum(reportDO.getAssetNormalNum());
        excelVO.setStockWarnNum(reportDO.getStockWarnNum());
        excelVO.setGenerateStatus(reportDO.getGenerateStatus());
        excelVO.setGenerateTime(reportDO.getGenerateTime());
        excelVO.setOperator(reportDO.getOperator());
        excelVO.setExportCount(reportDO.getExportCount());
        excelVO.setYearOnYearData(reportDO.getYearOnYearData());
        excelVO.setChainRatioData(reportDO.getChainRatioData());
        excelVO.setCreateTime(reportDO.getCreateTime());
        excelVO.setUpdateTime(reportDO.getUpdateTime());

        return excelVO;
    }


}