package cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.visitormgmt.visitorappoint.vo.*;
import cn.iocoder.yudao.module.accessmgmt.service.visitormgmt.visitorappoint.VisitorAppointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 访客预约")
@RestController
@RequestMapping("/accessmgmt/visitor-appoint")
@Validated
public class VisitorAppointController {

    @Resource
    private VisitorAppointService visitorAppointService;

    @GetMapping("/page")
    @Operation(summary = "获得访客预约分页")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:query')")
    public CommonResult<PageResult<VisitorAppointRespVO>> getPage(@Valid VisitorAppointPageReqVO pageReqVO) {
        return success(visitorAppointService.getVisitorAppointPage(pageReqVO));
    }

    @GetMapping("/get")
    @Operation(summary = "获得访客预约")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:query')")
    public CommonResult<VisitorAppointRespVO> get(@RequestParam("id") Long id) {
        return success(visitorAppointService.getVisitorAppoint(id));
    }

    @PostMapping("/create")
    @Operation(summary = "预约申请")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:create')")
    public CommonResult<Boolean> create(@Valid @RequestBody VisitorAppointCreateReqVO reqVO) {
        return success(visitorAppointService.createVisitorAppoint(reqVO));
    }

    @PutMapping("/audit")
    @Operation(summary = "信息审核")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:audit')")
    public CommonResult<Boolean> audit(@Valid @RequestBody VisitorAppointAuditReqVO reqVO) {
        return success(visitorAppointService.auditVisitorAppoint(reqVO));
    }

    @PutMapping("/reject")
    @Operation(summary = "审核驳回")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:reject')")
    public CommonResult<Boolean> reject(@Valid @RequestBody VisitorAppointRejectReqVO reqVO) {
        return success(visitorAppointService.rejectVisitorAppoint(reqVO));
    }

    @PostMapping("/generate")
    @Operation(summary = "凭证生成")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:generate')")
    public CommonResult<VisitorAppointGenerateRespVO> generate(@Valid @RequestBody VisitorAppointGenerateReqVO reqVO) {
        return success(visitorAppointService.generateVisitorAppoint(reqVO));
    }

    @PostMapping("/verify")
    @Operation(summary = "到访验证")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:verify')")
    public CommonResult<VisitorAppointVerifyRespVO> verify(@Valid @RequestBody VisitorAppointVerifyReqVO reqVO) {
        return success(visitorAppointService.verifyVisitorAppoint(reqVO));
    }

    @PutMapping("/cancel")
    @Operation(summary = "预约取消")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:cancel')")
    public CommonResult<Boolean> cancel(@Valid @RequestBody VisitorAppointCancelReqVO reqVO) {
        return success(visitorAppointService.cancelVisitorAppoint(reqVO));
    }

    @PutMapping("/leave")
    @Operation(summary = "确认离园")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:leave')")
    public CommonResult<Boolean> leave(@Valid @RequestBody VisitorAppointLeaveReqVO reqVO) {
        return success(visitorAppointService.leaveVisitorAppoint(reqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出访客预约 Excel")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void export(@Valid VisitorAppointPageReqVO pageReqVO, HttpServletResponse response) throws IOException {
        List<VisitorAppointRespVO> list = visitorAppointService.getVisitorAppointList(pageReqVO);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String dateStr = java.time.LocalDate.now().toString();
        String fileName = URLEncoder.encode("访客预约_" + dateStr + ".xls", StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8", "");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);
        ExcelUtils.write(response, "访客预约.xls", "数据", VisitorAppointRespVO.class,
                BeanUtils.toBean(list, VisitorAppointRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "访客预约态势")
    @PreAuthorize("@ss.hasPermission('accessmgmt:visitor-appoint:query')")
    public CommonResult<VisitorAppointChartRespVO> chart(
            @Parameter(name = "startTime", description = "统计开始时间") @RequestParam(value = "startTime", required = false) String startTime,
            @Parameter(name = "endTime", description = "统计结束时间") @RequestParam(value = "endTime", required = false) String endTime) {
        return success(visitorAppointService.getVisitorAppointChart(startTime, endTime));
    }

}
