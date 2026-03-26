package cn.iocoder.yudao.module.waterdetection.controller.admin.warningthreshold;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.warningthreshold.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningthreshold.WarningThresholdDO;
import cn.iocoder.yudao.module.waterdetection.service.warningthreshold.WarningThresholdService;

@Tag(name = "管理后台 - 预警阈值管理")
@RestController
@RequestMapping("/waterdetection/warning-threshold")
@Validated
public class WarningThresholdController {

    @Resource
    private WarningThresholdService warningThresholdService;

    @PostMapping("/create")
    @Operation(summary = "创建预警阈值管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-threshold:create')")
    public CommonResult<Long> createWarningThreshold(@Valid @RequestBody WarningThresholdSaveReqVO createReqVO) {
        return success(warningThresholdService.createWarningThreshold(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警阈值管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-threshold:update')")
    public CommonResult<Boolean> updateWarningThreshold(@Valid @RequestBody WarningThresholdSaveReqVO updateReqVO) {
        warningThresholdService.updateWarningThreshold(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警阈值管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-threshold:delete')")
    public CommonResult<Boolean> deleteWarningThreshold(@RequestParam("id") Long id) {
        warningThresholdService.deleteWarningThreshold(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警阈值管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-threshold:query')")
    public CommonResult<WarningThresholdRespVO> getWarningThreshold(@RequestParam("id") Long id) {
        WarningThresholdDO warningThreshold = warningThresholdService.getWarningThreshold(id);
        return success(BeanUtils.toBean(warningThreshold, WarningThresholdRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警阈值管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-threshold:query')")
    public CommonResult<PageResult<WarningThresholdRespVO>> getWarningThresholdPage(@Valid WarningThresholdPageReqVO pageReqVO) {
        PageResult<WarningThresholdDO> pageResult = warningThresholdService.getWarningThresholdPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WarningThresholdRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警阈值管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:warning-threshold:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWarningThresholdExcel(@Valid WarningThresholdPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WarningThresholdDO> list = warningThresholdService.getWarningThresholdPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警阈值管理.xls", "数据", WarningThresholdRespVO.class,
                        BeanUtils.toBean(list, WarningThresholdRespVO.class));
    }

}