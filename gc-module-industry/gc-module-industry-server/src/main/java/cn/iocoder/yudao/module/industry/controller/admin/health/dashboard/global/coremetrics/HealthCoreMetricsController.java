package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.coremetrics.vo.HealthCoreMetricsRespVO;

import cn.iocoder.yudao.module.industry.service.health.dashboard.global.coremetrics.HealthCoreMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 卫健核心指标")
@RestController
@RequestMapping("/industry/health-core-metrics")
@Validated
public class HealthCoreMetricsController {
    @Resource
    private HealthCoreMetricsService healthCoreMetricsService;
    @GetMapping("/get")
    @Operation(summary = "获得卫健核心指标")
    @PreAuthorize("@ss.hasPermission('industry:health-core-metrics:query')")
    public CommonResult<HealthCoreMetricsRespVO> getHealthCoreMetrics(
            @Valid HealthCoreMetricsQueryReqVO healthCoreMetricsQueryReqVO
    ) {
        HealthCoreMetricsRespVO healthCoreMetricsRespVO = healthCoreMetricsService.getHealthCoreMetrics(healthCoreMetricsQueryReqVO);
        return success(healthCoreMetricsRespVO);
    }


}
