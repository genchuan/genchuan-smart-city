package cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.handoverlog.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.handoverlog.HandoverLogDO;
import cn.iocoder.yudao.module.inspectop.service.handoverlog.HandoverLogService;

@Tag(name = "巡查巡检 - 交接日志")
@RestController
@RequestMapping("/inspectop/handover-log")
@Validated
public class HandoverLogController {

    @Resource
    private HandoverLogService handoverLogService;

    @PostMapping("/create")
    @Operation(summary = "创建交接日志")
    @PreAuthorize("@ss.hasPermission('inspectop:handover-log:create')")
    public CommonResult<Long> createHandoverLog(@Valid @RequestBody HandoverLogSaveReqVO createReqVO) {
        return success(handoverLogService.createHandoverLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新交接日志")
    @PreAuthorize("@ss.hasPermission('inspectop:handover-log:update')")
    public CommonResult<Boolean> updateHandoverLog(@Valid @RequestBody HandoverLogSaveReqVO updateReqVO) {
        handoverLogService.updateHandoverLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除交接日志")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:handover-log:delete')")
    public CommonResult<Boolean> deleteHandoverLog(@RequestParam("id") Long id) {
        handoverLogService.deleteHandoverLog(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除交接日志")
                @PreAuthorize("@ss.hasPermission('inspectop:handover-log:delete')")
    public CommonResult<Boolean> deleteHandoverLogList(@RequestParam("ids") List<Long> ids) {
        handoverLogService.deleteHandoverLogListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得交接日志")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:handover-log:query')")
    public CommonResult<HandoverLogRespVO> getHandoverLog(@RequestParam("id") Long id) {
        HandoverLogDO handoverLog = handoverLogService.getHandoverLog(id);
        return success(BeanUtils.toBean(handoverLog, HandoverLogRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得交接日志分页")
    @PreAuthorize("@ss.hasPermission('inspectop:handover-log:query')")
    public CommonResult<PageResult<HandoverLogRespVO>> getHandoverLogPage(@Valid HandoverLogPageReqVO pageReqVO) {
        PageResult<HandoverLogRespVO> pageResult = handoverLogService.getHandoverLogPage(pageReqVO);
        return success(pageResult);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认交接日志")
    @PreAuthorize("@ss.hasPermission('inspectop:handover-log:confirm')")
    public CommonResult<Boolean> confirmHandoverLog(@Valid @RequestBody HandoverLogConfirmReqVO reqVO) {
        handoverLogService.confirmHandoverLog(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取交接日志统计图表")
    @PreAuthorize("@ss.hasPermission('inspectop:handover-log:chart')")
    public CommonResult<HandoverLogChartRespVO> getHandoverLogChart(@Valid HandoverLogChartReqVO reqVO) {
        HandoverLogChartRespVO chartData = handoverLogService.getHandoverLogChart(reqVO);
        return success(chartData);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出交接日志 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:handover-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportHandoverLogExcel(@Valid HandoverLogPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<HandoverLogRespVO> list = handoverLogService.getHandoverLogPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "交接日志.xls", "数据", HandoverLogRespVO.class, list);
    }

}