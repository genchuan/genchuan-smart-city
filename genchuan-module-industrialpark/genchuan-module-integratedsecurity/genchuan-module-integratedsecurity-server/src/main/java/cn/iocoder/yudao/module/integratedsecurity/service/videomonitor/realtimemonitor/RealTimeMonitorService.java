package cn.iocoder.yudao.module.integratedsecurity.service.videomonitor.realtimemonitor;

import java.util.*;

import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.RealTimeMonitorChartRespVO;
import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.RealTimeMonitorPageReqVO;
import cn.iocoder.yudao.module.integratedsecurity.controller.admin.videomonitor.realtimemonitor.vo.RealTimeMonitorSaveReqVO;
import cn.iocoder.yudao.module.integratedsecurity.dal.dataobject.videomonitor.realtimemonitor.RealTimeMonitorDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 实时监控 Service 接口
 *
 * @author 亘川智城
 */
public interface RealTimeMonitorService {

    Long createTimeMonitor(@Valid RealTimeMonitorSaveReqVO createReqVO);

    void updateTimeMonitor(@Valid RealTimeMonitorSaveReqVO updateReqVO);

    void deleteTimeMonitor(Long id);

    void deleteTimeMonitorListByIds(List<Long> ids);

    RealTimeMonitorDO getTimeMonitor(Long id);

    PageResult<RealTimeMonitorDO> getTimeMonitorPage(RealTimeMonitorPageReqVO pageReqVO);

    void snapTimeMonitor(List<Long> ids);

    void pauseTimeMonitor(List<Long> ids);

    void restartTimeMonitor(List<Long> ids);

    void focusTimeMonitor(Long id);

    void alarmTimeMonitor(Long id, String alarmContent);

    void handleTimeMonitor(Long id, String handleResult);

    void recordTimeMonitor(Long id, Integer recordDuration);

    RealTimeMonitorChartRespVO getTimeMonitorChart();

}