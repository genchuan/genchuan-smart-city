package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo.HealthOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.overview.vo.HealthOverviewRespVO;

import cn.iocoder.yudao.module.industry.service.health.dashboard.global.overview.HealthOverviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 卫健全域数据概览")
@RestController
@RequestMapping("/industry/health-overview")
@Validated
public class HealthOverviewController {
    @Resource
    private HealthOverviewService healthOverviewService;
    @GetMapping("/get")
    @Operation(summary = "获得卫健全域数据概览")
    @PreAuthorize("@ss.hasPermission('industry:health-overview:query')")
    public CommonResult<HealthOverviewRespVO> getHealthOverview(
            @Valid HealthOverviewQueryReqVO healthOverviewQueryReqVO
    ) {
        HealthOverviewRespVO healthOverviewRespVO = healthOverviewService.getHealthOverview(healthOverviewQueryReqVO);
        return success(healthOverviewRespVO);
    }


}
