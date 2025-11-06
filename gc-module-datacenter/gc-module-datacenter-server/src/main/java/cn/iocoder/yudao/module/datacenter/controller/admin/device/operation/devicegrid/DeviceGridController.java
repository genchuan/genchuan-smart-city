package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo.DeviceGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo.DeviceGridRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicegrid.vo.DeviceGridSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicegrid.DeviceGridDO;
import cn.iocoder.yudao.module.datacenter.service.device.operation.devicegrid.DeviceGridService;

@Tag(name = "管理后台 - 设备关联网格")
@RestController
@RequestMapping("/datacenter/device-grid")
@Validated
public class DeviceGridController {

    @Resource
    private DeviceGridService deviceGridService;

    @PostMapping("/create")
    @Operation(summary = "创建设备关联网格")
    @PreAuthorize("@ss.hasPermission('datacenter:device-grid:create')")
    public CommonResult<Long> createDeviceGrid(@Valid @RequestBody DeviceGridSaveReqVO createReqVO) {
        return success(deviceGridService.createDeviceGrid(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备关联网格")
    @PreAuthorize("@ss.hasPermission('datacenter:device-grid:update')")
    public CommonResult<Boolean> updateDeviceGrid(@Valid @RequestBody DeviceGridSaveReqVO updateReqVO) {
        deviceGridService.updateDeviceGrid(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备关联网格")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-grid:delete')")
    public CommonResult<Boolean> deleteDeviceGrid(@RequestParam("id") Long id) {
        deviceGridService.deleteDeviceGrid(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备关联网格")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-grid:query')")
    public CommonResult<DeviceGridRespVO> getDeviceGrid(@RequestParam("id") Long id) {
        DeviceGridDO deviceGrid = deviceGridService.getDeviceGrid(id);
        return success(BeanUtils.toBean(deviceGrid, DeviceGridRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备关联网格分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-grid:query')")
    public CommonResult<PageResult<DeviceGridRespVO>> getDeviceGridPage(@Valid DeviceGridPageReqVO pageReqVO) {
        PageResult<DeviceGridDO> pageResult = deviceGridService.getDeviceGridPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceGridRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备关联网格 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-grid:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceGridExcel(@Valid DeviceGridPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceGridDO> list = deviceGridService.getDeviceGridPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备关联网格.xls", "数据", DeviceGridRespVO.class,
                        BeanUtils.toBean(list, DeviceGridRespVO.class));
    }

}