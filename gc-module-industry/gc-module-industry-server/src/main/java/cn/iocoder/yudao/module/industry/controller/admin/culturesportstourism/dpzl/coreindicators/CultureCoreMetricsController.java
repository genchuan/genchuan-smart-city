package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CultureCoreMetricsRespVO.CoreMetricVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coremetrics.CultureCoreMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/industry/culture-tourism-core-indicators")
@Tag(name = "管理后台 - 文旅核心指标")
public class CultureCoreMetricsController {

    @Resource
    private CultureCoreMetricsService cultureCoreMetricsService;

//    @GetMapping("/get2")
//    @Operation(summary = "获取文旅核心指标数据")
//    public CommonResult<CultureCoreMetricsRespVO> getCoreIndicators(
//            @Valid CultureCoreMetricsQueryReqVO queryVO) {
//
//        // 构造测试数据
//        CultureCoreMetricsRespVO respVO = new CultureCoreMetricsRespVO();
//
//        CoreMetricVO totalScene = new CoreMetricVO();
//        totalScene.setValue(BigDecimal.valueOf(1250));
//        totalScene.setUpdateTime(LocalDateTime.now());
//        totalScene.setWarnStatus(0);
//        totalScene.setTimeCycle("今日");
//        respVO.setTotalSceneCount(totalScene);
//
//        CoreMetricVO maxCount = new CoreMetricVO();
//        maxCount.setValue(BigDecimal.valueOf(3280));
//        maxCount.setUpdateTime(LocalDateTime.now());
//        maxCount.setWarnStatus(0);
//        maxCount.setTimeCycle("今日");
//        respVO.setMaxCount(maxCount);
//
//        CoreMetricVO completeRate = new CoreMetricVO();
//        completeRate.setValue(BigDecimal.valueOf(85.5));
//        completeRate.setUpdateTime(LocalDateTime.now());
//        completeRate.setWarnStatus(0);
//        completeRate.setTimeCycle("今日");
//        respVO.setCompleteRate(completeRate);
//
//        CoreMetricVO facilityGoodRate = new CoreMetricVO();
//        facilityGoodRate.setValue(BigDecimal.valueOf(92.0));
//        facilityGoodRate.setUpdateTime(LocalDateTime.now());
//        facilityGoodRate.setWarnStatus(0);
//        facilityGoodRate.setTimeCycle("今日");
//        respVO.setFacilityGoodRate(facilityGoodRate);
//
//        CoreMetricVO newSceneCount = new CoreMetricVO();
//        newSceneCount.setValue(BigDecimal.valueOf(28));
//        newSceneCount.setUpdateTime(LocalDateTime.now());
//        newSceneCount.setWarnStatus(0);
//        newSceneCount.setTimeCycle("今日");
//        respVO.setNewSceneCount(newSceneCount);
//
//        return CommonResult.success(respVO);
////         return CommonResult.success(cultureCoreMetricsService.getCoreIndicators(queryVO));
//    }

    @GetMapping("/get")
    @Operation(summary = "获取文旅核心指标数据")
    public CommonResult<CultureCoreMetricsRespVO> getCoreIndicators2(
            @Valid CultureCoreMetricsQueryReqVO queryVO) {

        return CommonResult.success(cultureCoreMetricsService.getCoreIndicators(queryVO));
    }
}
