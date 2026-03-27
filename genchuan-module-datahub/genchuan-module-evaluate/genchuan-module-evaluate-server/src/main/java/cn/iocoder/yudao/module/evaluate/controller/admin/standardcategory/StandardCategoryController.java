package cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.standardcategory.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.standardcategory.StandardCategoryDO;
import cn.iocoder.yudao.module.evaluate.service.standardcategory.StandardCategoryService;
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
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 标准分类")
@RestController
@RequestMapping("/evaluate/standard-category")
@Validated
public class StandardCategoryController {

    @Resource
    private StandardCategoryService standardCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建标准分类（含标准项批量创建）")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:create')")
    public CommonResult<Long> createStandardCategory(@Valid @RequestBody StandardCategorySaveReqVO createReqVO) {
        return success(standardCategoryService.createStandardCategoryWithItems(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新标准分类（含标准项批量更新）")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:update')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> updateStandardCategory(@Valid @RequestBody StandardCategorySaveReqVO updateReqVO) {
        standardCategoryService.updateStandardCategoryWithItems(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除标准分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:delete')")
    public CommonResult<Boolean> deleteStandardCategory(@RequestParam("id") Long id) {
        standardCategoryService.deleteStandardCategory(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除标准分类")
                @PreAuthorize("@ss.hasPermission('evaluate:standard-category:delete')")
    public CommonResult<Boolean> deleteStandardCategoryList(@RequestParam("ids") List<Long> ids) {
        standardCategoryService.deleteStandardCategoryListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得标准分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:query')")
    public CommonResult<StandardCategoryRespVO> getStandardCategory(@RequestParam("id") Long id) {
        StandardCategoryDO standardCategory = standardCategoryService.getStandardCategory(id);
        return success(BeanUtils.toBean(standardCategory, StandardCategoryRespVO.class));
    }

    @GetMapping("/get-with-items")
    @Operation(summary = "获得标准分类（包含关联的标准项列表）")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:query')")
    public CommonResult<StandardCategoryRespVO> getStandardCategoryWithItems(@RequestParam("id") Long id) {
        return success(standardCategoryService.getStandardCategoryWithItems(id));
    }

    @GetMapping("/statistics")
    @Operation(summary = "获取标准分类统计数据")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:query')")
    public CommonResult<StandardCategoryStatisticsVO> getStandardCategoryStatistics() {
        return success(standardCategoryService.getStandardCategoryStatistics());
    }

    @GetMapping("/page")
    @Operation(summary = "获得标准分类分页")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:query')")
    public CommonResult<PageResult<StandardCategoryRespVO>> getStandardCategoryPage(@Valid StandardCategoryPageReqVO pageReqVO) {
        return success(standardCategoryService.getStandardCategoryPageWithJoin(pageReqVO));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出标准分类 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStandardCategoryExcel(@Valid StandardCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StandardCategoryExportVO> list = standardCategoryService.getStandardCategoryExportListWithItems(pageReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "标准分类.xls", "数据", StandardCategoryExportVO.class, list);
    }

}