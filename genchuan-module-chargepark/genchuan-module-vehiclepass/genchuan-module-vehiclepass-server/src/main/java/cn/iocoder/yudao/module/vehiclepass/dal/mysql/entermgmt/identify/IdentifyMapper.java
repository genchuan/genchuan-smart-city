package cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.identify;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo.IdentifyPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.identify.IdentifyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 车牌识别 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IdentifyMapper extends BaseMapperX<IdentifyDO> {

    default PageResult<IdentifyDO> selectPage(IdentifyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IdentifyDO>()
                .likeIfPresent(IdentifyDO::getPlateNo, reqVO.getPlateNo())       // 车牌模糊
                .eqIfPresent(IdentifyDO::getPlateColor, reqVO.getPlateColor())
                .eqIfPresent(IdentifyDO::getConfidence, reqVO.getConfidence())
                .eqIfPresent(IdentifyDO::getStatus, reqVO.getStatus())
                .eqIfPresent(IdentifyDO::getStationId, reqVO.getStationId())
                .likeIfPresent(IdentifyDO::getRemark, reqVO.getRemark())         // 备注模糊
                .eqIfPresent(IdentifyDO::getIsCorrected, reqVO.getIsCorrected())
                .orderByDesc(IdentifyDO::getId));
    }


    Map<String, Object> selectCardData(
            @Param("startTime") String startTime,
            @Param("endTime") String endTime,
            @Param("stationId") Long stationId
    );

    List<Map<String, Object>> selectDayTrend(
            @Param("startTime") String startTime,
            @Param("endTime") String endTime,
            @Param("stationId") Long stationId
    );

    List<Map<String, Object>> selectStationCount(
            @Param("startTime") String startTime,
            @Param("endTime") String endTime,
            @Param("stationId") Long stationId
    );

}