package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo.EvalResultRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo.EvalResultRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultrpt.vo.EvalResultRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultrpt.EvalResultRptDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultrpt.EvalResultRptService;

@Tag(name = "管理后台 - 结果报表管理")
@RestController
@RequestMapping("/datacenter/eval-result-rpt")
@Validated
public class EvalResultRptController {

    @Resource
    private EvalResultRptService evalResultRptService;

    @PostMapping("/create")
    @Operation(summary = "创建结果报表管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-rpt:create')")
    public CommonResult<Long> createEvalResultRpt(@Valid @RequestBody EvalResultRptSaveReqVO createReqVO) {
        return success(evalResultRptService.createEvalResultRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结果报表管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-rpt:update')")
    public CommonResult<Boolean> updateEvalResultRpt(@Valid @RequestBody EvalResultRptSaveReqVO updateReqVO) {
        evalResultRptService.updateEvalResultRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结果报表管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-rpt:delete')")
    public CommonResult<Boolean> deleteEvalResultRpt(@RequestParam("id") Long id) {
        evalResultRptService.deleteEvalResultRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结果报表管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-rpt:query')")
    public CommonResult<EvalResultRptRespVO> getEvalResultRpt(@RequestParam("id") Long id) {
        EvalResultRptDO evalResultRpt = evalResultRptService.getEvalResultRpt(id);
        return success(BeanUtils.toBean(evalResultRpt, EvalResultRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结果报表管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-rpt:query')")
    public CommonResult<PageResult<EvalResultRptRespVO>> getEvalResultRptPage(@Valid EvalResultRptPageReqVO pageReqVO) {
        PageResult<EvalResultRptDO> pageResult = evalResultRptService.getEvalResultRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalResultRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结果报表管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalResultRptExcel(@Valid EvalResultRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalResultRptDO> list = evalResultRptService.getEvalResultRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结果报表管理.xls", "数据", EvalResultRptRespVO.class,
                        BeanUtils.toBean(list, EvalResultRptRespVO.class));
    }

}