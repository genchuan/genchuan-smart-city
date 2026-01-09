package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.coremetrics.vo.EmergCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.coremetrics.EmergCoreMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 应急核心指标")
@RestController
@RequestMapping("/industry/emerg-core-metrics")
@Validated
public class EmergCoreMetricsController {
    @Resource
    private EmergCoreMetricsService emergCoreMetricsService;


    @GetMapping("/list")
    @Operation(summary = "获得应急核心指标")
    @PreAuthorize("@ss.hasPermission('industry:emerg-core-metrics:query')")
    public CommonResult<EmergCoreMetricsRespVO> getEmergCoreMetrics(
            @Valid EmergCoreMetricsQueryReqVO emergCoreMetricsQueryReqVO
    ) {
        EmergCoreMetricsRespVO emergCoreMetricsRespVO = emergCoreMetricsService.getEmergCoreMetrics(emergCoreMetricsQueryReqVO);
        return success(emergCoreMetricsRespVO);
    }


}
