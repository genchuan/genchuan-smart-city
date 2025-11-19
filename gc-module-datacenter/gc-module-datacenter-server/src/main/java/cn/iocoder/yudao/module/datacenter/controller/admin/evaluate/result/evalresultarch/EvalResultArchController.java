package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo.EvalResultArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo.EvalResultArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultarch.vo.EvalResultArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultarch.EvalResultArchDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultarch.EvalResultArchService;

@Tag(name = "管理后台 - 结果存档管理")
@RestController
@RequestMapping("/datacenter/eval-result-arch")
@Validated
public class EvalResultArchController {

    @Resource
    private EvalResultArchService evalResultArchService;

    @PostMapping("/create")
    @Operation(summary = "创建结果存档管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-arch:create')")
    public CommonResult<Long> createEvalResultArch(@Valid @RequestBody EvalResultArchSaveReqVO createReqVO) {
        return success(evalResultArchService.createEvalResultArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结果存档管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-arch:update')")
    public CommonResult<Boolean> updateEvalResultArch(@Valid @RequestBody EvalResultArchSaveReqVO updateReqVO) {
        evalResultArchService.updateEvalResultArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结果存档管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-arch:delete')")
    public CommonResult<Boolean> deleteEvalResultArch(@RequestParam("id") Long id) {
        evalResultArchService.deleteEvalResultArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结果存档管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-arch:query')")
    public CommonResult<EvalResultArchRespVO> getEvalResultArch(@RequestParam("id") Long id) {
        EvalResultArchDO evalResultArch = evalResultArchService.getEvalResultArch(id);
        return success(BeanUtils.toBean(evalResultArch, EvalResultArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结果存档管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-arch:query')")
    public CommonResult<PageResult<EvalResultArchRespVO>> getEvalResultArchPage(@Valid EvalResultArchPageReqVO pageReqVO) {
        PageResult<EvalResultArchDO> pageResult = evalResultArchService.getEvalResultArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalResultArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结果存档管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalResultArchExcel(@Valid EvalResultArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalResultArchDO> list = evalResultArchService.getEvalResultArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结果存档管理.xls", "数据", EvalResultArchRespVO.class,
                        BeanUtils.toBean(list, EvalResultArchRespVO.class));
    }

}