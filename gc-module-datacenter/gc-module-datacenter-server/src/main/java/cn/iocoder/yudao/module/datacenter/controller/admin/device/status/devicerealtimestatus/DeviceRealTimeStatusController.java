package cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo.DeviceRealTimeStatusPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo.DeviceRealTimeStatusRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.devicerealtimestatus.vo.DeviceRealTimeStatusSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.devicerealtimestatus.DeviceRealTimeStatusDO;
import cn.iocoder.yudao.module.datacenter.service.device.status.devicerealtimestatus.DeviceRealTimeStatusService;

@Tag(name = "管理后台 - 设备实时状态")
@RestController
@RequestMapping("/datacenter/device-real-time-status")
@Validated
public class DeviceRealTimeStatusController {

    @Resource
    private DeviceRealTimeStatusService deviceRealTimeStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建设备实时状态")
    @PreAuthorize("@ss.hasPermission('datacenter:device-real-time-status:create')")
    public CommonResult<Long> createDeviceRealTimeStatus(@Valid @RequestBody DeviceRealTimeStatusSaveReqVO createReqVO) {
        return success(deviceRealTimeStatusService.createDeviceRealTimeStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备实时状态")
    @PreAuthorize("@ss.hasPermission('datacenter:device-real-time-status:update')")
    public CommonResult<Boolean> updateDeviceRealTimeStatus(@Valid @RequestBody DeviceRealTimeStatusSaveReqVO updateReqVO) {
        deviceRealTimeStatusService.updateDeviceRealTimeStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备实时状态")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-real-time-status:delete')")
    public CommonResult<Boolean> deleteDeviceRealTimeStatus(@RequestParam("id") Long id) {
        deviceRealTimeStatusService.deleteDeviceRealTimeStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备实时状态")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-real-time-status:query')")
    public CommonResult<DeviceRealTimeStatusRespVO> getDeviceRealTimeStatus(@RequestParam("id") Long id) {
        DeviceRealTimeStatusDO deviceRealTimeStatus = deviceRealTimeStatusService.getDeviceRealTimeStatus(id);
        return success(BeanUtils.toBean(deviceRealTimeStatus, DeviceRealTimeStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备实时状态分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-real-time-status:query')")
    public CommonResult<PageResult<DeviceRealTimeStatusRespVO>> getDeviceRealTimeStatusPage(@Valid DeviceRealTimeStatusPageReqVO pageReqVO) {
        PageResult<DeviceRealTimeStatusDO> pageResult = deviceRealTimeStatusService.getDeviceRealTimeStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceRealTimeStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备实时状态 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-real-time-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceRealTimeStatusExcel(@Valid DeviceRealTimeStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceRealTimeStatusDO> list = deviceRealTimeStatusService.getDeviceRealTimeStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备实时状态.xls", "数据", DeviceRealTimeStatusRespVO.class,
                        BeanUtils.toBean(list, DeviceRealTimeStatusRespVO.class));
    }

}