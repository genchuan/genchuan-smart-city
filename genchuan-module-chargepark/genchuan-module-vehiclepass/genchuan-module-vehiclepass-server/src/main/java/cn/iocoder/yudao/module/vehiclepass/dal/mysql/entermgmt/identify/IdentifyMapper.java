package cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.identify;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo.IdentifyPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.identify.IdentifyDO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.identify.vo.IdentifyRespVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 车牌识别 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface IdentifyMapper extends BaseMapperX<IdentifyDO> {

    // 基础分页查询（返回DO）
    default PageResult<IdentifyDO> selectPage(IdentifyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IdentifyDO>()
                .likeIfPresent(IdentifyDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(IdentifyDO::getPlateColor, reqVO.getPlateColor())
                .eqIfPresent(IdentifyDO::getConfidence, reqVO.getConfidence())
                .eqIfPresent(IdentifyDO::getStatus, reqVO.getStatus())
                .eqIfPresent(IdentifyDO::getStationId, reqVO.getStationId())
                .likeIfPresent(IdentifyDO::getStationName, reqVO.getStationName())
                .likeIfPresent(IdentifyDO::getRemark, reqVO.getRemark())
                .eqIfPresent(IdentifyDO::getIsCorrected, reqVO.getIsCorrected())
                .orderByDesc(IdentifyDO::getId));
    }

    // 带场站名称的分页查询（返回VO）
    default PageResult<IdentifyRespVO> selectPageWithStation(IdentifyPageReqVO reqVO) {
        Page<IdentifyRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<IdentifyRespVO> iPage = selectIdentifyPage(page, reqVO);
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }

    IPage<IdentifyRespVO> selectIdentifyPage(Page<IdentifyRespVO> page, @Param("query") IdentifyPageReqVO reqVO);

    IdentifyRespVO selectByIdJoinStation(@Param("id") Long id);

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