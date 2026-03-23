package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.LineItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 转运作业 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferOperationMapper extends BaseMapperX<TransferOperationDO> {

    default PageResult<TransferOperationDO> selectPage(TransferOperationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferOperationDO>()
                .eqIfPresent(TransferOperationDO::getOperationId, reqVO.getOperationId())
                .eqIfPresent(TransferOperationDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(TransferOperationDO::getGarbageTypeId, reqVO.getGarbageTypeId())
                .betweenIfPresent(TransferOperationDO::getEntryTime, reqVO.getEntryTime())
                .eqIfPresent(TransferOperationDO::getGarbageWeight, reqVO.getGarbageWeight())
                .eqIfPresent(TransferOperationDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(TransferOperationDO::getEquipmentStatus, reqVO.getEquipmentStatus())
                .eqIfPresent(TransferOperationDO::getProgress, reqVO.getProgress())
                .eqIfPresent(TransferOperationDO::getDestination, reqVO.getDestination())
                .eqIfPresent(TransferOperationDO::getAbnormalIsAbnormal, reqVO.getAbnormalIsAbnormal())
                .betweenIfPresent(TransferOperationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferOperationDO::getId));
    }

    /**
     * 查询全局最大序号（用于operation_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(operation_id, '-', -1)), 0) FROM garbage_transfer_operation")
    Integer selectMaxSeq();

    List<TransferOperationDetailDO> selectDetailPage(@Param("reqVO") TransferOperationPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferOperationPageReqVO pageReqVO);

    /**
     * 统计当前作业总数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_operation WHERE deleted = 0")
    Long selectTotalCount();

    /**
     * 统计正常运行数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_operation WHERE deleted = 0 AND abnormal_is_abnormal = '否'")
    Long selectNormalCount();

    /**
     * 统计异常标记数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_operation WHERE deleted = 0 AND abnormal_is_abnormal = '是'")
    Long selectAbnormalCount();

    /**
     * 查询已完成转运作业数量（返回List<Map>格式）
     *
     * @return 包含已完成数量的List<Map>
     */
    @Select("SELECT " +
            "    '已完成' as status_name, " +
            "    COUNT(DISTINCT gto.id) as count " +
            "FROM garbage_transfer_operation gto " +
            "INNER JOIN garbage_collection gc ON gto.plan_id = gc.collection_id " +
            "WHERE gto.deleted = 0 " +
            "  AND gc.deleted = 0 " +
            "  AND gc.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY '已完成'")
    List<Map<String, Object>> selectCompletedCountAsList();

    /**
     * 已完成任务总数
     */
    @Select("SELECT COUNT(DISTINCT gto.id) " +
            "FROM garbage_transfer_operation gto " +
            "INNER JOIN garbage_collection gc ON gto.plan_id = gc.collection_id " +
            "WHERE gto.deleted = false AND gc.deleted = false AND gc.plan_status_id = 'uuid-plan-status-003'")
    Long selectTotalCompletedTasks();

    /**
     * 进站总量（吨，累加垃圾重量，NULL兜底为0）
     */
    @Select("SELECT COALESCE(SUM(gto.garbage_weight), 0) " +
            "FROM garbage_transfer_operation gto " +
            "INNER JOIN garbage_collection gc ON gto.plan_id = gc.collection_id " +
            "WHERE gto.deleted = false AND gc.plan_status_id = 'uuid-plan-status-003'")
    BigDecimal selectTotalInboundVolume();

    /**
     * 设备完好率
     */
/*    @Select("SELECT ROUND( " +
            "COALESCE((SELECT COUNT(*) FROM garbage_transfer_equipment WHERE deleted = false AND equipment_status = '完好'), 0) " +
            "/ " +
            "COALESCE((SELECT COUNT(*) FROM garbage_transfer_equipment WHERE deleted = false), 1) * 100, 2)")
    BigDecimal selectEquipmentHealthRate();*/

    /**
     * 环境达标率（%，保留2位小数：达标数/总检测数*100）
     */
    @Select("SELECT ROUND( " +
            "COALESCE((SELECT COUNT(*) FROM garbage_transfer WHERE deleted = false AND environment_rate >= 90), 0) " +
            "/ " +
            "COALESCE((SELECT COUNT(*) FROM garbage_transfer WHERE deleted = false), 1) * 100, 2)")
    BigDecimal selectEnvironmentComplianceRate();

    // ========== 柱状图：按日/周/月进站量 ==========
    /**
     * 按日统计进站量
     */
    @Select("SELECT " +
            "TO_CHAR(gto.create_time, 'YYYY-MM-DD') AS name, " +
            "COALESCE(SUM(gto.garbage_weight), 0) AS value " +
            "FROM garbage_transfer_operation gto " +
            "WHERE gto.deleted = false " +
            "GROUP BY TO_CHAR(gto.create_time, 'YYYY-MM-DD') " +
            "ORDER BY name ASC")
    List<BarItemVO> selectInboundVolumeByDay();

    /**
     * 按周统计进站量
     */
    @Select("SELECT " +
            "TO_CHAR(gto.create_time, 'YYYY-\"W\"WW') AS name, " +
            "COALESCE(SUM(gto.garbage_weight), 0) AS value " +
            "FROM garbage_transfer_operation gto " +
            "WHERE gto.deleted = false " +
            "GROUP BY TO_CHAR(gto.create_time, 'YYYY-\"W\"WW') " +
            "ORDER BY name DESC")
    List<BarItemVO> selectInboundVolumeByWeek();

    /**
     * 按月统计进站量
     */
    @Select("SELECT " +
            "TO_CHAR(gto.create_time, 'YYYY-MM') AS name, " +
            "COALESCE(SUM(gto.garbage_weight), 0) AS value " +
            "FROM garbage_transfer_operation gto " +
            "WHERE gto.deleted = false " +
            "GROUP BY TO_CHAR(gto.create_time, 'YYYY-MM') " +
            "ORDER BY name DESC")
    List<BarItemVO> selectInboundVolumeByMonth();

    // ========== 折线图：设备完好率趋势（近30天，按日统计） ==========
/*    @Select("SELECT " +
            "TO_CHAR(stat_time, '%Y-%m-%d') AS timePoint, " +
            "COALESCE(equipment_health_rate, 0) AS value " +
            "FROM garbage_transfer_operation_stat " +
            "WHERE deleted = false AND stat_time >= DATE_SUB(NOW(), INTERVAL 30 DAY) " +
            "ORDER BY stat_time ASC")
    List<LineItemVO> selectEquipmentHealthRateTrend();*/

    // ========== 圆环图1：各任务类型占比（已完成任务，NULL显示未知） ==========
    @Select("SELECT COALESCE(gto.garbage_type_id, '未知') AS name, COUNT(*) AS value " +
            "FROM garbage_transfer_operation gto " +
            "INNER JOIN garbage_collection gc ON gto.plan_id = gc.collection_id " +
            "WHERE gto.deleted = false AND gc.deleted = false AND gc.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY COALESCE(gto.garbage_type_id, '未知')")
    List<PieItemVO> selectTaskTypeDistribution();

    // ========== 圆环图2：各转运站完成量占比（已完成任务，按吨统计，NULL显示未知） ==========
    @Select("SELECT COALESCE(gt.name, '未知') AS name, COALESCE(SUM(gto.garbage_weight), 0) AS value " +
            "FROM garbage_transfer_operation gto " +
            "INNER JOIN garbage_collection gc ON gto.plan_id = gc.collection_id " +
            "LEFT JOIN garbage_transfer gt ON gto.transfer_id = gt.transfer_id " +
            "WHERE gto.deleted = false AND gc.deleted = false AND gc.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY COALESCE(gt.name, '未知')")
    List<PieItemVO> selectStationCompletionDistribution();
}