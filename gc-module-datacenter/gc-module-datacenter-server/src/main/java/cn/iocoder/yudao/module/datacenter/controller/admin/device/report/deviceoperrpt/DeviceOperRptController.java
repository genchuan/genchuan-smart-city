package cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo.DeviceOperRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo.DeviceOperRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.deviceoperrpt.vo.DeviceOperRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.deviceoperrpt.DeviceOperRptDO;
import cn.iocoder.yudao.module.datacenter.service.device.report.deviceoperrpt.DeviceOperRptService;

@Tag(name = "管理后台 - 设备运行状态统计")
@RestController
@RequestMapping("/datacenter/device-oper-rpt")
@Validated
public class DeviceOperRptController {

    @Resource
    private DeviceOperRptService deviceOperRptService;

    @PostMapping("/create")
    @Operation(summary = "创建设备运行状态统计")
    @PreAuthorize("@ss.hasPermission('datacenter:device-oper-rpt:create')")
    public CommonResult<Long> createDeviceOperRpt(@Valid @RequestBody DeviceOperRptSaveReqVO createReqVO) {
        return success(deviceOperRptService.createDeviceOperRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备运行状态统计")
    @PreAuthorize("@ss.hasPermission('datacenter:device-oper-rpt:update')")
    public CommonResult<Boolean> updateDeviceOperRpt(@Valid @RequestBody DeviceOperRptSaveReqVO updateReqVO) {
        deviceOperRptService.updateDeviceOperRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备运行状态统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-oper-rpt:delete')")
    public CommonResult<Boolean> deleteDeviceOperRpt(@RequestParam("id") Long id) {
        deviceOperRptService.deleteDeviceOperRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备运行状态统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-oper-rpt:query')")
    public CommonResult<DeviceOperRptRespVO> getDeviceOperRpt(@RequestParam("id") Long id) {
        DeviceOperRptDO deviceOperRpt = deviceOperRptService.getDeviceOperRpt(id);
        return success(BeanUtils.toBean(deviceOperRpt, DeviceOperRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备运行状态统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-oper-rpt:query')")
    public CommonResult<PageResult<DeviceOperRptRespVO>> getDeviceOperRptPage(@Valid DeviceOperRptPageReqVO pageReqVO) {
        PageResult<DeviceOperRptDO> pageResult = deviceOperRptService.getDeviceOperRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceOperRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备运行状态统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-oper-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceOperRptExcel(@Valid DeviceOperRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceOperRptDO> list = deviceOperRptService.getDeviceOperRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备运行状态统计.xls", "数据", DeviceOperRptRespVO.class,
                        BeanUtils.toBean(list, DeviceOperRptRespVO.class));
    }

}