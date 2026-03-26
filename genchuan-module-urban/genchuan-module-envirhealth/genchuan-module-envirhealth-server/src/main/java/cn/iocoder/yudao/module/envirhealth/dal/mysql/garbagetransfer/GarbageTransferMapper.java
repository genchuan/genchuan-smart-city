package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 垃圾转运站 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GarbageTransferMapper extends BaseMapperX<GarbageTransferDO> {

    default PageResult<GarbageTransferDO> selectPage(GarbageTransferPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageTransferDO>()
                .eqIfPresent(GarbageTransferDO::getTransferId, reqVO.getTransferId())
                .likeIfPresent(GarbageTransferDO::getName, reqVO.getName())
                .eqIfPresent(GarbageTransferDO::getLocation, reqVO.getLocation())
                .eqIfPresent(GarbageTransferDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GarbageTransferDO::getEquipmentIds, reqVO.getEquipmentIds())
                .eqIfPresent(GarbageTransferDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(GarbageTransferDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(GarbageTransferDO::getDailyTransferVolume, reqVO.getDailyTransferVolume())
                .eqIfPresent(GarbageTransferDO::getEquipmentRate, reqVO.getEquipmentRate())
                .eqIfPresent(GarbageTransferDO::getEnvironmentRate, reqVO.getEnvironmentRate())
                .eqIfPresent(GarbageTransferDO::getUnhandledAlarmCount, reqVO.getUnhandledAlarmCount())
                .eqIfPresent(GarbageTransferDO::getPendingMaintenanceCount, reqVO.getPendingMaintenanceCount())
                .eqIfPresent(GarbageTransferDO::getEnvironmentData, reqVO.getEnvironmentData())
                .betweenIfPresent(GarbageTransferDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageTransferDO::getId));
    }

    /**
     * 查询全局最大序号（用于transfer_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(transfer_id, '-', -1)), 0) FROM garbage_transfer")
    Integer selectMaxSeq();

    List<GarbageTransferDetailDO> selectDetailPage(@Param("reqVO") GarbageTransferPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") GarbageTransferPageReqVO pageReqVO);

    /**
     * 统计总转运站数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer WHERE deleted = 0")
    Long selectTotalStations();

    /**
     * 统计正常运营数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer WHERE deleted = 0 AND operation_status_id = 'uuid-op-status-001'")
    Long selectNormalOperationCount();

    /**
     * 统计设备正常数 (设备正常运行率 ≥ 95%)
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer WHERE deleted = 0 AND equipment_rate >= 95")
    Long selectEquipmentNormalCount();

    /**
     * 统计环境达标数 (环境达标率 ≥ 90%)
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer WHERE deleted = 0 AND environment_rate >= 90")
    Long selectEnvironmentStandardCount();

// ========== 圆环图数据统计 ==========

    /**
     * 运营状态分布统计 (返回圆环图数据)
     * 关联 sys_operation_status 表获取状态名称
     */
    @Select("SELECT " +
            "sos.name, " +
            "COUNT(*) as value " +
            "FROM garbage_transfer gt " +
            "LEFT JOIN sys_operation_status sos ON gt.operation_status_id = sos.sys_operation_status_id " +
            "WHERE gt.deleted = 0 " +
            "AND (sos.deleted = 0 OR sos.deleted IS NULL) " +
            "GROUP BY gt.operation_status_id, sos.name")
    List<PieItemVO> selectOperationStatusPie();

    /**
     * 区域分布统计 (返回圆环图数据)
     * 需要关联sys_area表获取区域名称
     */
    @Select("SELECT a.area_name as name, COUNT(*) as value " +
            "FROM garbage_transfer gt " +
            "LEFT JOIN sys_area a ON gt.area_code = a.area_code " +
            "WHERE gt.deleted = 0 " +
            "GROUP BY gt.area_code, a.area_name")
    List<PieItemVO> selectAreaPie();

// ========== 柱状图数据统计 ==========

    /**
     * 日转运量对比统计 (返回柱状图数据)
     * 取前10个转运站按日转运量排序
     */
    @Select("SELECT name, daily_transfer_volume as value " +
            "FROM garbage_transfer " +
            "WHERE deleted = 0 AND daily_transfer_volume > 0 " +
            "ORDER BY daily_transfer_volume DESC " +
            "LIMIT 10")
    List<BarItemVO> selectDailyTransferVolumeBar();

// ========== 折线图数据统计 ==========


// ========== ==========

    /**
     * 校验transferId是否存在
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer WHERE transfer_id = #{transferId}")
    Integer countByTransferId(@Param("transferId") String transferId);

    /**
     * 未处理预警数+1
     */
    @Update("UPDATE garbage_transfer SET unhandled_alarm_count = unhandled_alarm_count + 1 WHERE transfer_id = #{transferId}")
    int incrementUnhandledAlarmCount(@Param("transferId") String transferId);

    /**
     * 未处理预警数-1
     */
    @Update("UPDATE garbage_transfer " +
            "SET unhandled_alarm_count = GREATEST(unhandled_alarm_count - 1, 0) " +
            "WHERE transfer_id = #{transferId}")
    int decrementUnhandledAlarmCount(@Param("transferId") String transferId);
}