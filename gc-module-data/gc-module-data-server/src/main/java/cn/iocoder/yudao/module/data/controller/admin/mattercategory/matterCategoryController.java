package cn.iocoder.yudao.module.data.controller.admin.mattercategory;

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

import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.*;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.matterCategoryDO;
import cn.iocoder.yudao.module.data.service.mattercategory.matterCategoryService;

@Tag(name = "管理后台 - 管理事项分类")
@RestController
@RequestMapping("/data/matter-category")
@Validated
public class matterCategoryController {

    @Resource
    private matterCategoryService matterCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项分类")
    @PreAuthorize("@ss.hasPermission('data:matter-category:create')")
    public CommonResult<Long> creatematterCategory(@Valid @RequestBody matterCategorySaveReqVO createReqVO) {
        return success(matterCategoryService.creatematterCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项分类")
    @PreAuthorize("@ss.hasPermission('data:matter-category:update')")
    public CommonResult<Boolean> updatematterCategory(@Valid @RequestBody matterCategorySaveReqVO updateReqVO) {
        matterCategoryService.updatematterCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:matter-category:delete')")
    public CommonResult<Boolean> deletematterCategory(@RequestParam("id") Long id) {
        matterCategoryService.deletematterCategory(id);
        return success(true);
    }

    @DeleteMapping("/batch-delete")
    @Operation(summary = "批量删除管理事项分类")
    @PreAuthorize("@ss.hasPermission('data:matter-category:delete')")
    public CommonResult<Boolean> deletematterCategories(@RequestBody List<Long> ids) {
        matterCategoryService.deletematterCategories(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:matter-category:query')")
    public CommonResult<matterCategoryRespVO> getmatterCategory(@RequestParam("id") Long id) {
        matterCategoryDO matterCategory = matterCategoryService.getmatterCategory(id);
        return success(BeanUtils.toBean(matterCategory, matterCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项分类分页")
    @PreAuthorize("@ss.hasPermission('data:matter-category:query')")
    public CommonResult<PageResult<matterCategoryRespVO>> getmatterCategoryPage(@Valid matterCategoryPageReqVO pageReqVO) {
        PageResult<matterCategoryDO> pageResult = matterCategoryService.getmatterCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, matterCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项分类 Excel")
    @PreAuthorize("@ss.hasPermission('data:matter-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportmatterCategoryExcel(@Valid matterCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<matterCategoryDO> list = matterCategoryService.getmatterCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项分类.xls", "数据", matterCategoryRespVO.class,
                        BeanUtils.toBean(list, matterCategoryRespVO.class));
    }

    @GetMapping("/tree")
    @Operation(summary = "获得管理事项分类简化树")
    @PreAuthorize("@ss.hasPermission('data:matter-category:query')")
    public CommonResult<List<matterCategorySimpleTreeRespVO>> getmatterCategoryTree() {
        List<matterCategorySimpleTreeRespVO> tree = matterCategoryService.getmatterCategorySimpleTree();
        return success(tree);
    }

}