package cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.device.deviceinfo.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.deviceinfo.DeviceInfoDO;
import cn.iocoder.yudao.module.datacenter.service.device.deviceinfo.DeviceInfoService;

@Tag(name = "管理后台 - 设备信息")
@RestController
@RequestMapping("/datacenter/device-info")
@Validated
public class DeviceInfoController {

    @Resource
    private DeviceInfoService deviceInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建设备信息")
    @PreAuthorize("@ss.hasPermission('datacenter:device-info:create')")
    public CommonResult<Long> createDeviceInfo(@Valid @RequestBody DeviceInfoSaveReqVO createReqVO) {
        return success(deviceInfoService.createDeviceInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备信息")
    @PreAuthorize("@ss.hasPermission('datacenter:device-info:update')")
    public CommonResult<Boolean> updateDeviceInfo(@Valid @RequestBody DeviceInfoSaveReqVO updateReqVO) {
        deviceInfoService.updateDeviceInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-info:delete')")
    public CommonResult<Boolean> deleteDeviceInfo(@RequestParam("id") Long id) {
        deviceInfoService.deleteDeviceInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-info:query')")
    public CommonResult<DeviceInfoRespVO> getDeviceInfo(@RequestParam("id") Long id) {
        DeviceInfoDO deviceInfo = deviceInfoService.getDeviceInfo(id);
        return success(BeanUtils.toBean(deviceInfo, DeviceInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-info:query')")
    public CommonResult<PageResult<DeviceInfoRespVO>> getDeviceInfoPage(@Valid DeviceInfoPageReqVO pageReqVO) {
        PageResult<DeviceInfoDO> pageResult = deviceInfoService.getDeviceInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceInfoExcel(@Valid DeviceInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceInfoDO> list = deviceInfoService.getDeviceInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备信息.xls", "数据", DeviceInfoRespVO.class,
                        BeanUtils.toBean(list, DeviceInfoRespVO.class));
    }

}