package cn.iocoder.yudao.module.data.controller.admin.mattercategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategoryRespVO;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.mattercategory.vo.MatterCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.mattercategory.MatterCategoryDO;
import cn.iocoder.yudao.module.data.service.mattercategory.MatterCategoryService;
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

@Tag(name = "管理后台 - 管理事项分类")
@RestController
@RequestMapping("/data/matter-category")
@Validated
public class MatterCategoryController {

    @Resource
    private MatterCategoryService matterCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项分类")
    @PreAuthorize("@ss.hasPermission('data:matter-category:create')")
    public CommonResult<Long> creatematterCategory(@Valid @RequestBody MatterCategorySaveReqVO createReqVO) {
        return success(matterCategoryService.creatematterCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项分类")
    @PreAuthorize("@ss.hasPermission('data:matter-category:update')")
    public CommonResult<Boolean> updatematterCategory(@Valid @RequestBody MatterCategorySaveReqVO updateReqVO) {
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
    public CommonResult<MatterCategoryRespVO> getmatterCategory(@RequestParam("id") Long id) {
        MatterCategoryDO matterCategory = matterCategoryService.getmatterCategory(id);
        return success(BeanUtils.toBean(matterCategory, MatterCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项分类分页")
    @PreAuthorize("@ss.hasPermission('data:matter-category:query')")
    public CommonResult<PageResult<MatterCategoryRespVO>> getmatterCategoryPage(@Valid MatterCategoryPageReqVO pageReqVO) {
        PageResult<MatterCategoryDO> pageResult = matterCategoryService.getmatterCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MatterCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项分类 Excel")
    @PreAuthorize("@ss.hasPermission('data:matter-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportmatterCategoryExcel(@Valid MatterCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MatterCategoryDO> list = matterCategoryService.getmatterCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项分类.xls", "数据", MatterCategoryRespVO.class,
                        BeanUtils.toBean(list, MatterCategoryRespVO.class));
    }

    @GetMapping("/tree")
    @Operation(summary = "获得管理事项分类简化树")
    @PreAuthorize("@ss.hasPermission('data:matter-category:query')")
    public CommonResult<List<MatterCategorySimpleTreeRespVO>> getmatterCategoryTree() {
        List<MatterCategorySimpleTreeRespVO> tree = matterCategoryService.getmatterCategorySimpleTree();
        return success(tree);
    }

}