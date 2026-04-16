package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.pile;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pile.PileStatusDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 充电桩状态字典 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PileStatusMapper extends BaseMapperX<PileStatusDO> {

}
