package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.indexcategory.vo.IndexCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.indexcategory.IndexCategoryDO;
import cn.iocoder.yudao.module.evaluate.service.indexcategory.IndexCategoryService;
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

@Tag(name = "管理后台 - 指标分类")
@RestController
@RequestMapping("/evaluate/index-category")
@Validated
public class IndexCategoryController {

    @Resource
    private IndexCategoryService indexCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建指标分类")
    @PreAuthorize("@ss.hasPermission('evaluate:index-category:create')")
    public CommonResult<Long> createIndexCategory(@Valid @RequestBody IndexCategorySaveReqVO createReqVO) {
        return success(indexCategoryService.createIndexCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标分类")
    @PreAuthorize("@ss.hasPermission('evaluate:index-category:update')")
    public CommonResult<Boolean> updateIndexCategory(@Valid @RequestBody IndexCategorySaveReqVO updateReqVO) {
        indexCategoryService.updateIndexCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:index-category:delete')")
    public CommonResult<Boolean> deleteIndexCategory(@RequestParam("id") Long id) {
        indexCategoryService.deleteIndexCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:index-category:query')")
    public CommonResult<IndexCategoryRespVO> getIndexCategory(@RequestParam("id") Long id) {
        IndexCategoryDO indexCategory = indexCategoryService.getIndexCategory(id);
        return success(BeanUtils.toBean(indexCategory, IndexCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标分类分页")
    @PreAuthorize("@ss.hasPermission('evaluate:index-category:query')")
    public CommonResult<PageResult<IndexCategoryRespVO>> getIndexCategoryPage(@Valid IndexCategoryPageReqVO pageReqVO) {
        PageResult<IndexCategoryDO> pageResult = indexCategoryService.getIndexCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IndexCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标分类 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:index-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIndexCategoryExcel(@Valid IndexCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IndexCategoryDO> list = indexCategoryService.getIndexCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标分类.xls", "数据", IndexCategoryRespVO.class,
                        BeanUtils.toBean(list, IndexCategoryRespVO.class));
    }

}