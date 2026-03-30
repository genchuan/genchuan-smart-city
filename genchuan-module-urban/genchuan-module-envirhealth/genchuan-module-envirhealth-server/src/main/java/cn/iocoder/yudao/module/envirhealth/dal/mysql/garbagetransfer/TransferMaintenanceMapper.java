package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 设备维护 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferMaintenanceMapper extends BaseMapperX<TransferMaintenanceDO> {

    default PageResult<TransferMaintenanceDO> selectPage(TransferMaintenancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferMaintenanceDO>()
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceId, reqVO.getMaintenanceId())
                .eqIfPresent(TransferMaintenanceDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(TransferMaintenanceDO::getEquipmentId, reqVO.getEquipmentId())
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceCycle, reqVO.getMaintenanceCycle())
                .betweenIfPresent(TransferMaintenanceDO::getLastMaintenanceTime, reqVO.getLastMaintenanceTime())
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceContent, reqVO.getMaintenanceContent())
                .eqIfPresent(TransferMaintenanceDO::getHandleBy, reqVO.getHandleBy())
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceStatus, reqVO.getMaintenanceStatus())
                .betweenIfPresent(TransferMaintenanceDO::getExpectedCompleteTime, reqVO.getExpectedCompleteTime())
                .eqIfPresent(TransferMaintenanceDO::getAbnormalIsTimeout, reqVO.getAbnormalIsTimeout())
                .betweenIfPresent(TransferMaintenanceDO::getMaintenanceTime, reqVO.getMaintenanceTime())
                .eqIfPresent(TransferMaintenanceDO::getReplaceParts, reqVO.getReplaceParts())
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceCost, reqVO.getMaintenanceCost())
                .eqIfPresent(TransferMaintenanceDO::getMaintenancePhoto, reqVO.getMaintenancePhoto())
                .betweenIfPresent(TransferMaintenanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferMaintenanceDO::getId));
    }

    /**
     * 查询全局最大序号（用于maintenance_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(maintenance_id, '-', -1)), 0) FROM garbage_transfer_maintenance")
    Integer selectMaxSeq();

    List<TransferMaintenanceDetailDO> selectDetailPage(@Param("reqVO") TransferMaintenancePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferMaintenancePageReqVO pageReqVO);

    //查询设备维护表的数量
    @Select("SELECT COUNT(*) FROM garbage_transfer_maintenance WHERE deleted = 0")
    Long selectAllCount();

    /**
     * 统计待维护设备总数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_maintenance WHERE deleted = 0 AND maintenance_status IN ('维护中', '待维护')")
    Long selectTotalPendingMaintenance();

    /**
     * 统计按类型待维护数
     */
    @Select("SELECT SUM(type_count) FROM (SELECT equipment_id, COUNT(*) as type_count FROM garbage_transfer_maintenance WHERE deleted = 0 AND maintenance_status IN ('维护中', '待维护') GROUP BY equipment_id) t")
    Long selectTypePendingMaintenance();

    /**
     * 统计超时未维护数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_maintenance WHERE deleted = 0 AND abnormal_is_timeout = 1 AND maintenance_status IN ('维护中', '待维护')")
    Long selectTimeoutUnmaintainedCount();

    /**
     * 查询设备类型占比（圆环图）
     */
    @Select("SELECT e.name as name, COUNT(m.id) as value " +
            "FROM garbage_transfer_maintenance m " +
            "LEFT JOIN sys_equipment e ON m.equipment_id = e.sys_equipment_id " +
            "WHERE m.deleted = 0 AND m.maintenance_status IN ('维护中', '待维护') " +
            "GROUP BY e.name")
    List<PieItemVO> selectEquipmentTypeDistribution();

    /**
     * 查询维护状态占比（圆环图）
     */
    @Select("SELECT " +
            "maintenance_status as name, " +
            "COUNT(id) as value " +
            "FROM garbage_transfer_maintenance " +
            "WHERE deleted = 0 " +
            "AND maintenance_status IN ('待维护', '维护中', '已完成') " +
            "GROUP BY maintenance_status")
    List<PieItemVO> selectMaintenanceStatusDistribution();

    /**
     * 查询不同转运站待维护设备数量对比（柱状图）
     */
    @Select("SELECT COALESCE(t.name, '其他') as name, COUNT(m.id) as value " +
            "FROM garbage_transfer_maintenance m " +
            "LEFT JOIN garbage_transfer t ON m.transfer_id = t.transfer_id " +
            "WHERE m.deleted = 0 AND m.maintenance_status IN ('维护中', '待维护') " +
            "GROUP BY COALESCE(t.name, '其他')")
    List<BarItemVO> selectStationPendingMaintenanceComparison();
}