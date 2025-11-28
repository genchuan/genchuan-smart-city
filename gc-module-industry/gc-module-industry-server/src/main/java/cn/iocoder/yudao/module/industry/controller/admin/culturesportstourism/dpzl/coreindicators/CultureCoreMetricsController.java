package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coremetrics.CultureCoreMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/industry/culture-tourism-core-indicators")
@Tag(name = "管理后台 - 文旅核心指标")
public class CultureCoreMetricsController {

    @Resource
    private CultureCoreMetricsService CultureCoreMetricsService;

    @GetMapping("/get")
    @Operation(summary = "获取文旅核心指标数据")
    public CommonResult<CultureCoreMetricsRespVO> getCoreIndicators(
            @Valid CultureCoreMetricsQueryReqVO queryVO) {  // 添加 @Valid 注解
        return CommonResult.success(CultureCoreMetricsService.getCoreIndicators(queryVO));
    }
}