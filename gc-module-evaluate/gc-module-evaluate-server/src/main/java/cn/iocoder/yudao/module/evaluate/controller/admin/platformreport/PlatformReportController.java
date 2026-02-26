package cn.iocoder.yudao.module.evaluate.controller.admin.platformreport;

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

import cn.iocoder.yudao.module.evaluate.controller.admin.platformreport.vo.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.platformreport.PlatformReportDO;
import cn.iocoder.yudao.module.evaluate.service.platformreport.PlatformReportService;

@Tag(name = "管理后台 - 平台上报")
@RestController
@RequestMapping("/evaluate/platform-report")
@Validated
public class PlatformReportController {

    @Resource
    private PlatformReportService platformReportService;

    @PostMapping("/create")
    @Operation(summary = "创建平台上报")
    @PreAuthorize("@ss.hasPermission('evaluate:platform-report:create')")
    public CommonResult<Long> createPlatformReport(@Valid @RequestBody PlatformReportSaveReqVO createReqVO) {
        return success(platformReportService.createPlatformReport(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新平台上报")
    @PreAuthorize("@ss.hasPermission('evaluate:platform-report:update')")
    public CommonResult<Boolean> updatePlatformReport(@Valid @RequestBody PlatformReportSaveReqVO updateReqVO) {
        platformReportService.updatePlatformReport(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除平台上报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:platform-report:delete')")
    public CommonResult<Boolean> deletePlatformReport(@RequestParam("id") Long id) {
        platformReportService.deletePlatformReport(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得平台上报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:platform-report:query')")
    public CommonResult<PlatformReportRespVO> getPlatformReport(@RequestParam("id") Long id) {
        PlatformReportDO platformReport = platformReportService.getPlatformReport(id);
        return success(BeanUtils.toBean(platformReport, PlatformReportRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得平台上报分页")
    @PreAuthorize("@ss.hasPermission('evaluate:platform-report:query')")
    public CommonResult<PageResult<PlatformReportRespVO>> getPlatformReportPage(@Valid PlatformReportPageReqVO pageReqVO) {
        PageResult<PlatformReportDO> pageResult = platformReportService.getPlatformReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PlatformReportRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出平台上报 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:platform-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPlatformReportExcel(@Valid PlatformReportPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PlatformReportDO> list = platformReportService.getPlatformReportPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "平台上报.xls", "数据", PlatformReportRespVO.class,
                        BeanUtils.toBean(list, PlatformReportRespVO.class));
    }

}