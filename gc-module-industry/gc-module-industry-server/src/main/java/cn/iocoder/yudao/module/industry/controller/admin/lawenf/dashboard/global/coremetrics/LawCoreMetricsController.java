package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.coremetrics.vo.LawCoreMetricsRespVO;

import cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.coremetrics.LawCoreMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 执法核心指标")
@RestController
@RequestMapping("/industry/law-core-metrics")
@Validated
public class LawCoreMetricsController {
    @Resource
    private LawCoreMetricsService lawCoreMetricsService;
    @GetMapping("/get2")
    @Operation(summary = "获得执法核心指标2")
    @PreAuthorize("@ss.hasPermission('industry:law-core-metrics:query')")
    public CommonResult<LawCoreMetricsRespVO> getLawCoreMetrics2(
            @Valid LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO
    ) {
        LawCoreMetricsRespVO lawCoreMetricsRespVO = lawCoreMetricsService.getLawCoreMetrics2(lawCoreMetricsQueryReqVO);
        return success(lawCoreMetricsRespVO);
    }

    @GetMapping("/get")
    @Operation(summary = "获得执法核心指标")
    @PreAuthorize("@ss.hasPermission('industry:law-core-metrics:query')")
    public CommonResult<LawCoreMetricsRespVO> getLawCoreMetrics(
            @Valid LawCoreMetricsQueryReqVO lawCoreMetricsQueryReqVO
    ) {
        LawCoreMetricsRespVO lawCoreMetricsRespVO = lawCoreMetricsService.getLawCoreMetrics(lawCoreMetricsQueryReqVO);
        return success(lawCoreMetricsRespVO);
    }


}
