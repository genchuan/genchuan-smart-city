package cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection;

import org.springframework.format.annotation.DateTimeFormat;
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
import java.time.*;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.interconnection.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.interconnection.InterconnectionDO;
import cn.iocoder.yudao.module.vehiclecharging.service.interconnection.InterconnectionService;

@Tag(name = "管理后台 - 互联互通表")
@RestController
@RequestMapping("/vehiclecharging/interconnection")
@Validated
public class InterconnectionController {

    @Resource
    private InterconnectionService interconnectionService;

    @GetMapping("/page")
    @Operation(summary = "获得互联互通表分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:interconnection:query')")
    public CommonResult<PageResult<InterconnectionRespVO>> getInterconnectionPage(@Valid InterconnectionPageReqVO pageReqVO) {
        PageResult<InterconnectionDO> pageResult = interconnectionService.getInterconnectionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InterconnectionRespVO.class));
    }

    @PostMapping("/apply")
    @Operation(summary = "申请互联互通表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:interconnection:apply')")
    public CommonResult<Long> applyInterconnection(@Valid @RequestBody InterconnectionApplyReqVO applyReqVO) {
        return success(interconnectionService.applyInterconnection(applyReqVO));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核互联互通表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:interconnection:audit')")
    public CommonResult<Boolean> auditInterconnection(@Valid @RequestBody InterconnectionAuditReqVO auditReqVO) {
        interconnectionService.auditInterconnection(auditReqVO);
        return success(true);
    }

    @PutMapping("/close")
    @Operation(summary = "关闭互联互通表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:interconnection:close')")
    public CommonResult<Boolean> closeInterconnection(@Valid @RequestBody InterconnectionCloseReqVO closeReqVO) {
        interconnectionService.closeInterconnection(closeReqVO);
        return success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出互联互通表 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:interconnection:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInterconnectionExcel(@Valid InterconnectionExportReqVO exportReqVO, HttpServletResponse resp) throws IOException {
        exportReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InterconnectionDO> list = interconnectionService.getInterconnectionPage(exportReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(resp, "互联互通表.xls", "数据", InterconnectionRespVO.class, BeanUtils.toBean(list, InterconnectionRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得互联互通表")
    @Parameter(name = "id", description = "编号", required = true, example = "4001")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:interconnection:query')")
    public CommonResult<InterconnectionRespVO> getInterconnection(@RequestParam("id") Long id) {
        InterconnectionDO interconnection = interconnectionService.getInterconnection(id);
        return success(BeanUtils.toBean(interconnection, InterconnectionRespVO.class));
    }

    @PostMapping("/reapply")
    @Operation(summary = "重新申请互联互通表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:interconnection:reapply')")
    public CommonResult<Boolean> reapplyInterconnection(@RequestParam("id") Long id) {
        interconnectionService.reapplyInterconnection(id);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得互联互通表图表数据")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:interconnection:query')")
    public CommonResult<InterconnectionChartRespVO> getInterconnectionChart() {
        return success(interconnectionService.getInterconnectionChart());
    }

}