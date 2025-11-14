package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo.KeyIdxSummaryRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo.KeyIdxSummaryRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.comprehensiveReport.keyidxsummaryrpt.vo.KeyIdxSummaryRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.comprehensiveReport.keyidxsummaryrpt.KeyIdxSummaryRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.comprehensiveReport.keyidxsummaryrpt.KeyIdxSummaryRptService;

@Tag(name = "管理后台 - 关键指标汇总报表")
@RestController
@RequestMapping("/datacenter/key-idx-summary-rpt")
@Validated
public class KeyIdxSummaryRptController {

    @Resource
    private KeyIdxSummaryRptService keyIdxSummaryRptService;

    @PostMapping("/create")
    @Operation(summary = "创建关键指标汇总报表")
    @PreAuthorize("@ss.hasPermission('datacenter:key-idx-summary-rpt:create')")
    public CommonResult<Long> createKeyIdxSummaryRpt(@Valid @RequestBody KeyIdxSummaryRptSaveReqVO createReqVO) {
        return success(keyIdxSummaryRptService.createKeyIdxSummaryRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新关键指标汇总报表")
    @PreAuthorize("@ss.hasPermission('datacenter:key-idx-summary-rpt:update')")
    public CommonResult<Boolean> updateKeyIdxSummaryRpt(@Valid @RequestBody KeyIdxSummaryRptSaveReqVO updateReqVO) {
        keyIdxSummaryRptService.updateKeyIdxSummaryRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除关键指标汇总报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:key-idx-summary-rpt:delete')")
    public CommonResult<Boolean> deleteKeyIdxSummaryRpt(@RequestParam("id") Long id) {
        keyIdxSummaryRptService.deleteKeyIdxSummaryRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得关键指标汇总报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:key-idx-summary-rpt:query')")
    public CommonResult<KeyIdxSummaryRptRespVO> getKeyIdxSummaryRpt(@RequestParam("id") Long id) {
        KeyIdxSummaryRptDO keyIdxSummaryRpt = keyIdxSummaryRptService.getKeyIdxSummaryRpt(id);
        return success(BeanUtils.toBean(keyIdxSummaryRpt, KeyIdxSummaryRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得关键指标汇总报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:key-idx-summary-rpt:query')")
    public CommonResult<PageResult<KeyIdxSummaryRptRespVO>> getKeyIdxSummaryRptPage(@Valid KeyIdxSummaryRptPageReqVO pageReqVO) {
        PageResult<KeyIdxSummaryRptDO> pageResult = keyIdxSummaryRptService.getKeyIdxSummaryRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, KeyIdxSummaryRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出关键指标汇总报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:key-idx-summary-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportKeyIdxSummaryRptExcel(@Valid KeyIdxSummaryRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<KeyIdxSummaryRptDO> list = keyIdxSummaryRptService.getKeyIdxSummaryRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "关键指标汇总报表.xls", "数据", KeyIdxSummaryRptRespVO.class,
                        BeanUtils.toBean(list, KeyIdxSummaryRptRespVO.class));
    }

}