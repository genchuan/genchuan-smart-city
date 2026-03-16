package cn.iocoder.yudao.module.facility.controller.admin.sysdevice;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.SysDevicePageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.SysDeviceRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.sysdevice.vo.SysDeviceSaveReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysdevice.SysDeviceDO;
import cn.iocoder.yudao.module.facility.service.sysdevice.SysDeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 设备")
@RestController
@RequestMapping("/facility/sys-device")
@Validated
public class SysDeviceController {

    @Resource
    private SysDeviceService sysDeviceService;

    @PostMapping("/create")
    @Operation(summary = "创建设备")
    @PreAuthorize("@ss.hasPermission('facility:sys-device:create')")
    public CommonResult<Long> createSysDevice(@Valid @RequestBody SysDeviceSaveReqVO createReqVO) {
        return success(sysDeviceService.createSysDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备")
    @PreAuthorize("@ss.hasPermission('facility:sys-device:update')")
    public CommonResult<Boolean> updateSysDevice(@Valid @RequestBody SysDeviceSaveReqVO updateReqVO) {
        sysDeviceService.updateSysDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('facility:sys-device:delete')")
    public CommonResult<Boolean> deleteSysDevice(@RequestParam("id") Long id) {
        sysDeviceService.deleteSysDevice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('facility:sys-device:query')")
    public CommonResult<SysDeviceRespVO> getSysDevice(@RequestParam("id") Long id) {
        SysDeviceDO sysDevice = sysDeviceService.getSysDevice(id);
        return success(BeanUtils.toBean(sysDevice, SysDeviceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分页")
    @PreAuthorize("@ss.hasPermission('facility:sys-device:query')")
    public CommonResult<PageResult<SysDeviceRespVO>> getSysDevicePage(@Valid SysDevicePageReqVO pageReqVO) {
        PageResult<SysDeviceDO> pageResult = sysDeviceService.getSysDevicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SysDeviceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备 Excel")
    @PreAuthorize("@ss.hasPermission('facility:sys-device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSysDeviceExcel(@Valid SysDevicePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SysDeviceDO> list = sysDeviceService.getSysDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备.xls", "数据", SysDeviceRespVO.class,
                        BeanUtils.toBean(list, SysDeviceRespVO.class));
    }

}
