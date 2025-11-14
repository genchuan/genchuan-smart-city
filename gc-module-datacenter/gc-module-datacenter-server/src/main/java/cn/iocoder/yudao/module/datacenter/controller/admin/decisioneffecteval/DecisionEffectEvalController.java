package cn.iocoder.yudao.module.datacenter.controller.admin.decisioneffecteval;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.decisioneffecteval.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisioneffecteval.DecisionEffectEvalDO;
import cn.iocoder.yudao.module.datacenter.service.decisioneffecteval.DecisionEffectEvalService;

@Tag(name = "管理后台 - 决策效果评估")
@RestController
@RequestMapping("/datacenter/decision-effect-eval")
@Validated
public class DecisionEffectEvalController {

    @Resource
    private DecisionEffectEvalService decisionEffectEvalService;

    @PostMapping("/create")
    @Operation(summary = "创建决策效果评估")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-effect-eval:create')")
    public CommonResult<Long> createDecisionEffectEval(@Valid @RequestBody DecisionEffectEvalSaveReqVO createReqVO) {
        return success(decisionEffectEvalService.createDecisionEffectEval(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新决策效果评估")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-effect-eval:update')")
    public CommonResult<Boolean> updateDecisionEffectEval(@Valid @RequestBody DecisionEffectEvalSaveReqVO updateReqVO) {
        decisionEffectEvalService.updateDecisionEffectEval(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除决策效果评估")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:decision-effect-eval:delete')")
    public CommonResult<Boolean> deleteDecisionEffectEval(@RequestParam("id") Long id) {
        decisionEffectEvalService.deleteDecisionEffectEval(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得决策效果评估")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-effect-eval:query')")
    public CommonResult<DecisionEffectEvalRespVO> getDecisionEffectEval(@RequestParam("id") Long id) {
        DecisionEffectEvalDO decisionEffectEval = decisionEffectEvalService.getDecisionEffectEval(id);
        return success(BeanUtils.toBean(decisionEffectEval, DecisionEffectEvalRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得决策效果评估分页")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-effect-eval:query')")
    public CommonResult<PageResult<DecisionEffectEvalRespVO>> getDecisionEffectEvalPage(@Valid DecisionEffectEvalPageReqVO pageReqVO) {
        PageResult<DecisionEffectEvalDO> pageResult = decisionEffectEvalService.getDecisionEffectEvalPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DecisionEffectEvalRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出决策效果评估 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-effect-eval:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDecisionEffectEvalExcel(@Valid DecisionEffectEvalPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DecisionEffectEvalDO> list = decisionEffectEvalService.getDecisionEffectEvalPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "决策效果评估.xls", "数据", DecisionEffectEvalRespVO.class,
                        BeanUtils.toBean(list, DecisionEffectEvalRespVO.class));
    }

}