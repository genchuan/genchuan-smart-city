package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo.DomMonCompMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo.DomMonCompMonRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo.DomMonCompMonRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommoncompmonrpt.DomMonCompMonRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommoncompmonrpt.DomMonCompMonRptService;

@Tag(name = "管理后台 - 监测部件分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-mon-comp-mon-rpt")
@Validated
public class DomMonCompMonRptController {

    @Resource
    private DomMonCompMonRptService domMonCompMonRptService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-comp-mon-rpt:create')")
    public CommonResult<Long> createDomMonCompMonRpt(@Valid @RequestBody DomMonCompMonRptSaveReqVO createReqVO) {
        return success(domMonCompMonRptService.createDomMonCompMonRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-comp-mon-rpt:update')")
    public CommonResult<Boolean> updateDomMonCompMonRpt(@Valid @RequestBody DomMonCompMonRptSaveReqVO updateReqVO) {
        domMonCompMonRptService.updateDomMonCompMonRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-comp-mon-rpt:delete')")
    public CommonResult<Boolean> deleteDomMonCompMonRpt(@RequestParam("id") Long id) {
        domMonCompMonRptService.deleteDomMonCompMonRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-comp-mon-rpt:query')")
    public CommonResult<DomMonCompMonRptRespVO> getDomMonCompMonRpt(@RequestParam("id") Long id) {
        DomMonCompMonRptDO domMonCompMonRpt = domMonCompMonRptService.getDomMonCompMonRpt(id);
        return success(BeanUtils.toBean(domMonCompMonRpt, DomMonCompMonRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-comp-mon-rpt:query')")
    public CommonResult<PageResult<DomMonCompMonRptRespVO>> getDomMonCompMonRptPage(@Valid DomMonCompMonRptPageReqVO pageReqVO) {
        PageResult<DomMonCompMonRptDO> pageResult = domMonCompMonRptService.getDomMonCompMonRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomMonCompMonRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mon-comp-mon-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomMonCompMonRptExcel(@Valid DomMonCompMonRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomMonCompMonRptDO> list = domMonCompMonRptService.getDomMonCompMonRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件分域监测报表.xls", "数据", DomMonCompMonRptRespVO.class,
                        BeanUtils.toBean(list, DomMonCompMonRptRespVO.class));
    }

}