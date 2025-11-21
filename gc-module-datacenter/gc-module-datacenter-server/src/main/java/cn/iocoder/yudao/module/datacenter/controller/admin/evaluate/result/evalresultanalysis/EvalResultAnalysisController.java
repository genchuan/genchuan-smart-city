package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo.EvalResultAnalysisPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo.EvalResultAnalysisRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultanalysis.vo.EvalResultAnalysisSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultanalysis.EvalResultAnalysisDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultanalysis.EvalResultAnalysisService;

@Tag(name = "管理后台 - 评价结果分析")
@RestController
@RequestMapping("/datacenter/eval-result-analysis")
@Validated
public class EvalResultAnalysisController {

    @Resource
    private EvalResultAnalysisService evalResultAnalysisService;

    @PostMapping("/create")
    @Operation(summary = "创建评价结果分析")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-analysis:create')")
    public CommonResult<Long> createEvalResultAnalysis(@Valid @RequestBody EvalResultAnalysisSaveReqVO createReqVO) {
        return success(evalResultAnalysisService.createEvalResultAnalysis(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价结果分析")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-analysis:update')")
    public CommonResult<Boolean> updateEvalResultAnalysis(@Valid @RequestBody EvalResultAnalysisSaveReqVO updateReqVO) {
        evalResultAnalysisService.updateEvalResultAnalysis(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价结果分析")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-analysis:delete')")
    public CommonResult<Boolean> deleteEvalResultAnalysis(@RequestParam("id") Long id) {
        evalResultAnalysisService.deleteEvalResultAnalysis(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价结果分析")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-analysis:query')")
    public CommonResult<EvalResultAnalysisRespVO> getEvalResultAnalysis(@RequestParam("id") Long id) {
        EvalResultAnalysisDO evalResultAnalysis = evalResultAnalysisService.getEvalResultAnalysis(id);
        return success(BeanUtils.toBean(evalResultAnalysis, EvalResultAnalysisRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价结果分析分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-analysis:query')")
    public CommonResult<PageResult<EvalResultAnalysisRespVO>> getEvalResultAnalysisPage(@Valid EvalResultAnalysisPageReqVO pageReqVO) {
        PageResult<EvalResultAnalysisDO> pageResult = evalResultAnalysisService.getEvalResultAnalysisPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalResultAnalysisRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价结果分析 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-analysis:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalResultAnalysisExcel(@Valid EvalResultAnalysisPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalResultAnalysisDO> list = evalResultAnalysisService.getEvalResultAnalysisPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价结果分析.xls", "数据", EvalResultAnalysisRespVO.class,
                        BeanUtils.toBean(list, EvalResultAnalysisRespVO.class));
    }

}