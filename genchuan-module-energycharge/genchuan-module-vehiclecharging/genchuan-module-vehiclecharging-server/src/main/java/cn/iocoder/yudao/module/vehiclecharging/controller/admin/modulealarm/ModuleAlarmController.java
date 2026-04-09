package cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.modulealarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.service.modulealarm.ModuleAlarmService;

@Tag(name = "管理后台 - 模块告警记录")
@RestController
@RequestMapping("/vehiclecharging/module-alarm")
@Validated
public class ModuleAlarmController {

    @Resource
    private ModuleAlarmService moduleAlarmService;

    @PostMapping("/create")
    @Operation(summary = "创建模块告警记录")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:create')")
    public CommonResult<Long> createModuleAlarm(@Valid @RequestBody ModuleAlarmSaveReqVO createReqVO) {
        return success(moduleAlarmService.createModuleAlarm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新模块告警记录")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:update')")
    public CommonResult<Boolean> updateModuleAlarm(@Valid @RequestBody ModuleAlarmSaveReqVO updateReqVO) {
        moduleAlarmService.updateModuleAlarm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除模块告警记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:delete')")
    public CommonResult<Boolean> deleteModuleAlarm(@RequestParam("id") Long id) {
        moduleAlarmService.deleteModuleAlarm(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除模块告警记录")
                @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:delete')")
    public CommonResult<Boolean> deleteModuleAlarmList(@RequestParam("ids") List<Long> ids) {
        moduleAlarmService.deleteModuleAlarmListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得模块告警记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:query')")
    public CommonResult<ModuleAlarmRespVO> getModuleAlarm(@RequestParam("id") Long id) {
        return success(moduleAlarmService.getModuleAlarm(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得模块告警记录分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:query')")
    public CommonResult<PageResult<ModuleAlarmRespVO>> getModuleAlarmPage(@Valid ModuleAlarmPageReqVO pageReqVO) {
        return success(moduleAlarmService.getModuleAlarmPage(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出模块告警记录 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportModuleAlarmExcel(@Valid ModuleAlarmPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ModuleAlarmRespVO> list = moduleAlarmService.getModuleAlarmPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "模块告警记录.xls", "数据", ModuleAlarmRespVO.class, list);
    }

    @PutMapping("/check")
    @Operation(summary = "排查告警记录")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:update')")
    public CommonResult<Boolean> checkModuleAlarm(@Valid @RequestBody ModuleAlarmCheckReqVO checkReqVO) {
        moduleAlarmService.checkModuleAlarm(checkReqVO);
        return success(true);
    }

    @PutMapping("/repair")
    @Operation(summary = "修复告警记录")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:update')")
    public CommonResult<Boolean> repairModuleAlarm(@Valid @RequestBody ModuleAlarmRepairReqVO repairReqVO) {
        moduleAlarmService.repairModuleAlarm(repairReqVO);
        return success(true);
    }

    @PutMapping("/close")
    @Operation(summary = "销账告警记录")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:update')")
    public CommonResult<Boolean> closeModuleAlarm(@Valid @RequestBody ModuleAlarmCloseReqVO closeReqVO) {
        moduleAlarmService.closeModuleAlarm(closeReqVO);
        return success(true);
    }

    @PutMapping("/remark")
    @Operation(summary = "更新告警备注")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module-alarm:update')")
    public CommonResult<Boolean> updateModuleAlarmRemark(@Valid @RequestBody ModuleAlarmRemarkReqVO remarkReqVO) {
        moduleAlarmService.updateRemark(remarkReqVO);
        return success(true);
    }

    @GetMapping("/repair-voucher")
    @Operation(summary = "获取修复凭证预览地址")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module_alarm:preview')")
    public CommonResult<String> getRepairVoucher(@Valid ModuleAlarmRepairVoucherReqVO reqVO) {
        return success(moduleAlarmService.getRepairVoucher(reqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "系统模块告警统计图表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module_alarm:chart')")
    public CommonResult<ModuleAlarmChartRespVO> getModuleAlarmChart(@Valid ModuleAlarmChartReqVO reqVO) {
        return success(moduleAlarmService.getChartData(reqVO));
    }

    @GetMapping("/chart/module-count")
    @Operation(summary = "各模块告警数量统计（柱状图钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module_alarm:chart')")
    public CommonResult<List<ModuleAlarmChartRespVO.BarData>> getModuleCount(@Valid ModuleAlarmModuleCountReqVO reqVO) {
        return success(moduleAlarmService.getModuleCount(reqVO));
    }

    @GetMapping("/chart/count")
    @Operation(summary = "模块告警状态数量统计（卡片钻取）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:module_alarm:chart')")
    public CommonResult<ModuleAlarmCountRespVO> getStatusCount(@Valid ModuleAlarmCountReqVO reqVO) {
        return success(moduleAlarmService.getStatusCount(reqVO));
    }

}
