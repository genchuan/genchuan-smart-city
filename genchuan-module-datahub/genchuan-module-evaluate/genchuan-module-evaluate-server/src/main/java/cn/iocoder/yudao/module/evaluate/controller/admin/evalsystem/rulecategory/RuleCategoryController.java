package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.rulecategory.vo.RuleCategoryDetailVO;
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
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "评价体系管理 - 评价规则管理")
@RestController
@RequestMapping("/evaluate/rule-category")
@Validated
public class RuleCategoryController {

    @Resource
    private RuleCategoryService ruleCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建规则分类")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:create')")
    public CommonResult<Long> createRuleCategory(@Valid @RequestBody RuleCategorySaveReqVO createReqVO) {
        return success(ruleCategoryService.createRuleCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新规则分类")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:update')")
    public CommonResult<Boolean> updateRuleCategory(@Valid @RequestBody RuleCategorySaveReqVO updateReqVO) {
        ruleCategoryService.updateRuleCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除规则分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:delete')")
    public CommonResult<Boolean> deleteRuleCategory(@RequestParam("id") Long id) {
        ruleCategoryService.deleteRuleCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得规则分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:query')")
    public CommonResult<RuleCategoryRespVO> getRuleCategory(@RequestParam("id") Long id) {
        RuleCategoryDO ruleCategory = ruleCategoryService.getRuleCategory(id);
        return success(BeanUtils.toBean(ruleCategory, RuleCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得规则分类分页")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:query')")
    public CommonResult<PageResult<RuleCategoryRespVO>> getRuleCategoryPage(@Valid RuleCategoryPageReqVO pageReqVO) {
        PageResult<RuleCategoryDO> pageResult = ruleCategoryService.getRuleCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RuleCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出规则分类 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:rule-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRuleCategoryExcel(@Valid RuleCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RuleCategoryDO> list = ruleCategoryService.getRuleCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "规则分类.xls", "数据", RuleCategoryRespVO.class,
                        BeanUtils.toBean(list, RuleCategoryRespVO.class));
    }

    /**
     * 1️⃣ 评分&否决规则合并分页查询接口
     * URL: GET /evaluate/rule-merge/page
     * 参数: pageNo, pageSize, statusName, objectTypeName, systemId, indexItemId
     */
    @GetMapping("/pageList")
    @Operation(summary = "获得规则合并分页列表")
    @PreAuthorize("@ss.hasPermission('evaluate:rule:query')")
    public CommonResult<PageResult<RuleCategoryRespVO>> getMergePage(@Valid RuleCategoryPageReqVO pageReqVO) {
        // 调用 Service 层分页查询
        PageResult<RuleCategoryRespVO> pageResult = ruleCategoryService.getRuleCategoryPageList(pageReqVO);
        return CommonResult.success(pageResult);
    }

    /**
     * 2️⃣ 规则分类详情弹窗接口（钻取）
     * URL: GET /evaluate/rule-merge/category-detail
     * 参数: categoryId (规则分类ID)
     */
    @GetMapping("/category-detail")
    @Operation(summary = "获取规则分类详情（钻取弹窗）")
    @Parameter(name = "categoryId", description = "规则分类ID", required = true, example = "rc001-1111-1111-1111-111111111111")
    @PreAuthorize("@ss.hasPermission('evaluate:rule:query')")
    public CommonResult<RuleCategoryDetailVO> getCategoryDetail(@RequestParam("categoryId") String categoryId) {
        // TODO 调用业务逻辑，查询规则分类基本信息 + 关联规则项列表
        // 示例：RuleCategoryDetailVO detailVO = ruleMergeService.getCategoryDetail(categoryId);
        // return CommonResult.success(detailVO);
        return CommonResult.success(null); // 暂未实现，返回空
    }

    @Operation(summary = "规则分类分页查询（支持全部/启用/停用状态）*")
    @GetMapping("/allpage")
    public CommonResult<PageResult<RuleCategoryRespVO>> getRuleCategoryAllPage(
            @Validated RuleCategoryPageReqVO reqVO) {
        // 调用Service层执行联表分页查询
        PageResult<RuleCategoryRespVO> pageResult = ruleCategoryService.getRuleCategoryAllPage(reqVO);
        // 返回统一封装的响应结果
        return CommonResult.success(pageResult);
    }
}