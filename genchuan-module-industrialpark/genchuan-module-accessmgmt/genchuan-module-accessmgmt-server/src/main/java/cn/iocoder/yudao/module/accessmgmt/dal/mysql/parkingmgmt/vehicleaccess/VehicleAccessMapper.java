package cn.iocoder.yudao.module.accessmgmt.dal.mysql.parkingmgmt.vehicleaccess;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo.VehicleAccessChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo.VehicleAccessPageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.vehicleaccess.VehicleAccessDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车辆通行 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface VehicleAccessMapper extends BaseMapperX<VehicleAccessDO> {

    default PageResult<VehicleAccessDO> selectPage(VehicleAccessPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleAccessDO>()
                .likeIfPresent(VehicleAccessDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(VehicleAccessDO::getVehicleType, reqVO.getVehicleType())
                .eqIfPresent(VehicleAccessDO::getParkName, reqVO.getParkName())
                .eqIfPresent(VehicleAccessDO::getAccessStatus, reqVO.getAccessStatus())
                .eqIfPresent(VehicleAccessDO::getPayStatus, reqVO.getPayStatus())
                .betweenIfPresent(VehicleAccessDO::getAccessTime, reqVO.getStartTime(), reqVO.getEndTime())
                .orderByDesc(VehicleAccessDO::getId));
    }

    /**
     * 统计各时段通行量趋势
     */
    List<VehicleAccessChartRespVO.TimeTrendItem> selectTimeTrendList(@Param("startTime") String startTime,
                                                                      @Param("endTime") String endTime);

    /**
     * 统计每日进出数量趋势
     */
    List<VehicleAccessChartRespVO.DayTrendItem> selectDayTrendList(@Param("startTime") String startTime,
                                                                    @Param("endTime") String endTime);

    /**
     * 统计各停车场通行数量
     */
    List<VehicleAccessChartRespVO.ParkCountItem> selectParkCountList(@Param("startTime") String startTime,
                                                                      @Param("endTime") String endTime);

    /**
     * 统计各车辆类型数量
     */
    List<VehicleAccessChartRespVO.VehicleTypeItem> selectVehicleTypeList(@Param("startTime") String startTime,
                                                                           @Param("endTime") String endTime);

}
