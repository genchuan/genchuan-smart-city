package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo.RuleItemPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo.RuleItemRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.ruleitem.vo.RuleItemSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.ruleitem.RuleItemDO;
import cn.iocoder.yudao.module.evaluate.service.ruleitem.RuleItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 规则项")
@RestController
@RequestMapping("/evaluate/rule-item")
@Validated
public class RuleItemController {

    @Resource
    private RuleItemService ruleItemService;

    @PostMapping("/create")
    @Operation(summary = "创建规则项")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-item:create')")
    public CommonResult<Long> createRuleItem(@Valid @RequestBody RuleItemSaveReqVO createReqVO) {
        return success(ruleItemService.createRuleItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新规则项")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-item:update')")
    public CommonResult<Boolean> updateRuleItem(@Valid @RequestBody RuleItemSaveReqVO updateReqVO) {
        ruleItemService.updateRuleItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除规则项")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:rule-item:delete')")
    public CommonResult<Boolean> deleteRuleItem(@RequestParam("id") Long id) {
        ruleItemService.deleteRuleItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得规则项")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-item:query')")
    public CommonResult<RuleItemRespVO> getRuleItem(@RequestParam("id") Long id) {
        RuleItemDO ruleItem = ruleItemService.getRuleItem(id);
        return success(BeanUtils.toBean(ruleItem, RuleItemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得规则项分页")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-item:query')")
    public CommonResult<PageResult<RuleItemRespVO>> getRuleItemPage(@Valid RuleItemPageReqVO pageReqVO) {
        PageResult<RuleItemDO> pageResult = ruleItemService.getRuleItemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RuleItemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出规则项 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-item:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRuleItemExcel(@Valid RuleItemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RuleItemDO> list = ruleItemService.getRuleItemPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "规则项.xls", "数据", RuleItemRespVO.class,
                        BeanUtils.toBean(list, RuleItemRespVO.class));
    }

}