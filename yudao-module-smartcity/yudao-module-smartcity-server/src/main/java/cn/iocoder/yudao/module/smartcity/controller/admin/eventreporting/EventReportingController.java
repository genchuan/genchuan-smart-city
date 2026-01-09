package cn.iocoder.yudao.module.smartcity.controller.admin.eventreporting;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.eventreporting.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventreporting.EventReportingDO;
import cn.iocoder.yudao.module.smartcity.service.eventreporting.EventReportingService;

@Tag(name = "管理后台 - 事件上报")
@RestController
@RequestMapping("/smartcity/event-reporting")
@Validated
public class EventReportingController {

    @Resource
    private EventReportingService eventReportingService;

    @PostMapping("/create")
    @Operation(summary = "创建事件上报")
    @PreAuthorize("@ss.hasPermission('smartcity:event-reporting:create')")
    public CommonResult<Long> createEventReporting(@Valid @RequestBody EventReportingSaveReqVO createReqVO) {
        return success(eventReportingService.createEventReporting(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件上报")
    @PreAuthorize("@ss.hasPermission('smartcity:event-reporting:update')")
    public CommonResult<Boolean> updateEventReporting(@Valid @RequestBody EventReportingSaveReqVO updateReqVO) {
        eventReportingService.updateEventReporting(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件上报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:event-reporting:delete')")
    public CommonResult<Boolean> deleteEventReporting(@RequestParam("id") Long id) {
        eventReportingService.deleteEventReporting(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件上报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:event-reporting:query')")
    public CommonResult<EventReportingRespVO> getEventReporting(@RequestParam("id") Long id) {
        EventReportingDO eventReporting = eventReportingService.getEventReporting(id);
        return success(BeanUtils.toBean(eventReporting, EventReportingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件上报分页")
    @PreAuthorize("@ss.hasPermission('smartcity:event-reporting:query')")
    public CommonResult<PageResult<EventReportingRespVO>> getEventReportingPage(@Valid EventReportingPageReqVO pageReqVO) {
        PageResult<EventReportingDO> pageResult = eventReportingService.getEventReportingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventReportingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件上报 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:event-reporting:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventReportingExcel(@Valid EventReportingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventReportingDO> list = eventReportingService.getEventReportingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件上报.xls", "数据", EventReportingRespVO.class,
                        BeanUtils.toBean(list, EventReportingRespVO.class));
    }

}