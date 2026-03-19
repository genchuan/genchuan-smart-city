package cn.iocoder.yudao.module.facility.service.manhole.manholemonitor;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor.ManholeMonitorMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.MONITOR_NOT_EXISTS;

/**
 * 窨井盖监测 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManholeMonitorServiceImpl implements ManholeMonitorService {

    @Resource
    private ManholeMonitorMapper monitorMapper;

    @Override
    public Long createMonitor(ManholeMonitorSaveReqVO createReqVO) {
        // 插入
        ManholeMonitorDO monitor = BeanUtils.toBean(createReqVO, ManholeMonitorDO.class);
        monitorMapper.insert(monitor);
        // 返回
        return monitor.getId();
    }

    @Override
    public void updateMonitor(ManholeMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateMonitorExists(updateReqVO.getId());
        // 更新
        ManholeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, ManholeMonitorDO.class);
        monitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitor(Long id) {
        // 校验存在
        validateMonitorExists(id);
        // 删除
        monitorMapper.deleteById(id);
    }

    private void validateMonitorExists(Long id) {
        if (monitorMapper.selectById(id) == null) {
            throw exception(MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public ManholeMonitorDO getMonitor(String id) {
        return monitorMapper.selectById(id);
    }

    @Override
    public PageResult<ManholeMonitorDO> getMonitorPage(ManholeMonitorPageReqVO pageReqVO) {
        return monitorMapper.selectPage(pageReqVO);
    }


    @Override
    public List<ManholeMonitorVO> getManholeMonitorList(String coverNo, String roadName, String statusName,
                                                        String onlineStatus, String monitorStatus, String riskLevel,
                                                        Integer abnormalVibrationFlag) {
        return monitorMapper.selectManholeMonitorList(coverNo, roadName, statusName, onlineStatus,
                monitorStatus, riskLevel, abnormalVibrationFlag);
    }

    /**
     * 按井盖编号查询详情（用于钻取弹窗）
     */
    @Override
    public ManholeMonitorVO getManholeDetailByCoverNo(String coverNo) {
        // 1. 参数校验
        if (coverNo == null || coverNo.trim().isEmpty()) {
            throw exception(MONITOR_NOT_EXISTS);
        }
        // 2. 查询详情
        ManholeMonitorVO detail = monitorMapper.selectManholeDetailByCoverNo(coverNo);
        // 3. 无数据兜底
        if (detail == null) {
            throw exception(MONITOR_NOT_EXISTS);
        }
        return detail;
    }

    /**
     * 批量更新监测状态
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer batchUpdateMonitorStatus(List<Long> coverIds, String monitorStatus) {
        // 1. 参数校验
        if (coverIds == null || coverIds.isEmpty()) {
            throw new IllegalArgumentException("请选择要操作的井盖");
        }
        // 3. 批量更新监测状态（同步更新操作人和更新时间）
        int count = monitorMapper.batchUpdateMonitorStatus(coverIds, monitorStatus);

        // 4. 同步更新关联设备状态
        String deviceOnlineStatus = "运行中".equals(monitorStatus) ? "在线" : "离线";
        monitorMapper.batchUpdateDeviceStatus(coverIds, deviceOnlineStatus);

        return count;
    }

    @Override
    public ManholeMonitorStatsRespVO get24HourStats(Long id) {

        ManholeMonitorStatsRespVO stats = monitorMapper.select24HourStats(id);

        if (stats == null) {
            stats = new ManholeMonitorStatsRespVO();
            stats.setAvgTiltAngle(BigDecimal.ZERO);
            stats.setMaxTiltAngle(BigDecimal.ZERO);
            stats.setMinTiltAngle(BigDecimal.ZERO);
            stats.setAvgVibration(BigDecimal.ZERO);
            stats.setMaxVibration(BigDecimal.ZERO);
            stats.setMinVibration(BigDecimal.ZERO);
        }

        return stats;
    }

    @Override
    public List<ManholeMonitorHourTrendVO> get24HourTrend(Long id) {

        List<ManholeMonitorHourTrendVO> trendList = monitorMapper.select24HourTrend(id);

        if (trendList == null) {
            return new ArrayList<>();
        }

        return trendList;
    }

}