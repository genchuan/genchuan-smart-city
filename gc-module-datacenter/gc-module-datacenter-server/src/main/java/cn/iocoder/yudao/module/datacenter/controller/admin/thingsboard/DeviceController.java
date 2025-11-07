package cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard;


import cn.iocoder.yudao.module.datacenter.service.thingsboard.DeviceService;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.DeviceDO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DeviceRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.vo.DeviceSaveReqVO;
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
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 设备")
@RestController
@RequestMapping("/datacenter/thingsboard/device")
@Validated
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @PostMapping("/create")
    @Operation(summary = "创建设备")
    @PreAuthorize("@ss.hasPermission('device:device:create')")
    public CommonResult<String> createDevice(@Valid @RequestBody DeviceSaveReqVO createReqVO) {
        return success(deviceService.createDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备")
    @PreAuthorize("@ss.hasPermission('device:device:update')")
    public CommonResult<Boolean> updateDevice(@Valid @RequestBody DeviceSaveReqVO updateReqVO) {
        deviceService.updateDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('device:device:delete')")
    public CommonResult<Boolean> deleteDevice(@RequestParam("id") String id) {
        deviceService.deleteDevice(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除设备")
    @PreAuthorize("@ss.hasPermission('device:device:delete')")
    public CommonResult<Boolean> deleteDeviceList(@RequestParam("ids") List<String> ids) {
        deviceService.deleteDeviceListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('device:device:query')")
    public CommonResult<DeviceInfo> getDevice(@RequestParam("id") String id) {
        DeviceInfo device = deviceService.getDevice(id);
        return success(BeanUtils.toBean(device, DeviceInfo.class));
    }

    @GetMapping("/getAttr")
    @Operation(summary = "获得设备属性")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('device:device:query')")
    public CommonResult<List<AttributeKvEntry>> getDeviceAttr(@RequestParam("id") String id) {
        List<AttributeKvEntry> device = deviceService.getAttributeKvEntries(id);
        return success(device);
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分页")
    @PreAuthorize("@ss.hasPermission('device:device:query')")
    public CommonResult<PageResult<DeviceRespVO>> getDevicePage(@Valid DevicePageReqVO pageReqVO) {
        PageResult<Device> pageResult = deviceService.getDevicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备 Excel")
    @PreAuthorize("@ss.hasPermission('device:device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceExcel(@Valid DevicePageReqVO pageReqVO,
                                  HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<Device> list = deviceService.getDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备.xls", "数据", DeviceRespVO.class,
                BeanUtils.toBean(list, DeviceRespVO.class));
    }

}
