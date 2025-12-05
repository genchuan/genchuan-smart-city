package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.FacOverviewReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.FacOverviewRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.MuniFacQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.vo.MuniFacRespVO;
import cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.MuniFacService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 市政设施专题")
@RestController
@RequestMapping("/industry/muni-fac")
@Validated
public class MuniFacController {
    @Resource
    private MuniFacService muniFacService;
    @GetMapping("/get")
    @Operation(summary = "获得市政设施专题")
    @PreAuthorize("@ss.hasPermission('industry:muni-fac:query')")
    public CommonResult<MuniFacRespVO> getMuniFac(
            @Valid MuniFacQueryReqVO muniFacQueryReqVO
    ) {
        MuniFacRespVO muniFacRespVO = muniFacService.getMuniFac(muniFacQueryReqVO);
        return success(muniFacRespVO);
    }

    /**
     * 获得市政设施概览数据
     * @param facOverviewReqVO
     * @return
     */
    @GetMapping("/fac-overview/get")
    @Operation(summary = "获得市政设施概览数据")
    @PreAuthorize("@ss.hasPermission('industry:muni-fac-fac-overview:query')")
    public CommonResult<FacOverviewRespVO> getMuniFacOverview(
            @Valid FacOverviewReqVO facOverviewReqVO
    ) {
        FacOverviewRespVO facOverviewRespVO = muniFacService.getMuniFacOverview(facOverviewReqVO);
        return success(facOverviewRespVO);
    }

}
