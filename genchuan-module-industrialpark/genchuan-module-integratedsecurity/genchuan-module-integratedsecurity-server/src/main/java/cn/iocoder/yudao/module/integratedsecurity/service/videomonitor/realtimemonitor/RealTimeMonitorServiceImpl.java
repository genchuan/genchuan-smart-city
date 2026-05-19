package cn.iocoder.yudao.module.integratedsecurity.service.videomonitor.realtimemonitor;

import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.RealTimeMonitorChartRespVO;
import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.RealTimeMonitorPageReqVO;
import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.RealTimeMonitorSaveReqVO;
import cn.iocoder.yudao.module.integratedsecurity.dal.dataobject.videomonitor.realtimemonitor.RealTimeMonitorDO;
import cn.iocoder.yudao.module.integratedsecurity.dal.mysql.videomonitor.realtimemonitor.RealTimeMonitorMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.integratedsecurity.enums.ErrorCodeConstants.TIME_MONITOR_NOT_EXISTS;


/**
 * 实时监控 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class RealTimeMonitorServiceImpl implements RealTimeMonitorService {

    @Resource
    private RealTimeMonitorMapper timeMonitorMapper;

    @Override
    public Long createTimeMonitor(RealTimeMonitorSaveReqVO createReqVO) {
        // 插入
        RealTimeMonitorDO timeMonitor = BeanUtils.toBean(createReqVO, RealTimeMonitorDO.class);
        timeMonitorMapper.insert(timeMonitor);

        // 返回
        return timeMonitor.getId();
    }

    @Override
    public void updateTimeMonitor(RealTimeMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateTimeMonitorExists(updateReqVO.getId());
        // 更新
        RealTimeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, RealTimeMonitorDO.class);
        timeMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteTimeMonitor(Long id) {
        // 校验存在
        validateTimeMonitorExists(id);
        // 删除
        timeMonitorMapper.deleteById(id);
    }

    @Override
    public void deleteTimeMonitorListByIds(List<Long> ids) {
        // 删除
        timeMonitorMapper.deleteByIds(ids);
    }


    private RealTimeMonitorDO validateTimeMonitorExists(Long id) {
        RealTimeMonitorDO monitor = timeMonitorMapper.selectById(id);
        if (monitor == null) {
            throw exception(TIME_MONITOR_NOT_EXISTS);
        }
        return monitor;
    }

    @Override
    public RealTimeMonitorDO getTimeMonitor(Long id) {
        return timeMonitorMapper.selectById(id);
    }

    @Override
    public PageResult<RealTimeMonitorDO> getTimeMonitorPage(RealTimeMonitorPageReqVO pageReqVO) {
        return timeMonitorMapper.selectPage(pageReqVO);
    }

    @Override
    public void snapTimeMonitor(List<Long> ids) {
        String snapTime = LocalDateTime.now().toString();
        List<RealTimeMonitorDO> monitors = timeMonitorMapper.selectBatchIds(ids);
        monitors.forEach(monitor -> {
            monitor.setSnapImg(snapTime);
            timeMonitorMapper.updateById(monitor);
        });
    }

    @Override
    public void pauseTimeMonitor(List<Long> ids) {
        List<RealTimeMonitorDO> monitors = timeMonitorMapper.selectBatchIds(ids);
        monitors.forEach(monitor -> {
            monitor.setRunStatus("2");
            timeMonitorMapper.updateById(monitor);
        });
    }

    @Override
    public void restartTimeMonitor(List<Long> ids) {
        List<RealTimeMonitorDO> monitors = timeMonitorMapper.selectBatchIds(ids);
        monitors.forEach(monitor -> {
            monitor.setRunStatus("1");
            timeMonitorMapper.updateById(monitor);
        });
    }

    @Override
    public void focusTimeMonitor(Long id) {
        validateTimeMonitorExists(id);
        // TODO 对接硬件：调整摄像头焦距
    }

    @Override
    public void alarmTimeMonitor(Long id, String alarmContent) {
        RealTimeMonitorDO monitor = validateTimeMonitorExists(id);
        monitor.setAlarmStatus("1");
        monitor.setAlarmContent(alarmContent);
        timeMonitorMapper.updateById(monitor);
    }

    @Override
    public void handleTimeMonitor(Long id, String handleResult) {
        RealTimeMonitorDO monitor = validateTimeMonitorExists(id);
        monitor.setAlarmStatus("2");
        monitor.setHandleResult(handleResult);
        timeMonitorMapper.updateById(monitor);
    }

    @Override
    public void recordTimeMonitor(Long id, Integer recordDuration) {
        validateTimeMonitorExists(id);
        // TODO 对接硬件：开始录制视频，时长 recordDuration 秒
    }

    @Override
    public RealTimeMonitorChartRespVO getTimeMonitorChart() {
        RealTimeMonitorChartRespVO respVO = new RealTimeMonitorChartRespVO();
        respVO.setOnlineCount(timeMonitorMapper.selectCountByRunStatus("1").intValue());
        respVO.setOfflineCount(timeMonitorMapper.selectCountByRunStatus("0").intValue());
        respVO.setAlarmTotalCount(timeMonitorMapper.selectCountByAlarmStatus("1").intValue());
        respVO.setHandleCompleteCount(timeMonitorMapper.selectCountByHandleResultIsNotNull().intValue());
        respVO.setAreaStatList(timeMonitorMapper.selectAreaStatList());
        respVO.setCameraMapList(timeMonitorMapper.selectCameraMapList());
        return respVO;
    }

}