package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo.EvalRptArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo.EvalRptArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rpt.evalrptarch.vo.EvalRptArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rpt.evalrptarch.EvalRptArchDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.rpt.evalrptarch.EvalRptArchService;

@Tag(name = "管理后台 - 报告归档导出")
@RestController
@RequestMapping("/datacenter/eval-rpt-arch")
@Validated
public class EvalRptArchController {

    @Resource
    private EvalRptArchService evalRptArchService;

    @PostMapping("/create")
    @Operation(summary = "创建报告归档导出")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-arch:create')")
    public CommonResult<Long> createEvalRptArch(@Valid @RequestBody EvalRptArchSaveReqVO createReqVO) {
        return success(evalRptArchService.createEvalRptArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报告归档导出")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-arch:update')")
    public CommonResult<Boolean> updateEvalRptArch(@Valid @RequestBody EvalRptArchSaveReqVO updateReqVO) {
        evalRptArchService.updateEvalRptArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报告归档导出")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-arch:delete')")
    public CommonResult<Boolean> deleteEvalRptArch(@RequestParam("id") Long id) {
        evalRptArchService.deleteEvalRptArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报告归档导出")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-arch:query')")
    public CommonResult<EvalRptArchRespVO> getEvalRptArch(@RequestParam("id") Long id) {
        EvalRptArchDO evalRptArch = evalRptArchService.getEvalRptArch(id);
        return success(BeanUtils.toBean(evalRptArch, EvalRptArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报告归档导出分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-arch:query')")
    public CommonResult<PageResult<EvalRptArchRespVO>> getEvalRptArchPage(@Valid EvalRptArchPageReqVO pageReqVO) {
        PageResult<EvalRptArchDO> pageResult = evalRptArchService.getEvalRptArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalRptArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报告归档导出 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rpt-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalRptArchExcel(@Valid EvalRptArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalRptArchDO> list = evalRptArchService.getEvalRptArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "报告归档导出.xls", "数据", EvalRptArchRespVO.class,
                        BeanUtils.toBean(list, EvalRptArchRespVO.class));
    }

}