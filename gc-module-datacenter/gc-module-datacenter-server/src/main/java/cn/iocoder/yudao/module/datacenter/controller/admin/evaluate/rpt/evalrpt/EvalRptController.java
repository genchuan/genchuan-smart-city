package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo.EvalRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo.EvalRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrpt.vo.EvalRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrpt.EvalRptDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrpt.EvalRptService;

@Tag(name = "管理后台 - 评价报告")
@RestController
@RequestMapping("/datacenter/eval-rpt")
@Validated
public class EvalRptController {

    @Resource
    private EvalRptService evalRptService;

    @PostMapping("/create")
    @Operation(summary = "创建评价报告")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt:create')")
    public CommonResult<Long> createEvalRpt(@Valid @RequestBody EvalRptSaveReqVO createReqVO) {
        return success(evalRptService.createEvalRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价报告")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt:update')")
    public CommonResult<Boolean> updateEvalRpt(@Valid @RequestBody EvalRptSaveReqVO updateReqVO) {
        evalRptService.updateEvalRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价报告")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt:delete')")
    public CommonResult<Boolean> deleteEvalRpt(@RequestParam("id") Long id) {
        evalRptService.deleteEvalRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价报告")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt:query')")
    public CommonResult<EvalRptRespVO> getEvalRpt(@RequestParam("id") Long id) {
        EvalRptDO evalRpt = evalRptService.getEvalRpt(id);
        return success(BeanUtils.toBean(evalRpt, EvalRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价报告分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt:query')")
    public CommonResult<PageResult<EvalRptRespVO>> getEvalRptPage(@Valid EvalRptPageReqVO pageReqVO) {
        PageResult<EvalRptDO> pageResult = evalRptService.getEvalRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价报告 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalRptExcel(@Valid EvalRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalRptDO> list = evalRptService.getEvalRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价报告.xls", "数据", EvalRptRespVO.class,
                        BeanUtils.toBean(list, EvalRptRespVO.class));
    }

}