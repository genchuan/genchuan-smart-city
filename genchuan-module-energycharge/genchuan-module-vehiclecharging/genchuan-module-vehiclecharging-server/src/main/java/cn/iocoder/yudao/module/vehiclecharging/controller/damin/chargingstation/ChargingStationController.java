package cn.iocoder.yudao.module.vehiclecharging.controller.damin.chargingstation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclecharging.controller.damin.chargingstation.vo.ChargingStationPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.damin.chargingstation.vo.ChargingStationRespVO;
import cn.iocoder.yudao.module.vehiclecharging.service.chargingstation.ChargingStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - 充电站")
@RestController
@RequestMapping("/vehiclecharging/charging_station")
public class ChargingStationController {

    @Resource
    private ChargingStationService chargingStationService;

    @GetMapping("/page")
    @Operation(summary = "获得充电站分页")
    public CommonResult<PageResult<ChargingStationRespVO>> getChargingStationPage(ChargingStationPageReqVO reqVO) {
        PageResult<ChargingStationRespVO> pageResult = chargingStationService.getChargingStationPage(reqVO);
        return CommonResult.success(pageResult);
    }

}