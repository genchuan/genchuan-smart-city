/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.user;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule.SchedulePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule.ScheduleRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule.ScheduleSaveReqVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ScheduleDO;
import cn.iocoder.yudao.module.envirhealth.service.user.schedule.ScheduleService;

@Tag(name = "管理后台 - 排班计划")
@RestController
@RequestMapping("/envirhealth/schedule")
@Validated
public class ScheduleController {

    @Resource
    private ScheduleService scheduleService;

    @PostMapping("/create")
    @Operation(summary = "创建排班计划")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule:create')")
    public CommonResult<Long> createSchedule(@Valid @RequestBody ScheduleSaveReqVO createReqVO) {
        return success(scheduleService.createSchedule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排班计划")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule:update')")
    public CommonResult<Boolean> updateSchedule(@Valid @RequestBody ScheduleSaveReqVO updateReqVO) {
        scheduleService.updateSchedule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排班计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule:delete')")
    public CommonResult<Boolean> deleteSchedule(@RequestParam("id") Long id) {
        scheduleService.deleteSchedule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排班计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule:query')")
    public CommonResult<ScheduleRespVO> getSchedule(@RequestParam("id") Long id) {
        ScheduleDO schedule = scheduleService.getSchedule(id);
        return success(BeanUtils.toBean(schedule, ScheduleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排班计划分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule:query')")
    public CommonResult<PageResult<ScheduleRespVO>> getSchedulePage(@Valid SchedulePageReqVO pageReqVO) {
        PageResult<ScheduleDO> pageResult = scheduleService.getSchedulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ScheduleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排班计划 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:schedule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportScheduleExcel(@Valid SchedulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ScheduleDO> list = scheduleService.getSchedulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "排班计划.xls", "数据", ScheduleRespVO.class,
                        BeanUtils.toBean(list, ScheduleRespVO.class));
    }

}*/
