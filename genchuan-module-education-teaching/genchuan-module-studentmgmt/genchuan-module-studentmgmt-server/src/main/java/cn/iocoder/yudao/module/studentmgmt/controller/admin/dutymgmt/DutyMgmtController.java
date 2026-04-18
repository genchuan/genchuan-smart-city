package cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.dutymgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dutymgmt.DutyMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.dutymgmt.DutyMgmtService;

@Tag(name = "学生管理后台 - 值班管理")
@RestController
@RequestMapping("/studentmgmt/duty-mgmt")
@Validated
public class DutyMgmtController {

    @Resource
    private DutyMgmtService dutyMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建值班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:create')")
    public CommonResult<Long> createDutyMgmt(@Valid @RequestBody DutyMgmtSaveReqVO createReqVO) {
        return success(dutyMgmtService.createDutyMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新值班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:update')")
    public CommonResult<Boolean> updateDutyMgmt(@Valid @RequestBody DutyMgmtUpdateReqVO updateReqVO) {
        dutyMgmtService.updateDutyMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除值班管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:delete')")
    public CommonResult<Boolean> deleteDutyMgmt(@RequestParam("id") Long id) {
        dutyMgmtService.deleteDutyMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除值班管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:delete')")
    public CommonResult<Boolean> deleteDutyMgmtList(@RequestParam("ids") List<Long> ids) {
        dutyMgmtService.deleteDutyMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得值班管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:query')")
    public CommonResult<DutyMgmtRespVO> getDutyMgmt(@RequestParam("id") Long id) {
        DutyMgmtDO dutyMgmt = dutyMgmtService.getDutyMgmt(id);
        return success(BeanUtils.toBean(dutyMgmt, DutyMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得值班管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:query')")
    public CommonResult<PageResult<DutyMgmtRespVO>> getDutyMgmtPage(@Valid DutyMgmtPageReqVO pageReqVO) {
        PageResult<DutyMgmtDO> pageResult = dutyMgmtService.getDutyMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DutyMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出值班管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDutyMgmtExcel(@Valid DutyMgmtPageReqVO pageReqVO,
                                    HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DutyMgmtDO> list = dutyMgmtService.getDutyMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "值班管理.xls", "数据", DutyMgmtRespVO.class,
                BeanUtils.toBean(list, DutyMgmtRespVO.class));
    }

    @PutMapping("/schedule")
    @Operation(summary = "排班")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:schedule')")
    public CommonResult<Boolean> schedule(@Valid @RequestBody DutyMgmtScheduleReqVO reqVo) {
        boolean isSuccess = dutyMgmtService.schedule(reqVo);
        return success(isSuccess);
    }

    @PutMapping("/checkin")
    @Operation(summary = "打卡")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:checkin')")
    public CommonResult<Boolean> checkin(@Valid @RequestBody DutyMgmtCheckinReqVO reqVo) {
        boolean isSuccess = dutyMgmtService.checkin(reqVo);
        return success(isSuccess);
    }

    @PutMapping("/shiftApply")
    @Operation(summary = "调班申请")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:shiftApply')")
    public CommonResult<Boolean> shiftApply(@Valid @RequestBody DutyMgmtShiftApplyReqVO reqVo) {
        boolean isSuccess = dutyMgmtService.shiftApply(reqVo);
        return success(isSuccess);
    }

    @PutMapping("/shiftAudit")
    @Operation(summary = "调班审批")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:shiftAudit')")
    public CommonResult<Boolean> shiftAudit(@Valid @RequestBody DutyMgmtShiftAuditReqVO reqVo) {
        boolean isSuccess = dutyMgmtService.shiftAudit(reqVo);
        return success(isSuccess);
    }

    @PutMapping("/vehicleApply")
    @Operation(summary = "出车申请")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:vehicleApply')")
    public CommonResult<Boolean> vehicleApply(@Valid @RequestBody DutyMgmtVehicleApplyReqVO reqVo) {
        boolean isSuccess = dutyMgmtService.vehicleApply(reqVo);
        return success(isSuccess);
    }

    @PutMapping("/vehicleAudit")
    @Operation(summary = "出车审批")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:vehicleAudit')")
    public CommonResult<Boolean> vehicleAudit(@Valid @RequestBody DutyMgmtShiftAuditReqVO reqVo) {
        boolean isSuccess = dutyMgmtService.vehicleAudit(reqVo);
        return success(isSuccess);
    }

    @PutMapping("/uploadRecord")
    @Operation(summary = "记录上传")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:uploadRecord')")
    public CommonResult<Boolean> uploadRecord(@Valid @RequestBody DutyMgmtUploadRecordReqVO reqVo) {
        boolean isSuccess = dutyMgmtService.uploadRecord(reqVo);
        return success(isSuccess);
    }

    @PutMapping("/chart")
    @Operation(summary = "值班调度看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:chart')")
    public CommonResult<DutyMgmtChartRespVO> chart(@Valid @RequestBody DutyMgmtChartReqVO reqVo) {
        DutyMgmtChartRespVO respVO = dutyMgmtService.chart(reqVo);
        return success(respVO);
    }

    @PutMapping("/chart/dutyIndex")
    @Operation(summary = "值班核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:duty-mgmt:dutyIndex')")
    public CommonResult<DutyMgmtChartIndexRespVO> dutyIndex(@Valid @RequestBody DutyMgmtChartReqVO reqVo) {
        DutyMgmtChartIndexRespVO respVO = dutyMgmtService.dutyIndex(reqVo);
        return success(respVO);
    }


}