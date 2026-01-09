package cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo.AlarmHandlingCategoryPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo.AlarmHandlingCategoryRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo.AlarmHandlingCategorySaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import cn.iocoder.yudao.module.smartcity.service.alarmhandlingcategory.AlarmHandlingCategoryService;
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

@Tag(name = "管理后台 - 报警处置类")
@RestController
@RequestMapping("/smartcity/alarm-handling-category")
@Validated
public class AlarmHandlingCategoryController {

    @Resource
    private AlarmHandlingCategoryService alarmHandlingCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建报警处置类")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:create')")
    public CommonResult<Long> createAlarmHandlingCategory(@Valid @RequestBody AlarmHandlingCategorySaveReqVO createReqVO) {
        return success(alarmHandlingCategoryService.createAlarmHandlingCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新报警处置类")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:update')")
    public CommonResult<Boolean> updateAlarmHandlingCategory(@Valid @RequestBody AlarmHandlingCategorySaveReqVO updateReqVO) {
        alarmHandlingCategoryService.updateAlarmHandlingCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除报警处置类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:delete')")
    public CommonResult<Boolean> deleteAlarmHandlingCategory(@RequestParam("id") Long id) {
        alarmHandlingCategoryService.deleteAlarmHandlingCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得报警处置类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:query')")
    public CommonResult<AlarmHandlingCategoryRespVO> getAlarmHandlingCategory(@RequestParam("id") Long id) {
        AlarmHandlingCategoryDO alarmHandlingCategory = alarmHandlingCategoryService.getAlarmHandlingCategory(id);
        return success(BeanUtils.toBean(alarmHandlingCategory, AlarmHandlingCategoryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得报警处置类分页")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:query')")
    public CommonResult<PageResult<AlarmHandlingCategoryRespVO>> getAlarmHandlingCategoryPage(@Valid AlarmHandlingCategoryPageReqVO pageReqVO) {
        PageResult<AlarmHandlingCategoryDO> pageResult = alarmHandlingCategoryService.getAlarmHandlingCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AlarmHandlingCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出报警处置类 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:alarm-handling-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAlarmHandlingCategoryExcel(@Valid AlarmHandlingCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AlarmHandlingCategoryDO> list = alarmHandlingCategoryService.getAlarmHandlingCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "报警处置类.xls", "数据", AlarmHandlingCategoryRespVO.class,
                        BeanUtils.toBean(list, AlarmHandlingCategoryRespVO.class));
    }

}