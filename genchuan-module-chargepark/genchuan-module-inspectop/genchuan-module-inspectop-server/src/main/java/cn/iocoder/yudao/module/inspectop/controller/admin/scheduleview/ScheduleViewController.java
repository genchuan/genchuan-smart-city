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

        // 【新增】对VO列表中的字典值进行转换（数字 -> 中文）
        convertScheduleViewDictValues(list);

        // 导出 Excel
        ExcelUtils.write(response, "排班查看.xls", "数据", ScheduleViewRespVO.class, list);
    }

    /**
     * 【新增】转换排班查看字典值为中文显示
     * 此方法会修改传入的 voList 中每个对象的 shiftType 和 status 字段
     * @param voList 排班查看响应VO列表
     */
    private void convertScheduleViewDictValues(List<ScheduleViewRespVO> voList) {
        if (voList == null || voList.isEmpty()) {
            return;
        }
        for (ScheduleViewRespVO vo : voList) {
            // 转换班次类型
            vo.setShiftType(convertShiftType(vo.getShiftType()));
            // 转换排班状态
            vo.setStatus(convertScheduleStatus(vo.getStatus()));
        }
    }

    /**
     * 【新增】转换排班查看班次类型字典值
     * 根据您提供的映射：1-晚班，2-中班，3-早班
     * @param shiftTypeCode 班次类型编码（例如 "1", "2", "3"）
     * @return 对应的中文班次类型描述
     */
    private String convertShiftType(String shiftTypeCode) {
        if (shiftTypeCode == null) {
            return "";
        }
        switch (shiftTypeCode.trim()) {
            case "1":
                return "晚班";
            case "2":
                return "中班";
            case "3":
                return "早班";
            default:
                // 如果遇到未知编码，返回原编码以便排查
                return shiftTypeCode;
        }
    }

    /**
     * 【新增】转换排班查看排班状态字典值
     * 根据您提供的映射：1-正常，2-已换班
     * @param statusCode 排班状态编码（例如 "1", "2"）
     * @return 对应的中文排班状态描述
     */
    private String convertScheduleStatus(String statusCode) {
        if (statusCode == null) {
            return "";
        }
        switch (statusCode.trim()) {
            case "1":
                return "正常";
            case "2":
                return "已换班";
            default:
                // 如果遇到未知编码，返回原编码以便排查
                return statusCode;
        }
    }

}