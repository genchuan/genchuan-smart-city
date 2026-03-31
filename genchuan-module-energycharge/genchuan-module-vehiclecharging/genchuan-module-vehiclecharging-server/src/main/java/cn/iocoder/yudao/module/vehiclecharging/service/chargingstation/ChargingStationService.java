package cn.iocoder.yudao.module.vehiclecharging.service.chargingstation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationCreateReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationUpdateReqVO;

public interface ChargingStationService {
    /**
     * 获得充电站分页
     *
     * @param reqVO 分页条件
     * @return 充电站分页
     */
    PageResult<ChargingStationRespVO> getChargingStationPage(ChargingStationPageReqVO reqVO);
    /**
     * 创建充电站
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createChargingStation(ChargingStationCreateReqVO createReqVO);

    /**
     * 更新充电站
     *
     * @param updateReqVO 更新信息
     */
    void updateChargingStation(ChargingStationUpdateReqVO updateReqVO);
}
