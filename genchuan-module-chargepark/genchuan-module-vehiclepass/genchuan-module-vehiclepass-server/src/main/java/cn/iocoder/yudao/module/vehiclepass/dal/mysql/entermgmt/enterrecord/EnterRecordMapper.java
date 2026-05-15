package cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.enterrecord;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.EnterRecordChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.EnterRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.MyEnterRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.MyEnterRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.enterrecord.EnterRecordDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 入场记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EnterRecordMapper extends BaseMapperX<EnterRecordDO> {

    default PageResult<EnterRecordDO> selectPage(EnterRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnterRecordDO>()
                .eqIfPresent(EnterRecordDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(EnterRecordDO::getPlateColor, reqVO.getPlateColor())
                .eqIfPresent(EnterRecordDO::getSpaceNo, reqVO.getSpaceNo())
                .betweenIfPresent(EnterRecordDO::getEnterTime, reqVO.getEnterTime())
                .eqIfPresent(EnterRecordDO::getRecordType, reqVO.getRecordType())
                .eqIfPresent(EnterRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EnterRecordDO::getStationId, reqVO.getStationId())
                .eqIfPresent(EnterRecordDO::getRemark, reqVO.getRemark())
                .eqIfPresent(EnterRecordDO::getProofImage, reqVO.getProofImage())
                .eqIfPresent(EnterRecordDO::getIsCorrected, reqVO.getIsCorrected())
                .eqIfPresent(EnterRecordDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(EnterRecordDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(EnterRecordDO::getCreator, reqVO.getCreator())
                .eqIfPresent(EnterRecordDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(EnterRecordDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(EnterRecordDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(EnterRecordDO::getId));
    }

    IPage<MyEnterRecordRespVO> selectEnterRecordPage(Page<?> page,
                                                     @Param("query") MyEnterRecordPageReqVO reqVO);

    // 折线图：按日统计
    List<EnterRecordChartRespVO.EnterCountTrend> selectEnterCountTrend(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("stationId") Long stationId);

    // 柱状图：按小时统计
    List<EnterRecordChartRespVO.HourEnterCount> selectHourEnterCount(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("stationId") Long stationId);

    // 今日入场量
    Integer selectTodayEnterCount(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("stationId") Long stationId);

    MyEnterRecordRespVO selectByIdJoinStation(@Param("id") Long id);

}