package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo.DomGridMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo.DomGridMonRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo.DomGridMonRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domgridmonrpt.DomGridMonRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domgridmonrpt.DomGridMonRptService;

@Tag(name = "管理后台 - 网格分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-grid-mon-rpt")
@Validated
public class DomGridMonRptController {

    @Resource
    private DomGridMonRptService domGridMonRptService;

    @PostMapping("/create")
    @Operation(summary = "创建网格分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-grid-mon-rpt:create')")
    public CommonResult<Long> createDomGridMonRpt(@Valid @RequestBody DomGridMonRptSaveReqVO createReqVO) {
        return success(domGridMonRptService.createDomGridMonRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-grid-mon-rpt:update')")
    public CommonResult<Boolean> updateDomGridMonRpt(@Valid @RequestBody DomGridMonRptSaveReqVO updateReqVO) {
        domGridMonRptService.updateDomGridMonRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-grid-mon-rpt:delete')")
    public CommonResult<Boolean> deleteDomGridMonRpt(@RequestParam("id") Long id) {
        domGridMonRptService.deleteDomGridMonRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-grid-mon-rpt:query')")
    public CommonResult<DomGridMonRptRespVO> getDomGridMonRpt(@RequestParam("id") Long id) {
        DomGridMonRptDO domGridMonRpt = domGridMonRptService.getDomGridMonRpt(id);
        return success(BeanUtils.toBean(domGridMonRpt, DomGridMonRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-grid-mon-rpt:query')")
    public CommonResult<PageResult<DomGridMonRptRespVO>> getDomGridMonRptPage(@Valid DomGridMonRptPageReqVO pageReqVO) {
        PageResult<DomGridMonRptDO> pageResult = domGridMonRptService.getDomGridMonRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomGridMonRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-grid-mon-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomGridMonRptExcel(@Valid DomGridMonRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomGridMonRptDO> list = domGridMonRptService.getDomGridMonRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格分域监测报表.xls", "数据", DomGridMonRptRespVO.class,
                        BeanUtils.toBean(list, DomGridMonRptRespVO.class));
    }

}