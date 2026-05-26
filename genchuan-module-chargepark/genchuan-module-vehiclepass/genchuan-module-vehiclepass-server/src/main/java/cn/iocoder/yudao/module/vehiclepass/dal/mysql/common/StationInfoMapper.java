package cn.iocoder.yudao.module.vehiclepass.dal.mysql.common;

import cn.iocoder.yudao.module.vehiclepass.constants.common.StationSimpleRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 场站信息 Mapper
 *
 * 用于在 vehiclepass 模块内直接查询 station_info 表
 */
@Mapper
public interface StationInfoMapper {

    @Select("SELECT id AS stationId, name AS stationName FROM station_info WHERE deleted = 0 ORDER BY id ASC")
    List<StationSimpleRespVO> selectStationSimpleList();
}
