package cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview;

import io.swagger.v3.oas.annotations.Hidden;
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

import cn.iocoder.yudao.module.inspectop.controller.admin.scheduleview.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.scheduleview.ScheduleViewDO;
import cn.iocoder.yudao.module.inspectop.service.scheduleview.ScheduleViewService;

@Tag(name = "巡查巡检 - 排班查看")
@RestController
@RequestMapping("/inspectop/schedule-view")
@Validated
@Hidden
public class ScheduleViewController {

    @Resource
    private ScheduleViewService scheduleViewService;

    @PostMapping("/create")
    @Operation(summary = "创建排班查看")
    @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:create')")
    public CommonResult<Long> createScheduleView(@Valid @RequestBody ScheduleViewSaveReqVO createReqVO) {
        return success(scheduleViewService.createScheduleView(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新排班查看")
    @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:update')")
    public CommonResult<Boolean> updateScheduleView(@Valid @RequestBody ScheduleViewSaveReqVO updateReqVO) {
        scheduleViewService.updateScheduleView(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除排班查看")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:delete')")
    public CommonResult<Boolean> deleteScheduleView(@RequestParam("id") Long id) {
        scheduleViewService.deleteScheduleView(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除排班查看")
                @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:delete')")
    public CommonResult<Boolean> deleteScheduleViewList(@RequestParam("ids") List<Long> ids) {
        scheduleViewService.deleteScheduleViewListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得排班查看")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:query')")
    public CommonResult<ScheduleViewRespVO> getScheduleView(@RequestParam("id") Long id) {
        ScheduleViewDO scheduleView = scheduleViewService.getScheduleView(id);
        return success(BeanUtils.toBean(scheduleView, ScheduleViewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得排班查看分页")
    @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:query')")
    public CommonResult<PageResult<ScheduleViewRespVO>> getScheduleViewPage(@Valid ScheduleViewPageReqVO pageReqVO) {
        PageResult<ScheduleViewRespVO> pageResult = scheduleViewService.getScheduleViewPage(pageReqVO);
        return success(pageResult);
    }

    @PostMapping("/apply-shift")
    @Operation(summary = "申请换班")
    @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:apply-shift')")
    public CommonResult<Boolean> applyShift(@Valid @RequestBody ShiftApplyReqVO reqVO) {
        scheduleViewService.applyShift(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取排班统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:chart')")
    public CommonResult<ScheduleViewChartRespVO> getScheduleViewChart(@Valid ScheduleViewChartReqVO reqVO) {
        ScheduleViewChartRespVO chartData = scheduleViewService.getScheduleViewChart(reqVO);
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出排班查看 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:schedule-view:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportScheduleViewExcel(@Valid ScheduleViewPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ScheduleViewRespVO> list = scheduleViewService.getScheduleViewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "排班查看.xls", "数据", ScheduleViewRespVO.class, list);
    }

}