package cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo.DeviceOfflineAlertPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo.DeviceOfflineAlertRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.status.deviceofflinealert.vo.DeviceOfflineAlertSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.status.deviceofflinealert.DeviceOfflineAlertDO;
import cn.iocoder.yudao.module.datacenter.service.device.status.deviceofflinealert.DeviceOfflineAlertService;

@Tag(name = "管理后台 - 设备离线预警")
@RestController
@RequestMapping("/datacenter/device-offline-alert")
@Validated
public class DeviceOfflineAlertController {

    @Resource
    private DeviceOfflineAlertService deviceOfflineAlertService;

    @PostMapping("/create")
    @Operation(summary = "创建设备离线预警")
    @PreAuthorize("@ss.hasPermission('datacenter:device-offline-alert:create')")
    public CommonResult<Long> createDeviceOfflineAlert(@Valid @RequestBody DeviceOfflineAlertSaveReqVO createReqVO) {
        return success(deviceOfflineAlertService.createDeviceOfflineAlert(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备离线预警")
    @PreAuthorize("@ss.hasPermission('datacenter:device-offline-alert:update')")
    public CommonResult<Boolean> updateDeviceOfflineAlert(@Valid @RequestBody DeviceOfflineAlertSaveReqVO updateReqVO) {
        deviceOfflineAlertService.updateDeviceOfflineAlert(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备离线预警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-offline-alert:delete')")
    public CommonResult<Boolean> deleteDeviceOfflineAlert(@RequestParam("id") Long id) {
        deviceOfflineAlertService.deleteDeviceOfflineAlert(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备离线预警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-offline-alert:query')")
    public CommonResult<DeviceOfflineAlertRespVO> getDeviceOfflineAlert(@RequestParam("id") Long id) {
        DeviceOfflineAlertDO deviceOfflineAlert = deviceOfflineAlertService.getDeviceOfflineAlert(id);
        return success(BeanUtils.toBean(deviceOfflineAlert, DeviceOfflineAlertRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备离线预警分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-offline-alert:query')")
    public CommonResult<PageResult<DeviceOfflineAlertRespVO>> getDeviceOfflineAlertPage(@Valid DeviceOfflineAlertPageReqVO pageReqVO) {
        PageResult<DeviceOfflineAlertDO> pageResult = deviceOfflineAlertService.getDeviceOfflineAlertPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceOfflineAlertRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备离线预警 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-offline-alert:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceOfflineAlertExcel(@Valid DeviceOfflineAlertPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceOfflineAlertDO> list = deviceOfflineAlertService.getDeviceOfflineAlertPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备离线预警.xls", "数据", DeviceOfflineAlertRespVO.class,
                        BeanUtils.toBean(list, DeviceOfflineAlertRespVO.class));
    }

}