package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr.vo.DeviceServerAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr.vo.DeviceServerAttrRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceserverattr.vo.DeviceServerAttrSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceserverattr.DeviceServerAttrDO;
import cn.iocoder.yudao.module.datacenter.service.device.data.deviceserverattr.DeviceServerAttrService;

@Tag(name = "管理后台 - 设备服务器属性配置")
@RestController
@RequestMapping("/datacenter/device-server-attr")
@Validated
public class DeviceServerAttrController {

    @Resource
    private DeviceServerAttrService deviceServerAttrService;

    @PostMapping("/create")
    @Operation(summary = "创建设备服务器属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-server-attr:create')")
    public CommonResult<Long> createDeviceServerAttr(@Valid @RequestBody DeviceServerAttrSaveReqVO createReqVO) {
        return success(deviceServerAttrService.createDeviceServerAttr(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备服务器属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-server-attr:update')")
    public CommonResult<Boolean> updateDeviceServerAttr(@Valid @RequestBody DeviceServerAttrSaveReqVO updateReqVO) {
        deviceServerAttrService.updateDeviceServerAttr(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备服务器属性配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-server-attr:delete')")
    public CommonResult<Boolean> deleteDeviceServerAttr(@RequestParam("id") Long id) {
        deviceServerAttrService.deleteDeviceServerAttr(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备服务器属性配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-server-attr:query')")
    public CommonResult<DeviceServerAttrRespVO> getDeviceServerAttr(@RequestParam("id") Long id) {
        DeviceServerAttrDO deviceServerAttr = deviceServerAttrService.getDeviceServerAttr(id);
        return success(BeanUtils.toBean(deviceServerAttr, DeviceServerAttrRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备服务器属性配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-server-attr:query')")
    public CommonResult<PageResult<DeviceServerAttrRespVO>> getDeviceServerAttrPage(@Valid DeviceServerAttrPageReqVO pageReqVO) {
        PageResult<DeviceServerAttrDO> pageResult = deviceServerAttrService.getDeviceServerAttrPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceServerAttrRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备服务器属性配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-server-attr:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceServerAttrExcel(@Valid DeviceServerAttrPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceServerAttrDO> list = deviceServerAttrService.getDeviceServerAttrPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备服务器属性配置.xls", "数据", DeviceServerAttrRespVO.class,
                        BeanUtils.toBean(list, DeviceServerAttrRespVO.class));
    }

}