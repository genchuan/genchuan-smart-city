package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateConfirmReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateMediateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediatePageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.complaint.vo.DisputeMediateUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.complaint.DisputeMediateDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.pdf.PdfUtils;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.complaint.DisputeMediateService;
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

@Tag(name = "投诉调解 - 纠纷调解")
@RestController
@RequestMapping("/carservice/dispute-mediate")
@Validated
public class DisputeMediateController {

    @Resource
    private DisputeMediateService disputeMediateService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 纠纷调解")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:query')")
    public CommonResult<PageResult<DisputeMediateRespVO>> getDisputeMediatePage(@Valid DisputeMediatePageReqVO pageReqVO) {
        PageResult<DisputeMediateDO> pageResult = disputeMediateService.getDisputeMediatePage(pageReqVO);
        PageResult<DisputeMediateRespVO> respPage = BeanUtils.toBean(pageResult, DisputeMediateRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 纠纷调解")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:query')")
    public CommonResult<DisputeMediateRespVO> getDisputeMediate(@RequestParam("id") Long id) {
        DisputeMediateDO disputeMediate = disputeMediateService.getDisputeMediate(id);
        DisputeMediateRespVO respVO = BeanUtils.toBean(disputeMediate, DisputeMediateRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 纠纷调解(format=excel|pdf,默认 excel)")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDisputeMediate(@Valid DisputeMediatePageReqVO pageReqVO,
                                     @RequestParam(value = "format", required = false, defaultValue = "excel") String format,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DisputeMediateDO> list = disputeMediateService.getDisputeMediatePage(pageReqVO).getList();
        List<DisputeMediateRespVO> respList = BeanUtils.toBean(list, DisputeMediateRespVO.class);
        injectUserNames(respList);
        if ("pdf".equalsIgnoreCase(format)) {
            PdfUtils.write(response, "纠纷调解.pdf", "纠纷调解台账",
                    PdfUtils.headers(
                            "userName", "用户",
                            "merchantId", "商户",
                            "content", "纠纷内容",
                            "submitTime", "发起时间",
                            "status", "状态",
                            "mediateUserName", "调解人",
                            "progress", "调解进度",
                            "confirmTime", "确认时间"),
                    respList);
        } else {
            ExcelUtils.write(response, "纠纷调解.xls", "数据", DisputeMediateRespVO.class, respList);
        }
    }

    @GetMapping("/chart")
    @Operation(summary = "纠纷调解统计图表 - 折线图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:query')")
    public CommonResult<DisputeMediateChartRespVO> getDisputeMediateChart(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartDisputeMediate(startTime, endTime));
    }

    @GetMapping("/chart-drill-line")
    @Operation(summary = "各日期纠纷量统计(折线图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:query')")
    public CommonResult<PageResult<DisputeMediateRespVO>> drillDisputeMediateLine(@Valid DisputeMediatePageReqVO pageReqVO) {
        PageResult<DisputeMediateDO> pageResult = disputeMediateService.getDisputeMediatePage(pageReqVO);
        PageResult<DisputeMediateRespVO> respPage = BeanUtils.toBean(pageResult, DisputeMediateRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/count-by-status")
    @Operation(summary = "按状态统计纠纷数 - 卡片角标用")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:query')")
    public CommonResult<java.util.Map<String, Long>> countDisputeMediateByStatus() {
        return success(serviceOpReportService.countDisputeMediateByStatus());
    }

    // ========== 业务操作(状态机) ==========

    @PutMapping("/mediate")
    @Operation(summary = "调解 - 待调解 → 调解中(首次接单)")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:mediate')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> mediateDisputeMediate(@Valid @RequestBody DisputeMediateMediateReqVO reqVO) {
        disputeMediateService.mediateDisputeMediate(reqVO);
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新进度 - 调解中 → 调解中")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:updateProgress')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> updateDisputeMediateProgress(@Valid @RequestBody DisputeMediateUpdateProgressReqVO reqVO) {
        disputeMediateService.updateDisputeMediateProgress(reqVO);
        return success(true);
    }

    @PutMapping("/confirm")
    @Operation(summary = "确认 - 调解中 → 已关闭")
    @PreAuthorize("@ss.hasPermission('carservice:dispute-mediate:confirm')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> confirmDisputeMediate(@Valid @RequestBody DisputeMediateConfirmReqVO reqVO) {
        disputeMediateService.confirmDisputeMediate(reqVO);
        return success(true);
    }

    private void injectUserNames(List<DisputeMediateRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(DisputeMediateRespVO::getUserId, DisputeMediateRespVO::setUserName),
                UserNameInjector.field(DisputeMediateRespVO::getMediateUserId, DisputeMediateRespVO::setMediateUserName));
    }

}
