package cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice;

import cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo.SysDevicePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo.SysDeviceRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.sysdevice.vo.SysDeviceSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.sysdevice.SysDeviceDO;
import cn.iocoder.yudao.module.kitchen.service.sysdevice.SysDeviceService;
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


@Tag(name = "管理后台 - 设备信息")
@RestController
@RequestMapping("/kitchen/sys-device")
@Validated
public class SysDeviceController {

    @Resource
    private SysDeviceService sysDeviceService;

    @PostMapping("/create")
    @Operation(summary = "创建设备信息")
    @PreAuthorize("@ss.hasPermission('kitchen:sys-device:create')")
    public CommonResult<Long> createSysDevice(@Valid @RequestBody SysDeviceSaveReqVO createReqVO) {
        return success(sysDeviceService.createSysDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备信息")
    @PreAuthorize("@ss.hasPermission('kitchen:sys-device:update')")
    public CommonResult<Boolean> updateSysDevice(@Valid @RequestBody SysDeviceSaveReqVO updateReqVO) {
        sysDeviceService.updateSysDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('kitchen:sys-device:delete')")
    public CommonResult<Boolean> deleteSysDevice(@RequestParam("id") Long id) {
        sysDeviceService.deleteSysDevice(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除设备信息")
                @PreAuthorize("@ss.hasPermission('kitchen:sys-device:delete')")
    public CommonResult<Boolean> deleteSysDeviceList(@RequestParam("ids") List<Long> ids) {
        sysDeviceService.deleteSysDeviceListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('kitchen:sys-device:query')")
    public CommonResult<SysDeviceRespVO> getSysDevice(@RequestParam("id") Long id) {
        SysDeviceDO sysDevice = sysDeviceService.getSysDevice(id);
        return success(BeanUtils.toBean(sysDevice, SysDeviceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备信息分页")
    @PreAuthorize("@ss.hasPermission('kitchen:sys-device:query')")
    public CommonResult<PageResult<SysDeviceRespVO>> getSysDevicePage(@Valid SysDevicePageReqVO pageReqVO) {
        PageResult<SysDeviceDO> pageResult = sysDeviceService.getSysDevicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SysDeviceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备信息 Excel")
    @PreAuthorize("@ss.hasPermission('kitchen:sys-device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSysDeviceExcel(@Valid SysDevicePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SysDeviceDO> list = sysDeviceService.getSysDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备信息.xls", "数据", SysDeviceRespVO.class,
                        BeanUtils.toBean(list, SysDeviceRespVO.class));
    }

}
