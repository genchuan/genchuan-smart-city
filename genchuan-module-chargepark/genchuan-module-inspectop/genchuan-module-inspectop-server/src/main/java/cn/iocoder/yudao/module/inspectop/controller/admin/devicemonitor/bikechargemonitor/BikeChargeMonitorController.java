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
        // 1. 从Service层获取定位信息（已包含longitude、latitude、stationName 以及从 device_id 转换来的 deviceCode）
        BikeChargeMonitorLocationRespVO locationRespVO = bikeChargeMonitorService.getBikeChargeMonitorLocation(id);

        // 2. 直接返回结果
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
        // 1. 获取VO数据列表（注意：Service层已返回RespVO）
        List<BikeChargeMonitorRespVO> list = bikeChargeMonitorService.getBikeChargeMonitorPage(pageReqVO).getList();
        // 2. 对VO列表中的字典值进行转换（数字 -> 中文）
        convertDictValues(list);
        // 3. 导出 Excel
        ExcelUtils.write(response, "两轮充电监测.xls", "数据", BikeChargeMonitorRespVO.class, list);
    }

    /**
     * 转换字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 monitorStatus, alarmStatus, processStatus 字段。
     * @param voList 两轮充电监测响应VO列表
     */
    private void convertDictValues(List<BikeChargeMonitorRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (BikeChargeMonitorRespVO vo : voList) {
            // 转换监测状态
            vo.setMonitorStatus(convertMonitorStatus(vo.getMonitorStatus()));
            // 转换告警状态
            vo.setAlarmStatus(convertAlarmStatus(vo.getAlarmStatus()));
            // 转换处理状态
            vo.setProcessStatus(convertProcessStatus(vo.getProcessStatus()));
        }
    }

    /**
     * 转换监测状态字典值
     * 0-正常，1-异常
     * @param statusCode 状态编码
     * @return 对应的中文状态描述
     */
    private String convertMonitorStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "0":
                return "正常";
            case "1":
                return "异常";
            default:
                // 如果遇到未知编码，返回原编码以便排查。
                return statusCode;
        }
    }

    /**
     * 转换告警状态字典值
     * 1-已告警，0-未告警
     * @param statusCode 状态编码
     * @return 对应的中文状态描述
     */
    private String convertAlarmStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "1":
                return "已告警";
            case "0":
                return "未告警";
            default:
                // 如果遇到未知编码，返回原编码以便排查。
                return statusCode;
        }
    }

    /**
     * 转换处理状态字典值
     * 0-已处理，1-处理中，2-未处理
     * @param statusCode 状态编码
     * @return 对应的中文状态描述
     */
    private String convertProcessStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "0":
                return "已处理";
            case "1":
                return "处理中";
            case "2":
                return "未处理";
            default:
                // 如果遇到未知编码，返回原编码以便排查。
                return statusCode;
        }
    }

}