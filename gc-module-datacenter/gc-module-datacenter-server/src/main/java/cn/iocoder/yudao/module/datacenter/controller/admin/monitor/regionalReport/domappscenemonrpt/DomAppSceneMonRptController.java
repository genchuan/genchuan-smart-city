package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo.DomAppSceneMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo.DomAppSceneMonRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo.DomAppSceneMonRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domappscenemonrpt.DomAppSceneMonRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domappscenemonrpt.DomAppSceneMonRptService;

@Tag(name = "管理后台 - 应用场景分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-app-scene-mon-rpt")
@Validated
public class DomAppSceneMonRptController {

    @Resource
    private DomAppSceneMonRptService domAppSceneMonRptService;

    @PostMapping("/create")
    @Operation(summary = "创建应用场景分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-app-scene-mon-rpt:create')")
    public CommonResult<Long> createDomAppSceneMonRpt(@Valid @RequestBody DomAppSceneMonRptSaveReqVO createReqVO) {
        return success(domAppSceneMonRptService.createDomAppSceneMonRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新应用场景分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-app-scene-mon-rpt:update')")
    public CommonResult<Boolean> updateDomAppSceneMonRpt(@Valid @RequestBody DomAppSceneMonRptSaveReqVO updateReqVO) {
        domAppSceneMonRptService.updateDomAppSceneMonRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除应用场景分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-app-scene-mon-rpt:delete')")
    public CommonResult<Boolean> deleteDomAppSceneMonRpt(@RequestParam("id") Long id) {
        domAppSceneMonRptService.deleteDomAppSceneMonRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得应用场景分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-app-scene-mon-rpt:query')")
    public CommonResult<DomAppSceneMonRptRespVO> getDomAppSceneMonRpt(@RequestParam("id") Long id) {
        DomAppSceneMonRptDO domAppSceneMonRpt = domAppSceneMonRptService.getDomAppSceneMonRpt(id);
        return success(BeanUtils.toBean(domAppSceneMonRpt, DomAppSceneMonRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得应用场景分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-app-scene-mon-rpt:query')")
    public CommonResult<PageResult<DomAppSceneMonRptRespVO>> getDomAppSceneMonRptPage(@Valid DomAppSceneMonRptPageReqVO pageReqVO) {
        PageResult<DomAppSceneMonRptDO> pageResult = domAppSceneMonRptService.getDomAppSceneMonRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomAppSceneMonRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出应用场景分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-app-scene-mon-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomAppSceneMonRptExcel(@Valid DomAppSceneMonRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomAppSceneMonRptDO> list = domAppSceneMonRptService.getDomAppSceneMonRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "应用场景分域监测报表.xls", "数据", DomAppSceneMonRptRespVO.class,
                        BeanUtils.toBean(list, DomAppSceneMonRptRespVO.class));
    }

}