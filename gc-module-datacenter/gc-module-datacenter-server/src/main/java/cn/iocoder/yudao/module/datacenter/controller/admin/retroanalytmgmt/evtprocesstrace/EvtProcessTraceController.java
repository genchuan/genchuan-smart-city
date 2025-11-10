package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.EvtProcessTracePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.EvtProcessTraceRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.EvtProcessTraceSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.evtprocesstrace.EvtProcessTraceDO;
import cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.evtprocesstrace.EvtProcessTraceService;

@Tag(name = "管理后台 - 事件过程回溯")
@RestController
@RequestMapping("/datacenter/evt-process-trace")
@Validated
public class EvtProcessTraceController {

    @Resource
    private EvtProcessTraceService evtProcessTraceService;

    @PostMapping("/create")
    @Operation(summary = "创建事件过程回溯")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-process-trace:create')")
    public CommonResult<Long> createEvtProcessTrace(@Valid @RequestBody EvtProcessTraceSaveReqVO createReqVO) {
        return success(evtProcessTraceService.createEvtProcessTrace(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件过程回溯")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-process-trace:update')")
    public CommonResult<Boolean> updateEvtProcessTrace(@Valid @RequestBody EvtProcessTraceSaveReqVO updateReqVO) {
        evtProcessTraceService.updateEvtProcessTrace(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件过程回溯")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:evt-process-trace:delete')")
    public CommonResult<Boolean> deleteEvtProcessTrace(@RequestParam("id") Long id) {
        evtProcessTraceService.deleteEvtProcessTrace(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件过程回溯")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-process-trace:query')")
    public CommonResult<EvtProcessTraceRespVO> getEvtProcessTrace(@RequestParam("id") Long id) {
        EvtProcessTraceDO evtProcessTrace = evtProcessTraceService.getEvtProcessTrace(id);
        return success(BeanUtils.toBean(evtProcessTrace, EvtProcessTraceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件过程回溯分页")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-process-trace:query')")
    public CommonResult<PageResult<EvtProcessTraceRespVO>> getEvtProcessTracePage(@Valid EvtProcessTracePageReqVO pageReqVO) {
        PageResult<EvtProcessTraceDO> pageResult = evtProcessTraceService.getEvtProcessTracePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvtProcessTraceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件过程回溯 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-process-trace:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvtProcessTraceExcel(@Valid EvtProcessTracePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvtProcessTraceDO> list = evtProcessTraceService.getEvtProcessTracePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件过程回溯.xls", "数据", EvtProcessTraceRespVO.class,
                        BeanUtils.toBean(list, EvtProcessTraceRespVO.class));
    }

}