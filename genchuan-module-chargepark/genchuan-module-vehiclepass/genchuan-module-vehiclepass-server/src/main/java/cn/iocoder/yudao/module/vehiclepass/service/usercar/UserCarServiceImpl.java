package cn.iocoder.yudao.module.vehiclepass.service.usercar;

import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.usercar.UserCarDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.usercar.UserCarMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.USER_CAR_NOT_EXISTS;

/**
 * 用户车辆 Service 实现类
 *
 * @author 亘川智城
 */
@Service
public class UserCarServiceImpl implements UserCarService {

    @Resource
    private UserCarMapper userCarMapper;

    @Override
    public UserCarDO getUserCarByPlateNo(String plateNo) {
        UserCarDO userCar = userCarMapper.selectByPlateNo(plateNo);
        if (userCar == null) {
            throw exception(USER_CAR_NOT_EXISTS);
        }
        return userCar;
    }

}
