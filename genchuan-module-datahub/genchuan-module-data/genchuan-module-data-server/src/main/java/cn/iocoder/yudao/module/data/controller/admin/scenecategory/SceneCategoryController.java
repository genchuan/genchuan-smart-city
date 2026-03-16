package cn.iocoder.yudao.module.data.controller.admin.scenecategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategoryPageReqVO;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategoryRespVO;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategorySaveReqVO;
import cn.iocoder.yudao.module.data.controller.admin.scenecategory.vo.SceneCategorySimpleTreeRespVO;
import cn.iocoder.yudao.module.data.dal.dataobject.scenecategory.SceneCategoryDO;
import cn.iocoder.yudao.module.data.service.scenecategory.SceneCategoryService;
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

@Tag(name = "管理后台 - 应用场景分类")
@RestController
@RequestMapping("/data/scene-category")
@Validated
public class SceneCategoryController {

    @Resource
    private SceneCategoryService sceneCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建应用场景分类")
    @PreAuthorize("@ss.hasPermission('data:scene-category:create')")
    public CommonResult<Long> createSceneCategory(@Valid @RequestBody SceneCategorySaveReqVO createReqVO) {
        return success(sceneCategoryService.createSceneCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应用场景分类")
    @PreAuthorize("@ss.hasPermission('data:scene-category:update')")
    public CommonResult<Boolean> updateSceneCategory(@Valid @RequestBody SceneCategorySaveReqVO updateReqVO) {
        sceneCategoryService.updateSceneCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应用场景分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('data:scene-category:delete')")
    public CommonResult<Boolean> deleteSceneCategory(@RequestParam("id") Long id) {
        sceneCategoryService.deleteSceneCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应用场景分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('data:scene-category:query')")
    public CommonResult<SceneCategoryRespVO> getSceneCategory(@RequestParam("id") Long id) {
        SceneCategoryDO sceneCategory = sceneCategoryService.getSceneCategory(id);
        return success(BeanUtils.toBean(sceneCategory, SceneCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应用场景分类分页")
    @PreAuthorize("@ss.hasPermission('data:scene-category:query')")
    public CommonResult<PageResult<SceneCategoryRespVO>> getSceneCategoryPage(@Valid SceneCategoryPageReqVO pageReqVO) {
        PageResult<SceneCategoryDO> pageResult = sceneCategoryService.getSceneCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SceneCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应用场景分类 Excel")
    @PreAuthorize("@ss.hasPermission('data:scene-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSceneCategoryExcel(@Valid SceneCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SceneCategoryDO> list = sceneCategoryService.getSceneCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应用场景分类.xls", "数据", SceneCategoryRespVO.class,
                        BeanUtils.toBean(list, SceneCategoryRespVO.class));
    }

    @DeleteMapping("/batch-delete")
    @Operation(summary = "批量删除应用场景分类")
    @PreAuthorize("@ss.hasPermission('data:scene-category:delete')")
    public CommonResult<Boolean> deleteSceneCategories(@RequestBody List<Long> ids) {
        sceneCategoryService.deleteSceneCategories(ids);
        return success(true);
    }

    @GetMapping("/tree")
    @Operation(summary = "获得应用场景分类树")
    @PreAuthorize("@ss.hasPermission('data:scene-category:query')")
    public CommonResult<List<SceneCategorySimpleTreeRespVO>> getSceneCategoryTree() {
        List<SceneCategorySimpleTreeRespVO> tree = sceneCategoryService.getSceneCategorySimpleTree();
        return success(tree);
    }

}