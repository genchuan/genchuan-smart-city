package cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.vehicle.VehiclePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.VehicleDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 车辆 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface VehicleMapper extends BaseMapperX<VehicleDO> {

    default PageResult<VehicleDO> selectPage(VehiclePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<VehicleDO>()
                .eqIfPresent(VehicleDO::getSysVehicleId, reqVO.getSysVehicleId())
                .eqIfPresent(VehicleDO::getLicensePlate, reqVO.getLicensePlate())
                .eqIfPresent(VehicleDO::getVehicleTypeId, reqVO.getVehicleTypeId())
                .eqIfPresent(VehicleDO::getModel, reqVO.getModel())
                .eqIfPresent(VehicleDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(VehicleDO::getRouteId, reqVO.getRouteId())
                .eqIfPresent(VehicleDO::getMaintenanceCycle, reqVO.getMaintenanceCycle())
                .eqIfPresent(VehicleDO::getDriverId, reqVO.getDriverId())
                .eqIfPresent(VehicleDO::getVehicleStatusId, reqVO.getVehicleStatusId())
                .eqIfPresent(VehicleDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(VehicleDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(VehicleDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(VehicleDO::getTotalWorkHours, reqVO.getTotalWorkHours())
                .betweenIfPresent(VehicleDO::getLastMaintenanceTime, reqVO.getLastMaintenanceTime())
                .eqIfPresent(VehicleDO::getAlarmCount, reqVO.getAlarmCount())
                .eqIfPresent(VehicleDO::getVehiclePhotoUrl, reqVO.getVehiclePhotoUrl())
                .betweenIfPresent(VehicleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(VehicleDO::getId));
    }

    /**
     * 查询全局最大序号（用于sys_vehicle_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(sys_vehicle_id, '-', -1)), 0) FROM sys_vehicle")
    Integer selectMaxSeq();

    List<VehicleDetailDO> selectDetailPage(@Param("reqVO") VehiclePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") VehiclePageReqVO pageReqVO);

    // ========== 卡片数据 ==========
    @Select("SELECT COUNT(*) FROM sys_vehicle WHERE deleted = 0")
    Long selectTotalVehicleCount();

    @Select("SELECT COUNT(*) FROM sys_vehicle v " +
            "LEFT JOIN sys_vehicle_status vs ON v.vehicle_status_id = vs.sys_vehicle_status_id " +
            "WHERE v.deleted = 0 AND vs.name = '正常运行'")
    Long selectNormalOperationCount();

    @Select("SELECT COUNT(*) FROM sys_vehicle v " +
            "LEFT JOIN sys_vehicle_status vs ON v.vehicle_status_id = vs.sys_vehicle_status_id " +
            "WHERE v.deleted = 0 AND vs.name = '维护中'")
    Long selectMaintenanceCount();

    @Select("SELECT IFNULL(SUM(alarm_count), 0) FROM sys_vehicle WHERE deleted = 0")
    Long selectViolationAlertCount();

    @Select("SELECT COUNT(*) FROM sys_vehicle v " +
            "LEFT JOIN sys_work_status ws ON v.work_status_id = ws.work_status_id " +
            "WHERE v.deleted = 0 AND ws.work_status_name = '待出发'")
    Long selectPendingWorkCount();

    // ========== 圆环图数据 ==========
    @Select("SELECT " +
            "CASE " +
            "   WHEN vt.name IS NULL THEN '未知' " +
            "   ELSE vt.name " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_vehicle v " +
            "LEFT JOIN sys_vehicle_type vt ON v.vehicle_type_id = vt.sys_vehicle_type_id " +
            "WHERE v.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN vt.name IS NULL THEN '未知' " +
            "   ELSE vt.name " +
            "END")
    List<PieItemVO> selectVehicleTypeDistribution();

    @Select("SELECT " +
            "CASE " +
            "   WHEN vs.name IS NULL THEN '未知' " +
            "   ELSE vs.name " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_vehicle v " +
            "LEFT JOIN sys_vehicle_status vs ON v.vehicle_status_id = vs.sys_vehicle_status_id " +
            "WHERE v.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN vs.name IS NULL THEN '未知' " +
            "   ELSE vs.name " +
            "END")
    List<PieItemVO> selectVehicleStatusDistribution();

    @Select("SELECT " +
            "CASE " +
            "   WHEN sd.name IS NULL THEN '未知' " +
            "   ELSE sd.name " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_vehicle v " +
            "LEFT JOIN sys_dept sd ON v.dept_id = sd.sys_dept_id " +
            "WHERE v.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN sd.name IS NULL THEN '未知' " +
            "   ELSE sd.name " +
            "END")
    List<PieItemVO> selectDeptDistribution();

    // ========== 柱状图数据 ==========
    @Select("SELECT " +
            "CASE " +
            "   WHEN sd.name IS NULL THEN '未知' " +
            "   ELSE sd.name " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM sys_vehicle v " +
            "LEFT JOIN sys_dept sd ON v.dept_id = sd.sys_dept_id " +
            "WHERE v.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN sd.name IS NULL THEN '未知' " +
            "   ELSE sd.name " +
            "END " +
            "ORDER BY value DESC")
    List<BarItemVO> selectVehicleCountByDept();

    @Select("SELECT " +
            "CASE " +
            "   WHEN vt.name IS NULL THEN '未知' " +
            "   ELSE vt.name " +
            "END as name, " +
            "ROUND(SUM(CASE WHEN vs.name = '正常运行' THEN 1 ELSE 0 END) / COUNT(*) * 100, 2) as value " +
            "FROM sys_vehicle v " +
            "LEFT JOIN sys_vehicle_type vt ON v.vehicle_type_id = vt.sys_vehicle_type_id " +
            "LEFT JOIN sys_vehicle_status vs ON v.vehicle_status_id = vs.sys_vehicle_status_id " +
            "WHERE v.deleted = 0 " +
            "GROUP BY " +
            "CASE " +
            "   WHEN vt.name IS NULL THEN '未知' " +
            "   ELSE vt.name " +
            "END")
    List<BarItemVO> selectVehicleIntegrityRateByType();
}