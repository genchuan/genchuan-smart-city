package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo.EarlyWarnDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo.EarlyWarnDeviceRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarndevice.vo.EarlyWarnDeviceSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarndevice.EarlyWarnDeviceDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarndevice.EarlyWarnDeviceService;

@Tag(name = "管理后台 - 按设备分域预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-device")
@Validated
public class EarlyWarnDeviceController {

    @Resource
    private EarlyWarnDeviceService earlyWarnDeviceService;

    @PostMapping("/create")
    @Operation(summary = "创建按设备分域预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-device:create')")
    public CommonResult<Long> createEarlyWarnDevice(@Valid @RequestBody EarlyWarnDeviceSaveReqVO createReqVO) {
        return success(earlyWarnDeviceService.createEarlyWarnDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按设备分域预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-device:update')")
    public CommonResult<Boolean> updateEarlyWarnDevice(@Valid @RequestBody EarlyWarnDeviceSaveReqVO updateReqVO) {
        earlyWarnDeviceService.updateEarlyWarnDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按设备分域预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-device:delete')")
    public CommonResult<Boolean> deleteEarlyWarnDevice(@RequestParam("id") Long id) {
        earlyWarnDeviceService.deleteEarlyWarnDevice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按设备分域预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-device:query')")
    public CommonResult<EarlyWarnDeviceRespVO> getEarlyWarnDevice(@RequestParam("id") Long id) {
        EarlyWarnDeviceDO earlyWarnDevice = earlyWarnDeviceService.getEarlyWarnDevice(id);
        return success(BeanUtils.toBean(earlyWarnDevice, EarlyWarnDeviceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按设备分域预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-device:query')")
    public CommonResult<PageResult<EarlyWarnDeviceRespVO>> getEarlyWarnDevicePage(@Valid EarlyWarnDevicePageReqVO pageReqVO) {
        PageResult<EarlyWarnDeviceDO> pageResult = earlyWarnDeviceService.getEarlyWarnDevicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnDeviceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按设备分域预警告警统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnDeviceExcel(@Valid EarlyWarnDevicePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnDeviceDO> list = earlyWarnDeviceService.getEarlyWarnDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按设备分域预警告警统计.xls", "数据", EarlyWarnDeviceRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnDeviceRespVO.class));
    }

}