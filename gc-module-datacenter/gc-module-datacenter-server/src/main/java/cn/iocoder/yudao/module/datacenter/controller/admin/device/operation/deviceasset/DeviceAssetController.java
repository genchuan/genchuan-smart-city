package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo.DeviceAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo.DeviceAssetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceasset.vo.DeviceAssetSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceasset.DeviceAssetDO;
import cn.iocoder.yudao.module.datacenter.service.device.operation.deviceasset.DeviceAssetService;

@Tag(name = "管理后台 - 设备关联资产")
@RestController
@RequestMapping("/datacenter/device-asset")
@Validated
public class DeviceAssetController {

    @Resource
    private DeviceAssetService deviceAssetService;

    @PostMapping("/create")
    @Operation(summary = "创建设备关联资产")
    @PreAuthorize("@ss.hasPermission('datacenter:device-asset:create')")
    public CommonResult<Long> createDeviceAsset(@Valid @RequestBody DeviceAssetSaveReqVO createReqVO) {
        return success(deviceAssetService.createDeviceAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备关联资产")
    @PreAuthorize("@ss.hasPermission('datacenter:device-asset:update')")
    public CommonResult<Boolean> updateDeviceAsset(@Valid @RequestBody DeviceAssetSaveReqVO updateReqVO) {
        deviceAssetService.updateDeviceAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备关联资产")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-asset:delete')")
    public CommonResult<Boolean> deleteDeviceAsset(@RequestParam("id") Long id) {
        deviceAssetService.deleteDeviceAsset(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备关联资产")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-asset:query')")
    public CommonResult<DeviceAssetRespVO> getDeviceAsset(@RequestParam("id") Long id) {
        DeviceAssetDO deviceAsset = deviceAssetService.getDeviceAsset(id);
        return success(BeanUtils.toBean(deviceAsset, DeviceAssetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备关联资产分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-asset:query')")
    public CommonResult<PageResult<DeviceAssetRespVO>> getDeviceAssetPage(@Valid DeviceAssetPageReqVO pageReqVO) {
        PageResult<DeviceAssetDO> pageResult = deviceAssetService.getDeviceAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceAssetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备关联资产 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceAssetExcel(@Valid DeviceAssetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceAssetDO> list = deviceAssetService.getDeviceAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备关联资产.xls", "数据", DeviceAssetRespVO.class,
                        BeanUtils.toBean(list, DeviceAssetRespVO.class));
    }

}