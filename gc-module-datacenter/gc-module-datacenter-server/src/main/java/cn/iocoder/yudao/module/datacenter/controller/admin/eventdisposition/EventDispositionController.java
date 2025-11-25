package cn.iocoder.yudao.module.datacenter.controller.admin.eventdisposition;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.eventdisposition.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.eventdisposition.EventDispositionDO;
import cn.iocoder.yudao.module.datacenter.service.eventdisposition.EventDispositionService;

@Tag(name = "管理后台 - 监测事件关联处置")
@RestController
@RequestMapping("/datacenter/event-disposition")
@Validated
public class EventDispositionController {

    @Resource
    private EventDispositionService eventDispositionService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件关联处置")
    @PreAuthorize("@ss.hasPermission('datacenter:event-disposition:create')")
    public CommonResult<Long> createEventDisposition(@Valid @RequestBody EventDispositionSaveReqVO createReqVO) {
        return success(eventDispositionService.createEventDisposition(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件关联处置")
    @PreAuthorize("@ss.hasPermission('datacenter:event-disposition:update')")
    public CommonResult<Boolean> updateEventDisposition(@Valid @RequestBody EventDispositionSaveReqVO updateReqVO) {
        eventDispositionService.updateEventDisposition(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件关联处置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:event-disposition:delete')")
    public CommonResult<Boolean> deleteEventDisposition(@RequestParam("id") Long id) {
        eventDispositionService.deleteEventDisposition(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件关联处置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:event-disposition:query')")
    public CommonResult<EventDispositionRespVO> getEventDisposition(@RequestParam("id") Long id) {
        EventDispositionDO eventDisposition = eventDispositionService.getEventDisposition(id);
        return success(BeanUtils.toBean(eventDisposition, EventDispositionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件关联处置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:event-disposition:query')")
    public CommonResult<PageResult<EventDispositionRespVO>> getEventDispositionPage(@Valid EventDispositionPageReqVO pageReqVO) {
        PageResult<EventDispositionDO> pageResult = eventDispositionService.getEventDispositionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EventDispositionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件关联处置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:event-disposition:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEventDispositionExcel(@Valid EventDispositionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EventDispositionDO> list = eventDispositionService.getEventDispositionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件关联处置.xls", "数据", EventDispositionRespVO.class,
                        BeanUtils.toBean(list, EventDispositionRespVO.class));
    }

}