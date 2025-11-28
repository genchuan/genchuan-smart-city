package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.CulturePasFlowQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.CulturePasFlowRespVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.passengerflow.CulturePasFlowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "管理后台 - 文旅客流总览视图")
@RestController
@RequestMapping("/industry/culture-tourism-passenger-flow")
@RequiredArgsConstructor
public class CulturePasFlowController {

    private final CulturePasFlowService CulturePasFlowService;

    @GetMapping("/get")
    @Operation(summary = "获取文旅客流总览数据")
    public CommonResult<CulturePasFlowRespVO> getOverview(@Valid CulturePasFlowQueryReqVO queryVO) {
        return CommonResult.success(CulturePasFlowService.getPassengerFlowOverview(queryVO));
    }

    @GetMapping("/regions")
    @Operation(summary = "获取区域筛选列表")
    public CommonResult<Object> getRegions() {
        return CommonResult.success(CulturePasFlowService.getRegionOptions());
    }
}