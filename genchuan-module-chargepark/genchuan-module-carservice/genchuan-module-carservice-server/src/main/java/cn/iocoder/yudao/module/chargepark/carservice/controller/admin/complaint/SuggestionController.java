package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionFeedbackReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionHandleReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.SuggestionUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.SuggestionDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.pdf.PdfUtils;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.complaint.SuggestionService;
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

@Tag(name = "投诉调解 - 意见建议")
@RestController
@RequestMapping("/carservice/suggestion")
@Validated
public class SuggestionController {

    @Resource
    private SuggestionService suggestionService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 意见建议")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:query')")
    public CommonResult<PageResult<SuggestionRespVO>> getSuggestionPage(@Valid SuggestionPageReqVO pageReqVO) {
        PageResult<SuggestionDO> pageResult = suggestionService.getSuggestionPage(pageReqVO);
        PageResult<SuggestionRespVO> respPage = BeanUtils.toBean(pageResult, SuggestionRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 意见建议")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:query')")
    public CommonResult<SuggestionRespVO> getSuggestion(@RequestParam("id") Long id) {
        SuggestionDO suggestion = suggestionService.getSuggestion(id);
        SuggestionRespVO respVO = BeanUtils.toBean(suggestion, SuggestionRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 意见建议(format=excel|pdf,默认 excel)")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSuggestion(@Valid SuggestionPageReqVO pageReqVO,
                                 @RequestParam(value = "format", required = false, defaultValue = "excel") String format,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SuggestionDO> list = suggestionService.getSuggestionPage(pageReqVO).getList();
        List<SuggestionRespVO> respList = BeanUtils.toBean(list, SuggestionRespVO.class);
        injectUserNames(respList);
        // 服务器 JVM 时区可能不是 Asia/Shanghai，导出时把 LocalDateTime 转为北京时间
        respList.forEach(vo -> {
            vo.setSubmitTime(toBeijing(vo.getSubmitTime()));
            vo.setFeedbackTime(toBeijing(vo.getFeedbackTime()));
            vo.setCreateTime(toBeijing(vo.getCreateTime()));
            vo.setUpdateTime(toBeijing(vo.getUpdateTime()));
        });
        if ("pdf".equalsIgnoreCase(format)) {
            PdfUtils.write(response, "意见建议.pdf", "意见建议台账",
                    PdfUtils.headers(
                            "id", "ID",
                            "userName", "用户",
                            "content", "意见内容",
                            "submitTime", "提交时间",
                            "status", "状态",
                            "handleUserName", "处理人",
                            "progress", "进度",
                            "feedbackContent", "反馈",
                            "feedbackTime", "反馈时间"),
                    respList);
        } else {
            ExcelUtils.write(response, "意见建议.xls", "数据", SuggestionRespVO.class, respList);
        }
    }

    @GetMapping("/chart")
    @Operation(summary = "意见建议统计图表 - 折线图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:query')")
    public CommonResult<SuggestionChartRespVO> getSuggestionChart(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartSuggestion(startTime, endTime));
    }

    @GetMapping("/chart-drill-line")
    @Operation(summary = "各日期意见量统计(折线图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:query')")
    public CommonResult<PageResult<SuggestionRespVO>> drillSuggestionLine(@Valid SuggestionPageReqVO pageReqVO) {
        PageResult<SuggestionDO> pageResult = suggestionService.getSuggestionPage(pageReqVO);
        PageResult<SuggestionRespVO> respPage = BeanUtils.toBean(pageResult, SuggestionRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/count-by-status")
    @Operation(summary = "按状态统计意见数 - 卡片角标用")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:query')")
    public CommonResult<java.util.Map<String, Long>> countSuggestionByStatus() {
        return success(serviceOpReportService.countSuggestionByStatus());
    }

    // ========== 业务操作(状态机) ==========

    @PutMapping("/handle")
    @Operation(summary = "处理 - 待处理 → 处理中(首次接单)")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:handle')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> handleSuggestion(@Valid @RequestBody SuggestionHandleReqVO reqVO) {
        suggestionService.handleSuggestion(reqVO);
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新进度 - 处理中 → 处理中")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:updateProgress')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> updateSuggestionProgress(@Valid @RequestBody SuggestionUpdateProgressReqVO reqVO) {
        suggestionService.updateSuggestionProgress(reqVO);
        return success(true);
    }

    @PutMapping("/feedback")
    @Operation(summary = "反馈 - 处理中 → 已完成")
    @PreAuthorize("@ss.hasPermission('carservice:suggestion:feedback')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> feedbackSuggestion(@Valid @RequestBody SuggestionFeedbackReqVO reqVO) {
        suggestionService.feedbackSuggestion(reqVO);
        return success(true);
    }

    private void injectUserNames(List<SuggestionRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(SuggestionRespVO::getUserId, SuggestionRespVO::setUserName),
                UserNameInjector.field(SuggestionRespVO::getHandleUserId, SuggestionRespVO::setHandleUserName));
    }

    /** 把 JVM 本地时区的 LocalDateTime 转换为 Asia/Shanghai(北京)时间，导出场景使用。 */
    private static java.time.LocalDateTime toBeijing(java.time.LocalDateTime ldt) {
        if (ldt == null) return null;
        return ldt.atZone(java.time.ZoneId.systemDefault())
                .withZoneSameInstant(java.time.ZoneId.of("Asia/Shanghai"))
                .toLocalDateTime();
    }

}
