package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo.MonPtSummaryRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo.MonPtSummaryRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.monptsummaryrpt.vo.MonPtSummaryRptSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.monptsummaryrpt.MonPtSummaryRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.monptsummaryrpt.MonPtSummaryRptService;

@Tag(name = "管理后台 - 监测点位汇总报表")
@RestController
@RequestMapping("/datacenter/mon-pt-summary-rpt")
@Validated
public class MonPtSummaryRptController {

    @Resource
    private MonPtSummaryRptService monPtSummaryRptService;

    @PostMapping("/create")
    @Operation(summary = "创建监测点位汇总报表")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-pt-summary-rpt:create')")
    public CommonResult<Long> createMonPtSummaryRpt(@Valid @RequestBody MonPtSummaryRptSaveReqVO createReqVO) {
        return success(monPtSummaryRptService.createMonPtSummaryRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测点位汇总报表")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-pt-summary-rpt:update')")
    public CommonResult<Boolean> updateMonPtSummaryRpt(@Valid @RequestBody MonPtSummaryRptSaveReqVO updateReqVO) {
        monPtSummaryRptService.updateMonPtSummaryRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测点位汇总报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-pt-summary-rpt:delete')")
    public CommonResult<Boolean> deleteMonPtSummaryRpt(@RequestParam("id") Long id) {
        monPtSummaryRptService.deleteMonPtSummaryRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测点位汇总报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-pt-summary-rpt:query')")
    public CommonResult<MonPtSummaryRptRespVO> getMonPtSummaryRpt(@RequestParam("id") Long id) {
        MonPtSummaryRptDO monPtSummaryRpt = monPtSummaryRptService.getMonPtSummaryRpt(id);
        return success(BeanUtils.toBean(monPtSummaryRpt, MonPtSummaryRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测点位汇总报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-pt-summary-rpt:query')")
    public CommonResult<PageResult<MonPtSummaryRptRespVO>> getMonPtSummaryRptPage(@Valid MonPtSummaryRptPageReqVO pageReqVO) {
        PageResult<MonPtSummaryRptDO> pageResult = monPtSummaryRptService.getMonPtSummaryRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonPtSummaryRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测点位汇总报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-pt-summary-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonPtSummaryRptExcel(@Valid MonPtSummaryRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonPtSummaryRptDO> list = monPtSummaryRptService.getMonPtSummaryRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测点位汇总报表.xls", "数据", MonPtSummaryRptRespVO.class,
                        BeanUtils.toBean(list, MonPtSummaryRptRespVO.class));
    }

}