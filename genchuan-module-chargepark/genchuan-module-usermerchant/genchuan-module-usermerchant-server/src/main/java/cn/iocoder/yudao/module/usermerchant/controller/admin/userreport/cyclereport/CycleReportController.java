package cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport;

import cn.iocoder.yudao.module.usermerchant.controller.admin.userreport.cyclereport.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.userreport.cyclereport.CycleReportDO;
import cn.iocoder.yudao.module.usermerchant.service.userreport.cyclereport.CycleReportService;
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

@Tag(name = "管理后台 - 周期报表存储")
@RestController
@RequestMapping("/usermerchant/cycle-report")
@Validated
public class CycleReportController {

    @Resource
    private CycleReportService cycleReportService;

    @PostMapping("/create")
    @Operation(summary = "创建周期报表存储")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:create')")
    public CommonResult<Long> createCycleReport(@Valid @RequestBody CycleReportSaveReqVO createReqVO) {
        return success(cycleReportService.createCycleReport(createReqVO));
    }

//    @PutMapping("/update")
//    @Operation(summary = "更新周期报表存储")
//    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:update')")
//    public CommonResult<Boolean> updateCycleReport(@Valid @RequestBody CycleReportSaveReqVO updateReqVO) {
//        cycleReportService.updateCycleReport(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @Operation(summary = "删除周期报表存储")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:delete')")
//    public CommonResult<Boolean> deleteCycleReport(@RequestParam("id") Long id) {
//        cycleReportService.deleteCycleReport(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除周期报表存储")
//                @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:delete')")
//    public CommonResult<Boolean> deleteCycleReportList(@RequestParam("ids") List<Long> ids) {
//        cycleReportService.deleteCycleReportListByIds(ids);
//        return success(true);
//    }

    @GetMapping("/get")
    @Operation(summary = "获得周期报表存储")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:query')")
    public CommonResult<CycleReportRespVO> getCycleReport(@RequestParam("id") Long id) {
        CycleReportDO cycleReport = cycleReportService.getCycleReport(id);
        return success(BeanUtils.toBean(cycleReport, CycleReportRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得周期报表存储分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:query')")
    public CommonResult<PageResult<CycleReportPageRespVO>> getCycleReportPage(@Valid CycleReportPageReqVO pageReqVO) {
        PageResult<CycleReportDO> pageResult = cycleReportService.getCycleReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CycleReportPageRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出周期报表存储")
    @PreAuthorize("@ss.hasPermission('usermerchant:cycle-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCycleReportExcel(@Valid CycleReportPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CycleReportDO> list = cycleReportService.getCycleReportPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "周期报表存储.xls", "数据", CycleReportRespVO.class,
                        BeanUtils.toBean(list, CycleReportRespVO.class));
    }

}