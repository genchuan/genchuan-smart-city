package cn.iocoder.yudao.module.facility.service.road.monitor;

import java.util.*;

import cn.iocoder.yudao.module.facility.controller.admin.road.monitor.vo.*;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.monitor.MonitorDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 道路监测 Service 接口
 *
 * @author 亘川智城
 */
public interface MonitorService {

    /**
     * 创建道路监测
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitor(@Valid MonitorSaveReqVO createReqVO);

    /**
     * 更新道路监测
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitor(@Valid MonitorUpdateReqVO updateReqVO);

    /**
     * 删除道路监测
     *
     * @param id 编号
     */
    void deleteMonitor(Long id);

    /**
     * 获得道路监测
     *
     * @param id 编号
     * @return 道路监测
     */
    MonitorDO getMonitor(Long id);

    /**
     * 获得道路监测分页
     *
     * @param pageReqVO 分页查询
     * @return 道路监测分页
     */
    PageResult<MonitorDO> getMonitorPage(MonitorPageReqVO pageReqVO);

    PageResult<RealtimePageRespVO> getRealtimePage(RealtimePageReqVO reqVO);
}
