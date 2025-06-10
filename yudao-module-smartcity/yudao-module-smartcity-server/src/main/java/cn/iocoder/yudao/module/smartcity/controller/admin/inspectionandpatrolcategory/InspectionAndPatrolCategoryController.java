package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo.InspectionAndPatrolCategoryPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo.InspectionAndPatrolCategoryRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionandpatrolcategory.vo.InspectionAndPatrolCategorySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionandpatrolcategory.InspectionAndPatrolCategoryDO;
import cn.iocoder.yudao.module.smartcity.service.inspectionandpatrolcategory.InspectionAndPatrolCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 巡查巡检类")
@RestController
@RequestMapping("/smartcity/inspection-and-patrol-category")
@Validated
public class InspectionAndPatrolCategoryController {

    @Resource
    private InspectionAndPatrolCategoryService inspectionAndPatrolCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检类")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-and-patrol-category:create')")
    public CommonResult<Long> createInspectionAndPatrolCategory(@Valid @RequestBody InspectionAndPatrolCategorySaveReqVO createReqVO) {
        return success(inspectionAndPatrolCategoryService.createInspectionAndPatrolCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检类")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-and-patrol-category:update')")
    public CommonResult<Boolean> updateInspectionAndPatrolCategory(@Valid @RequestBody InspectionAndPatrolCategorySaveReqVO updateReqVO) {
        inspectionAndPatrolCategoryService.updateInspectionAndPatrolCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-and-patrol-category:delete')")
    public CommonResult<Boolean> deleteInspectionAndPatrolCategory(@RequestParam("id") Long id) {
        inspectionAndPatrolCategoryService.deleteInspectionAndPatrolCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-and-patrol-category:query')")
    public CommonResult<InspectionAndPatrolCategoryRespVO> getInspectionAndPatrolCategory(@RequestParam("id") Long id) {
        InspectionAndPatrolCategoryDO inspectionAndPatrolCategory = inspectionAndPatrolCategoryService.getInspectionAndPatrolCategory(id);
        return success(BeanUtils.toBean(inspectionAndPatrolCategory, InspectionAndPatrolCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检类分页")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-and-patrol-category:query')")
    public CommonResult<PageResult<InspectionAndPatrolCategoryRespVO>> getInspectionAndPatrolCategoryPage(@Valid InspectionAndPatrolCategoryPageReqVO pageReqVO) {
        PageResult<InspectionAndPatrolCategoryDO> pageResult = inspectionAndPatrolCategoryService.getInspectionAndPatrolCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionAndPatrolCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检类 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-and-patrol-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionAndPatrolCategoryExcel(@Valid InspectionAndPatrolCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionAndPatrolCategoryDO> list = inspectionAndPatrolCategoryService.getInspectionAndPatrolCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检类.xls", "数据", InspectionAndPatrolCategoryRespVO.class,
                        BeanUtils.toBean(list, InspectionAndPatrolCategoryRespVO.class));
    }

}