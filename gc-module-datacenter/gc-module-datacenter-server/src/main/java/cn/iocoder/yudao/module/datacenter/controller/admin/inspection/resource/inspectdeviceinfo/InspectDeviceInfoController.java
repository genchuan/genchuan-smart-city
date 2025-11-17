package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo.InspectDeviceInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo.InspectDeviceInfoRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo.InspectDeviceInfoSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectdeviceinfo.InspectDeviceInfoDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectdeviceinfo.InspectDeviceInfoService;

@Tag(name = "管理后台 - 巡查巡检设备信息")
@RestController
@RequestMapping("/datacenter/inspect-device-info")
@Validated
public class InspectDeviceInfoController {

    @Resource
    private InspectDeviceInfoService inspectDeviceInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检设备信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-device-info:create')")
    public CommonResult<Long> createInspectDeviceInfo(@Valid @RequestBody InspectDeviceInfoSaveReqVO createReqVO) {
        return success(inspectDeviceInfoService.createInspectDeviceInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检设备信息")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-device-info:update')")
    public CommonResult<Boolean> updateInspectDeviceInfo(@Valid @RequestBody InspectDeviceInfoSaveReqVO updateReqVO) {
        inspectDeviceInfoService.updateInspectDeviceInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检设备信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-device-info:delete')")
    public CommonResult<Boolean> deleteInspectDeviceInfo(@RequestParam("id") Long id) {
        inspectDeviceInfoService.deleteInspectDeviceInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检设备信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-device-info:query')")
    public CommonResult<InspectDeviceInfoRespVO> getInspectDeviceInfo(@RequestParam("id") Long id) {
        InspectDeviceInfoDO inspectDeviceInfo = inspectDeviceInfoService.getInspectDeviceInfo(id);
        return success(BeanUtils.toBean(inspectDeviceInfo, InspectDeviceInfoRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检设备信息分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-device-info:query')")
    public CommonResult<PageResult<InspectDeviceInfoRespVO>> getInspectDeviceInfoPage(@Valid InspectDeviceInfoPageReqVO pageReqVO) {
        PageResult<InspectDeviceInfoDO> pageResult = inspectDeviceInfoService.getInspectDeviceInfoPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectDeviceInfoRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检设备信息 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-device-info:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectDeviceInfoExcel(@Valid InspectDeviceInfoPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectDeviceInfoDO> list = inspectDeviceInfoService.getInspectDeviceInfoPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检设备信息.xls", "数据", InspectDeviceInfoRespVO.class,
                        BeanUtils.toBean(list, InspectDeviceInfoRespVO.class));
    }

}