package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt.vo.DomMngMatterRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt.vo.DomMngMatterRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt.vo.DomMngMatterRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngmatterrpt.DomMngMatterRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommngmatterrpt.DomMngMatterRptService;

@Tag(name = "管理后台 - 管理事项分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-mng-matter-rpt")
@Validated
public class DomMngMatterRptController {

    @Resource
    private DomMngMatterRptService domMngMatterRptService;

    @PostMapping("/create")
    @Operation(summary = "创建管理事项分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-matter-rpt:create')")
    public CommonResult<Long> createDomMngMatterRpt(@Valid @RequestBody DomMngMatterRptSaveReqVO createReqVO) {
        return success(domMngMatterRptService.createDomMngMatterRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理事项分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-matter-rpt:update')")
    public CommonResult<Boolean> updateDomMngMatterRpt(@Valid @RequestBody DomMngMatterRptSaveReqVO updateReqVO) {
        domMngMatterRptService.updateDomMngMatterRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理事项分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-matter-rpt:delete')")
    public CommonResult<Boolean> deleteDomMngMatterRpt(@RequestParam("id") Long id) {
        domMngMatterRptService.deleteDomMngMatterRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理事项分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-matter-rpt:query')")
    public CommonResult<DomMngMatterRptRespVO> getDomMngMatterRpt(@RequestParam("id") Long id) {
        DomMngMatterRptDO domMngMatterRpt = domMngMatterRptService.getDomMngMatterRpt(id);
        return success(BeanUtils.toBean(domMngMatterRpt, DomMngMatterRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理事项分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-matter-rpt:query')")
    public CommonResult<PageResult<DomMngMatterRptRespVO>> getDomMngMatterRptPage(@Valid DomMngMatterRptPageReqVO pageReqVO) {
        PageResult<DomMngMatterRptDO> pageResult = domMngMatterRptService.getDomMngMatterRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomMngMatterRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理事项分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-mng-matter-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomMngMatterRptExcel(@Valid DomMngMatterRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomMngMatterRptDO> list = domMngMatterRptService.getDomMngMatterRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理事项分域监测报表.xls", "数据", DomMngMatterRptRespVO.class,
                        BeanUtils.toBean(list, DomMngMatterRptRespVO.class));
    }

}