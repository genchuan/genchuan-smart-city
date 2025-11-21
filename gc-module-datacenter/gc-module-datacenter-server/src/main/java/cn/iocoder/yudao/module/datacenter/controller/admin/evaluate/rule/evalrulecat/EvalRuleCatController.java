package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo.EvalRuleCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo.EvalRuleCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo.EvalRuleCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalrulecat.EvalRuleCatDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalrulecat.EvalRuleCatService;

@Tag(name = "管理后台 - 规则分类管理")
@RestController
@RequestMapping("/datacenter/eval-rule-cat")
@Validated
public class EvalRuleCatController {

    @Resource
    private EvalRuleCatService evalRuleCatService;

    @PostMapping("/create")
    @Operation(summary = "创建规则分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-cat:create')")
    public CommonResult<Long> createEvalRuleCat(@Valid @RequestBody EvalRuleCatSaveReqVO createReqVO) {
        return success(evalRuleCatService.createEvalRuleCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新规则分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-cat:update')")
    public CommonResult<Boolean> updateEvalRuleCat(@Valid @RequestBody EvalRuleCatSaveReqVO updateReqVO) {
        evalRuleCatService.updateEvalRuleCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除规则分类管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-cat:delete')")
    public CommonResult<Boolean> deleteEvalRuleCat(@RequestParam("id") Long id) {
        evalRuleCatService.deleteEvalRuleCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得规则分类管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-cat:query')")
    public CommonResult<EvalRuleCatRespVO> getEvalRuleCat(@RequestParam("id") Long id) {
        EvalRuleCatDO evalRuleCat = evalRuleCatService.getEvalRuleCat(id);
        return success(BeanUtils.toBean(evalRuleCat, EvalRuleCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得规则分类管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-cat:query')")
    public CommonResult<PageResult<EvalRuleCatRespVO>> getEvalRuleCatPage(@Valid EvalRuleCatPageReqVO pageReqVO) {
        PageResult<EvalRuleCatDO> pageResult = evalRuleCatService.getEvalRuleCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalRuleCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出规则分类管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalRuleCatExcel(@Valid EvalRuleCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalRuleCatDO> list = evalRuleCatService.getEvalRuleCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "规则分类管理.xls", "数据", EvalRuleCatRespVO.class,
                        BeanUtils.toBean(list, EvalRuleCatRespVO.class));
    }

}