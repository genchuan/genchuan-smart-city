package cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics.vo.BusiCoreMetricsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.businessservices.dpzl.coremetrics.vo.BusiCoreMetricsRespVO;
import cn.iocoder.yudao.module.industry.service.businessservices.dpzl.coremetrics.BusiCoreMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 营商服务关键指标")
@RestController
@RequestMapping("/industry/business-services-key-indicators")
@Validated
public class BusiCoreMetricsController {

    @Resource
    private BusiCoreMetricsService BusiCoreMetricsService;

    @GetMapping("/get")
    @Operation(summary = "获取关键指标展示数据")
    public CommonResult<BusiCoreMetricsRespVO> getKeyIndicatorsData(@Valid BusiCoreMetricsQueryReqVO queryReqVO) {
        BusiCoreMetricsRespVO indicatorsData = BusiCoreMetricsService.getKeyIndicatorsData(queryReqVO);
        return success(indicatorsData);
    }
}