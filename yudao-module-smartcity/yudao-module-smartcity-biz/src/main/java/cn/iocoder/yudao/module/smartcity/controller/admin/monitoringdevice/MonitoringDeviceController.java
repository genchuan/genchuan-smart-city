package cn.iocoder.yudao.module.smartcity.controller.admin.monitoringdevice;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringdevice.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringdevice.MonitoringDeviceDO;
import cn.iocoder.yudao.module.smartcity.service.monitoringdevice.MonitoringDeviceService;

@Tag(name = "管理后台 - 监测设备")
@RestController
@RequestMapping("/smartcity/monitoring-device")
@Validated
public class MonitoringDeviceController {

    @Resource
    private MonitoringDeviceService monitoringDeviceService;

    @PostMapping("/create")
    @Operation(summary = "创建监测设备")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-device:create')")
    public CommonResult<Long> createMonitoringDevice(@Valid @RequestBody MonitoringDeviceSaveReqVO createReqVO) {
        return success(monitoringDeviceService.createMonitoringDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测设备")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-device:update')")
    public CommonResult<Boolean> updateMonitoringDevice(@Valid @RequestBody MonitoringDeviceSaveReqVO updateReqVO) {
        monitoringDeviceService.updateMonitoringDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测设备")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-device:delete')")
    public CommonResult<Boolean> deleteMonitoringDevice(@RequestParam("id") Long id) {
        monitoringDeviceService.deleteMonitoringDevice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测设备")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-device:query')")
    public CommonResult<MonitoringDeviceRespVO> getMonitoringDevice(@RequestParam("id") Long id) {
        MonitoringDeviceDO monitoringDevice = monitoringDeviceService.getMonitoringDevice(id);
        return success(BeanUtils.toBean(monitoringDevice, MonitoringDeviceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测设备分页")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-device:query')")
    public CommonResult<PageResult<MonitoringDeviceRespVO>> getMonitoringDevicePage(@Valid MonitoringDevicePageReqVO pageReqVO) {
        PageResult<MonitoringDeviceDO> pageResult = monitoringDeviceService.getMonitoringDevicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonitoringDeviceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测设备 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:monitoring-device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonitoringDeviceExcel(@Valid MonitoringDevicePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonitoringDeviceDO> list = monitoringDeviceService.getMonitoringDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测设备.xls", "数据", MonitoringDeviceRespVO.class,
                        BeanUtils.toBean(list, MonitoringDeviceRespVO.class));
    }

}