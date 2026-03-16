package cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor;

import cn.iocoder.yudao.module.facility.controller.admin.road.roadmonitor.vo.*;

import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadmonitor.RoadMonitorDO;
import cn.iocoder.yudao.module.facility.service.road.roadmonitor.RoadMonitorService;
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


@Tag(name = "管理后台 - 道路监测")
@RestController
@RequestMapping("/facility/monitor")
@Validated
public class RoadMonitorController {

    @Resource
    private RoadMonitorService roadMonitorService;

    @GetMapping("/bar-chart")
    @Operation(summary = "获取-道路监测-柱状图统计")
    @PreAuthorize("@ss.hasPermission('facility:monitor:bar-chart')")
    public CommonResult<MonitorBarChartVO> getBarChart() {

        MonitorBarChartVO vo = roadMonitorService.getBarChart();

        return success(vo);
    }
    @GetMapping("/device-online-rate")
    @Operation(summary = "获取-道路监测-设备在线率")
    @PreAuthorize("@ss.hasPermission('facility:monitor:device-online-rate')")
    public CommonResult<List<ChartItemVO>> getDeviceOnlineRate() {
        List<ChartItemVO> list =roadMonitorService.getDeviceOnlineRate();
        return success(list);
    }
    /**
     * 圆环图 - 监测状态占比
     */
    @GetMapping("/monitor-status-rate")
    @Operation(summary = "获取-道路监测-监测状态占比")
    @PreAuthorize("@ss.hasPermission('facility:monitor:monitor-status-rate')")
    public CommonResult<List<ChartItemVO>> getMonitorStatusRate() {

        List<ChartItemVO> list = roadMonitorService.getMonitorStatusRate();

        return success(list);
    }
    @Operation(summary = "获取道路监测折线图数据（近24小时）")
    @GetMapping("/road-monitor/line-chart")
    public RoadMonitorLineChartVO getLineChart(
            @RequestParam(required = false) Long roadId // 不传时返回全区域
    ) {
        return roadMonitorService.getLineChart(roadId);
    }
    @Operation(summary = "获取道路监测卡片统计信息")
    @GetMapping("/card")
    public RoadMonitorCardVO getCardStatistics() {
        return roadMonitorService.getCardStatistics();
    }
    @PostMapping("/batch-update-monitor-status")
    @Operation(summary = "批量修改-道路监测-运行监测状态")
    @PreAuthorize("@ss.hasPermission('facility:monitor:batch-update-monitor-status')")
    public CommonResult<Integer> batchUpdateMonitorStatus(@Valid @RequestBody BatchUpdateRoadMonitorStatusReqVO reqVO) {
        //返回成功修改记录数目
        int resultNum = roadMonitorService.batchUpdateMonitorStatus(reqVO);
        return success(resultNum);
    }
    @GetMapping("/realtime-page")
    @Operation(summary = "获得道路监测实时监测分页")
    @PreAuthorize("@ss.hasPermission('facility:monitor:realtime-page')")
    public CommonResult<PageResult<RealtimePageRespVO>> getRealtimePage(@Valid RealtimePageReqVO reqVO) {
        PageResult<RealtimePageRespVO> pageResult = roadMonitorService.getRealtimePage(reqVO);
        return success(pageResult);
    }
    @PostMapping("/create")
    @Operation(summary = "创建道路监测")
    @PreAuthorize("@ss.hasPermission('facility:monitor:create')")
    public CommonResult<Long> createMonitor(@Valid @RequestBody RoadMonitorSaveReqVO createReqVO) {
        return success(roadMonitorService.createMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路监测")
    @PreAuthorize("@ss.hasPermission('facility:monitor:update')")
    public CommonResult<Boolean> updateMonitor(@Valid @RequestBody RoadMonitorUpdateReqVO updateReqVO) {
        roadMonitorService.updateMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:monitor:delete')")
    public CommonResult<Boolean> deleteMonitor(@RequestParam("id") Long id) {
        roadMonitorService.deleteMonitor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:monitor:query')")
    public CommonResult<RoadMonitorRespVO> getMonitor(@RequestParam("id") Long id) {
        RoadMonitorDO monitor = roadMonitorService.getMonitor(id);
        return success(BeanUtils.toBean(monitor, RoadMonitorRespVO.class));
    }

    @GetMapping("/page")
//    @Operation(summary = "获得道路监测分页")
    @PreAuthorize("@ss.hasPermission('facility:monitor:query')")
    public CommonResult<PageResult<RoadMonitorRespVO>> getMonitorPage(@Valid RoadMonitorPageReqVO pageReqVO) {
        PageResult<RoadMonitorDO> pageResult = roadMonitorService.getMonitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RoadMonitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路监测 Excel")
    @PreAuthorize("@ss.hasPermission('facility:monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitorExcel(@Valid RoadMonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RoadMonitorDO> list = roadMonitorService.getMonitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "道路监测.xls", "数据", RoadMonitorRespVO.class,
                        BeanUtils.toBean(list, RoadMonitorRespVO.class));
    }

}
