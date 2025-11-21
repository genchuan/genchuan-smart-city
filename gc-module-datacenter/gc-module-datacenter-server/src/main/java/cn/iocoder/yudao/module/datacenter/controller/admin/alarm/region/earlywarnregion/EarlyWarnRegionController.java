package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnregion;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnregion.vo.*;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;

import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnregion.EarlyWarnRegionDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnregion.EarlyWarnRegionService;

@Tag(name = "管理后台 - 按行政区划预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-region")
@Validated
public class EarlyWarnRegionController {

    @Resource
    private EarlyWarnRegionService earlyWarnRegionService;

    @PostMapping("/create")
    @Operation(summary = "创建按行政区划预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:create')")
    public CommonResult<Long> createEarlyWarnRegion(@Valid @RequestBody EarlyWarnRegionSaveReqVO createReqVO) {
        return success(earlyWarnRegionService.createEarlyWarnRegion(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按行政区划预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:update')")
    public CommonResult<Boolean> updateEarlyWarnRegion(@Valid @RequestBody EarlyWarnRegionSaveReqVO updateReqVO) {
        earlyWarnRegionService.updateEarlyWarnRegion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按行政区划预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:delete')")
    public CommonResult<Boolean> deleteEarlyWarnRegion(@RequestParam("id") Long id) {
        earlyWarnRegionService.deleteEarlyWarnRegion(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按行政区划预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:query')")
    public CommonResult<EarlyWarnRegionRespVO> getEarlyWarnRegion(@RequestParam("id") Long id) {
        EarlyWarnRegionDO earlyWarnRegion = earlyWarnRegionService.getEarlyWarnRegion(id);
        return success(BeanUtils.toBean(earlyWarnRegion, EarlyWarnRegionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按行政区划预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:query')")
    public CommonResult<PageResult<EarlyWarnRegionRespVO>> getEarlyWarnRegionPage(@Valid EarlyWarnRegionPageReqVO pageReqVO) {
        PageResult<EarlyWarnRegionDO> pageResult = earlyWarnRegionService.getEarlyWarnRegionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnRegionRespVO.class));
    }

//    @GetMapping("/export-excel")
//    @Operation(summary = "导出按行政区划预警告警统计 Excel")
//    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportEarlyWarnRegionExcel(@Valid EarlyWarnRegionPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<EarlyWarnRegionDO> list = earlyWarnRegionService.getEarlyWarnRegionPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "按行政区划预警告警统计.xls", "数据", EarlyWarnRegionRespVO.class,
//                        BeanUtils.toBean(list, EarlyWarnRegionRespVO.class));
//    }
    @GetMapping("/export-warning-alert-excel")
    @Operation(summary = "导出预警明细 Excel")
    @Parameters({
            @Parameter(name = "regionCode", description = "行政区划代码", required = true),
            @Parameter(name = "statCycle", description = "统计周期", required = true)
    })
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWarningAlertExcel(
            @RequestParam("regionCode") String regionCode,
            @RequestParam("statCycle") String statCycle,
            HttpServletResponse response) throws IOException {

        PageParam pageReqVO = new PageParam();
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);

        PageResult<WarningAlertRespVO> pageResult = earlyWarnRegionService.getWarningAlertPage(regionCode, statCycle, pageReqVO);

        // 导出 Excel
        ExcelUtils.write(response, "预警明细.xls", "数据", WarningAlertRespVO.class, pageResult.getList());
    }

    @PostMapping("/generate-stat")
    @Operation(summary = "生成按行政区划预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:generate')")
    public CommonResult<Long> generateEarlyWarnRegionStat(@Valid @RequestBody EarlyWarnRegionGenerateReqVO generateReqVO) {
//        log.info("收到生成统计请求: {}", generateReqVO);

        try {
            Long result = earlyWarnRegionService.generateEarlyWarnRegionStat(generateReqVO);
//            log.info("统计生成成功，ID: {}", result);
            return success(result);
        } catch (Exception e) {
//            log.error("统计生成失败", e);
            return CommonResult.error(500, "统计生成失败: " + e.getMessage());
        }
    }

    @GetMapping("/warning-alert-page")
    @Operation(summary = "获取预警明细分页（钻取功能）")
    @Parameters({
            @Parameter(name = "regionCode", description = "行政区划代码", required = true),
            @Parameter(name = "statCycle", description = "统计周期", required = true)
    })
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-region:query')")
    public CommonResult<PageResult<WarningAlertRespVO>> getWarningAlertPage(
            @RequestParam("regionCode") String regionCode,
            @RequestParam("statCycle") String statCycle,
            @Valid PageParam pageReqVO) {
        return success(earlyWarnRegionService.getWarningAlertPage(regionCode, statCycle, pageReqVO));
    }

}