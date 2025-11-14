package cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo.DeviceDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo.DeviceDataQualCkRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.device.data.devicedataqualck.vo.DeviceDataQualCkSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.device.data.devicedataqualck.DeviceDataQualCkDO;
import cn.iocoder.yudao.module.datacenter.service.device.data.devicedataqualck.DeviceDataQualCkService;

@Tag(name = "管理后台 - 设备数据质量检查")
@RestController
@RequestMapping("/datacenter/device-data-qual-ck")
@Validated
public class DeviceDataQualCkController {

    @Resource
    private DeviceDataQualCkService deviceDataQualCkService;

    @PostMapping("/create")
    @Operation(summary = "创建设备数据质量检查")
    @PreAuthorize("@ss.hasPermission('datacenter:device-data-qual-ck:create')")
    public CommonResult<Long> createDeviceDataQualCk(@Valid @RequestBody DeviceDataQualCkSaveReqVO createReqVO) {
        return success(deviceDataQualCkService.createDeviceDataQualCk(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备数据质量检查")
    @PreAuthorize("@ss.hasPermission('datacenter:device-data-qual-ck:update')")
    public CommonResult<Boolean> updateDeviceDataQualCk(@Valid @RequestBody DeviceDataQualCkSaveReqVO updateReqVO) {
        deviceDataQualCkService.updateDeviceDataQualCk(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备数据质量检查")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:device-data-qual-ck:delete')")
    public CommonResult<Boolean> deleteDeviceDataQualCk(@RequestParam("id") Long id) {
        deviceDataQualCkService.deleteDeviceDataQualCk(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备数据质量检查")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:device-data-qual-ck:query')")
    public CommonResult<DeviceDataQualCkRespVO> getDeviceDataQualCk(@RequestParam("id") Long id) {
        DeviceDataQualCkDO deviceDataQualCk = deviceDataQualCkService.getDeviceDataQualCk(id);
        return success(BeanUtils.toBean(deviceDataQualCk, DeviceDataQualCkRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备数据质量检查分页")
    @PreAuthorize("@ss.hasPermission('datacenter:device-data-qual-ck:query')")
    public CommonResult<PageResult<DeviceDataQualCkRespVO>> getDeviceDataQualCkPage(@Valid DeviceDataQualCkPageReqVO pageReqVO) {
        PageResult<DeviceDataQualCkDO> pageResult = deviceDataQualCkService.getDeviceDataQualCkPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DeviceDataQualCkRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备数据质量检查 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:device-data-qual-ck:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDeviceDataQualCkExcel(@Valid DeviceDataQualCkPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DeviceDataQualCkDO> list = deviceDataQualCkService.getDeviceDataQualCkPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备数据质量检查.xls", "数据", DeviceDataQualCkRespVO.class,
                        BeanUtils.toBean(list, DeviceDataQualCkRespVO.class));
    }

}