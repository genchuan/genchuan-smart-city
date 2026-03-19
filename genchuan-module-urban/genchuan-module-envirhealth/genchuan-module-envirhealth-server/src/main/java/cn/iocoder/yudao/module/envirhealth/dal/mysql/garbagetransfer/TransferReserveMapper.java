package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReservePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 进站预约 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferReserveMapper extends BaseMapperX<TransferReserveDO> {

    default PageResult<TransferReserveDO> selectPage(TransferReservePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferReserveDO>()
                .eqIfPresent(TransferReserveDO::getReserveId, reqVO.getReserveId())
                .eqIfPresent(TransferReserveDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(TransferReserveDO::getGarbageTypeId, reqVO.getGarbageTypeId())
                .betweenIfPresent(TransferReserveDO::getExpectedTime, reqVO.getExpectedTime())
                .eqIfPresent(TransferReserveDO::getGarbageWeight, reqVO.getGarbageWeight())
                .eqIfPresent(TransferReserveDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(TransferReserveDO::getReserveStatus, reqVO.getReserveStatus())
                .eqIfPresent(TransferReserveDO::getSortNo, reqVO.getSortNo())
                .betweenIfPresent(TransferReserveDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .eqIfPresent(TransferReserveDO::getHandleBy, reqVO.getHandleBy())
                .betweenIfPresent(TransferReserveDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferReserveDO::getId));
    }

    /**
     * 查询全局最大序号（用于reserve_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(reserve_id, '-', -1)), 0) FROM garbage_transfer_reserve")
    Integer selectMaxSeq();

    /**
     * 查询已经排序的最大值
     */
    @Select("SELECT COALESCE(MAX(sort_no), 0) FROM garbage_transfer_reserve WHERE deleted = 0 AND reserve_status = '已排序' ")
    Integer selectMaxSortNo();

    List<TransferReserveDetailDO> selectDetailPage(@Param("reqVO") TransferReservePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferReservePageReqVO pageReqVO);

    // ========== 看板统计相关方法 ==========

    /**
     * 统计待进站车辆数 (全部)
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_reserve WHERE deleted = 0")
    Long selectAllCount();

    /**
     * 统计待进站车辆数 (预约状态为'待排序')
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_reserve WHERE deleted = 0 AND reserve_status = '待排序'")
    Long selectPendingVehicles();

    /**
     * 统计已排序车辆数 (预约状态为'已排序')
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_reserve WHERE deleted = 0 AND reserve_status = '已排序'")
    Long selectSortedVehicles();

    /**
     * 统计今日预约总数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_reserve WHERE deleted = 0 AND DATE(expected_time) = '2026-02-20'")
//    @Select("SELECT COUNT(*) FROM garbage_transfer_reserve WHERE deleted = 0 AND DATE(expected_time) = CURDATE()")
    Long selectTodayTotalReserves();

    /**
     * 垃圾品类分布统计 (返回圆环图数据)
     * 关联 sys_garbage_type 表获取垃圾品类名称
     */
    @Select("SELECT " +
            "COALESCE(sgt.name, '未知品类') as name, " +
            "COUNT(*) as value " +
            "FROM garbage_transfer_reserve tr " +
            "LEFT JOIN sys_garbage_type sgt ON tr.garbage_type_id = sgt.sys_garbage_type_id " +
            "WHERE tr.deleted = 0 " +
            "GROUP BY tr.garbage_type_id, sgt.name")
    List<PieItemVO> selectGarbageTypePie();

    /**
     * 区域分布统计 (返回圆环图数据)
     * 关联 sys_area 表获取区域名称
     */
    @Select("SELECT " +
            "COALESCE(sa.area_name, '未知区域') as name, " +
            "COUNT(*) as value " +
            "FROM garbage_transfer_reserve tr " +
            "LEFT JOIN sys_area sa ON tr.area_code = sa.area_code " +
            "WHERE tr.deleted = 0 " +
            "GROUP BY tr.area_code, sa.area_name")
    List<PieItemVO> selectAreaPie();

    /**
     * 不同时段预约车辆数量对比 (返回柱状图数据) - 按时间段分组
     * 早上、上午、下午、晚上
     */
    @Select("SELECT " +
            "CASE " +
            "   WHEN HOUR(expected_time) BETWEEN 0 AND 6 THEN '凌晨' " +
            "   WHEN HOUR(expected_time) BETWEEN 7 AND 11 THEN '上午' " +
            "   WHEN HOUR(expected_time) BETWEEN 12 AND 13 THEN '中午' " +
            "   WHEN HOUR(expected_time) BETWEEN 14 AND 18 THEN '下午' " +
            "   ELSE '晚上' " +
            "END as name, " +
            "COUNT(*) as value " +
            "FROM garbage_transfer_reserve " +
            "WHERE deleted = 0 AND DATE(expected_time) = '2026-02-20' " +
            "GROUP BY " +
            "CASE " +
            "   WHEN HOUR(expected_time) BETWEEN 0 AND 6 THEN '凌晨' " +
            "   WHEN HOUR(expected_time) BETWEEN 7 AND 11 THEN '上午' " +
            "   WHEN HOUR(expected_time) BETWEEN 12 AND 13 THEN '中午' " +
            "   WHEN HOUR(expected_time) BETWEEN 14 AND 18 THEN '下午' " +
            "   ELSE '晚上' " +
            "END " +
            "ORDER BY MIN(HOUR(expected_time))")
    List<BarItemVO> selectReserveCountByTimeSlot();
}