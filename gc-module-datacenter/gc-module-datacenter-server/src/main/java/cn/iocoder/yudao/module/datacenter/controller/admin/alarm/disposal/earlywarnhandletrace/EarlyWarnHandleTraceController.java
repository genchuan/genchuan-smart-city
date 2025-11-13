package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo.EarlyWarnHandleTracePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo.EarlyWarnHandleTraceRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandletrace.vo.EarlyWarnHandleTraceSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandletrace.EarlyWarnHandleTraceDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnhandletrace.EarlyWarnHandleTraceService;

@Tag(name = "管理后台 - 预警告警处置跟踪")
@RestController
@RequestMapping("/datacenter/early-warn-handle-trace")
@Validated
public class EarlyWarnHandleTraceController {

    @Resource
    private EarlyWarnHandleTraceService earlyWarnHandleTraceService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警处置跟踪")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-trace:create')")
    public CommonResult<Long> createEarlyWarnHandleTrace(@Valid @RequestBody EarlyWarnHandleTraceSaveReqVO createReqVO) {
        return success(earlyWarnHandleTraceService.createEarlyWarnHandleTrace(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警处置跟踪")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-trace:update')")
    public CommonResult<Boolean> updateEarlyWarnHandleTrace(@Valid @RequestBody EarlyWarnHandleTraceSaveReqVO updateReqVO) {
        earlyWarnHandleTraceService.updateEarlyWarnHandleTrace(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警处置跟踪")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-trace:delete')")
    public CommonResult<Boolean> deleteEarlyWarnHandleTrace(@RequestParam("id") Long id) {
        earlyWarnHandleTraceService.deleteEarlyWarnHandleTrace(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警处置跟踪")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-trace:query')")
    public CommonResult<EarlyWarnHandleTraceRespVO> getEarlyWarnHandleTrace(@RequestParam("id") Long id) {
        EarlyWarnHandleTraceDO earlyWarnHandleTrace = earlyWarnHandleTraceService.getEarlyWarnHandleTrace(id);
        return success(BeanUtils.toBean(earlyWarnHandleTrace, EarlyWarnHandleTraceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警处置跟踪分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-trace:query')")
    public CommonResult<PageResult<EarlyWarnHandleTraceRespVO>> getEarlyWarnHandleTracePage(@Valid EarlyWarnHandleTracePageReqVO pageReqVO) {
        PageResult<EarlyWarnHandleTraceDO> pageResult = earlyWarnHandleTraceService.getEarlyWarnHandleTracePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnHandleTraceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警处置跟踪 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-trace:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnHandleTraceExcel(@Valid EarlyWarnHandleTracePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnHandleTraceDO> list = earlyWarnHandleTraceService.getEarlyWarnHandleTracePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警处置跟踪.xls", "数据", EarlyWarnHandleTraceRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnHandleTraceRespVO.class));
    }

}