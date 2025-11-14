package cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo.DeviceCatRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo.DeviceCatRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.report.devicecatrpt.vo.DeviceCatRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.report.devicecatrpt.DeviceCatRptDO;
import cn.iocoder.yudao.module.datacenter.service.device.report.devicecatrpt.DeviceCatRptService;

@Tag(name = "管理后台 - 设备分类统计")
@RestController
@RequestMapping("/datacenter/device-cat-rpt")
@Validated
public class DeviceCatRptController {

    @Resource
    private DeviceCatRptService deviceCatRptService;

    @PostMapping("/create")
    @Operation(summary = "创建设备分类统计")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rpt:create')")
    public CommonResult<Long> createDeviceCatRpt(@Valid @RequestBody DeviceCatRptSaveReqVO createReqVO) {
        return success(deviceCatRptService.createDeviceCatRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备分类统计")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rpt:update')")
    public CommonResult<Boolean> updateDeviceCatRpt(@Valid @RequestBody DeviceCatRptSaveReqVO updateReqVO) {
        deviceCatRptService.updateDeviceCatRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备分类统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rpt:delete')")
    public CommonResult<Boolean> deleteDeviceCatRpt(@RequestParam("id") Long id) {
        deviceCatRptService.deleteDeviceCatRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备分类统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rpt:query')")
    public CommonResult<DeviceCatRptRespVO> getDeviceCatRpt(@RequestParam("id") Long id) {
        DeviceCatRptDO deviceCatRpt = deviceCatRptService.getDeviceCatRpt(id);
        return success(BeanUtils.toBean(deviceCatRpt, DeviceCatRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分类统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rpt:query')")
    public CommonResult<PageResult<DeviceCatRptRespVO>> getDeviceCatRptPage(@Valid DeviceCatRptPageReqVO pageReqVO) {
        PageResult<DeviceCatRptDO> pageResult = deviceCatRptService.getDeviceCatRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceCatRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备分类统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-cat-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceCatRptExcel(@Valid DeviceCatRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceCatRptDO> list = deviceCatRptService.getDeviceCatRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备分类统计.xls", "数据", DeviceCatRptRespVO.class,
                        BeanUtils.toBean(list, DeviceCatRptRespVO.class));
    }

}