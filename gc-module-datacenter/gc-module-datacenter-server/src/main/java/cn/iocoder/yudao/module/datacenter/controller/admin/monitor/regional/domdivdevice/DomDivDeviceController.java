package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo.DomDivDevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo.DomDivDeviceRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivdevice.vo.DomDivDeviceSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivdevice.DomDivDeviceDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivdevice.DomDivDeviceService;

@Tag(name = "管理后台 - 设备分域")
@RestController
@RequestMapping("/datacenter/dom-div-device")
@Validated
public class DomDivDeviceController {

    @Resource
    private DomDivDeviceService domDivDeviceService;

    @PostMapping("/create")
    @Operation(summary = "创建设备分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-device:create')")
    public CommonResult<Long> createDomDivDevice(@Valid @RequestBody DomDivDeviceSaveReqVO createReqVO) {
        return success(domDivDeviceService.createDomDivDevice(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-device:update')")
    public CommonResult<Boolean> updateDomDivDevice(@Valid @RequestBody DomDivDeviceSaveReqVO updateReqVO) {
        domDivDeviceService.updateDomDivDevice(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备分域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-device:delete')")
    public CommonResult<Boolean> deleteDomDivDevice(@RequestParam("id") Long id) {
        domDivDeviceService.deleteDomDivDevice(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备分域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-device:query')")
    public CommonResult<DomDivDeviceRespVO> getDomDivDevice(@RequestParam("id") Long id) {
        DomDivDeviceDO domDivDevice = domDivDeviceService.getDomDivDevice(id);
        return success(BeanUtils.toBean(domDivDevice, DomDivDeviceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分域分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-device:query')")
    public CommonResult<PageResult<DomDivDeviceRespVO>> getDomDivDevicePage(@Valid DomDivDevicePageReqVO pageReqVO) {
        PageResult<DomDivDeviceDO> pageResult = domDivDeviceService.getDomDivDevicePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDivDeviceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备分域 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-device:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDivDeviceExcel(@Valid DomDivDevicePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDivDeviceDO> list = domDivDeviceService.getDomDivDevicePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备分域.xls", "数据", DomDivDeviceRespVO.class,
                        BeanUtils.toBean(list, DomDivDeviceRespVO.class));
    }

}