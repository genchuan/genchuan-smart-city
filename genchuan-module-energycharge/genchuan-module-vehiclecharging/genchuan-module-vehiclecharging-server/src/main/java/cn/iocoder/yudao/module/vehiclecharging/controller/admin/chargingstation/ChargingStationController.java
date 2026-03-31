package cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationCreateReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationUpdateReqVO;
import cn.iocoder.yudao.module.vehiclecharging.service.chargingstation.ChargingStationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理后台 - 充电站")
@RestController
@RequestMapping("/vehiclecharging/charging_station")
public class ChargingStationController {

    @Resource
    private ChargingStationService chargingStationService;
    /**
     * 获得充电站分页
     */
    @GetMapping("/page")
    @Operation(summary = "获得充电站分页")
    public CommonResult<PageResult<ChargingStationRespVO>> getChargingStationPage(ChargingStationPageReqVO reqVO) {
        PageResult<ChargingStationRespVO> pageResult = chargingStationService.getChargingStationPage(reqVO);
        return CommonResult.success(pageResult);
    }
    /**
     * 创建充电站场站
     */
    @PostMapping("/create")
    @Operation(summary = "创建充电站场站")
    public CommonResult<Long> createChargingStation(@Valid @RequestBody ChargingStationCreateReqVO createReqVO) {
        Long id = chargingStationService.createChargingStation(createReqVO);
        return CommonResult.success(id);
    }
    /**
     * 更新充电站场站
     */
    @PutMapping("/update")
    @Operation(summary = "更新充电站场站")
    public CommonResult<Boolean> updateChargingStation(@Valid @RequestBody ChargingStationUpdateReqVO updateReqVO) {
        chargingStationService.updateChargingStation(updateReqVO);
        return CommonResult.success(true);
    }
}