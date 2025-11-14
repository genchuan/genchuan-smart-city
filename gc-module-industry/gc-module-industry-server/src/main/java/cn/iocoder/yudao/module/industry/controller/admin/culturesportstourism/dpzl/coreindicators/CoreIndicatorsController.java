package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CoreIndicatorsQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.coreindicators.vo.CoreIndicatorsRespVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.coreindicators.CoreIndicatorsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/industry/culture-sports-tourism")
@Tag(name = "管理后台 - 文旅核心指标")
public class CoreIndicatorsController {

    @Resource
    private CoreIndicatorsService coreIndicatorsService;

    @GetMapping("/core-indicators")
    @Operation(summary = "获取文旅核心指标数据")
    public CommonResult<CoreIndicatorsRespVO> getCoreIndicators(
            @Valid CoreIndicatorsQueryReqVO queryVO) {  // 添加 @Valid 注解
        return CommonResult.success(coreIndicatorsService.getCoreIndicators(queryVO));
    }
}