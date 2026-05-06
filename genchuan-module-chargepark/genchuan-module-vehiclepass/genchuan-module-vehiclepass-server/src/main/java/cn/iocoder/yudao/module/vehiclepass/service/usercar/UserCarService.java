package cn.iocoder.yudao.module.vehiclepass.service.usercar;

import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.usercar.UserCarDO;

/**
 * 用户车辆 Service 接口
 *
 * @author 亘川智城
 */
public interface UserCarService {

    /**
     * 通过车牌号获得用户车辆
     *
     * @param plateNo 车牌号码
     * @return 用户车辆
     */
    UserCarDO getUserCarByPlateNo(String plateNo);

}
