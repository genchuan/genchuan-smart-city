package cn.iocoder.yudao.module.waterdetection.controller.admin.waterusecategory;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.waterusecategory.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.waterusecategory.WaterUseCategoryDO;
import cn.iocoder.yudao.module.waterdetection.service.waterusecategory.WaterUseCategoryService;

@Tag(name = "管理后台 - 用水性质分类管理")
@RestController
@RequestMapping("/waterdetection/water-use-category")
@Validated
public class WaterUseCategoryController {

    @Resource
    private WaterUseCategoryService waterUseCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建用水性质分类管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-use-category:create')")
    public CommonResult<Long> createWaterUseCategory(@Valid @RequestBody WaterUseCategorySaveReqVO createReqVO) {
        return success(waterUseCategoryService.createWaterUseCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用水性质分类管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-use-category:update')")
    public CommonResult<Boolean> updateWaterUseCategory(@Valid @RequestBody WaterUseCategorySaveReqVO updateReqVO) {
        waterUseCategoryService.updateWaterUseCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用水性质分类管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-use-category:delete')")
    public CommonResult<Boolean> deleteWaterUseCategory(@RequestParam("id") Long id) {
        waterUseCategoryService.deleteWaterUseCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用水性质分类管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-use-category:query')")
    public CommonResult<WaterUseCategoryRespVO> getWaterUseCategory(@RequestParam("id") Long id) {
        WaterUseCategoryDO waterUseCategory = waterUseCategoryService.getWaterUseCategory(id);
        return success(BeanUtils.toBean(waterUseCategory, WaterUseCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用水性质分类管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-use-category:query')")
    public CommonResult<PageResult<WaterUseCategoryRespVO>> getWaterUseCategoryPage(@Valid WaterUseCategoryPageReqVO pageReqVO) {
        PageResult<WaterUseCategoryDO> pageResult = waterUseCategoryService.getWaterUseCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WaterUseCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用水性质分类管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-use-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWaterUseCategoryExcel(@Valid WaterUseCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WaterUseCategoryDO> list = waterUseCategoryService.getWaterUseCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "用水性质分类管理.xls", "数据", WaterUseCategoryRespVO.class,
                        BeanUtils.toBean(list, WaterUseCategoryRespVO.class));
    }

}