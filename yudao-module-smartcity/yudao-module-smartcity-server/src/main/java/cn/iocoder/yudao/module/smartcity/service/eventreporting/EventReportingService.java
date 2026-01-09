package cn.iocoder.yudao.module.smartcity.service.eventreporting;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventreporting.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventreporting.EventReportingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import jakarta.validation.Valid;

/**
 * 事件上报 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface EventReportingService {

    /**
     * 创建事件上报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventReporting(@Valid EventReportingSaveReqVO createReqVO);

    /**
     * 更新事件上报
     *
     * @param updateReqVO 更新信息
     */
    void updateEventReporting(@Valid EventReportingSaveReqVO updateReqVO);

    /**
     * 删除事件上报
     *
     * @param id 编号
     */
    void deleteEventReporting(Long id);

    /**
     * 获得事件上报
     *
     * @param id 编号
     * @return 事件上报
     */
    EventReportingDO getEventReporting(Long id);

    /**
     * 获得事件上报分页
     *
     * @param pageReqVO 分页查询
     * @return 事件上报分页
     */
    PageResult<EventReportingDO> getEventReportingPage(EventReportingPageReqVO pageReqVO);

}