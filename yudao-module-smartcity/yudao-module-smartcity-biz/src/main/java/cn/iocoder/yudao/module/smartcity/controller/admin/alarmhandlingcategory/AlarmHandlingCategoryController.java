package cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import cn.iocoder.yudao.module.smartcity.service.alarmhandlingcategory.AlarmHandlingCategoryService;

@Tag(name = "管理后台 - 智慧城管")
@RestController
@RequestMapping("/smartcity/alarm-handling-category")
@Validated
public class AlarmHandlingCategoryController {

    @Resource
    private AlarmHandlingCategoryService alarmHandlingCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建智慧城管")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:create')")
    public CommonResult<Long> createAlarmHandlingCategory(@Valid @RequestBody AlarmHandlingCategorySaveReqVO createReqVO) {
        return success(alarmHandlingCategoryService.createAlarmHandlingCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新智慧城管")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:update')")
    public CommonResult<Boolean> updateAlarmHandlingCategory(@Valid @RequestBody AlarmHandlingCategorySaveReqVO updateReqVO) {
        alarmHandlingCategoryService.updateAlarmHandlingCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除智慧城管")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:delete')")
    public CommonResult<Boolean> deleteAlarmHandlingCategory(@RequestParam("id") Long id) {
        alarmHandlingCategoryService.deleteAlarmHandlingCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得智慧城管")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:query')")
    public CommonResult<AlarmHandlingCategoryRespVO> getAlarmHandlingCategory(@RequestParam("id") Long id) {
        AlarmHandlingCategoryDO alarmHandlingCategory = alarmHandlingCategoryService.getAlarmHandlingCategory(id);
        return success(BeanUtils.toBean(alarmHandlingCategory, AlarmHandlingCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得智慧城管分页")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:query')")
    public CommonResult<PageResult<AlarmHandlingCategoryRespVO>> getAlarmHandlingCategoryPage(@Valid AlarmHandlingCategoryPageReqVO pageReqVO) {
        PageResult<AlarmHandlingCategoryDO> pageResult = alarmHandlingCategoryService.getAlarmHandlingCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlarmHandlingCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出智慧城管 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAlarmHandlingCategoryExcel(@Valid AlarmHandlingCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlarmHandlingCategoryDO> list = alarmHandlingCategoryService.getAlarmHandlingCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "智慧城管.xls", "数据", AlarmHandlingCategoryRespVO.class,
                        BeanUtils.toBean(list, AlarmHandlingCategoryRespVO.class));
    }

}