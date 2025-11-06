package cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene.vo.DeviceAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene.vo.DeviceAppSceneRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.operation.deviceappscene.vo.DeviceAppSceneSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.operation.deviceappscene.DeviceAppSceneDO;
import cn.iocoder.yudao.module.datacenter.service.device.operation.deviceappscene.DeviceAppSceneService;

@Tag(name = "管理后台 - 设备关联应用场景")
@RestController
@RequestMapping("/datacenter/device-app-scene")
@Validated
public class DeviceAppSceneController {

    @Resource
    private DeviceAppSceneService deviceAppSceneService;

    @PostMapping("/create")
    @Operation(summary = "创建设备关联应用场景")
    @PreAuthorize("@ss.hasPermission('datacenter:device-app-scene:create')")
    public CommonResult<Long> createDeviceAppScene(@Valid @RequestBody DeviceAppSceneSaveReqVO createReqVO) {
        return success(deviceAppSceneService.createDeviceAppScene(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备关联应用场景")
    @PreAuthorize("@ss.hasPermission('datacenter:device-app-scene:update')")
    public CommonResult<Boolean> updateDeviceAppScene(@Valid @RequestBody DeviceAppSceneSaveReqVO updateReqVO) {
        deviceAppSceneService.updateDeviceAppScene(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备关联应用场景")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-app-scene:delete')")
    public CommonResult<Boolean> deleteDeviceAppScene(@RequestParam("id") Long id) {
        deviceAppSceneService.deleteDeviceAppScene(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备关联应用场景")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-app-scene:query')")
    public CommonResult<DeviceAppSceneRespVO> getDeviceAppScene(@RequestParam("id") Long id) {
        DeviceAppSceneDO deviceAppScene = deviceAppSceneService.getDeviceAppScene(id);
        return success(BeanUtils.toBean(deviceAppScene, DeviceAppSceneRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备关联应用场景分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-app-scene:query')")
    public CommonResult<PageResult<DeviceAppSceneRespVO>> getDeviceAppScenePage(@Valid DeviceAppScenePageReqVO pageReqVO) {
        PageResult<DeviceAppSceneDO> pageResult = deviceAppSceneService.getDeviceAppScenePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceAppSceneRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备关联应用场景 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-app-scene:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceAppSceneExcel(@Valid DeviceAppScenePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceAppSceneDO> list = deviceAppSceneService.getDeviceAppScenePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备关联应用场景.xls", "数据", DeviceAppSceneRespVO.class,
                        BeanUtils.toBean(list, DeviceAppSceneRespVO.class));
    }

}