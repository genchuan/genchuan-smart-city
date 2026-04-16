package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sharingreport;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingratio.SharingRatioDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingreport.SharingReportDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分账报表 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SharingReportMapper extends BaseMapperX<SharingReportDO> {
}
