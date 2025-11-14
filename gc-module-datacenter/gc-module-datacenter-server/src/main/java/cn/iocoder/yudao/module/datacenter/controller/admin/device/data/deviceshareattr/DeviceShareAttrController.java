package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr.vo.DeviceShareAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr.vo.DeviceShareAttrRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceshareattr.vo.DeviceShareAttrSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceshareattr.DeviceShareAttrDO;
import cn.iocoder.yudao.module.datacenter.service.device.data.deviceshareattr.DeviceShareAttrService;

@Tag(name = "管理后台 - 设备共享属性配置")
@RestController
@RequestMapping("/datacenter/device-share-attr")
@Validated
public class DeviceShareAttrController {

    @Resource
    private DeviceShareAttrService deviceShareAttrService;

    @PostMapping("/create")
    @Operation(summary = "创建设备共享属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-share-attr:create')")
    public CommonResult<Long> createDeviceShareAttr(@Valid @RequestBody DeviceShareAttrSaveReqVO createReqVO) {
        return success(deviceShareAttrService.createDeviceShareAttr(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备共享属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-share-attr:update')")
    public CommonResult<Boolean> updateDeviceShareAttr(@Valid @RequestBody DeviceShareAttrSaveReqVO updateReqVO) {
        deviceShareAttrService.updateDeviceShareAttr(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备共享属性配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-share-attr:delete')")
    public CommonResult<Boolean> deleteDeviceShareAttr(@RequestParam("id") Long id) {
        deviceShareAttrService.deleteDeviceShareAttr(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备共享属性配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-share-attr:query')")
    public CommonResult<DeviceShareAttrRespVO> getDeviceShareAttr(@RequestParam("id") Long id) {
        DeviceShareAttrDO deviceShareAttr = deviceShareAttrService.getDeviceShareAttr(id);
        return success(BeanUtils.toBean(deviceShareAttr, DeviceShareAttrRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备共享属性配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-share-attr:query')")
    public CommonResult<PageResult<DeviceShareAttrRespVO>> getDeviceShareAttrPage(@Valid DeviceShareAttrPageReqVO pageReqVO) {
        PageResult<DeviceShareAttrDO> pageResult = deviceShareAttrService.getDeviceShareAttrPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceShareAttrRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备共享属性配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-share-attr:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceShareAttrExcel(@Valid DeviceShareAttrPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceShareAttrDO> list = deviceShareAttrService.getDeviceShareAttrPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备共享属性配置.xls", "数据", DeviceShareAttrRespVO.class,
                        BeanUtils.toBean(list, DeviceShareAttrRespVO.class));
    }

}