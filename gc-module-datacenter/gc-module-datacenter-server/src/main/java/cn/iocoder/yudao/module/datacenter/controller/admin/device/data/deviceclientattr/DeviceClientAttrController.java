package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo.DeviceClientAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo.DeviceClientAttrRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.deviceclientattr.vo.DeviceClientAttrSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.deviceclientattr.DeviceClientAttrDO;
import cn.iocoder.yudao.module.datacenter.service.device.data.deviceclientattr.DeviceClientAttrService;

@Tag(name = "管理后台 - 设备客户端属性配置")
@RestController
@RequestMapping("/datacenter/device-client-attr")
@Validated
public class DeviceClientAttrController {

    @Resource
    private DeviceClientAttrService deviceClientAttrService;

    @PostMapping("/create")
    @Operation(summary = "创建设备客户端属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-client-attr:create')")
    public CommonResult<Long> createDeviceClientAttr(@Valid @RequestBody DeviceClientAttrSaveReqVO createReqVO) {
        return success(deviceClientAttrService.createDeviceClientAttr(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备客户端属性配置")
    @PreAuthorize("@ss.hasPermission('datacenter:device-client-attr:update')")
    public CommonResult<Boolean> updateDeviceClientAttr(@Valid @RequestBody DeviceClientAttrSaveReqVO updateReqVO) {
        deviceClientAttrService.updateDeviceClientAttr(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备客户端属性配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-client-attr:delete')")
    public CommonResult<Boolean> deleteDeviceClientAttr(@RequestParam("id") Long id) {
        deviceClientAttrService.deleteDeviceClientAttr(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备客户端属性配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-client-attr:query')")
    public CommonResult<DeviceClientAttrRespVO> getDeviceClientAttr(@RequestParam("id") Long id) {
        DeviceClientAttrDO deviceClientAttr = deviceClientAttrService.getDeviceClientAttr(id);
        return success(BeanUtils.toBean(deviceClientAttr, DeviceClientAttrRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备客户端属性配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-client-attr:query')")
    public CommonResult<PageResult<DeviceClientAttrRespVO>> getDeviceClientAttrPage(@Valid DeviceClientAttrPageReqVO pageReqVO) {
        PageResult<DeviceClientAttrDO> pageResult = deviceClientAttrService.getDeviceClientAttrPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceClientAttrRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备客户端属性配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-client-attr:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceClientAttrExcel(@Valid DeviceClientAttrPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceClientAttrDO> list = deviceClientAttrService.getDeviceClientAttrPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备客户端属性配置.xls", "数据", DeviceClientAttrRespVO.class,
                        BeanUtils.toBean(list, DeviceClientAttrRespVO.class));
    }

}