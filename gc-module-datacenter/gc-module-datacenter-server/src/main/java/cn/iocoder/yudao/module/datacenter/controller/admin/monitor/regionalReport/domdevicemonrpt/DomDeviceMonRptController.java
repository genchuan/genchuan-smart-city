package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo.DomDeviceMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo.DomDeviceMonRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domdevicemonrpt.vo.DomDeviceMonRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domdevicemonrpt.DomDeviceMonRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domdevicemonrpt.DomDeviceMonRptService;

@Tag(name = "管理后台 - 设备分域监测报表")
@RestController
@RequestMapping("/datacenter/dom-device-mon-rpt")
@Validated
public class DomDeviceMonRptController {

    @Resource
    private DomDeviceMonRptService domDeviceMonRptService;

    @PostMapping("/create")
    @Operation(summary = "创建设备分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-device-mon-rpt:create')")
    public CommonResult<Long> createDomDeviceMonRpt(@Valid @RequestBody DomDeviceMonRptSaveReqVO createReqVO) {
        return success(domDeviceMonRptService.createDomDeviceMonRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新设备分域监测报表")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-device-mon-rpt:update')")
    public CommonResult<Boolean> updateDomDeviceMonRpt(@Valid @RequestBody DomDeviceMonRptSaveReqVO updateReqVO) {
        domDeviceMonRptService.updateDomDeviceMonRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除设备分域监测报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-device-mon-rpt:delete')")
    public CommonResult<Boolean> deleteDomDeviceMonRpt(@RequestParam("id") Long id) {
        domDeviceMonRptService.deleteDomDeviceMonRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得设备分域监测报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-device-mon-rpt:query')")
    public CommonResult<DomDeviceMonRptRespVO> getDomDeviceMonRpt(@RequestParam("id") Long id) {
        DomDeviceMonRptDO domDeviceMonRpt = domDeviceMonRptService.getDomDeviceMonRpt(id);
        return success(BeanUtils.toBean(domDeviceMonRpt, DomDeviceMonRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得设备分域监测报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-device-mon-rpt:query')")
    public CommonResult<PageResult<DomDeviceMonRptRespVO>> getDomDeviceMonRptPage(@Valid DomDeviceMonRptPageReqVO pageReqVO) {
        PageResult<DomDeviceMonRptDO> pageResult = domDeviceMonRptService.getDomDeviceMonRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDeviceMonRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出设备分域监测报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-device-mon-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDeviceMonRptExcel(@Valid DomDeviceMonRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDeviceMonRptDO> list = domDeviceMonRptService.getDomDeviceMonRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "设备分域监测报表.xls", "数据", DomDeviceMonRptRespVO.class,
                        BeanUtils.toBean(list, DomDeviceMonRptRespVO.class));
    }

}