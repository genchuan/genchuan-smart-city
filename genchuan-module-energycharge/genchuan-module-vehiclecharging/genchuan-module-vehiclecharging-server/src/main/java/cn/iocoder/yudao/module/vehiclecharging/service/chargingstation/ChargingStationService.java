package cn.iocoder.yudao.module.vehiclecharging.service.chargingstation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclecharging.controller.damin.chargingstation.vo.ChargingStationPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.damin.chargingstation.vo.ChargingStationRespVO;

public interface ChargingStationService {
    PageResult<ChargingStationRespVO> getChargingStationPage(ChargingStationPageReqVO reqVO);
}
