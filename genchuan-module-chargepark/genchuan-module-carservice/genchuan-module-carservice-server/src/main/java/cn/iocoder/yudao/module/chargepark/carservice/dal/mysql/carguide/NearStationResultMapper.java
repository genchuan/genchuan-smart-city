package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationResultDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 周边场站查询结果快照 Mapper
 *
 * @author carservice
 */
@Mapper
public interface NearStationResultMapper extends BaseMapperX<NearStationResultDO> {

    default List<NearStationResultDO> selectByNearStationId(Long nearStationId) {
        return selectList(new LambdaQueryWrapperX<NearStationResultDO>()
                .eq(NearStationResultDO::getNearStationId, nearStationId)
                .orderByAsc(NearStationResultDO::getDistanceKm));
    }

}
