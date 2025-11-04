package cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo.EvalGridRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo.EvalGridRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.rpt.evalgridrpt.vo.EvalGridRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.rpt.evalgridrpt.EvalGridRptDO;
import cn.iocoder.yudao.module.datacenter.service.grid.rpt.evalgridrpt.EvalGridRptService;

@Tag(name = "管理后台 - 评价网格统计")
@RestController
@RequestMapping("/datacenter/eval-grid-rpt")
@Validated
public class EvalGridRptController {

    @Resource
    private EvalGridRptService evalGridRptService;

    @PostMapping("/create")
    @Operation(summary = "创建评价网格统计")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-rpt:create')")
    public CommonResult<Long> createEvalGridRpt(@Valid @RequestBody EvalGridRptSaveReqVO createReqVO) {
        return success(evalGridRptService.createEvalGridRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价网格统计")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-rpt:update')")
    public CommonResult<Boolean> updateEvalGridRpt(@Valid @RequestBody EvalGridRptSaveReqVO updateReqVO) {
        evalGridRptService.updateEvalGridRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价网格统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-rpt:delete')")
    public CommonResult<Boolean> deleteEvalGridRpt(@RequestParam("id") Long id) {
        evalGridRptService.deleteEvalGridRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价网格统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-rpt:query')")
    public CommonResult<EvalGridRptRespVO> getEvalGridRpt(@RequestParam("id") Long id) {
        EvalGridRptDO evalGridRpt = evalGridRptService.getEvalGridRpt(id);
        return success(BeanUtils.toBean(evalGridRpt, EvalGridRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价网格统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-rpt:query')")
    public CommonResult<PageResult<EvalGridRptRespVO>> getEvalGridRptPage(@Valid EvalGridRptPageReqVO pageReqVO) {
        PageResult<EvalGridRptDO> pageResult = evalGridRptService.getEvalGridRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalGridRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价网格统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalGridRptExcel(@Valid EvalGridRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalGridRptDO> list = evalGridRptService.getEvalGridRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价网格统计.xls", "数据", EvalGridRptRespVO.class,
                        BeanUtils.toBean(list, EvalGridRptRespVO.class));
    }

}