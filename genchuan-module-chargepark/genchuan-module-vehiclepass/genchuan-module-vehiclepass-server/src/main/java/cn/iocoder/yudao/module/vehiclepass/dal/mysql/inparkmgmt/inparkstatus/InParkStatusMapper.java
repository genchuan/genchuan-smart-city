package cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.inparkstatus;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.inparkstatus.InParkStatusDO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;


/**
 * 在停状态 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface InParkStatusMapper extends BaseMapperX<InParkStatusDO> {

    default PageResult<InParkStatusDO> selectPage(InParkStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InParkStatusDO>()
                .eqIfPresent(InParkStatusDO::getStationId, reqVO.getStationId())
                .likeIfPresent(InParkStatusDO::getStationName, reqVO.getStationName())
                .eqIfPresent(InParkStatusDO::getSpaceId, reqVO.getSpaceId())
                .eqIfPresent(InParkStatusDO::getCarNo, reqVO.getCarNo())
                .betweenIfPresent(InParkStatusDO::getInTime, reqVO.getInTime())
                .eqIfPresent(InParkStatusDO::getOverTime, reqVO.getOverTime())
                .eqIfPresent(InParkStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InParkStatusDO::getRemark, reqVO.getRemark())
                .eqIfPresent(InParkStatusDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InParkStatusDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InParkStatusDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InParkStatusDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InParkStatusDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InParkStatusDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InParkStatusDO::getId));
    }

    InParkStatusRespVO selectByIdJoinStation(@Param("id") Long id);

    IPage<InParkStatusRespVO> selectPageJoinSpaceStation(Page<?> page, @Param("reqVO") InParkStatusPageReqVO reqVO);

    /**
     * 查询定位信息
     */
    Map<String, Object> selectLocationById(@Param("id") Long id);

    /**
     * 查询车辆分布（含经纬度）
     */
    List<Map<String, Object>> selectCarLocationList(@Param("stationId") Long stationId);

    /**
     * 查询在停量趋势
     */
    List<Map<String, Object>> selectInParkCountTrend(@Param("stationId") Long stationId);

    /**
     * 查询在停车辆数和超时长车辆数
     */
    Map<String, Object> selectInParkStats(@Param("stationId") Long stationId);

}