package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.charge;

import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charge.ChargeModeDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

/**
 * 充电模式字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ChargeModeMapper extends BaseMapperX<ChargeModeDO> {

}
