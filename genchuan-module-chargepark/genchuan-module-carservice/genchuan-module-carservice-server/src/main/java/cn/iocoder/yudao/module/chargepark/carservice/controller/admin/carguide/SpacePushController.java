package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushBatchPushReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushChartRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.SpacePushDO;
import cn.iocoder.yudao.module.chargepark.carservice.framework.pdf.PdfUtils;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.StationNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.chargepark.carservice.service.carguide.SpacePushService;
import cn.iocoder.yudao.module.chargepark.carservice.service.decision.ServiceOpReportService;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
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

@Tag(name = "车辆引导 - 空位推送")
@RestController
@RequestMapping("/carservice/space-push")
@Validated
public class SpacePushController {

    @Resource
    private SpacePushService spacePushService;

    @Resource
    private ServiceOpReportService serviceOpReportService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private StationInfoApi stationInfoApi;

    @GetMapping("/page")
    @Operation(summary = "筛选/刷新 空位推送")
    @PreAuthorize("@ss.hasPermission('carservice:space-push:query')")
    public CommonResult<PageResult<SpacePushRespVO>> getSpacePushPage(@Valid SpacePushPageReqVO pageReqVO) {
        PageResult<SpacePushDO> pageResult = spacePushService.getSpacePushPage(pageReqVO);
        PageResult<SpacePushRespVO> respPage = BeanUtils.toBean(pageResult, SpacePushRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/get")
    @Operation(summary = "详情 - 空位推送")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('carservice:space-push:query')")
    public CommonResult<SpacePushRespVO> getSpacePush(@RequestParam("id") Long id) {
        SpacePushDO spacePush = spacePushService.getSpacePush(id);
        SpacePushRespVO respVO = BeanUtils.toBean(spacePush, SpacePushRespVO.class);
        if (respVO != null) {
            injectUserNames(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/export")
    @Operation(summary = "导出 - 空位推送(format=excel|pdf,默认 excel)")
    @PreAuthorize("@ss.hasPermission('carservice:space-push:query')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSpacePush(@Valid SpacePushPageReqVO pageReqVO,
                                @RequestParam(value = "format", required = false, defaultValue = "excel") String format,
                                HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SpacePushDO> list = spacePushService.getSpacePushPage(pageReqVO).getList();
        List<SpacePushRespVO> respList = BeanUtils.toBean(list, SpacePushRespVO.class);
        injectUserNames(respList);
        if ("pdf".equalsIgnoreCase(format)) {
            PdfUtils.write(response, "空位推送.pdf", "空位推送台账",
                    PdfUtils.headers(
                            "id", "ID",
                            "userName", "用户",
                            "stationId", "场站ID",
                            "spaceInfo", "空位信息",
                            "pushTime", "推送时间",
                            "status", "状态",
                            "pushResult", "推送结果",
                            "feedbackTime", "反馈时间"),
                    respList);
        } else {
            ExcelUtils.write(response, "空位推送.xls", "数据", SpacePushRespVO.class, respList);
        }
    }

    @GetMapping("/chart")
    @Operation(summary = "空位推送统计图表 - 折线图+卡片")
    @PreAuthorize("@ss.hasPermission('carservice:space-push:query')")
    public CommonResult<SpacePushChartRespVO> getSpacePushChart(@RequestParam(value = "startTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @RequestParam(value = "endTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {
        return success(serviceOpReportService.chartSpacePush(startTime, endTime));
    }

    @GetMapping("/chart-drill-line")
    @Operation(summary = "各日期推送量统计(折线图钻取) - 同 page 接口")
    @PreAuthorize("@ss.hasPermission('carservice:space-push:query')")
    public CommonResult<PageResult<SpacePushRespVO>> drillSpacePushLine(@Valid SpacePushPageReqVO pageReqVO) {
        PageResult<SpacePushDO> pageResult = spacePushService.getSpacePushPage(pageReqVO);
        PageResult<SpacePushRespVO> respPage = BeanUtils.toBean(pageResult, SpacePushRespVO.class);
        injectUserNames(respPage.getList());
        return success(respPage);
    }

    @GetMapping("/count-by-status")
    @Operation(summary = "按状态统计推送数 - 卡片角标用")
    @PreAuthorize("@ss.hasPermission('carservice:space-push:query')")
    public CommonResult<java.util.Map<String, Long>> countSpacePushByStatus() {
        return success(serviceOpReportService.countSpacePushByStatus());
    }

    @PutMapping("/push")
    @Operation(summary = "推送 - 待推送 → 已推送")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('carservice:space-push:push')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> pushSpacePush(@RequestParam("id") Long id) {
        spacePushService.pushSpacePush(id);
        return success(true);
    }

    @PutMapping("/batch-push")
    @Operation(summary = "批量推送")
    @PreAuthorize("@ss.hasPermission('carservice:space-push:batchPush')")
    @ApiAccessLog(operateType = UPDATE)
    public CommonResult<Boolean> batchPushSpacePush(@Valid @RequestBody SpacePushBatchPushReqVO reqVO) {
        spacePushService.batchPushSpacePush(reqVO);
        return success(true);
    }

    private void injectUserNames(List<SpacePushRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(SpacePushRespVO::getUserId, SpacePushRespVO::setUserName));
        StationNameInjector.inject(list, stationInfoApi,
                StationNameInjector.field(SpacePushRespVO::getStationId, SpacePushRespVO::setStationName));
    }

}
