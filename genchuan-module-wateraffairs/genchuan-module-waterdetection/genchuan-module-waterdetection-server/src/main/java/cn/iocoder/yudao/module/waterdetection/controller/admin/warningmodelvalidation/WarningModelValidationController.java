package cn.iocoder.yudao.module.waterdetection.controller.admin.warningmodelvalidation;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.warningmodelvalidation.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningmodelvalidation.WarningModelValidationDO;
import cn.iocoder.yudao.module.waterdetection.service.warningmodelvalidation.WarningModelValidationService;

@Tag(name = "管理后台 - 预警模型校验")
@RestController
@RequestMapping("/waterdetection/warning-model-validation")
@Validated
public class WarningModelValidationController {

    @Resource
    private WarningModelValidationService warningModelValidationService;

    @PostMapping("/create")
    @Operation(summary = "创建预警模型校验")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-model-validation:create')")
    public CommonResult<Long> createWarningModelValidation(@Valid @RequestBody WarningModelValidationSaveReqVO createReqVO) {
        return success(warningModelValidationService.createWarningModelValidation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警模型校验")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-model-validation:update')")
    public CommonResult<Boolean> updateWarningModelValidation(@Valid @RequestBody WarningModelValidationSaveReqVO updateReqVO) {
        warningModelValidationService.updateWarningModelValidation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警模型校验")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-model-validation:delete')")
    public CommonResult<Boolean> deleteWarningModelValidation(@RequestParam("id") Long id) {
        warningModelValidationService.deleteWarningModelValidation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警模型校验")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-model-validation:query')")
    public CommonResult<WarningModelValidationRespVO> getWarningModelValidation(@RequestParam("id") Long id) {
        WarningModelValidationDO warningModelValidation = warningModelValidationService.getWarningModelValidation(id);
        return success(BeanUtils.toBean(warningModelValidation, WarningModelValidationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警模型校验分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-model-validation:query')")
    public CommonResult<PageResult<WarningModelValidationRespVO>> getWarningModelValidationPage(@Valid WarningModelValidationPageReqVO pageReqVO) {
        PageResult<WarningModelValidationDO> pageResult = warningModelValidationService.getWarningModelValidationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WarningModelValidationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警模型校验 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-model-validation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWarningModelValidationExcel(@Valid WarningModelValidationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WarningModelValidationDO> list = warningModelValidationService.getWarningModelValidationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警模型校验.xls", "数据", WarningModelValidationRespVO.class,
                        BeanUtils.toBean(list, WarningModelValidationRespVO.class));
    }

}