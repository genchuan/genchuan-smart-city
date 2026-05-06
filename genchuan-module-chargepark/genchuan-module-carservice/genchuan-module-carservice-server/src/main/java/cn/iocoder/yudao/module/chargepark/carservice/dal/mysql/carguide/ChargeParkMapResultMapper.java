package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.ChargeParkMapResultDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 充停地图查询结果快照 Mapper
 *
 * @author carservice
 */
@Mapper
public interface ChargeParkMapResultMapper extends BaseMapperX<ChargeParkMapResultDO> {

    default List<ChargeParkMapResultDO> selectByChargeParkMapId(Long chargeParkMapId) {
        return selectList(new LambdaQueryWrapperX<ChargeParkMapResultDO>()
                .eq(ChargeParkMapResultDO::getChargeParkMapId, chargeParkMapId)
                .orderByAsc(ChargeParkMapResultDO::getDistanceKm));
    }

}
