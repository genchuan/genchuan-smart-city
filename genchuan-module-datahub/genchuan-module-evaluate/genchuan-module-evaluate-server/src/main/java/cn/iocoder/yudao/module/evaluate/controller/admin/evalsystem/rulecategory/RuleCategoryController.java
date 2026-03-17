package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.rulecategory.RuleCategoryDO;
import cn.iocoder.yudao.module.evaluate.service.rulecategory.RuleCategoryService;
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
import java.util.*;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 规则分类管理")
@RestController
@RequestMapping("/evaluate/rule-category")
@Validated
public class RuleCategoryController {

    @Resource
    private RuleCategoryService ruleCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建规则分类管理")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:create')")
    public CommonResult<Long> createRuleCategory(@Valid @RequestBody RuleCategorySaveReqVO createReqVO) {
        return success(ruleCategoryService.createRuleCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新规则分类管理")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:update')")
    public CommonResult<Boolean> updateRuleCategory(@Valid @RequestBody RuleCategorySaveReqVO updateReqVO) {
        ruleCategoryService.updateRuleCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除规则分类管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:delete')")
    public CommonResult<Boolean> deleteRuleCategory(@RequestParam("id") Long id) {
        ruleCategoryService.deleteRuleCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得规则分类管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:query')")
    public CommonResult<RuleCategoryRespVO> getRuleCategory(@RequestParam("id") Long id) {
        RuleCategoryDO ruleCategory = ruleCategoryService.getRuleCategory(id);
        return success(BeanUtils.toBean(ruleCategory, RuleCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得规则分类管理分页")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:query')")
    public CommonResult<PageResult<RuleCategoryRespVO>> getRuleCategoryPage(@Valid RuleCategoryPageReqVO pageReqVO) {
        PageResult<RuleCategoryDO> pageResult = ruleCategoryService.getRuleCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RuleCategoryRespVO.class));
    }

    @GetMapping("/allpage")
    @Operation(summary = "获得规则分类分页（全部/启用/停用）")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:query')")
    public CommonResult<PageResult<RuleCategoryRespVO>> getRuleCategoryJoinPage(@Valid RuleCategoryPageReqVO pageReqVO) {
        PageResult<RuleCategoryRespVO> pageResult = ruleCategoryService.getRuleCategoryJoinPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出规则分类管理 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRuleCategoryExcel(@Valid RuleCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RuleCategoryDO> list = ruleCategoryService.getRuleCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "规则分类管理.xls", "数据", RuleCategoryRespVO.class,
                        BeanUtils.toBean(list, RuleCategoryRespVO.class));
    }

}