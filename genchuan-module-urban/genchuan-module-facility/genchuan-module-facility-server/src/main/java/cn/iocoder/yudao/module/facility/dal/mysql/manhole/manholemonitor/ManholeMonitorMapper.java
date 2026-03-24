package cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 窨井盖监测 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ManholeMonitorMapper extends BaseMapperX<ManholeMonitorDO> {

    default PageResult<ManholeMonitorDO> selectPage(ManholeMonitorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ManholeMonitorDO>()
                .eqIfPresent(ManholeMonitorDO::getCoverId, reqVO.getCoverId())
                .eqIfPresent(ManholeMonitorDO::getDeviceId, reqVO.getDeviceId())
                .eqIfPresent(ManholeMonitorDO::getStaffId, reqVO.getStaffId())
                .eqIfPresent(ManholeMonitorDO::getOpenStatusId, reqVO.getOpenStatusId())
                .eqIfPresent(ManholeMonitorDO::getTiltAngle, reqVO.getTiltAngle())
                .eqIfPresent(ManholeMonitorDO::getVibrationData, reqVO.getVibrationData())
                .eqIfPresent(ManholeMonitorDO::getRiskLevelId, reqVO.getRiskLevelId())
                .eqIfPresent(ManholeMonitorDO::getMonitorStatus, reqVO.getMonitorStatus())
                .betweenIfPresent(ManholeMonitorDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ManholeMonitorDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ManholeMonitorDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ManholeMonitorDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ManholeMonitorDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ManholeMonitorDO::getId));
    }

    /**
     * 井盖实时详情
     * @param page
     * @param reqVO
     * @return
     */
    IPage<ManholeCoverRealTimePageRespVO> selectRealTimePage(IPage<ManholeCoverRealTimePageRespVO> page,
                                                             @Param("reqVO") ManholeCoverRealTimePageReqVO reqVO);

    /**
     * 按井盖编号查询详情
     * @param coverNo 井盖编号
     * @return 窨井盖详情
     */
//    ManholeCoverRealTimePageRespVO selectManholeDetailByCoverNo(@Param("coverNo") String coverNo);

    /**
     * 按井盖id查询详情
     * @param coverId 井盖编号
     * @return 窨井盖详情
     */
//    ManholeCoverRealTimePageRespVO selectManholeDetailByCoverId(@Param("coverId") Long coverId);

    /**
     * 批量更新窨井盖监测状态
     *
     * @param coverIds 窨井盖 ID 列表
     * @param monitorStatus 监测状态
     * @return 更新成功的记录数
     */
    Integer batchUpdateMonitorStatus(@Param("coverIds") List<Long> coverIds,
                                     @Param("monitorStatus") String monitorStatus);

    /**
     * 批量更新关联设备状态
     *
     * @param coverIds 窨井盖 ID 列表
     * @param onlineStatus 设备在线状态
     * @return 更新成功的记录数
     */
    Integer batchUpdateDeviceStatus(@Param("coverIds") List<Long> coverIds,
                                    @Param("onlineStatus") String onlineStatus);

    /**
     * 查询近 24 小时监测统计数据（基于你的 SQL）
     */
//    @Select("SELECT " +
//            "COUNT(m.id) AS totalRecords, " +
//            "COUNT(DISTINCT m.cover_id) AS coverCount, " +
//            "ROUND(AVG(m.tilt_angle), 2) AS avgTiltAngle, " +
//            "MAX(m.tilt_angle) AS maxTiltAngle, " +
//            "MIN(m.tilt_angle) AS minTiltAngle, " +
//            "ROUND(AVG(m.vibration_data), 2) AS avgVibration, " +
//            "MAX(m.vibration_data) AS maxVibration, " +
//            "MIN(m.vibration_data) AS minVibration, " +
//            "COUNT(CASE WHEN m.monitor_status = '运行中' THEN 1 END) AS runningCount, " +
//            "COUNT(CASE WHEN m.monitor_status = '已停止' THEN 1 END) AS stoppedCount, " +
//            "(NOW() - INTERVAL 24 HOUR) AS startTime, " +
//            "NOW() AS endTime " +
//            "FROM manhole_monitor m " +
//            "WHERE m.deleted = 0 " +
//            "AND m.cover_id = #{coverId} " +
//            "AND m.create_time >= (NOW() - INTERVAL 24 HOUR)")
//    ManholeMonitorStatsRespVO select24HourStats(@Param("coverId") Long coverId);

    /**
     * 查询近 24 小时按小时分组的变化趋势数据（用于绘制曲线）
     */
//    @Select("SELECT " +
//            "DATE_FORMAT(m.create_time, '%Y-%m-%d %H:00:00') AS hourTime, " +
//            "ROUND(AVG(m.tilt_angle), 2) AS avgTiltAngle, " +
//            "MAX(m.tilt_angle) AS maxTiltAngle, " +
//            "MIN(m.tilt_angle) AS minTiltAngle, " +
//            "ROUND(AVG(m.vibration_data), 2) AS avgVibration, " +
//            "MAX(m.vibration_data) AS maxVibration, " +
//            "MIN(m.vibration_data) AS minVibration, " +
//            "COUNT(m.id) AS recordCount " +
//            "FROM manhole_monitor m " +
//            "WHERE m.deleted = 0 " +
//            "AND m.cover_id = #{coverId} " +
//            "AND m.create_time >= (NOW() - INTERVAL 24 HOUR) " +
//            "GROUP BY DATE_FORMAT(m.create_time, '%Y-%m-%d %H:00:00') " +
//            "ORDER BY hourTime ASC")
//    List<ManholeMonitorHourTrendVO> select24HourTrend(@Param("coverId") Long coverId);

    /**
     * 查询分页数据（手动拼接 LIMIT/OFFSET）
     */
    List<ManholeMonitorWarningRespVO> selectWarningMonitorPageData(
            @Param("reqVO") ManholeMonitorWarningPageReqVO reqVO);

    /**
     * 查询总条数（用于计算分页总数）
     */
    Long selectWarningMonitorPageCount(
            @Param("reqVO") ManholeMonitorWarningPageReqVO reqVO);

    /**
     * 查询井盖实时详情
     * @param coverId 井盖ID
     * @param tenantId 租户ID
     * @return 详情数据
     */
    ManholeCoverRealTimeDetailRespVO selectRealTimeDetail(@Param("coverId") String coverId,
                                                          @Param("tenantId") String tenantId);


    /**
     * 查询井盖基础信息
     */
    ManholeCoverRealTimeTrendRespVO selectCoverBaseInfo(
            @Param("coverId") String coverId,
            @Param("tenantId") String tenantId);


    /**
     * 查询近24小时按小时聚合的监测数据
     */
    @MapKey("hour")
    List<Map<String, Object>> selectHourlyMonitorData(
            @Param("coverId") String coverId,
            @Param("tenantId") String tenantId,
            @Param("indicatorType") Integer indicatorType);

    /**
     * 查询指标阈值
     */
    BigDecimal selectIndicatorThreshold(
            @Param("coverId") String coverId,
            @Param("indicatorType") Integer indicatorType,
            @Param("tenantId") String tenantId);

    /**
     * 查询井盖最新监测数据
     */
    ManholeMonitorDO selectLatestByCoverId(@Param("coverId") Long coverId);

    /**
     * 查询设备在线状态
     */
    String selectDeviceOnlineStatus(@Param("deviceId") Long deviceId);

}