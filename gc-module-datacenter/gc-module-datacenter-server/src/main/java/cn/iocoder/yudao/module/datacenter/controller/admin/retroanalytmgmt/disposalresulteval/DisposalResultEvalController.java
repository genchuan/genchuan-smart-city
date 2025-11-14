package cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.DisposalResultEvalPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.DisposalResultEvalRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.DisposalResultEvalSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.disposalresulteval.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.disposalresulteval.DisposalResultEvalDO;
import cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.disposalresulteval.DisposalResultEvalService;

@Tag(name = "管理后台 - 处置结果评估")
@RestController
@RequestMapping("/datacenter/disposal-result-eval")
@Validated
public class DisposalResultEvalController {

    @Resource
    private DisposalResultEvalService disposalResultEvalService;

    @PostMapping("/create")
    @Operation(summary = "创建处置结果评估")
    @PreAuthorize("@ss.hasPermission('datacenter:disposal-result-eval:create')")
    public CommonResult<Long> createDisposalResultEval(@Valid @RequestBody DisposalResultEvalSaveReqVO createReqVO) {
        return success(disposalResultEvalService.createDisposalResultEval(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新处置结果评估")
    @PreAuthorize("@ss.hasPermission('datacenter:disposal-result-eval:update')")
    public CommonResult<Boolean> updateDisposalResultEval(@Valid @RequestBody DisposalResultEvalSaveReqVO updateReqVO) {
        disposalResultEvalService.updateDisposalResultEval(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除处置结果评估")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:disposal-result-eval:delete')")
    public CommonResult<Boolean> deleteDisposalResultEval(@RequestParam("id") Long id) {
        disposalResultEvalService.deleteDisposalResultEval(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得处置结果评估")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:disposal-result-eval:query')")
    public CommonResult<DisposalResultEvalRespVO> getDisposalResultEval(@RequestParam("id") Long id) {
        DisposalResultEvalDO disposalResultEval = disposalResultEvalService.getDisposalResultEval(id);
        return success(BeanUtils.toBean(disposalResultEval, DisposalResultEvalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得处置结果评估分页")
    @PreAuthorize("@ss.hasPermission('datacenter:disposal-result-eval:query')")
    public CommonResult<PageResult<DisposalResultEvalRespVO>> getDisposalResultEvalPage(@Valid DisposalResultEvalPageReqVO pageReqVO) {
        PageResult<DisposalResultEvalDO> pageResult = disposalResultEvalService.getDisposalResultEvalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DisposalResultEvalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出处置结果评估 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:disposal-result-eval:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDisposalResultEvalExcel(@Valid DisposalResultEvalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DisposalResultEvalDO> list = disposalResultEvalService.getDisposalResultEvalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "处置结果评估.xls", "数据", DisposalResultEvalRespVO.class,
                        BeanUtils.toBean(list, DisposalResultEvalRespVO.class));
    }

}