package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
//import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.BizMngCompSpatialPageReqVO;
//import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.BizMngCompSpatialRespVO;
//import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompspatial.BizMngCompSpatialDO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo.UniversalOverviewQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.global.overview.vo.UniversalOverviewRespVO;

import cn.iocoder.yudao.module.industry.service.universal.dashboard.global.overview.UniversalOverviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 通用大屏全域数据地图")
@RestController
@RequestMapping("/industry/universal-overview")
@Validated
public class UniversalOverviewController {
    @Resource
    private UniversalOverviewService universalOverviewService;

    @GetMapping("/list")
    @Operation(summary = "获得通用大屏全域数据地图")
    @PreAuthorize("@ss.hasPermission('industry:universal-overview:query')")
    public CommonResult<List<UniversalOverviewRespVO>> getUniversalOverview(
            @Valid UniversalOverviewQueryReqVO universalOverviewQueryReqVO
    ) {
        List<UniversalOverviewRespVO> list = universalOverviewService.listUniversalOverview(universalOverviewQueryReqVO);
        return success(list);
    }


}
