package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo.DomMonEvtMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo.DomMonEvtMonRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo.DomMonEvtMonRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommonevtmonrpt.DomMonEvtMonRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommonevtmonrpt.DomMonEvtMonRptService;

@Tag(name = "管理后台 - 监测事件分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-mon-evt-mon-rpt")
@Validated
public class DomMonEvtMonRptController {

    @Resource
    private DomMonEvtMonRptService domMonEvtMonRptService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-evt-mon-rpt:create')")
    public CommonResult<Long> createDomMonEvtMonRpt(@Valid @RequestBody DomMonEvtMonRptSaveReqVO createReqVO) {
        return success(domMonEvtMonRptService.createDomMonEvtMonRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-evt-mon-rpt:update')")
    public CommonResult<Boolean> updateDomMonEvtMonRpt(@Valid @RequestBody DomMonEvtMonRptSaveReqVO updateReqVO) {
        domMonEvtMonRptService.updateDomMonEvtMonRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-evt-mon-rpt:delete')")
    public CommonResult<Boolean> deleteDomMonEvtMonRpt(@RequestParam("id") Long id) {
        domMonEvtMonRptService.deleteDomMonEvtMonRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-evt-mon-rpt:query')")
    public CommonResult<DomMonEvtMonRptRespVO> getDomMonEvtMonRpt(@RequestParam("id") Long id) {
        DomMonEvtMonRptDO domMonEvtMonRpt = domMonEvtMonRptService.getDomMonEvtMonRpt(id);
        return success(BeanUtils.toBean(domMonEvtMonRpt, DomMonEvtMonRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-evt-mon-rpt:query')")
    public CommonResult<PageResult<DomMonEvtMonRptRespVO>> getDomMonEvtMonRptPage(@Valid DomMonEvtMonRptPageReqVO pageReqVO) {
        PageResult<DomMonEvtMonRptDO> pageResult = domMonEvtMonRptService.getDomMonEvtMonRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomMonEvtMonRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-evt-mon-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomMonEvtMonRptExcel(@Valid DomMonEvtMonRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomMonEvtMonRptDO> list = domMonEvtMonRptService.getDomMonEvtMonRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件分域监测报表.xls", "数据", DomMonEvtMonRptRespVO.class,
                        BeanUtils.toBean(list, DomMonEvtMonRptRespVO.class));
    }

}