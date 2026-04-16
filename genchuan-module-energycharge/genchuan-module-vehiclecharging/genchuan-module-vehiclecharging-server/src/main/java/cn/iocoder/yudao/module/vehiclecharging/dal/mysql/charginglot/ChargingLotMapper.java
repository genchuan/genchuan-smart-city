package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.charginglot;

import java.util.*;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charginglot.ChargingLotDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 充电车位 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ChargingLotMapper extends BaseMapperX<ChargingLotDO> {

    default PageResult<ChargingLotDO> selectPage(ChargingLotPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChargingLotDO>()
                .eqIfPresent(ChargingLotDO::getLotCode, reqVO.getLotCode())
                .eqIfPresent(ChargingLotDO::getStationId, reqVO.getStationId())
                .eqIfPresent(ChargingLotDO::getLotType, reqVO.getLotType())
                .eqIfPresent(ChargingLotDO::getPileId, reqVO.getPileId())
                .betweenIfPresent(ChargingLotDO::getOccupyTime, reqVO.getOccupyTime())
                .eqIfPresent(ChargingLotDO::getLotStatus, reqVO.getLotStatus())
                .eqIfPresent(ChargingLotDO::getOccupyTimeout, reqVO.getOccupyTimeout())
                .eqIfPresent(ChargingLotDO::getMaintainReason, reqVO.getMaintainReason())
                .eqIfPresent(ChargingLotDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ChargingLotDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ChargingLotDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(ChargingLotDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ChargingLotDO::getId));
    }

    /**
     * 查询所有充电车位列表
     * 注意：此方法应包含多租户隔离条件（如果项目启用多租户）
     * @return 车位列表
     */
    default List<ChargingLotDO> selectList() {
        return selectList(new LambdaQueryWrapperX<>());
    }

    /**
     * 批量查询场站名称
     * @param ids 场站ID集合
     * @return 场站ID和名称的映射
     */
    @Select("<script>" +
            "SELECT id, station_name FROM charging_station " +
            "WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Map<String, Object>> selectStationNamesByIds(@Param("ids") Set<Long> ids);

    /**
     * 批量查询充电桩名称
     * @param ids 充电桩ID集合
     * @return 充电桩ID和名称的映射
     */
    @Select("<script>" +
            "SELECT id, pile_code FROM charging_pile " +
            "WHERE id IN " +
            "<foreach collection='ids' item='id' open='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Map<String, Object>> selectPileNamesByIds(@Param("ids") Set<Long> ids);
}