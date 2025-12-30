package cn.iocoder.yudao.module.datacenter.service.eventdisposition;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.eventdisposition.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.eventdisposition.EventDispositionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 监测事件关联处置 Service 接口
 *
 * @author zcq
 */
public interface EventDispositionService {

    /**
     * 创建监测事件关联处置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventDisposition(@Valid EventDispositionSaveReqVO createReqVO);

    /**
     * 更新监测事件关联处置
     *
     * @param updateReqVO 更新信息
     */
    void updateEventDisposition(@Valid EventDispositionSaveReqVO updateReqVO);

    /**
     * 删除监测事件关联处置
     *
     * @param id 编号
     */
    void deleteEventDisposition(Long id);

    /**
     * 获得监测事件关联处置
     *
     * @param id 编号
     * @return 监测事件关联处置
     */
    EventDispositionDO getEventDisposition(Long id);

    /**
     * 获得监测事件关联处置分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件关联处置分页
     */
    PageResult<EventDispositionDO> getEventDispositionPage(EventDispositionPageReqVO pageReqVO);

    /**
     * 根据事件类型ID查询监测事件关联处置
     * @param eventTypeId 事件类型ID
     * @return 监测事件关联处置
     */
    EventDispositionDO getEventDispositionByEventTypeId(String eventTypeId);

    /**
     * 根据事件类型ID和行政区划编码查询监测事件关联处置
     * @param eventTypeId 事件类型ID
     * @param divisionCode 行政区划编码
     * @return 监测事件关联处置
     */
    EventDispositionDO getEventDispositionByEventTypeIdAndDivisionCode(String eventTypeId, String divisionCode);
}