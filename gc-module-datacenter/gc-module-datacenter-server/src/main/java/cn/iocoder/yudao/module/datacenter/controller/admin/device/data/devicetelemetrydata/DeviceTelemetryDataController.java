package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo.DeviceTelemetryDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo.DeviceTelemetryDataRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicetelemetrydata.vo.DeviceTelemetryDataSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicetelemetrydata.DeviceTelemetryDataDO;
import cn.iocoder.yudao.module.datacenter.service.device.data.devicetelemetrydata.DeviceTelemetryDataService;

@Tag(name = "管理后台 - 设备遥测数据")
@RestController
@RequestMapping("/datacenter/device-telemetry-data")
@Validated
public class DeviceTelemetryDataController {

    @Resource
    private DeviceTelemetryDataService deviceTelemetryDataService;

    @PostMapping("/create")
    @Operation(summary = "创建设备遥测数据")
    @PreAuthorize("@ss.hasPermission('datacenter:device-telemetry-data:create')")
    public CommonResult<Long> createDeviceTelemetryData(@Valid @RequestBody DeviceTelemetryDataSaveReqVO createReqVO) {
        return success(deviceTelemetryDataService.createDeviceTelemetryData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备遥测数据")
    @PreAuthorize("@ss.hasPermission('datacenter:device-telemetry-data:update')")
    public CommonResult<Boolean> updateDeviceTelemetryData(@Valid @RequestBody DeviceTelemetryDataSaveReqVO updateReqVO) {
        deviceTelemetryDataService.updateDeviceTelemetryData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备遥测数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-telemetry-data:delete')")
    public CommonResult<Boolean> deleteDeviceTelemetryData(@RequestParam("id") Long id) {
        deviceTelemetryDataService.deleteDeviceTelemetryData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备遥测数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-telemetry-data:query')")
    public CommonResult<DeviceTelemetryDataRespVO> getDeviceTelemetryData(@RequestParam("id") Long id) {
        DeviceTelemetryDataDO deviceTelemetryData = deviceTelemetryDataService.getDeviceTelemetryData(id);
        return success(BeanUtils.toBean(deviceTelemetryData, DeviceTelemetryDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备遥测数据分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-telemetry-data:query')")
    public CommonResult<PageResult<DeviceTelemetryDataRespVO>> getDeviceTelemetryDataPage(@Valid DeviceTelemetryDataPageReqVO pageReqVO) {
        PageResult<DeviceTelemetryDataDO> pageResult = deviceTelemetryDataService.getDeviceTelemetryDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceTelemetryDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备遥测数据 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-telemetry-data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceTelemetryDataExcel(@Valid DeviceTelemetryDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceTelemetryDataDO> list = deviceTelemetryDataService.getDeviceTelemetryDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备遥测数据.xls", "数据", DeviceTelemetryDataRespVO.class,
                        BeanUtils.toBean(list, DeviceTelemetryDataRespVO.class));
    }

}