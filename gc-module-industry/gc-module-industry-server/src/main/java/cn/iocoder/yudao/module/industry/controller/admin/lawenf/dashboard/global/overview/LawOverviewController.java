package cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.LawOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.lawenf.dashboard.global.overview.vo.LawOverviewRespVO;

import cn.iocoder.yudao.module.industry.service.lawenf.dashboard.global.overview.LawOverviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 执法全域数据概览")
@RestController
@RequestMapping("/industry/law-overview")
@Validated
public class LawOverviewController {
    @Resource
    private LawOverviewService lawOverviewService;
    @GetMapping("/get")
    @Operation(summary = "获得执法全域数据概览")
    @PreAuthorize("@ss.hasPermission('industry:law-overview:query')")
    public CommonResult<LawOverviewRespVO> getLawOverview(
            @Valid LawOverviewQueryReqVO lawOverviewQueryReqVO
    ) {
        LawOverviewRespVO lawOverviewRespVO = lawOverviewService.getLawOverview(lawOverviewQueryReqVO);
        return success(lawOverviewRespVO);
    }


}
