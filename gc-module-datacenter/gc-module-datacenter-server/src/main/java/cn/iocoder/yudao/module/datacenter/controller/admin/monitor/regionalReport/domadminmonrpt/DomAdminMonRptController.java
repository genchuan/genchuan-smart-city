package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt.vo.DomAdminMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt.vo.DomAdminMonRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt.vo.DomAdminMonRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domadminmonrpt.DomAdminMonRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domadminmonrpt.DomAdminMonRptService;

@Tag(name = "管理后台 - 行政区划分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-admin-mon-rpt")
@Validated
public class DomAdminMonRptController {

    @Resource
    private DomAdminMonRptService domAdminMonRptService;

    @PostMapping("/create")
    @Operation(summary = "创建行政区划分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-admin-mon-rpt:create')")
    public CommonResult<Long> createDomAdminMonRpt(@Valid @RequestBody DomAdminMonRptSaveReqVO createReqVO) {
        return success(domAdminMonRptService.createDomAdminMonRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行政区划分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-admin-mon-rpt:update')")
    public CommonResult<Boolean> updateDomAdminMonRpt(@Valid @RequestBody DomAdminMonRptSaveReqVO updateReqVO) {
        domAdminMonRptService.updateDomAdminMonRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除行政区划分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-admin-mon-rpt:delete')")
    public CommonResult<Boolean> deleteDomAdminMonRpt(@RequestParam("id") Long id) {
        domAdminMonRptService.deleteDomAdminMonRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行政区划分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-admin-mon-rpt:query')")
    public CommonResult<DomAdminMonRptRespVO> getDomAdminMonRpt(@RequestParam("id") Long id) {
        DomAdminMonRptDO domAdminMonRpt = domAdminMonRptService.getDomAdminMonRpt(id);
        return success(BeanUtils.toBean(domAdminMonRpt, DomAdminMonRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得行政区划分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-admin-mon-rpt:query')")
    public CommonResult<PageResult<DomAdminMonRptRespVO>> getDomAdminMonRptPage(@Valid DomAdminMonRptPageReqVO pageReqVO) {
        PageResult<DomAdminMonRptDO> pageResult = domAdminMonRptService.getDomAdminMonRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomAdminMonRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出行政区划分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-admin-mon-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomAdminMonRptExcel(@Valid DomAdminMonRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomAdminMonRptDO> list = domAdminMonRptService.getDomAdminMonRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "行政区划分域监测报表.xls", "数据", DomAdminMonRptRespVO.class,
                        BeanUtils.toBean(list, DomAdminMonRptRespVO.class));
    }

}