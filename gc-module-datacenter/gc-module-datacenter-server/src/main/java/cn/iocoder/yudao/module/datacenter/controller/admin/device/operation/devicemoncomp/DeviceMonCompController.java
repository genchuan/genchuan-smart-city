package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo.DeviceMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo.DeviceMonCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.devicemoncomp.vo.DeviceMonCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.devicemoncomp.DeviceMonCompDO;
import cn.iocoder.yudao.module.datacenter.service.device.operation.devicemoncomp.DeviceMonCompService;

@Tag(name = "管理后台 - 设备关联监测部件")
@RestController
@RequestMapping("/datacenter/device-mon-comp")
@Validated
public class DeviceMonCompController {

    @Resource
    private DeviceMonCompService deviceMonCompService;

    @PostMapping("/create")
    @Operation(summary = "创建设备关联监测部件")
    @PreAuthorize("@ss.hasPermission('datacenter:device-mon-comp:create')")
    public CommonResult<Long> createDeviceMonComp(@Valid @RequestBody DeviceMonCompSaveReqVO createReqVO) {
        return success(deviceMonCompService.createDeviceMonComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备关联监测部件")
    @PreAuthorize("@ss.hasPermission('datacenter:device-mon-comp:update')")
    public CommonResult<Boolean> updateDeviceMonComp(@Valid @RequestBody DeviceMonCompSaveReqVO updateReqVO) {
        deviceMonCompService.updateDeviceMonComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备关联监测部件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-mon-comp:delete')")
    public CommonResult<Boolean> deleteDeviceMonComp(@RequestParam("id") Long id) {
        deviceMonCompService.deleteDeviceMonComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备关联监测部件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-mon-comp:query')")
    public CommonResult<DeviceMonCompRespVO> getDeviceMonComp(@RequestParam("id") Long id) {
        DeviceMonCompDO deviceMonComp = deviceMonCompService.getDeviceMonComp(id);
        return success(BeanUtils.toBean(deviceMonComp, DeviceMonCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备关联监测部件分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-mon-comp:query')")
    public CommonResult<PageResult<DeviceMonCompRespVO>> getDeviceMonCompPage(@Valid DeviceMonCompPageReqVO pageReqVO) {
        PageResult<DeviceMonCompDO> pageResult = deviceMonCompService.getDeviceMonCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceMonCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备关联监测部件 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-mon-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceMonCompExcel(@Valid DeviceMonCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceMonCompDO> list = deviceMonCompService.getDeviceMonCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备关联监测部件.xls", "数据", DeviceMonCompRespVO.class,
                        BeanUtils.toBean(list, DeviceMonCompRespVO.class));
    }

}