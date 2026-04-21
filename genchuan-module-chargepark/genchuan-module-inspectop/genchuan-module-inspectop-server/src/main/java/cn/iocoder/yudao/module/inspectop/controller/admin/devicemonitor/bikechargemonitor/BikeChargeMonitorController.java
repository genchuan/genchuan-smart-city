package cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.bikechargemonitor;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.bikechargemonitor.vo.*;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import java.util.concurrent.ThreadLocalRandom;


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

import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.bikechargemonitor.BikeChargeMonitorDO;
import cn.iocoder.yudao.module.inspectop.service.devicemonitor.bikechargemonitor.BikeChargeMonitorService;

@Tag(name = "巡查巡检 - 两轮充电监测")
@RestController
@RequestMapping("/inspectop/bike-charge-monitor")
@Validated
public class BikeChargeMonitorController {

    @Resource
    private BikeChargeMonitorService bikeChargeMonitorService;

    @PostMapping("/create")
    @Operation(summary = "创建两轮充电监测")
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:create')")
    public CommonResult<Long> createBikeChargeMonitor(@Valid @RequestBody BikeChargeMonitorSaveReqVO createReqVO) {
        return success(bikeChargeMonitorService.createBikeChargeMonitor(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新两轮充电监测")
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:update')")
    public CommonResult<Boolean> updateBikeChargeMonitor(@Valid @RequestBody BikeChargeMonitorSaveReqVO updateReqVO) {
        bikeChargeMonitorService.updateBikeChargeMonitor(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除两轮充电监测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:delete')")
    public CommonResult<Boolean> deleteBikeChargeMonitor(@RequestParam("id") Long id) {
        bikeChargeMonitorService.deleteBikeChargeMonitor(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除两轮充电监测")
                @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:delete')")
    public CommonResult<Boolean> deleteBikeChargeMonitorList(@RequestParam("ids") List<Long> ids) {
        bikeChargeMonitorService.deleteBikeChargeMonitorListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得两轮充电监测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:query')")
    public CommonResult<BikeChargeMonitorRespVO> getBikeChargeMonitor(@RequestParam("id") Long id) {
        BikeChargeMonitorDO bikeChargeMonitor = bikeChargeMonitorService.getBikeChargeMonitor(id);
        return success(BeanUtils.toBean(bikeChargeMonitor, BikeChargeMonitorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得两轮充电监测分页")
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:query')")
    public CommonResult<PageResult<BikeChargeMonitorRespVO>> getBikeChargeMonitorPage(@Valid BikeChargeMonitorPageReqVO pageReqVO) {
        PageResult<BikeChargeMonitorRespVO> pageResult = bikeChargeMonitorService.getBikeChargeMonitorPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/location")
    @Operation(summary = "定位")
    @Parameter(name = "id", description = "监测记录ID", required = true, example = "1")
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:location')")
    public CommonResult<BikeChargeMonitorLocationRespVO> getBikeChargeMonitorLocation(@RequestParam("id") Long id) {
        // 1. 从Service层获取基础定位信息（包含longitude、latitude、stationName）
        BikeChargeMonitorLocationRespVO locationRespVO = bikeChargeMonitorService.getBikeChargeMonitorLocation(id);

        // 2. 模拟生成设备编号：BC-01 到 BC-50
        int deviceNum = ThreadLocalRandom.current().nextInt(1, 51); // 生成1-50的随机数
        String deviceCode = String.format("BC-%02d", deviceNum); // 格式化为两位数字，如BC-01

        // 3. 设置设备编号到响应对象
        locationRespVO.setDeviceCode(deviceCode);

        // 4. 返回成功响应
        return success(locationRespVO);
    }

    @PutMapping("/alarm")
    @Operation(summary = "告警更新两轮充电监测")
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:alarm')")
    public CommonResult<Boolean> alarmBikeChargeMonitor(@Valid @RequestBody BikeChargeMonitorAlarmReqVO alarmReqVO) {
        // 调用Service层处理告警更新
        bikeChargeMonitorService.alarmBikeChargeMonitor(alarmReqVO);

        // 返回成功响应
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:chart')")
    public CommonResult<BikeChargeMonitorChartRespVO> getBikeChargeMonitorChart(@Valid BikeChargeMonitorChartReqVO reqVO) {
        // 调用Service层获取图表数据
        BikeChargeMonitorChartRespVO chartData = bikeChargeMonitorService.getBikeChargeMonitorChart(reqVO);

        // 返回成功响应
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出两轮充电监测 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:bike-charge-monitor:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBikeChargeMonitorExcel(@Valid BikeChargeMonitorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BikeChargeMonitorRespVO> list = bikeChargeMonitorService.getBikeChargeMonitorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "两轮充电监测.xls", "数据", BikeChargeMonitorRespVO.class,
                        BeanUtils.toBean(list, BikeChargeMonitorRespVO.class));
    }

}