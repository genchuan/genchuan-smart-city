package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 转运站预警 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferAlarmMapper extends BaseMapperX<TransferAlarmDO> {

    default PageResult<TransferAlarmDO> selectPage(TransferAlarmPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferAlarmDO>()
                .eqIfPresent(TransferAlarmDO::getAlarmId, reqVO.getAlarmId())
                .eqIfPresent(TransferAlarmDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(TransferAlarmDO::getAlarmTypeId, reqVO.getAlarmTypeId())
                .betweenIfPresent(TransferAlarmDO::getAlarmTime, reqVO.getAlarmTime())
                .eqIfPresent(TransferAlarmDO::getAlarmContent, reqVO.getAlarmContent())
                .eqIfPresent(TransferAlarmDO::getRelevantInfo, reqVO.getRelevantInfo())
                .eqIfPresent(TransferAlarmDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(TransferAlarmDO::getHandleBy, reqVO.getHandleBy())
                .eqIfPresent(TransferAlarmDO::getAbnormalIsTimeout, reqVO.getAbnormalIsTimeout())
                .eqIfPresent(TransferAlarmDO::getHandleProgress, reqVO.getHandleProgress())
                .eqIfPresent(TransferAlarmDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(TransferAlarmDO::getProofMaterial, reqVO.getProofMaterial())
                .betweenIfPresent(TransferAlarmDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferAlarmDO::getId));
    }

    /**
     * 查询全局最大序号（用于alarm_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(alarm_id, '-', -1)), 0) FROM garbage_transfer_alarm")
    Integer selectMaxSeq();

    List<TransferAlarmDetailDO> selectDetailPage(@Param("reqVO") TransferAlarmPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferAlarmPageReqVO pageReqVO);

    //查询预警表的数量
    @Select("SELECT COUNT(*) FROM garbage_transfer_alarm WHERE deleted = 0")
    Long selectAllCount();

    // ========== 看板统计相关方法 ==========
    /**
     * 获取待处置预警总数（handle_status = '待处置'）
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_alarm WHERE deleted = 0 AND handle_status = '待处置'")
    Long selectTotalPendingAlarm();

    /**
     * 获取高优先级预警数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_alarm WHERE deleted = 0 AND handle_status = '待处置' AND priority = '高'")
    Long selectHighPriorityCount();

    /**
     * 获取超时未处理预警数（abnormal_is_timeout = '是' 且 待处置）
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_alarm WHERE deleted = 0 AND handle_status = '待处置' AND abnormal_is_timeout = '是'")
    Long selectTimeoutUnprocessedCount();

    /**
     * 获取预警类型分布占比
     */
    @Select("SELECT COALESCE(t.alarm_name, '未知') AS name, COUNT(a.id) AS value " +
            "FROM garbage_transfer_alarm a " +
            "LEFT JOIN sys_alarm_type t ON a.alarm_type_id = t.alarm_type_id " +
            "WHERE a.deleted = 0 " +
            "GROUP BY COALESCE(t.alarm_name, '未知')")
    List<PieItemVO> selectAlarmTypeDistribution();

    /**
     * 获取预警转运站分布占比
     */
    @Select("SELECT COALESCE(t.name, '未知') AS name, COUNT(a.id) AS value " +
            "FROM garbage_transfer_alarm a " +
            "LEFT JOIN garbage_transfer t ON a.transfer_id = t.transfer_id " +
            "WHERE a.deleted = 0 " +
            "GROUP BY COALESCE(t.name, '未知')")
    List<PieItemVO> selectTransferStationDistribution();

    /**
     * 获取不同责任人待处置预警数量对比
     */
    @Select("SELECT COALESCE(u.user_name, '未知') AS name, COUNT(a.id) AS value " +
            "FROM garbage_transfer_alarm a " +
            "LEFT JOIN sys_user u ON a.handle_by = u.user_id " +
            "WHERE a.deleted = 0 AND a.handle_status = '待处置' " +
            "GROUP BY COALESCE(u.user_name, '未知')")
    List<BarItemVO> selectHandlerPendingAlarmComparison();
}