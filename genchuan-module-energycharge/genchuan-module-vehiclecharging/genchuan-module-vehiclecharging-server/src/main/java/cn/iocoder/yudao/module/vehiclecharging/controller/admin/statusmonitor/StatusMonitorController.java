package cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.StatusMonitorPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.chart.StatusMonitorChartRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.newvo.*;
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

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.statusmonitor.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.statusmonitor.StatusMonitorDO;
import cn.iocoder.yudao.module.vehiclecharging.service.statusmonitor.StatusMonitorService;

@Tag(name = "管理后台 - 实时监测")
@RestController
@RequestMapping("/vehiclecharging/status-monitor")
@Validated
public class StatusMonitorController {

    @Resource
    private StatusMonitorService statusMonitorService;

    @GetMapping("/chart")
    @Operation(summary = "获取场站设备实时运行监测图数据")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:query')")
    public CommonResult<StatusMonitorChartRespVO> getStatusMonitorChart() {
        StatusMonitorChartRespVO chartData = statusMonitorService.getStatusMonitorChart();
        return success(chartData);
    }
    @GetMapping("/page")
    @Operation(summary = "获得实时监测分页（支持筛选）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:query')")
    public CommonResult<PageResult<StatusMonitorRespVO>> getStatusMonitorPage(@Valid StatusMonitorPageReqVO pageReqVO) {
        PageResult<StatusMonitorRespVO> pageResult = statusMonitorService.getStatusMonitorPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/refresh")
    @Operation(summary = "秒级刷新监测数据")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:query')")
    public CommonResult<List<StatusMonitorRefreshRespVO>> refreshStatusMonitor(@Valid StatusMonitorRefreshReqVO refreshReqVO) {
        List<StatusMonitorRefreshRespVO> data = statusMonitorService.refreshStatusMonitor(refreshReqVO);
        return success(data);
    }



    @PutMapping("/handleAbnormal")
    @Operation(summary = "批量处置异常记录")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:handle')")
    public CommonResult<Long> handleAbnormal(@Valid @RequestBody StatusMonitorHandleAbnormalReqVO handleReqVO) {
        Long count =  statusMonitorService.handleAbnormal(handleReqVO);
        return success(count);
    }



//    ---------------------------------------
    @PostMapping("/create")
    @Operation(summary = "创建实时监测")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:create')")
    public CommonResult<Long> createStatusMonitor(@Valid @RequestBody StatusMonitorSaveReqVO createReqVO) {
        return success(statusMonitorService.createStatusMonitor(createReqVO));
    }
//
    @PutMapping("/update")
    @Operation(summary = "更新实时监测")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:update')")
    public CommonResult<Boolean> updateStatusMonitor(@Valid @RequestBody StatusMonitorSaveReqVO updateReqVO) {
        statusMonitorService.updateStatusMonitor(updateReqVO);
        return success(true);
    }
//
    @DeleteMapping("/delete")
    @Operation(summary = "删除实时监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:delete')")
    public CommonResult<Boolean> deleteStatusMonitor(@RequestParam("id") Long id) {
        statusMonitorService.deleteStatusMonitor(id);
        return success(true);
    }
//

//
    @GetMapping("/get")
    @Operation(summary = "获得实时监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:query')")
    public CommonResult<StatusMonitorRespVO> getStatusMonitor(@RequestParam("id") Long id) {
        StatusMonitorDO statusMonitor = statusMonitorService.getStatusMonitor(id);
        return success(BeanUtils.toBean(statusMonitor, StatusMonitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出实时监测 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:status-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStatusMonitorExcel(@Valid StatusMonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(9999);
        List<StatusMonitorRespVO> list = statusMonitorService.getStatusMonitorPage(pageReqVO).getList();
        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment; filename*=" + "excel");
        // 导出 Excel
        ExcelUtils.write(response, "实时监测.xls", "数据", StatusMonitorRespVO.class,
                        BeanUtils.toBean(list, StatusMonitorRespVO.class));
    }

}
