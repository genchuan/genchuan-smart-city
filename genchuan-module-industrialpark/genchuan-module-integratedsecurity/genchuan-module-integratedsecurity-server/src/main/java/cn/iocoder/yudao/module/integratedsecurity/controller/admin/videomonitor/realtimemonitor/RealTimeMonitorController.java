package cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor;

import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.*;
import cn.iocoder.yudao.module.integratedsecurity.dal.dataobject.videomonitor.realtimemonitor.RealTimeMonitorDO;
import cn.iocoder.yudao.module.integratedsecurity.service.videomonitor.realtimemonitor.RealTimeMonitorService;
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


@Tag(name = "管理后台 - 实时监控")
@RestController
@RequestMapping("/securitymgmt/real-time-monitor")
@Validated
public class RealTimeMonitorController {

    @Resource
    private RealTimeMonitorService timeMonitorService;

    @PostMapping("/create")
    @Operation(summary = "创建实时监控")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:create')")
    public CommonResult<Long> createTimeMonitor(@Valid @RequestBody RealTimeMonitorSaveReqVO createReqVO) {
        return success(timeMonitorService.createTimeMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新实时监控")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:update')")
    public CommonResult<Boolean> updateTimeMonitor(@Valid @RequestBody RealTimeMonitorSaveReqVO updateReqVO) {
        timeMonitorService.updateTimeMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除实时监控")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:delete')")
    public CommonResult<Boolean> deleteTimeMonitor(@RequestParam("id") Long id) {
        timeMonitorService.deleteTimeMonitor(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除实时监控")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:delete')")
    public CommonResult<Boolean> deleteTimeMonitorList(@RequestParam("ids") List<Long> ids) {
        timeMonitorService.deleteTimeMonitorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得实时监控")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:query')")
    public CommonResult<RealTimeMonitorRespVO> getTimeMonitor(@RequestParam("id") Long id) {
        RealTimeMonitorDO timeMonitor = timeMonitorService.getTimeMonitor(id);
        return success(BeanUtils.toBean(timeMonitor, RealTimeMonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得实时监控分页")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:query')")
    public CommonResult<PageResult<RealTimeMonitorRespVO>> getTimeMonitorPage(@Valid RealTimeMonitorPageReqVO pageReqVO) {
        PageResult<RealTimeMonitorDO> pageResult = timeMonitorService.getTimeMonitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RealTimeMonitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出实时监控 Excel")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTimeMonitorExcel(@Valid RealTimeMonitorPageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RealTimeMonitorDO> list = timeMonitorService.getTimeMonitorPage(pageReqVO).getList();
        ExcelUtils.write(response, "实时监控.xls", "数据", RealTimeMonitorRespVO.class,
                BeanUtils.toBean(list, RealTimeMonitorRespVO.class));
    }

    // ========== 业务操作接口 ==========

    @PostMapping("/snap")
    @Operation(summary = "截图")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:snap')")
    public CommonResult<Boolean> snapTimeMonitor(@Valid @RequestBody RealTimeMonitorBatchReqVO reqVO) {
        timeMonitorService.snapTimeMonitor(reqVO.getIds());
        return success(true);
    }

    @PutMapping("/pause")
    @Operation(summary = "暂停")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:pause')")
    public CommonResult<Boolean> pauseTimeMonitor(@Valid @RequestBody RealTimeMonitorBatchReqVO reqVO) {
        timeMonitorService.pauseTimeMonitor(reqVO.getIds());
        return success(true);
    }

    @PutMapping("/restart")
    @Operation(summary = "重启")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:restart')")
    public CommonResult<Boolean> restartTimeMonitor(@Valid @RequestBody RealTimeMonitorBatchReqVO reqVO) {
        timeMonitorService.restartTimeMonitor(reqVO.getIds());
        return success(true);
    }

    @PostMapping("/focus")
    @Operation(summary = "聚焦")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:focus')")
    public CommonResult<Boolean> focusTimeMonitor(@Valid @RequestBody RealTimeMonitorFocusReqVO reqVO) {
        timeMonitorService.focusTimeMonitor(reqVO.getId());
        return success(true);
    }

    @PostMapping("/alarm")
    @Operation(summary = "告警")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:alarm')")
    public CommonResult<Boolean> alarmTimeMonitor(@Valid @RequestBody RealTimeMonitorAlarmReqVO reqVO) {
        timeMonitorService.alarmTimeMonitor(reqVO.getId(), reqVO.getAlarmContent());
        return success(true);
    }

    @PutMapping("/handle")
    @Operation(summary = "处置")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:handle')")
    public CommonResult<Boolean> handleTimeMonitor(@Valid @RequestBody RealTimeMonitorHandleReqVO reqVO) {
        timeMonitorService.handleTimeMonitor(reqVO.getId(), reqVO.getHandleResult());
        return success(true);
    }

    @PostMapping("/record")
    @Operation(summary = "录像")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:record')")
    public CommonResult<Boolean> recordTimeMonitor(@Valid @RequestBody RealTimeMonitorRecordReqVO reqVO) {
        timeMonitorService.recordTimeMonitor(reqVO.getId(), reqVO.getRecordDuration());
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "视频监控实时态势")
    @PreAuthorize("@ss.hasPermission('securitymgmt:real-time-monitor:chart')")
    public CommonResult<RealTimeMonitorChartRespVO> getTimeMonitorChart() {
        return success(timeMonitorService.getTimeMonitorChart());
    }

}