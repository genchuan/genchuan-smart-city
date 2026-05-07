package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealApproveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealBatchAuditReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealExecuteReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealFeedbackReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealRejectReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.UserAppealRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.UserAppealDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.pdf.PdfUtils;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.complaint.UserAppealService;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "投诉调解 - 用户申诉")
@RestController
@RequestMapping("/carservice/user-appeal")
@Validated
public class UserAppealController {

    @Resource
    private UserAppealService userAppealService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 用户申诉")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:query')")
    public CommonResult<PageResult<UserAppealRespVO>> getUserAppealPage(@Valid UserAppealPageReqVO pageReqVO) {
        PageResult<UserAppealDO> pageResult = userAppealService.getUserAppealPage(pageReqVO);
        PageResult<UserAppealRespVO> respPage = BeanUtils.toBean(pageResult, UserAppealRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 用户申诉")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:query')")
    public CommonResult<UserAppealRespVO> getUserAppeal(@RequestParam("id") Long id) {
        UserAppealDO userAppeal = userAppealService.getUserAppeal(id);
        UserAppealRespVO respVO = BeanUtils.toBean(userAppeal, UserAppealRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 用户申诉(format=excel|pdf,默认 excel)")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUserAppeal(@Valid UserAppealPageReqVO pageReqVO,
                                 @RequestParam(value = "format", required = false, defaultValue = "excel") String format,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UserAppealDO> list = userAppealService.getUserAppealPage(pageReqVO).getList();
        List<UserAppealRespVO> respList = BeanUtils.toBean(list, UserAppealRespVO.class);
        injectUserNames(respList);
        // 服务器 JVM 时区可能不是 Asia/Shanghai，导出时把 LocalDateTime 转为北京时间
        respList.forEach(vo -> {
            vo.setSubmitTime(toBeijing(vo.getSubmitTime()));
            vo.setAuditTime(toBeijing(vo.getAuditTime()));
            vo.setFeedbackTime(toBeijing(vo.getFeedbackTime()));
            vo.setCreateTime(toBeijing(vo.getCreateTime()));
            vo.setUpdateTime(toBeijing(vo.getUpdateTime()));
        });
        if ("pdf".equalsIgnoreCase(format)) {
            PdfUtils.write(response, "用户申诉.pdf", "用户申诉台账",
                    PdfUtils.headers(
                            "userName", "用户",
                            "orderId", "订单",
                            "content", "申诉内容",
                            "submitTime", "提交时间",
                            "status", "状态",
                            "auditUserName", "审核人",
                            "auditTime", "审核时间",
                            "handleUserName", "处置人",
                            "progress", "处置进度",
                            "feedbackTime", "反馈时间"),
                    respList);
        } else {
            ExcelUtils.write(response, "用户申诉.xls", "数据", UserAppealRespVO.class, respList);
        }
    }

    @GetMapping("/chart")
    @Operation(summary = "用户申诉统计图表 - 折线图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:query')")
    public CommonResult<UserAppealChartRespVO> getUserAppealChart(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartUserAppeal(startTime, endTime));
    }

    @GetMapping("/chart-drill-line")
    @Operation(summary = "各日期申诉量统计(折线图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:query')")
    public CommonResult<PageResult<UserAppealRespVO>> drillUserAppealLine(@Valid UserAppealPageReqVO pageReqVO) {
        PageResult<UserAppealDO> pageResult = userAppealService.getUserAppealPage(pageReqVO);
        PageResult<UserAppealRespVO> respPage = BeanUtils.toBean(pageResult, UserAppealRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/count-by-status")
    @Operation(summary = "按状态统计申诉数 - 卡片角标用")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:query')")
    public CommonResult<java.util.Map<String, Long>> countUserAppealByStatus() {
        return success(serviceOpReportService.countUserAppealByStatus());
    }

    // ========== 业务操作(状态机) ==========

    @PutMapping("/approve")
    @Operation(summary = "通过 - 待审核 → 待处置")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:approve')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> approveUserAppeal(@Valid @RequestBody UserAppealApproveReqVO reqVO) {
        UserAppealAuditReqVO auditReq = new UserAppealAuditReqVO();
        auditReq.setId(reqVO.getId());
        auditReq.setApproved(Boolean.TRUE);
        auditReq.setAuditRemark(reqVO.getAuditRemark());
        userAppealService.auditUserAppeal(auditReq);
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "驳回 - 待审核 → 已完成(驳回)")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:reject')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> rejectUserAppeal(@Valid @RequestBody UserAppealRejectReqVO reqVO) {
        UserAppealAuditReqVO auditReq = new UserAppealAuditReqVO();
        auditReq.setId(reqVO.getId());
        auditReq.setApproved(Boolean.FALSE);
        auditReq.setRejectReason(reqVO.getRejectReason());
        userAppealService.auditUserAppeal(auditReq);
        return success(true);
    }

    @PutMapping("/batch-audit")
    @Operation(summary = "批量审核")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:batchAudit')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> batchAuditUserAppeal(@Valid @RequestBody UserAppealBatchAuditReqVO reqVO) {
        userAppealService.batchAuditUserAppeal(reqVO);
        return success(true);
    }

    @PutMapping("/execute")
    @Operation(summary = "执行 - 标记处置人为当前登录用户,触发处置流程")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:execute')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> executeUserAppeal(@Valid @RequestBody UserAppealExecuteReqVO reqVO) {
        userAppealService.executeUserAppeal(reqVO);
        return success(true);
    }

    @PutMapping("/feedback")
    @Operation(summary = "反馈 - 待处置 → 已完成")
    @PreAuthorize("@ss.hasPermission('carservice:user-appeal:feedback')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> feedbackUserAppeal(@Valid @RequestBody UserAppealFeedbackReqVO reqVO) {
        userAppealService.feedbackUserAppeal(reqVO);
        return success(true);
    }

    private void injectUserNames(List<UserAppealRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(UserAppealRespVO::getUserId, UserAppealRespVO::setUserName),
                UserNameInjector.field(UserAppealRespVO::getAuditUserId, UserAppealRespVO::setAuditUserName),
                UserNameInjector.field(UserAppealRespVO::getHandleUserId, UserAppealRespVO::setHandleUserName));
    }

    /** 把 JVM 本地时区的 LocalDateTime 转换为 Asia/Shanghai(北京)时间，导出场景使用。 */
    private static java.time.LocalDateTime toBeijing(java.time.LocalDateTime ldt) {
        if (ldt == null) return null;
        return ldt.atZone(java.time.ZoneId.systemDefault())
                .withZoneSameInstant(java.time.ZoneId.of("Asia/Shanghai"))
                .toLocalDateTime();
    }

}
