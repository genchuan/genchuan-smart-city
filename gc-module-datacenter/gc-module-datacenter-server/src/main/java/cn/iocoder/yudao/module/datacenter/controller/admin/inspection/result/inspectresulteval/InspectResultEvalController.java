package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo.InspectResultEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo.InspectResultEvalRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresulteval.vo.InspectResultEvalSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresulteval.InspectResultEvalDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.result.inspectresulteval.InspectResultEvalService;

@Tag(name = "管理后台 - 巡查巡检结果评估")
@RestController
@RequestMapping("/datacenter/inspect-result-eval")
@Validated
public class InspectResultEvalController {

    @Resource
    private InspectResultEvalService inspectResultEvalService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检结果评估")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-eval:create')")
    public CommonResult<Long> createInspectResultEval(@Valid @RequestBody InspectResultEvalSaveReqVO createReqVO) {
        return success(inspectResultEvalService.createInspectResultEval(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检结果评估")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-eval:update')")
    public CommonResult<Boolean> updateInspectResultEval(@Valid @RequestBody InspectResultEvalSaveReqVO updateReqVO) {
        inspectResultEvalService.updateInspectResultEval(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检结果评估")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-eval:delete')")
    public CommonResult<Boolean> deleteInspectResultEval(@RequestParam("id") Long id) {
        inspectResultEvalService.deleteInspectResultEval(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检结果评估")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-eval:query')")
    public CommonResult<InspectResultEvalRespVO> getInspectResultEval(@RequestParam("id") Long id) {
        InspectResultEvalDO inspectResultEval = inspectResultEvalService.getInspectResultEval(id);
        return success(BeanUtils.toBean(inspectResultEval, InspectResultEvalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检结果评估分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-eval:query')")
    public CommonResult<PageResult<InspectResultEvalRespVO>> getInspectResultEvalPage(@Valid InspectResultEvalPageReqVO pageReqVO) {
        PageResult<InspectResultEvalDO> pageResult = inspectResultEvalService.getInspectResultEvalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectResultEvalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检结果评估 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-result-eval:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectResultEvalExcel(@Valid InspectResultEvalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectResultEvalDO> list = inspectResultEvalService.getInspectResultEvalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检结果评估.xls", "数据", InspectResultEvalRespVO.class,
                        BeanUtils.toBean(list, InspectResultEvalRespVO.class));
    }

}