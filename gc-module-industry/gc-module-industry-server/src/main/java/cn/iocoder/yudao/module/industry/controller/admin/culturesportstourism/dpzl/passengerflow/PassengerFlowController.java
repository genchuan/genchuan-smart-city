package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.PassengerFlowQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.passengerflow.vo.PassengerFlowRespVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.passengerflow.PassengerFlowServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "管理后台 - 文旅客流总览视图")
@RestController
@RequestMapping("/industry/passenger-flow")
@RequiredArgsConstructor
public class PassengerFlowController {

    private final PassengerFlowServer passengerFlowServer;

    @GetMapping("/overview")
    @Operation(summary = "获取文旅客流总览数据")
    public CommonResult<PassengerFlowRespVO> getOverview(@Valid PassengerFlowQueryReqVO queryVO) {
        return CommonResult.success(passengerFlowServer.getPassengerFlowOverview(queryVO));
    }

    @GetMapping("/regions")
    @Operation(summary = "获取区域筛选列表")
    public CommonResult<Object> getRegions() {
        return CommonResult.success(passengerFlowServer.getRegionOptions());
    }
}