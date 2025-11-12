package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo.DomMngCompMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo.DomMngCompMonRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo.DomMngCompMonRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngcompmonrpt.DomMngCompMonRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommngcompmonrpt.DomMngCompMonRptService;

@Tag(name = "管理后台 - 管理部件分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-mng-comp-mon-rpt")
@Validated
public class DomMngCompMonRptController {

    @Resource
    private DomMngCompMonRptService domMngCompMonRptService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-comp-mon-rpt:create')")
    public CommonResult<Long> createDomMngCompMonRpt(@Valid @RequestBody DomMngCompMonRptSaveReqVO createReqVO) {
        return success(domMngCompMonRptService.createDomMngCompMonRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-comp-mon-rpt:update')")
    public CommonResult<Boolean> updateDomMngCompMonRpt(@Valid @RequestBody DomMngCompMonRptSaveReqVO updateReqVO) {
        domMngCompMonRptService.updateDomMngCompMonRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-comp-mon-rpt:delete')")
    public CommonResult<Boolean> deleteDomMngCompMonRpt(@RequestParam("id") Long id) {
        domMngCompMonRptService.deleteDomMngCompMonRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-comp-mon-rpt:query')")
    public CommonResult<DomMngCompMonRptRespVO> getDomMngCompMonRpt(@RequestParam("id") Long id) {
        DomMngCompMonRptDO domMngCompMonRpt = domMngCompMonRptService.getDomMngCompMonRpt(id);
        return success(BeanUtils.toBean(domMngCompMonRpt, DomMngCompMonRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-comp-mon-rpt:query')")
    public CommonResult<PageResult<DomMngCompMonRptRespVO>> getDomMngCompMonRptPage(@Valid DomMngCompMonRptPageReqVO pageReqVO) {
        PageResult<DomMngCompMonRptDO> pageResult = domMngCompMonRptService.getDomMngCompMonRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomMngCompMonRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-comp-mon-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomMngCompMonRptExcel(@Valid DomMngCompMonRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomMngCompMonRptDO> list = domMngCompMonRptService.getDomMngCompMonRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件分域监测报表.xls", "数据", DomMngCompMonRptRespVO.class,
                        BeanUtils.toBean(list, DomMngCompMonRptRespVO.class));
    }

}