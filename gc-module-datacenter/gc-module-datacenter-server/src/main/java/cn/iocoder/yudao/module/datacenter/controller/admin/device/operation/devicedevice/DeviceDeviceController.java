package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo.DeviceDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo.DeviceDeviceRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicedevice.vo.DeviceDeviceSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicedevice.DeviceDeviceDO;
import cn.iocoder.yudao.module.datacenter.service.device.operation.devicedevice.DeviceDeviceService;

@Tag(name = "管理后台 - 设备关联设备")
@RestController
@RequestMapping("/datacenter/device-device")
@Validated
public class DeviceDeviceController {

    @Resource
    private DeviceDeviceService deviceDeviceService;

    @PostMapping("/create")
    @Operation(summary = "创建设备关联设备")
    @PreAuthorize("@ss.hasPermission('datacenter:device-device:create')")
    public CommonResult<Long> createDeviceDevice(@Valid @RequestBody DeviceDeviceSaveReqVO createReqVO) {
        return success(deviceDeviceService.createDeviceDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备关联设备")
    @PreAuthorize("@ss.hasPermission('datacenter:device-device:update')")
    public CommonResult<Boolean> updateDeviceDevice(@Valid @RequestBody DeviceDeviceSaveReqVO updateReqVO) {
        deviceDeviceService.updateDeviceDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备关联设备")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-device:delete')")
    public CommonResult<Boolean> deleteDeviceDevice(@RequestParam("id") Long id) {
        deviceDeviceService.deleteDeviceDevice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备关联设备")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-device:query')")
    public CommonResult<DeviceDeviceRespVO> getDeviceDevice(@RequestParam("id") Long id) {
        DeviceDeviceDO deviceDevice = deviceDeviceService.getDeviceDevice(id);
        return success(BeanUtils.toBean(deviceDevice, DeviceDeviceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备关联设备分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-device:query')")
    public CommonResult<PageResult<DeviceDeviceRespVO>> getDeviceDevicePage(@Valid DeviceDevicePageReqVO pageReqVO) {
        PageResult<DeviceDeviceDO> pageResult = deviceDeviceService.getDeviceDevicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceDeviceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备关联设备 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceDeviceExcel(@Valid DeviceDevicePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceDeviceDO> list = deviceDeviceService.getDeviceDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备关联设备.xls", "数据", DeviceDeviceRespVO.class,
                        BeanUtils.toBean(list, DeviceDeviceRespVO.class));
    }

}