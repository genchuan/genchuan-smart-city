package cn.iocoder.yudao.module.facility.service.manhole.manholemonitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorVO;
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
     * @param roadName 路段名称（模糊查询）
     * @param statusName 开合状态名称
     * @param onlineStatus 设备在线状态
     * @return 窨井盖监测数据列表
     */


    List<ManholeMonitorVO> getManholeMonitorList(String coverNo, String roadName, String statusName,
                                                 String onlineStatus, String monitorStatus, String riskLevel,
                                                 Integer abnormalVibrationFlag);

    /**
     * 更加井盖编号查询
     */

    ManholeMonitorVO getManholeDetailByCoverNo(String coverNo);

    /**
     * 批量更新窨井盖监测状态
     *
     * @param coverIds 窨井盖 ID 列表
     * @param monitorStatus 监测状态（运行中/已停止）
     * @return 更新成功的记录数
     */
    Integer batchUpdateMonitorStatus(List<Long> coverIds, String monitorStatus);
}