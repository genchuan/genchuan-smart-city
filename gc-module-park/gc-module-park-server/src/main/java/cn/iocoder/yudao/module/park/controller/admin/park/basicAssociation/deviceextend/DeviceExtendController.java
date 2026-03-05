package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo.DeviceExtendPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo.DeviceExtendRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo.DeviceExtendSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.deviceextend.DeviceExtendDO;
import cn.iocoder.yudao.module.park.service.park.basicAssociation.deviceextend.DeviceExtendService;
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

@Tag(name = "管理后台 - 设备扩展")
@RestController
@RequestMapping("/park/device-extend")
@Validated
public class DeviceExtendController {

    @Resource
    private DeviceExtendService deviceExtendService;

    @PostMapping("/create")
    @Operation(summary = "创建设备扩展")
    @PreAuthorize("@ss.hasPermission('park:device-extend:create')")
    public CommonResult<Long> createDeviceExtend(@Valid @RequestBody DeviceExtendSaveReqVO createReqVO) {
        return success(deviceExtendService.createDeviceExtend(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备扩展")
    @PreAuthorize("@ss.hasPermission('park:device-extend:update')")
    public CommonResult<Boolean> updateDeviceExtend(@Valid @RequestBody DeviceExtendSaveReqVO updateReqVO) {
        deviceExtendService.updateDeviceExtend(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备扩展")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:device-extend:delete')")
    public CommonResult<Boolean> deleteDeviceExtend(@RequestParam("id") Long id) {
        deviceExtendService.deleteDeviceExtend(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备扩展")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:device-extend:query')")
    public CommonResult<DeviceExtendRespVO> getDeviceExtend(@RequestParam("id") Long id) {
        DeviceExtendDO deviceExtend = deviceExtendService.getDeviceExtend(id);
        return success(BeanUtils.toBean(deviceExtend, DeviceExtendRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备扩展分页")
    @PreAuthorize("@ss.hasPermission('park:device-extend:query')")
    public CommonResult<PageResult<DeviceExtendRespVO>> getDeviceExtendPage(@Valid DeviceExtendPageReqVO pageReqVO) {
        PageResult<DeviceExtendDO> pageResult = deviceExtendService.getDeviceExtendPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceExtendRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备扩展 Excel")
    @PreAuthorize("@ss.hasPermission('park:device-extend:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceExtendExcel(@Valid DeviceExtendPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceExtendDO> list = deviceExtendService.getDeviceExtendPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备扩展.xls", "数据", DeviceExtendRespVO.class,
                        BeanUtils.toBean(list, DeviceExtendRespVO.class));
    }

}
