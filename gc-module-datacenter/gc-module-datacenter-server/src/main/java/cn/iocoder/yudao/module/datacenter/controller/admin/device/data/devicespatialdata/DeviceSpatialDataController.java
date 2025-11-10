package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo.DeviceSpatialDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo.DeviceSpatialDataRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicespatialdata.vo.DeviceSpatialDataSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicespatialdata.DeviceSpatialDataDO;
import cn.iocoder.yudao.module.datacenter.service.device.data.devicespatialdata.DeviceSpatialDataService;

@Tag(name = "管理后台 - 设备空间数据")
@RestController
@RequestMapping("/datacenter/device-spatial-data")
@Validated
public class DeviceSpatialDataController {

    @Resource
    private DeviceSpatialDataService deviceSpatialDataService;

    @PostMapping("/create")
    @Operation(summary = "创建设备空间数据")
    @PreAuthorize("@ss.hasPermission('datacenter:device-spatial-data:create')")
    public CommonResult<Long> createDeviceSpatialData(@Valid @RequestBody DeviceSpatialDataSaveReqVO createReqVO) {
        return success(deviceSpatialDataService.createDeviceSpatialData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备空间数据")
    @PreAuthorize("@ss.hasPermission('datacenter:device-spatial-data:update')")
    public CommonResult<Boolean> updateDeviceSpatialData(@Valid @RequestBody DeviceSpatialDataSaveReqVO updateReqVO) {
        deviceSpatialDataService.updateDeviceSpatialData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备空间数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-spatial-data:delete')")
    public CommonResult<Boolean> deleteDeviceSpatialData(@RequestParam("id") Long id) {
        deviceSpatialDataService.deleteDeviceSpatialData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备空间数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-spatial-data:query')")
    public CommonResult<DeviceSpatialDataRespVO> getDeviceSpatialData(@RequestParam("id") Long id) {
        DeviceSpatialDataDO deviceSpatialData = deviceSpatialDataService.getDeviceSpatialData(id);
        return success(BeanUtils.toBean(deviceSpatialData, DeviceSpatialDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备空间数据分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-spatial-data:query')")
    public CommonResult<PageResult<DeviceSpatialDataRespVO>> getDeviceSpatialDataPage(@Valid DeviceSpatialDataPageReqVO pageReqVO) {
        PageResult<DeviceSpatialDataDO> pageResult = deviceSpatialDataService.getDeviceSpatialDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceSpatialDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备空间数据 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-spatial-data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceSpatialDataExcel(@Valid DeviceSpatialDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceSpatialDataDO> list = deviceSpatialDataService.getDeviceSpatialDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备空间数据.xls", "数据", DeviceSpatialDataRespVO.class,
                        BeanUtils.toBean(list, DeviceSpatialDataRespVO.class));
    }

}