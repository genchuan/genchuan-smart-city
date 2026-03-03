package cn.iocoder.yudao.module.facility.controller.admin.road.monitor;

import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.*;

import cn.iocoder.yudao.module.facility.dal.dataobject.road.monitor.MonitorDO;
import cn.iocoder.yudao.module.facility.service.road.monitor.MonitorService;
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


@Tag(name = "管理后台 - 道路监测")
@RestController
@RequestMapping("/facility/monitor")
@Validated
public class MonitorController {

    @Resource
    private MonitorService monitorService;

    @GetMapping("/realtime-page")
    @Operation(summary = "获得道路监测实时监测分页")
    @PreAuthorize("@ss.hasPermission('facility:monitor:realtime-page')")
    public CommonResult<PageResult<RealtimePageRespVO>> getRealtimePage(@Valid RealtimePageReqVO reqVO) {
        PageResult<RealtimePageRespVO> pageResult = monitorService.getRealtimePage(reqVO);
        return success(pageResult);
    }
    @PostMapping("/create")
    @Operation(summary = "创建道路监测")
    @PreAuthorize("@ss.hasPermission('facility:monitor:create')")
    public CommonResult<Long> createMonitor(@Valid @RequestBody MonitorSaveReqVO createReqVO) {
        return success(monitorService.createMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路监测")
    @PreAuthorize("@ss.hasPermission('facility:monitor:update')")
    public CommonResult<Boolean> updateMonitor(@Valid @RequestBody MonitorUpdateReqVO updateReqVO) {
        monitorService.updateMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:monitor:delete')")
    public CommonResult<Boolean> deleteMonitor(@RequestParam("id") Long id) {
        monitorService.deleteMonitor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:monitor:query')")
    public CommonResult<MonitorRespVO> getMonitor(@RequestParam("id") Long id) {
        MonitorDO monitor = monitorService.getMonitor(id);
        return success(BeanUtils.toBean(monitor, MonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得道路监测分页")
    @PreAuthorize("@ss.hasPermission('facility:monitor:query')")
    public CommonResult<PageResult<MonitorRespVO>> getMonitorPage(@Valid MonitorPageReqVO pageReqVO) {
        PageResult<MonitorDO> pageResult = monitorService.getMonitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路监测 Excel")
    @PreAuthorize("@ss.hasPermission('facility:monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitorExcel(@Valid MonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonitorDO> list = monitorService.getMonitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "道路监测.xls", "数据", MonitorRespVO.class,
                        BeanUtils.toBean(list, MonitorRespVO.class));
    }

}
