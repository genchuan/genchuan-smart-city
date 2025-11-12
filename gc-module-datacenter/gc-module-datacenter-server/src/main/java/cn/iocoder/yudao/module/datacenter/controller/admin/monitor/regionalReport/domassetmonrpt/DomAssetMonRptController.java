package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo.DomAssetMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo.DomAssetMonRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo.DomAssetMonRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domassetmonrpt.DomAssetMonRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domassetmonrpt.DomAssetMonRptService;

@Tag(name = "管理后台 - 资产分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-asset-mon-rpt")
@Validated
public class DomAssetMonRptController {

    @Resource
    private DomAssetMonRptService domAssetMonRptService;

    @PostMapping("/create")
    @Operation(summary = "创建资产分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-asset-mon-rpt:create')")
    public CommonResult<Long> createDomAssetMonRpt(@Valid @RequestBody DomAssetMonRptSaveReqVO createReqVO) {
        return success(domAssetMonRptService.createDomAssetMonRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资产分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-asset-mon-rpt:update')")
    public CommonResult<Boolean> updateDomAssetMonRpt(@Valid @RequestBody DomAssetMonRptSaveReqVO updateReqVO) {
        domAssetMonRptService.updateDomAssetMonRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资产分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-asset-mon-rpt:delete')")
    public CommonResult<Boolean> deleteDomAssetMonRpt(@RequestParam("id") Long id) {
        domAssetMonRptService.deleteDomAssetMonRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资产分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-asset-mon-rpt:query')")
    public CommonResult<DomAssetMonRptRespVO> getDomAssetMonRpt(@RequestParam("id") Long id) {
        DomAssetMonRptDO domAssetMonRpt = domAssetMonRptService.getDomAssetMonRpt(id);
        return success(BeanUtils.toBean(domAssetMonRpt, DomAssetMonRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资产分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-asset-mon-rpt:query')")
    public CommonResult<PageResult<DomAssetMonRptRespVO>> getDomAssetMonRptPage(@Valid DomAssetMonRptPageReqVO pageReqVO) {
        PageResult<DomAssetMonRptDO> pageResult = domAssetMonRptService.getDomAssetMonRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomAssetMonRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资产分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-asset-mon-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomAssetMonRptExcel(@Valid DomAssetMonRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomAssetMonRptDO> list = domAssetMonRptService.getDomAssetMonRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资产分域监测报表.xls", "数据", DomAssetMonRptRespVO.class,
                        BeanUtils.toBean(list, DomAssetMonRptRespVO.class));
    }

}