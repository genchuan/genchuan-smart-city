package cn.iocoder.yudao.module.vehiclepass.dal.mysql.usercar;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.usercar.UserCarDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户车辆 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface UserCarMapper extends BaseMapperX<UserCarDO> {

    default UserCarDO selectByPlateNo(String plateNo) {
        return selectOne(UserCarDO::getPlateNo, plateNo);
    }

}
