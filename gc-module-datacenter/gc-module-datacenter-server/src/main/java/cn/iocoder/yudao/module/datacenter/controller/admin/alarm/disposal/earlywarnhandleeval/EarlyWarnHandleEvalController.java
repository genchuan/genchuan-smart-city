package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo.EarlyWarnHandleEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo.EarlyWarnHandleEvalRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnhandleeval.vo.EarlyWarnHandleEvalSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnhandleeval.EarlyWarnHandleEvalDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnhandleeval.EarlyWarnHandleEvalService;

@Tag(name = "管理后台 - 预警告警处置评估")
@RestController
@RequestMapping("/datacenter/early-warn-handle-eval")
@Validated
public class EarlyWarnHandleEvalController {

    @Resource
    private EarlyWarnHandleEvalService earlyWarnHandleEvalService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警处置评估")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eval:create')")
    public CommonResult<Long> createEarlyWarnHandleEval(@Valid @RequestBody EarlyWarnHandleEvalSaveReqVO createReqVO) {
        return success(earlyWarnHandleEvalService.createEarlyWarnHandleEval(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警处置评估")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eval:update')")
    public CommonResult<Boolean> updateEarlyWarnHandleEval(@Valid @RequestBody EarlyWarnHandleEvalSaveReqVO updateReqVO) {
        earlyWarnHandleEvalService.updateEarlyWarnHandleEval(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警处置评估")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eval:delete')")
    public CommonResult<Boolean> deleteEarlyWarnHandleEval(@RequestParam("id") Long id) {
        earlyWarnHandleEvalService.deleteEarlyWarnHandleEval(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警处置评估")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eval:query')")
    public CommonResult<EarlyWarnHandleEvalRespVO> getEarlyWarnHandleEval(@RequestParam("id") Long id) {
        EarlyWarnHandleEvalDO earlyWarnHandleEval = earlyWarnHandleEvalService.getEarlyWarnHandleEval(id);
        return success(BeanUtils.toBean(earlyWarnHandleEval, EarlyWarnHandleEvalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警处置评估分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eval:query')")
    public CommonResult<PageResult<EarlyWarnHandleEvalRespVO>> getEarlyWarnHandleEvalPage(@Valid EarlyWarnHandleEvalPageReqVO pageReqVO) {
        PageResult<EarlyWarnHandleEvalDO> pageResult = earlyWarnHandleEvalService.getEarlyWarnHandleEvalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnHandleEvalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警处置评估 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-handle-eval:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnHandleEvalExcel(@Valid EarlyWarnHandleEvalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnHandleEvalDO> list = earlyWarnHandleEvalService.getEarlyWarnHandleEvalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警处置评估.xls", "数据", EarlyWarnHandleEvalRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnHandleEvalRespVO.class));
    }

}