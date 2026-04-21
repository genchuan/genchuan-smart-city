package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.MockNearbyStationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 周边场站 Mock Mapper（过渡方案，stationresource RPC 上线后删除）
 *
 * @author carservice
 */
@Mapper
public interface MockNearbyStationMapper extends BaseMapperX<MockNearbyStationDO> {
}
