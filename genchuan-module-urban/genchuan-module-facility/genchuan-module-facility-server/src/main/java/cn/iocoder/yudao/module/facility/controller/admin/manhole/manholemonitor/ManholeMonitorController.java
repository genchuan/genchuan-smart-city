package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import cn.iocoder.yudao.module.facility.service.manhole.manholemonitor.ManholeMonitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 窨井盖监测")
@RestController
@RequestMapping("/manhole/monitor")
@Validated
public class ManholeMonitorController {

    @Resource
    private ManholeMonitorService monitorService;

    @PostMapping("/create")
    @Operation(summary = "创建窨井盖监测")
    @PreAuthorize("@ss.hasPermission('manhole:monitor:create')")
    public CommonResult<Long> createMonitor(@Valid @RequestBody ManholeMonitorSaveReqVO createReqVO) {
        return success(monitorService.createMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新窨井盖监测")
    @PreAuthorize("@ss.hasPermission('manhole:monitor:update')")
    public CommonResult<Boolean> updateMonitor(@Valid @RequestBody ManholeMonitorSaveReqVO updateReqVO) {
        monitorService.updateMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除窨井盖监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('manhole:monitor:delete')")
    public CommonResult<Boolean> deleteMonitor(@RequestParam("id") Long id) {
        monitorService.deleteMonitor(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得窨井盖监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('manhole:monitor:query')")
    public CommonResult<ManholeMonitorRespVO> getMonitor(@RequestParam("id") String id) {
        ManholeMonitorDO monitor = monitorService.getMonitor(id);
        return success(BeanUtils.toBean(monitor, ManholeMonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得窨井盖监测分页")
    @PreAuthorize("@ss.hasPermission('manhole:monitor:query')")
    public CommonResult<PageResult<ManholeMonitorRespVO>> getMonitorPage(@Valid ManholeMonitorPageReqVO pageReqVO) {
        PageResult<ManholeMonitorDO> pageResult = monitorService.getMonitorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ManholeMonitorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出窨井盖监测 Excel")
    @PreAuthorize("@ss.hasPermission('manhole:monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitorExcel(@Valid ManholeMonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ManholeMonitorDO> list = monitorService.getMonitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "窨井盖监测.xls", "数据", ManholeMonitorRespVO.class,
                        BeanUtils.toBean(list, ManholeMonitorRespVO.class));
    }

    // 列表查询（支持所有筛选条件）
    @GetMapping("/list")
    @Operation(summary = "获取窨井盖监测列表")
    public CommonResult<List<ManholeMonitorVO>> getManholeMonitorList(
            // 原有筛选参数
            @Parameter(description = "井盖编号") @RequestParam(required = false) String coverNo,
            @Parameter(description = "路段名称") @RequestParam(required = false) String roadName,
            @Parameter(description = "开合状态") @RequestParam(required = false) String statusName,
            @Parameter(description = "设备在线状态") @RequestParam(required = false) String onlineStatus,
            // 新增筛选参数
            @Parameter(description = "监测状态") @RequestParam(required = false) String monitorStatus,
            @Parameter(description = "安全风险等级") @RequestParam(required = false) String riskLevel,
            @Parameter(description = "异常振动标识（1=是/0=否）") @RequestParam(required = false) Integer abnormalVibrationFlag
    ) {
        List<ManholeMonitorVO> list = monitorService.getManholeMonitorList(
                coverNo, roadName, statusName, onlineStatus, monitorStatus, riskLevel, abnormalVibrationFlag);
        return CommonResult.success(list);
    }

    /**
     * 按井盖编号查询详情（支持钻取，弹窗专用）
     * @param coverNo 井盖编号
     */
    @GetMapping("/by-cover-no")
    @Operation(summary = "按井盖编号查询详情", description = "用于井盖编号钻取，点击跳转详情弹窗")
    public CommonResult<ManholeMonitorVO> getManholeDetailByCoverNo(
            @Parameter(description = "井盖编号", required = true)
            @RequestParam("coverNo") String coverNo) {
        return success(monitorService.getManholeDetailByCoverNo(coverNo));
    }

    /**
     * 批量更新监测状态
     */
    @PostMapping("/batch-update-monitor-status")
    @Operation(summary = "批量更新监测状态")
    @PreAuthorize("@ss.hasPermission('manhole:monitor:update')")
    public CommonResult<Integer> batchUpdateMonitorStatus(@Valid @RequestBody BatchMonitorStatusReqVO batchVO) {
        // 调用服务层执行批量更新，同步更新监测状态、操作人和更新时间
        Integer count = monitorService.batchUpdateMonitorStatus(batchVO.getCoverIds(), batchVO.getStatus());
        return success(count);
    }

    @GetMapping("/stats/24hour")
    @Operation(summary = "查询近 24 小时统计数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('manhole:cover:query')")
    public CommonResult<ManholeMonitorStatsRespVO> get24HourStats(@RequestParam("id") Long id) {
        return success(monitorService.get24HourStats(id));
    }

    @GetMapping("/trend/24hour")
    @Operation(summary = "查询近 24 小时变化趋势")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('manhole:cover:query')")
    public CommonResult<List<ManholeMonitorHourTrendVO>> get24HourTrend(@RequestParam("id") Long id) {
        return success(monitorService.get24HourTrend(id));
    }

    /**
     * 查询窨井盖预警监测列表
     * 包含预警编号、井盖编号、路段名称、异常类型、开合状态、倾斜角度、振动数据、
     * 处置时限、剩余处置时间、派单状态、风险等级、处置建议
     */
    @GetMapping("/warning-list")
    @Operation(summary = "查询窨井盖预警监测列表（分页）")
    public CommonResult<PageResult<ManholeMonitorWarningRespVO>> getWarningMonitorList(
            @Valid ManholeMonitorWarningPageReqVO pageReqVO) {
        PageResult<ManholeMonitorWarningRespVO> pageResult = monitorService.getWarningMonitorPage(pageReqVO);
        return CommonResult.success(pageResult);
    }
}