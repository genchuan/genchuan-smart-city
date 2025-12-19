package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo.UniversalCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.coremetrics.vo.UniversalCoreMetricsRespVO;

import cn.iocoder.yudao.module.industry.service.universal.dashboard.global.coremetrics.UniversalCoreMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 通用大屏核心指标看板")
@RestController
@RequestMapping("/industry/universal-coreMetrics")
@Validated
public class UniversalCoreMetricsController {
    @Resource
    private UniversalCoreMetricsService universalCoreMetricsService;

    @GetMapping("/get")
    @Operation(summary = "获得通用大屏核心指标看板")
    @PreAuthorize("@ss.hasPermission('industry:universal-coreMetrics:query')")
    public CommonResult<UniversalCoreMetricsRespVO> getUniversalCoreMetrics(
            @Valid UniversalCoreMetricsQueryReqVO universalCoreMetricsQueryReqVO
    ) {
        UniversalCoreMetricsRespVO universalCoreMetricsRespVO = universalCoreMetricsService.getUniversalCoreMetrics(universalCoreMetricsQueryReqVO);
        return success(universalCoreMetricsRespVO);
    }
}
