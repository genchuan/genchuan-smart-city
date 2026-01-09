package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo.FltSiteQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.fltsite.vo.FltSiteRespVO;

import cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.fltsite.FltSiteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 市政设施专题-故障设施空间数据")
@RestController
@RequestMapping("/industry/muni-fac/flt-site")
@Validated
public class FltSiteController {
    @Resource
    private FltSiteService fltSiteService;

    @GetMapping("/get")
    @Operation(summary = "获得市政设施专题-故障设施空间数据")
    @PreAuthorize("@ss.hasPermission('industry:muni-fac-flt-site:query')")
    public CommonResult<List<FltSiteRespVO>> getFltSite(
            @Valid FltSiteQueryReqVO fltSiteQueryReqVO
    ) {
        List<FltSiteRespVO> fltSiteRespVOList = fltSiteService.getFltSite(fltSiteQueryReqVO);
        return success(fltSiteRespVOList);
    }
}
