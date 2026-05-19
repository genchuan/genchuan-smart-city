package cn.iocoder.yudao.module.accessmgmt.dal.mysql.parkingmgmt.vehicleaccess;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo.VehicleAccessChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo.VehicleAccessPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.vehicleaccess.VehicleAccessDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

/**
 * 车辆通行 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface VehicleAccessMapper extends BaseMapperX<VehicleAccessDO> {

    /**
     * 分页查询车辆通行，支持按车牌号(模糊)/车辆类型(精确)/停车场名称(精确)/通行状态(精确)/缴费状态(精确)/通行时间范围筛选，按主键倒序
     */
    default PageResult<VehicleAccessDO> selectPage(VehicleAccessPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleAccessDO>()
                .likeIfPresent(VehicleAccessDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(VehicleAccessDO::getVehicleType, reqVO.getVehicleType())
                .eqIfPresent(VehicleAccessDO::getParkName, reqVO.getParkName())
                .eqIfPresent(VehicleAccessDO::getAccessStatus, reqVO.getAccessStatus())
                .eqIfPresent(VehicleAccessDO::getPayStatus, reqVO.getPayStatus())
                .betweenIfPresent(VehicleAccessDO::getAccessTime,
                        reqVO.getStartTime() != null ? Instant.ofEpochMilli(reqVO.getStartTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null,
                        reqVO.getEndTime() != null ? Instant.ofEpochMilli(reqVO.getEndTime()).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime() : null)
                .orderByDesc(VehicleAccessDO::getId));
    }

    /**
     * 统计各时段通行量趋势
     */
    List<VehicleAccessChartRespVO.TimeTrendItem> selectTimeTrendList(@Param("startTime") Long startTime,
                                                                      @Param("endTime") Long endTime);

    /**
     * 统计每日进出数量趋势
     */
    List<VehicleAccessChartRespVO.DayTrendItem> selectDayTrendList(@Param("startTime") Long startTime,
                                                                    @Param("endTime") Long endTime);

    /**
     * 统计各停车场通行数量
     */
    List<VehicleAccessChartRespVO.ParkCountItem> selectParkCountList(@Param("startTime") Long startTime,
                                                                      @Param("endTime") Long endTime);

    /**
     * 统计各车辆类型数量
     */
    List<VehicleAccessChartRespVO.VehicleTypeItem> selectVehicleTypeList(@Param("startTime") Long startTime,
                                                                           @Param("endTime") Long endTime);

}
