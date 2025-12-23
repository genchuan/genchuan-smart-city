package cn.iocoder.yudao.module.datacenter.controller.admin.appscenecategory;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.appscenecategory.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.appscenecategory.AppSceneCategoryDO;
import cn.iocoder.yudao.module.datacenter.service.appscenecategory.AppSceneCategoryService;

@Tag(name = "管理后台 - 应用场景分类配置")
@RestController
@RequestMapping("/datacenter/app-scene-category")
@Validated
public class AppSceneCategoryController {

    @Resource
    private AppSceneCategoryService appSceneCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建应用场景分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:app-scene-category:create')")
    public CommonResult<Long> createAppSceneCategory(@Valid @RequestBody AppSceneCategorySaveReqVO createReqVO) {
        return success(appSceneCategoryService.createAppSceneCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应用场景分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:app-scene-category:update')")
    public CommonResult<Boolean> updateAppSceneCategory(@Valid @RequestBody AppSceneCategorySaveReqVO updateReqVO) {
        appSceneCategoryService.updateAppSceneCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应用场景分类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:app-scene-category:delete')")
    public CommonResult<Boolean> deleteAppSceneCategory(@RequestParam("id") Long id) {
        appSceneCategoryService.deleteAppSceneCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应用场景分类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:app-scene-category:query')")
    public CommonResult<AppSceneCategoryRespVO> getAppSceneCategory(@RequestParam("id") Long id) {
        AppSceneCategoryDO appSceneCategory = appSceneCategoryService.getAppSceneCategory(id);
        return success(BeanUtils.toBean(appSceneCategory, AppSceneCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应用场景分类配置分页")
//    @PreAuthorize("@ss.hasPermission('datacenter:app-scene-category:query')")
    public CommonResult<PageResult<AppSceneCategoryRespVO>> getAppSceneCategoryPage(@Valid AppSceneCategoryPageReqVO pageReqVO) {
        PageResult<AppSceneCategoryDO> pageResult = appSceneCategoryService.getAppSceneCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AppSceneCategoryRespVO.class));
    }

    @GetMapping("/tree")
    @Operation(summary = "获得应用场景分类配置树形结构")
//    @PreAuthorize("@ss.hasPermission('datacenter:app-scene-category:query')")
    public CommonResult<List<AppSceneCategoryTreeRespVO>> getAppSceneCategoryTree() {
        List<AppSceneCategoryDO> tree = appSceneCategoryService.getAppSceneCategoryTree();
        return success(BeanUtils.toBean(tree, AppSceneCategoryTreeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应用场景分类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:app-scene-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAppSceneCategoryExcel(@Valid AppSceneCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AppSceneCategoryDO> list = appSceneCategoryService.getAppSceneCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应用场景分类配置.xls", "数据", AppSceneCategoryRespVO.class,
                        BeanUtils.toBean(list, AppSceneCategoryRespVO.class));
    }

}