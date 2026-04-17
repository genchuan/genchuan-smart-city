package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingreport.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.settlementbill.SettlementBillDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import cn.iocoder.yudao.module.vehiclecharging.framework.common.utils.TimeRangeParser;
import cn.iocoder.yudao.module.vehiclecharging.service.sharingreport.SharingReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.*;

import java.time.LocalDateTime;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.*;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

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

    @GetMapping("/exportSingle")
    @Operation(summary = "导出单条分账报表")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSingleSharingReport(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {
        // 调用 Service 生成 Excel 并写入响应
        sharingReportService.exportSingleReport(id, response);
    }

    @PostMapping("/customCreate")
    @Operation(summary = "自定义报表生成（返回分页结果）")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:sharing_report:create')")
    public CommonResult<PageResult<SharingReportPageRespVO>> customCreate(@Valid @RequestBody SharingReportPageReqVO reqVO) {
        PageResult<SharingReportPageRespVO> pageResult = sharingReportService.getSharingReportPage(reqVO);
        return success(pageResult);
    }


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
