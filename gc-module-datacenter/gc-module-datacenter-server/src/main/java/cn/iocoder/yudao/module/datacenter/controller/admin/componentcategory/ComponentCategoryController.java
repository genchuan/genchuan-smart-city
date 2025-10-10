package cn.iocoder.yudao.module.datacenter.controller.admin.componentcategory;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.componentcategory.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.componentcategory.ComponentCategoryDO;
import cn.iocoder.yudao.module.datacenter.service.componentcategory.ComponentCategoryService;

@Tag(name = "管理后台 - 监测部件分类")
@RestController
@RequestMapping("/datacenter/component-category")
@Validated
public class ComponentCategoryController {

    @Resource
    private ComponentCategoryService componentCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件分类")
    @PreAuthorize("@ss.hasPermission('datacenter:component-category:create')")
    public CommonResult<Long> createComponentCategory(@Valid @RequestBody ComponentCategorySaveReqVO createReqVO) {
        return success(componentCategoryService.createComponentCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件分类")
    @PreAuthorize("@ss.hasPermission('datacenter:component-category:update')")
    public CommonResult<Boolean> updateComponentCategory(@Valid @RequestBody ComponentCategorySaveReqVO updateReqVO) {
        componentCategoryService.updateComponentCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:component-category:delete')")
    public CommonResult<Boolean> deleteComponentCategory(@RequestParam("id") Long id) {
        componentCategoryService.deleteComponentCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:component-category:query')")
    public CommonResult<ComponentCategoryRespVO> getComponentCategory(@RequestParam("id") Long id) {
        ComponentCategoryDO componentCategory = componentCategoryService.getComponentCategory(id);
        return success(BeanUtils.toBean(componentCategory, ComponentCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件分类分页")
    @PreAuthorize("@ss.hasPermission('datacenter:component-category:query')")
    public CommonResult<PageResult<ComponentCategoryRespVO>> getComponentCategoryPage(@Valid ComponentCategoryPageReqVO pageReqVO) {
        PageResult<ComponentCategoryDO> pageResult = componentCategoryService.getComponentCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ComponentCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件分类 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:component-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportComponentCategoryExcel(@Valid ComponentCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ComponentCategoryDO> list = componentCategoryService.getComponentCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件分类.xls", "数据", ComponentCategoryRespVO.class,
                        BeanUtils.toBean(list, ComponentCategoryRespVO.class));
    }

}