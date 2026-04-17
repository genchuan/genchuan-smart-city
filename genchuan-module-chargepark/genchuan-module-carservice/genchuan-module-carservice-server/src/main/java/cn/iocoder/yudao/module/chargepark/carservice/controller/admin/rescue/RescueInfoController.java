package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoBatchDispatchReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoDispatchReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoEvaluateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoTransferReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.rescue.RescueInfoDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.pdf.PdfUtils;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.chargepark.carservice.service.rescue.RescueInfoService;
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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "救援服务 - 救援信息")
@RestController
@RequestMapping("/carservice/rescue-info")
@Validated
public class RescueInfoController {

    @Resource
    private RescueInfoService rescueInfoService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    // ========== 列表/详情 ==========

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 救援工单")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:query')")
    public CommonResult<PageResult<RescueInfoRespVO>> getRescueInfoPage(@Valid RescueInfoPageReqVO pageReqVO) {
        PageResult<RescueInfoDO> pageResult = rescueInfoService.getRescueInfoPage(pageReqVO);
        PageResult<RescueInfoRespVO> respPage = BeanUtils.toBean(pageResult, RescueInfoRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 救援工单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:query')")
    public CommonResult<RescueInfoRespVO> getRescueInfo(@RequestParam("id") Long id) {
        RescueInfoDO rescueInfo = rescueInfoService.getRescueInfo(id);
        RescueInfoRespVO respVO = BeanUtils.toBean(rescueInfo, RescueInfoRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 救援工单(format=excel|pdf,默认 excel)")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRescueInfo(@Valid RescueInfoPageReqVO pageReqVO,
                                 @RequestParam(value = "format", required = false, defaultValue = "excel") String format,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RescueInfoDO> list = rescueInfoService.getRescueInfoPage(pageReqVO).getList();
        List<RescueInfoRespVO> respList = BeanUtils.toBean(list, RescueInfoRespVO.class);
        injectUserNames(respList);
        if ("pdf".equalsIgnoreCase(format)) {
            PdfUtils.write(response, "救援信息.pdf", "救援信息台账",
                    PdfUtils.headers(
                            "id", "ID",
                            "userName", "用户",
                            "location", "救援位置",
                            "rescueType", "救援类型",
                            "status", "状态",
                            "rescueUserName", "救援人员",
                            "dispatchTime", "派发时间",
                            "finishTime", "完成时间",
                            "score", "评分",
                            "archiveStatus", "归档状态"),
                    respList);
        } else {
            ExcelUtils.write(response, "救援信息.xls", "数据", RescueInfoRespVO.class, respList);
        }
    }

    @GetMapping("/chart")
    @Operation(summary = "救援服务统计图表 - 地图+折线图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:query')")
    public CommonResult<RescueInfoChartRespVO> getRescueInfoChart() {
        return success(serviceOpReportService.chartRescue());
    }

    @GetMapping("/chart-drill-line")
    @Operation(summary = "各日期救援时效统计(折线图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:query')")
    public CommonResult<PageResult<RescueInfoRespVO>> drillRescueInfoLine(@Valid RescueInfoPageReqVO pageReqVO) {
        PageResult<RescueInfoDO> pageResult = rescueInfoService.getRescueInfoPage(pageReqVO);
        PageResult<RescueInfoRespVO> respPage = BeanUtils.toBean(pageResult, RescueInfoRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/chart-drill-card")
    @Operation(summary = "待救援记录统计(卡片钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:query')")
    public CommonResult<PageResult<RescueInfoRespVO>> drillRescueInfoCard(@Valid RescueInfoPageReqVO pageReqVO) {
        PageResult<RescueInfoDO> pageResult = rescueInfoService.getRescueInfoPage(pageReqVO);
        PageResult<RescueInfoRespVO> respPage = BeanUtils.toBean(pageResult, RescueInfoRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/count-by-status")
    @Operation(summary = "按状态统计救援工单数 - 卡片角标用")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:query')")
    public CommonResult<Map<String, Long>> countRescueInfoByStatus() {
        return success(serviceOpReportService.countRescueInfoByStatus());
    }

    // ========== 业务操作(状态机) ==========

    @PutMapping("/dispatch")
    @Operation(summary = "派发 - 待派发 → 待认领")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:dispatch')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> dispatchRescueInfo(@Valid @RequestBody RescueInfoDispatchReqVO reqVO) {
        rescueInfoService.dispatchRescueInfo(reqVO);
        return success(true);
    }

    @PutMapping("/batch-dispatch")
    @Operation(summary = "批量派发")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:batchDispatch')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> batchDispatchRescueInfo(@Valid @RequestBody RescueInfoBatchDispatchReqVO reqVO) {
        rescueInfoService.batchDispatchRescueInfo(reqVO);
        return success(true);
    }

    @PutMapping("/claim")
    @Operation(summary = "认领 - 待认领 → 处理中")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:claim')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> claimRescueInfo(@RequestParam("id") Long id) {
        rescueInfoService.claimRescueInfo(id);
        return success(true);
    }

    @PutMapping("/update-progress")
    @Operation(summary = "更新进度 - 处理中状态")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:updateProgress')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> updateRescueInfoProgress(@Valid @RequestBody RescueInfoUpdateProgressReqVO reqVO) {
        rescueInfoService.updateRescueInfoProgress(reqVO);
        return success(true);
    }

    @PutMapping("/transfer")
    @Operation(summary = "转派 - 处理中状态")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:transfer')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> transferRescueInfo(@Valid @RequestBody RescueInfoTransferReqVO reqVO) {
        rescueInfoService.transferRescueInfo(reqVO);
        return success(true);
    }

    @PutMapping("/evaluate")
    @Operation(summary = "评价 - 已完成状态")
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:evaluate')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> evaluateRescueInfo(@Valid @RequestBody RescueInfoEvaluateReqVO reqVO) {
        rescueInfoService.evaluateRescueInfo(reqVO);
        return success(true);
    }

    @PutMapping("/archive")
    @Operation(summary = "归档 - 已完成 → 已归档")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:rescue-info:archive')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> archiveRescueInfo(@RequestParam("id") Long id) {
        rescueInfoService.archiveRescueInfo(id);
        return success(true);
    }

    private void injectUserNames(List<RescueInfoRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(RescueInfoRespVO::getUserId, RescueInfoRespVO::setUserName),
                UserNameInjector.field(RescueInfoRespVO::getRescueUserId, RescueInfoRespVO::setRescueUserName));
    }

}
