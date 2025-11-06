package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo.DeviceAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo.DeviceAreaRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicearea.vo.DeviceAreaSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicearea.DeviceAreaDO;
import cn.iocoder.yudao.module.datacenter.service.device.operation.devicearea.DeviceAreaService;

@Tag(name = "管理后台 - 设备关联行政区划")
@RestController
@RequestMapping("/datacenter/device-area")
@Validated
public class DeviceAreaController {

    @Resource
    private DeviceAreaService deviceAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建设备关联行政区划")
    @PreAuthorize("@ss.hasPermission('datacenter:device-area:create')")
    public CommonResult<Long> createDeviceArea(@Valid @RequestBody DeviceAreaSaveReqVO createReqVO) {
        return success(deviceAreaService.createDeviceArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备关联行政区划")
    @PreAuthorize("@ss.hasPermission('datacenter:device-area:update')")
    public CommonResult<Boolean> updateDeviceArea(@Valid @RequestBody DeviceAreaSaveReqVO updateReqVO) {
        deviceAreaService.updateDeviceArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备关联行政区划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-area:delete')")
    public CommonResult<Boolean> deleteDeviceArea(@RequestParam("id") Long id) {
        deviceAreaService.deleteDeviceArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备关联行政区划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-area:query')")
    public CommonResult<DeviceAreaRespVO> getDeviceArea(@RequestParam("id") Long id) {
        DeviceAreaDO deviceArea = deviceAreaService.getDeviceArea(id);
        return success(BeanUtils.toBean(deviceArea, DeviceAreaRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备关联行政区划分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-area:query')")
    public CommonResult<PageResult<DeviceAreaRespVO>> getDeviceAreaPage(@Valid DeviceAreaPageReqVO pageReqVO) {
        PageResult<DeviceAreaDO> pageResult = deviceAreaService.getDeviceAreaPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceAreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备关联行政区划 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceAreaExcel(@Valid DeviceAreaPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceAreaDO> list = deviceAreaService.getDeviceAreaPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备关联行政区划.xls", "数据", DeviceAreaRespVO.class,
                        BeanUtils.toBean(list, DeviceAreaRespVO.class));
    }

}