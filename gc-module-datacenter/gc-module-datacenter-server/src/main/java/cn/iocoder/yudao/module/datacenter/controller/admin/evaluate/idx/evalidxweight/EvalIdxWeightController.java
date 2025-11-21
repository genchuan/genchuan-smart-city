package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo.EvalIdxWeightPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo.EvalIdxWeightRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxweight.vo.EvalIdxWeightSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxweight.EvalIdxWeightDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxweight.EvalIdxWeightService;

@Tag(name = "管理后台 - 指标权重管理")
@RestController
@RequestMapping("/datacenter/eval-idx-weight")
@Validated
public class EvalIdxWeightController {

    @Resource
    private EvalIdxWeightService evalIdxWeightService;

    @PostMapping("/create")
    @Operation(summary = "创建指标权重管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-weight:create')")
    public CommonResult<Long> createEvalIdxWeight(@Valid @RequestBody EvalIdxWeightSaveReqVO createReqVO) {
        return success(evalIdxWeightService.createEvalIdxWeight(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标权重管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-weight:update')")
    public CommonResult<Boolean> updateEvalIdxWeight(@Valid @RequestBody EvalIdxWeightSaveReqVO updateReqVO) {
        evalIdxWeightService.updateEvalIdxWeight(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标权重管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-weight:delete')")
    public CommonResult<Boolean> deleteEvalIdxWeight(@RequestParam("id") Long id) {
        evalIdxWeightService.deleteEvalIdxWeight(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标权重管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-weight:query')")
    public CommonResult<EvalIdxWeightRespVO> getEvalIdxWeight(@RequestParam("id") Long id) {
        EvalIdxWeightDO evalIdxWeight = evalIdxWeightService.getEvalIdxWeight(id);
        return success(BeanUtils.toBean(evalIdxWeight, EvalIdxWeightRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标权重管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-weight:query')")
    public CommonResult<PageResult<EvalIdxWeightRespVO>> getEvalIdxWeightPage(@Valid EvalIdxWeightPageReqVO pageReqVO) {
        PageResult<EvalIdxWeightDO> pageResult = evalIdxWeightService.getEvalIdxWeightPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalIdxWeightRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标权重管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-weight:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalIdxWeightExcel(@Valid EvalIdxWeightPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalIdxWeightDO> list = evalIdxWeightService.getEvalIdxWeightPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标权重管理.xls", "数据", EvalIdxWeightRespVO.class,
                        BeanUtils.toBean(list, EvalIdxWeightRespVO.class));
    }

}