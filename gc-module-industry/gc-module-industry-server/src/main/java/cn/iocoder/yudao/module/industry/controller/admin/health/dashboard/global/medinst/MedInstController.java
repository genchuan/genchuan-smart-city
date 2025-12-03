package cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.MedInstQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.MedInstRespVO;

import cn.iocoder.yudao.module.industry.controller.admin.health.dashboard.global.medinst.vo.TreatTrendVO;
import cn.iocoder.yudao.module.industry.service.health.dashboard.global.medinst.MedInstService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 卫健医疗机构分布视图")
@RestController
@RequestMapping("/industry/med-inst")
@Validated
public class MedInstController {
    @Resource
    private MedInstService medInstService;
    @GetMapping("/get")
    @Operation(summary = "获得医疗机构分布视图")
    @PreAuthorize("@ss.hasPermission('industry:med-inst:query')")
    public CommonResult<MedInstRespVO> getMedInst(
            @Valid MedInstQueryReqVO medInstQueryReqVO
    ) {
        MedInstRespVO medInstRespVO = medInstService.getMedInst(medInstQueryReqVO);
        return success(medInstRespVO);
    }

    @GetMapping("/treat-trend")
    @Operation(summary = "获得机构接诊趋势（近7天）")
    @PreAuthorize("@ss.hasPermission('industry:med-inst:query')")
    public CommonResult<List<TreatTrendVO>> getInstTrend(
            @RequestParam("orgId") Long orgId
    ) {
        return success(medInstService.getInstTrend(orgId));
    }

}
