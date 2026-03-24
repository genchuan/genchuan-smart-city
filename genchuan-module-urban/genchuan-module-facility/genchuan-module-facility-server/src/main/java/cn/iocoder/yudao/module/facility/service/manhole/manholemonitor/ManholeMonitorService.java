package cn.iocoder.yudao.module.facility.service.manhole.manholemonitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 窨井盖监测 Service 接口
 *
 * @author 亘川智城
 */
public interface ManholeMonitorService {

    /**
     * 创建窨井盖监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitor(@Valid ManholeMonitorSaveReqVO createReqVO);

    /**
     * 更新窨井盖监测
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitor(@Valid ManholeMonitorSaveReqVO updateReqVO);

    /**
     * 删除窨井盖监测
     *
     * @param id 编号
     */
    void deleteMonitor(Long id);

    /**
     * 获得窨井盖监测
     *
     * @param id 编号
     * @return 窨井盖监测
     */
    ManholeMonitorDO getMonitor(String id);

    /**
     * 获得窨井盖监测分页
     *
     * @param pageReqVO 分页查询
     * @return 窨井盖监测分页
     */
    PageResult<ManholeMonitorDO> getMonitorPage(ManholeMonitorPageReqVO pageReqVO);

    /**
     * 分页查询窨井盖监测数据（支持动态筛选）
     *
     * @return 窨井盖监测数据列表
     */


    PageResult<ManholeCoverRealTimePageRespVO> getRealTimePage(ManholeCoverRealTimePageReqVO reqVO);

    /**
     * 根据井盖编号查询
     */

//    ManholeCoverRealTimePageRespVO getManholeDetailByCoverNo(String coverNo);

    /**
     * 批量更新窨井盖监测状态
     *
     * @param coverIds 窨井盖 ID 列表
     * @param monitorStatus 监测状态（运行中/已停止）
     * @return 更新成功的记录数
     */
    Integer batchUpdateMonitorStatus(List<Long> coverIds, String monitorStatus);

    /**
     * 查询近 24 小时统计数据
     *
     * @param id 编号
     * @return 统计数据
     */
//    ManholeMonitorStatsRespVO get24HourStats(Long id);

    /**
     * 查询近 24 小时变化趋势
     *
     * @param id 编号
     * @return 趋势数据列表
     */
//    List<ManholeMonitorHourTrendVO> get24HourTrend(Long id);

    /**
     * 查询窨井盖预警监测列表（基于 sys_warn 表）
     * 包含预警编号、井盖编号、路段名称、异常类型、开合状态、倾斜角度、振动数据、
     * 处置时限、剩余处置时间、派单状态、风险等级、处置建议
     *
     * @return 窨井盖预警监测列表
     */
    PageResult<ManholeMonitorWarningRespVO> getWarningMonitorPage(ManholeMonitorWarningPageReqVO pageReqVO);

    /**
     * 获取井盖实时详情
     * @param coverId 井盖ID
     * @param tenantId 租户ID
     * @return 详情数据
     */
    ManholeCoverRealTimeDetailRespVO getRealTimeDetail(String coverId, String tenantId);

    /**
     * 获取 24 小时变化趋势
     * @param coverId 井盖ID
     * @return 详情数据
     */
    ManholeCoverRealTimeTrendRespVO getRealTimeTrend(String coverId, ManholeCoverRealTimeTrendReqVO reqVO);
}