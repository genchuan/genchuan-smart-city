package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusAlarmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusLocationReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusLocationRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.inparkstatus.InParkStatusDO;
import cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.inparkstatus.InParkStatusService;
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
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 在停状态")
@RestController
@RequestMapping("/vehiclepass/in-park-status")
@Validated
public class InParkStatusController {

    @Resource
    private InParkStatusService parkStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建在停状态")
    @PreAuthorize("@ss.hasPermission('in:park-status:create')")
    public CommonResult<Long> createParkStatus(@Valid @RequestBody InParkStatusSaveReqVO createReqVO) {
        return success(parkStatusService.createParkStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新在停状态")
    @PreAuthorize("@ss.hasPermission('in:park-status:update')")
    public CommonResult<Boolean> updateParkStatus(@Valid @RequestBody InParkStatusSaveReqVO updateReqVO) {
        parkStatusService.updateParkStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除在停状态")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('in:park-status:delete')")
    public CommonResult<Boolean> deleteParkStatus(@RequestParam("id") Long id) {
        parkStatusService.deleteParkStatus(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除在停状态")
    @PreAuthorize("@ss.hasPermission('in:park-status:delete')")
    public CommonResult<Boolean> deleteParkStatusList(@RequestParam("ids") List<Long> ids) {
        parkStatusService.deleteParkStatusListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得在停状态")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('in:park-status:query')")
    public CommonResult<InParkStatusRespVO> getParkStatus(@RequestParam("id") Long id) {
        return success(parkStatusService.getInParkStatusWithStation(id));
    }

    @GetMapping("/page")
    @Operation(summary = "获得在停状态分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:in-park-status:query')")
    public CommonResult<PageResult<InParkStatusRespVO>> getParkStatusPage(@Valid InParkStatusPageReqVO pageReqVO) {
        return success(parkStatusService.getInParkStatusPage(pageReqVO));
    }

    @GetMapping("/location")
    @Operation(summary = "在停状态定位")
    @PreAuthorize("@ss.hasPermission('vehiclepass:in-park-status:location')")
    public CommonResult<InParkStatusLocationRespVO> getParkStatusLocation(@Valid InParkStatusLocationReqVO reqVO) {
        return success(parkStatusService.getInParkStatusLocation(reqVO));
    }

    @PutMapping("/remind")
    @Operation(summary = "提醒在场车辆")
    @PreAuthorize("@ss.hasPermission('vehiclepass:in-park-status:remind')")
    public CommonResult<Boolean> remindParkStatus(@RequestParam("id") Long id) {
        parkStatusService.remindParkStatus(id);
        return success(true);
    }

    @PutMapping("/alarm")
    @Operation(summary = "告警在场车辆")
    @PreAuthorize("@ss.hasPermission('vehiclepass:in-park-status:alarm')")
    public CommonResult<Boolean> alarmParkStatus(@Valid @RequestBody InParkStatusAlarmReqVO reqVO) {
        parkStatusService.alarmParkStatus(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "在停状态统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:in-park-status:chart')")
    public CommonResult<InParkStatusChartRespVO> getInParkStatusChart(@Valid InParkStatusChartReqVO reqVO) {
        return success(parkStatusService.getInParkStatusChart(reqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出在停状态 Excel")
    @PreAuthorize("@ss.hasPermission('in:park-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkStatusExcel(InParkStatusPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageNo(1);
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<InParkStatusRespVO> pageResult = parkStatusService.getInParkStatusPage(pageReqVO);
        ExcelUtils.write(response, "在停状态.xls", "数据", InParkStatusRespVO.class, pageResult.getList());
    }

}