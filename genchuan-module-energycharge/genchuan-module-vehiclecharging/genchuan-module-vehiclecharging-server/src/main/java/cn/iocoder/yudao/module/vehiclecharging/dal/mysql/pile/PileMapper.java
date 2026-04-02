package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.pile;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.pile.PileDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.pile.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 充电桩 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PileMapper extends BaseMapperX<PileDO> {

    default PageResult<PileDO> selectPage(PilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PileDO>()
                .eqIfPresent(PileDO::getPileCode, reqVO.getPileCode())
                .eqIfPresent(PileDO::getModel, reqVO.getModel())
                .eqIfPresent(PileDO::getPower, reqVO.getPower())
                .eqIfPresent(PileDO::getManufacturer, reqVO.getManufacturer())
                .eqIfPresent(PileDO::getStationId, reqVO.getStationId())
                .eqIfPresent(PileDO::getLotId, reqVO.getLotId())
                .eqIfPresent(PileDO::getChargeMode, reqVO.getChargeMode())
                .eqIfPresent(PileDO::getPileStatus, reqVO.getPileStatus())
                .eqIfPresent(PileDO::getFaultFlag, reqVO.getFaultFlag())
                .betweenIfPresent(PileDO::getRunTime, reqVO.getRunTime())
                .eqIfPresent(PileDO::getQrcode, reqVO.getQrcode())
                .eqIfPresent(PileDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PileDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(PileDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(PileDO::getCreator, reqVO.getCreator())
                .eqIfPresent(PileDO::getUpdater, reqVO.getUpdater())
                .eqIfPresent(PileDO::getDeleted, reqVO.getDeleted())
                .betweenIfPresent(PileDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(PileDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(PileDO::getId));
    }

    default Long selectIdByPileCode(String pileCode) {
        PileDO pile = selectOne(new LambdaQueryWrapperX<PileDO>()
                .eq(PileDO::getPileCode, pileCode));
        return pile != null ? pile.getId() : null;
    }

    List<PileChartRespVO.RunTimeTrend> selectRunTimeTrend(@Param("startTime") String startTime,
                                                           @Param("endTime") String endTime,
                                                           @Param("stationId") Long stationId);

    List<PileChartRespVO.TypeBar> selectTypeBarList(@Param("stationId") Long stationId);

    PileChartRespVO.CardInfo selectCardInfo(@Param("stationId") Long stationId);

    List<PileChargeModeStatRespVO> selectChargeModeStat(@Param("stationId") Long stationId);

    List<PileStatusStatRespVO> selectPileStatusStat();

}