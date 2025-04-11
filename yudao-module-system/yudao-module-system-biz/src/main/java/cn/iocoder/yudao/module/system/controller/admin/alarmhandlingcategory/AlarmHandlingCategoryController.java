package cn.iocoder.yudao.module.system.controller.admin.alarmhandlingcategory;

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

import cn.iocoder.yudao.module.system.controller.admin.alarmhandlingcategory.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import cn.iocoder.yudao.module.system.service.alarmhandlingcategory.AlarmHandlingCategoryService;

@Tag(name = "管理后台 - 警报处理类别")
@RestController
@RequestMapping("/system/alarm-handling-category")
@Validated
public class AlarmHandlingCategoryController {

    @Resource
    private AlarmHandlingCategoryService alarmHandlingCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建警报处理类别")
    @PreAuthorize("@ss.hasPermission('system:alarm-handling-category:create')")
    public CommonResult<Long> createAlarmHandlingCategory(@Valid @RequestBody AlarmHandlingCategorySaveReqVO createReqVO) {
        return success(alarmHandlingCategoryService.createAlarmHandlingCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新警报处理类别")
    @PreAuthorize("@ss.hasPermission('system:alarm-handling-category:update')")
    public CommonResult<Boolean> updateAlarmHandlingCategory(@Valid @RequestBody AlarmHandlingCategorySaveReqVO updateReqVO) {
        alarmHandlingCategoryService.updateAlarmHandlingCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除警报处理类别")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:alarm-handling-category:delete')")
    public CommonResult<Boolean> deleteAlarmHandlingCategory(@RequestParam("id") Long id) {
        alarmHandlingCategoryService.deleteAlarmHandlingCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得警报处理类别")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:alarm-handling-category:query')")
    public CommonResult<AlarmHandlingCategoryRespVO> getAlarmHandlingCategory(@RequestParam("id") Long id) {
        AlarmHandlingCategoryDO alarmHandlingCategory = alarmHandlingCategoryService.getAlarmHandlingCategory(id);
        return success(BeanUtils.toBean(alarmHandlingCategory, AlarmHandlingCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得警报处理类别分页")
    @PreAuthorize("@ss.hasPermission('system:alarm-handling-category:query')")
    public CommonResult<PageResult<AlarmHandlingCategoryRespVO>> getAlarmHandlingCategoryPage(@Valid AlarmHandlingCategoryPageReqVO pageReqVO) {
        PageResult<AlarmHandlingCategoryDO> pageResult = alarmHandlingCategoryService.getAlarmHandlingCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlarmHandlingCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出警报处理类别 Excel")
    @PreAuthorize("@ss.hasPermission('system:alarm-handling-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAlarmHandlingCategoryExcel(@Valid AlarmHandlingCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlarmHandlingCategoryDO> list = alarmHandlingCategoryService.getAlarmHandlingCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "警报处理类别.xls", "数据", AlarmHandlingCategoryRespVO.class,
                        BeanUtils.toBean(list, AlarmHandlingCategoryRespVO.class));
    }

}