package cn.iocoder.yudao.module.waterdetection.controller.admin.warningindicator;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.warningindicator.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningindicator.WarningIndicatorDO;
import cn.iocoder.yudao.module.waterdetection.service.warningindicator.WarningIndicatorService;

@Tag(name = "管理后台 - 预警指标配置")
@RestController
@RequestMapping("/waterdetection/warning-indicator")
@Validated
public class WarningIndicatorController {

    @Resource
    private WarningIndicatorService warningIndicatorService;

    @PostMapping("/create")
    @Operation(summary = "创建预警指标配置")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-indicator:create')")
    public CommonResult<Long> createWarningIndicator(@Valid @RequestBody WarningIndicatorSaveReqVO createReqVO) {
        return success(warningIndicatorService.createWarningIndicator(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警指标配置")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-indicator:update')")
    public CommonResult<Boolean> updateWarningIndicator(@Valid @RequestBody WarningIndicatorSaveReqVO updateReqVO) {
        warningIndicatorService.updateWarningIndicator(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警指标配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-indicator:delete')")
    public CommonResult<Boolean> deleteWarningIndicator(@RequestParam("id") Long id) {
        warningIndicatorService.deleteWarningIndicator(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警指标配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-indicator:query')")
    public CommonResult<WarningIndicatorRespVO> getWarningIndicator(@RequestParam("id") Long id) {
        WarningIndicatorDO warningIndicator = warningIndicatorService.getWarningIndicator(id);
        return success(BeanUtils.toBean(warningIndicator, WarningIndicatorRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警指标配置分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-indicator:query')")
    public CommonResult<PageResult<WarningIndicatorRespVO>> getWarningIndicatorPage(@Valid WarningIndicatorPageReqVO pageReqVO) {
        PageResult<WarningIndicatorDO> pageResult = warningIndicatorService.getWarningIndicatorPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WarningIndicatorRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警指标配置 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-indicator:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWarningIndicatorExcel(@Valid WarningIndicatorPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WarningIndicatorDO> list = warningIndicatorService.getWarningIndicatorPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警指标配置.xls", "数据", WarningIndicatorRespVO.class,
                        BeanUtils.toBean(list, WarningIndicatorRespVO.class));
    }

}