package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo.EvalResultAuditPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo.EvalResultAuditRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultaudit.vo.EvalResultAuditSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultaudit.EvalResultAuditDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultaudit.EvalResultAuditService;

@Tag(name = "管理后台 - 结果审核")
@RestController
@RequestMapping("/datacenter/eval-result-audit")
@Validated
public class EvalResultAuditController {

    @Resource
    private EvalResultAuditService evalResultAuditService;

    @PostMapping("/create")
    @Operation(summary = "创建结果审核")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-audit:create')")
    public CommonResult<Long> createEvalResultAudit(@Valid @RequestBody EvalResultAuditSaveReqVO createReqVO) {
        return success(evalResultAuditService.createEvalResultAudit(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结果审核")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-audit:update')")
    public CommonResult<Boolean> updateEvalResultAudit(@Valid @RequestBody EvalResultAuditSaveReqVO updateReqVO) {
        evalResultAuditService.updateEvalResultAudit(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结果审核")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-audit:delete')")
    public CommonResult<Boolean> deleteEvalResultAudit(@RequestParam("id") Long id) {
        evalResultAuditService.deleteEvalResultAudit(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结果审核")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-audit:query')")
    public CommonResult<EvalResultAuditRespVO> getEvalResultAudit(@RequestParam("id") Long id) {
        EvalResultAuditDO evalResultAudit = evalResultAuditService.getEvalResultAudit(id);
        return success(BeanUtils.toBean(evalResultAudit, EvalResultAuditRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结果审核分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-audit:query')")
    public CommonResult<PageResult<EvalResultAuditRespVO>> getEvalResultAuditPage(@Valid EvalResultAuditPageReqVO pageReqVO) {
        PageResult<EvalResultAuditDO> pageResult = evalResultAuditService.getEvalResultAuditPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalResultAuditRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结果审核 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-audit:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalResultAuditExcel(@Valid EvalResultAuditPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalResultAuditDO> list = evalResultAuditService.getEvalResultAuditPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结果审核.xls", "数据", EvalResultAuditRespVO.class,
                        BeanUtils.toBean(list, EvalResultAuditRespVO.class));
    }

}