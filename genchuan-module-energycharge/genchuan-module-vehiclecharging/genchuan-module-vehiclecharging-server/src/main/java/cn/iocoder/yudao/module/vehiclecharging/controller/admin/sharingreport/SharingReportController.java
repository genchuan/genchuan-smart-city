package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import cn.iocoder.yudao.module.vehiclecharging.service.sharingreport.SharingReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.*;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

@Tag(name = "汽车充电 - 分账报表")
@RestController
@RequestMapping("/vehiclecharging/sharing-report")
@Validated
public class SharingReportController {

    @Resource
    private SharingReportService sharingReportService;

    @GetMapping("/page")
    @Operation(summary = "分账报表分页（筛选、刷新）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:query')")
    public CommonResult<PageResult<SharingReportPageRespVO>> getSharingReportPage(@Valid SharingReportPageReqVO pageReqVO) {
        PageResult<SharingReportPageRespVO> pageResult = sharingReportService.getSharingReportPage(pageReqVO);
        return success(pageResult);
    }

    @GetMapping("/export")
    @Operation(summary = "导出分账报表 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSharingReportExcel(@Valid SharingReportPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        // 注意：这里的分页查询需要确保报表存在（复用之前的分页逻辑）
        PageResult<SharingReportPageRespVO> pageResult = sharingReportService.getSharingReportPage(pageReqVO);
        List<SharingReportPageRespVO> list = pageResult.getList();
        // 导出 Excel
        ExcelUtils.write(response, "分账报表.xls", "数据", SharingReportPageRespVO.class,
                BeanUtils.toBean(list, SharingReportPageRespVO.class));
    }

    @PostMapping("/customCreate")
    @Operation(summary = "自定义报表生成")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:create')")
    public CommonResult<Long> customCreate(@Valid @RequestBody SharingReportCustomCreateReqVO reqVO) {
        Long reportId = sharingReportService.createCustomReport(reqVO);
        return success(reportId);
    }

    @GetMapping("/exportSingle")
    @Operation(summary = "导出单条分账报表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSingleSharingReport(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        // 1. 查询报表主信息
        SharingReportDO report = sharingReportService.getSharingReport(id);
        if (report == null) {
            throw new RuntimeException("报表不存在");
        }
        // 2. 转换为导出 VO（复用分页响应 VO）
        SharingReportPageRespVO exportVO = BeanUtils.toBean(report, SharingReportPageRespVO.class);
        // 3. 导出 Excel
        ExcelUtils.write(response, "分账报表_" + report.getReportCode() + ".xls",
                "数据", SharingReportPageRespVO.class, Collections.singletonList(exportVO));    }

    @GetMapping("/print")
    @Operation(summary = "打印分账报表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:print')")
    public CommonResult<SharingReportPageRespVO> print(@RequestParam("id") Long id) {
        // 查询报表主信息
        SharingReportDO report = sharingReportService.getSharingReport(id);
        if (report == null) {
            throw new RuntimeException("报表不存在");
        }
        // 转换为响应 VO 并返回
        return success(BeanUtils.toBean(report, SharingReportPageRespVO.class));
    }

    @PostMapping("/recreate")
    @Operation(summary = "重新生成自定义报表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:create')")
    public CommonResult<Long> recreateCustomReport(@RequestParam("id") Long id) {
        Long reportId = sharingReportService.recreateCustomReport(id);
        return success(reportId);
    }

}
