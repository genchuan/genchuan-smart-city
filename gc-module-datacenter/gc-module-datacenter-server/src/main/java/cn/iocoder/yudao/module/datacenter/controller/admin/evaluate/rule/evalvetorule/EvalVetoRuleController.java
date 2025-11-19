package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo.EvalVetoRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo.EvalVetoRuleRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalvetorule.vo.EvalVetoRuleSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalvetorule.EvalVetoRuleDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalvetorule.EvalVetoRuleService;

@Tag(name = "管理后台 - 否决项规则管理")
@RestController
@RequestMapping("/datacenter/eval-veto-rule")
@Validated
public class EvalVetoRuleController {

    @Resource
    private EvalVetoRuleService evalVetoRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建否决项规则管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-veto-rule:create')")
    public CommonResult<Long> createEvalVetoRule(@Valid @RequestBody EvalVetoRuleSaveReqVO createReqVO) {
        return success(evalVetoRuleService.createEvalVetoRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新否决项规则管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-veto-rule:update')")
    public CommonResult<Boolean> updateEvalVetoRule(@Valid @RequestBody EvalVetoRuleSaveReqVO updateReqVO) {
        evalVetoRuleService.updateEvalVetoRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除否决项规则管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-veto-rule:delete')")
    public CommonResult<Boolean> deleteEvalVetoRule(@RequestParam("id") Long id) {
        evalVetoRuleService.deleteEvalVetoRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得否决项规则管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-veto-rule:query')")
    public CommonResult<EvalVetoRuleRespVO> getEvalVetoRule(@RequestParam("id") Long id) {
        EvalVetoRuleDO evalVetoRule = evalVetoRuleService.getEvalVetoRule(id);
        return success(BeanUtils.toBean(evalVetoRule, EvalVetoRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得否决项规则管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-veto-rule:query')")
    public CommonResult<PageResult<EvalVetoRuleRespVO>> getEvalVetoRulePage(@Valid EvalVetoRulePageReqVO pageReqVO) {
        PageResult<EvalVetoRuleDO> pageResult = evalVetoRuleService.getEvalVetoRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalVetoRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出否决项规则管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-veto-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalVetoRuleExcel(@Valid EvalVetoRulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalVetoRuleDO> list = evalVetoRuleService.getEvalVetoRulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "否决项规则管理.xls", "数据", EvalVetoRuleRespVO.class,
                        BeanUtils.toBean(list, EvalVetoRuleRespVO.class));
    }

}