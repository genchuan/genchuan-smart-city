package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo.EvalRuleItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo.EvalRuleItemRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalruleitem.vo.EvalRuleItemSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalruleitem.EvalRuleItemDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.rule.evalruleitem.EvalRuleItemService;

@Tag(name = "管理后台 - 规则项管理")
@RestController
@RequestMapping("/datacenter/eval-rule-item")
@Validated
public class EvalRuleItemController {

    @Resource
    private EvalRuleItemService evalRuleItemService;

    @PostMapping("/create")
    @Operation(summary = "创建规则项管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-item:create')")
    public CommonResult<Long> createEvalRuleItem(@Valid @RequestBody EvalRuleItemSaveReqVO createReqVO) {
        return success(evalRuleItemService.createEvalRuleItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新规则项管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-item:update')")
    public CommonResult<Boolean> updateEvalRuleItem(@Valid @RequestBody EvalRuleItemSaveReqVO updateReqVO) {
        evalRuleItemService.updateEvalRuleItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除规则项管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-item:delete')")
    public CommonResult<Boolean> deleteEvalRuleItem(@RequestParam("id") Long id) {
        evalRuleItemService.deleteEvalRuleItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得规则项管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-item:query')")
    public CommonResult<EvalRuleItemRespVO> getEvalRuleItem(@RequestParam("id") Long id) {
        EvalRuleItemDO evalRuleItem = evalRuleItemService.getEvalRuleItem(id);
        return success(BeanUtils.toBean(evalRuleItem, EvalRuleItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得规则项管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-item:query')")
    public CommonResult<PageResult<EvalRuleItemRespVO>> getEvalRuleItemPage(@Valid EvalRuleItemPageReqVO pageReqVO) {
        PageResult<EvalRuleItemDO> pageResult = evalRuleItemService.getEvalRuleItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalRuleItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出规则项管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-rule-item:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalRuleItemExcel(@Valid EvalRuleItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalRuleItemDO> list = evalRuleItemService.getEvalRuleItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "规则项管理.xls", "数据", EvalRuleItemRespVO.class,
                        BeanUtils.toBean(list, EvalRuleItemRespVO.class));
    }

}