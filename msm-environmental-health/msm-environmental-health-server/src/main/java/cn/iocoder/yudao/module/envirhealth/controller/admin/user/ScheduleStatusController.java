/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.user;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus.ScheduleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus.ScheduleStatusRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus.ScheduleStatusSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ScheduleStatusDO;
import cn.iocoder.yudao.module.envirhealth.service.user.schedulestatus.ScheduleStatusService;

@Tag(name = "管理后台 - 排班状态字典表")
@RestController
@RequestMapping("/envirhealth/schedule-status")
@Validated
public class ScheduleStatusController {

    @Resource
    private ScheduleStatusService scheduleStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建排班状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule-status:create')")
    public CommonResult<Long> createScheduleStatus(@Valid @RequestBody ScheduleStatusSaveReqVO createReqVO) {
        return success(scheduleStatusService.createScheduleStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排班状态字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule-status:update')")
    public CommonResult<Boolean> updateScheduleStatus(@Valid @RequestBody ScheduleStatusSaveReqVO updateReqVO) {
        scheduleStatusService.updateScheduleStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排班状态字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule-status:delete')")
    public CommonResult<Boolean> deleteScheduleStatus(@RequestParam("id") Long id) {
        scheduleStatusService.deleteScheduleStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排班状态字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule-status:query')")
    public CommonResult<ScheduleStatusRespVO> getScheduleStatus(@RequestParam("id") Long id) {
        ScheduleStatusDO scheduleStatus = scheduleStatusService.getScheduleStatus(id);
        return success(BeanUtils.toBean(scheduleStatus, ScheduleStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排班状态字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule-status:query')")
    public CommonResult<PageResult<ScheduleStatusRespVO>> getScheduleStatusPage(@Valid ScheduleStatusPageReqVO pageReqVO) {
        PageResult<ScheduleStatusDO> pageResult = scheduleStatusService.getScheduleStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ScheduleStatusRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排班状态字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule-status:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportScheduleStatusExcel(@Valid ScheduleStatusPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ScheduleStatusDO> list = scheduleStatusService.getScheduleStatusPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "排班状态字典表.xls", "数据", ScheduleStatusRespVO.class,
                        BeanUtils.toBean(list, ScheduleStatusRespVO.class));
    }

}*/
