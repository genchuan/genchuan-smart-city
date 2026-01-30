package cn.iocoder.yudao.module.park.service.park.basicAssociation.eventtype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo.EventTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo.EventTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.eventtype.EventTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测事件类别 Service 接口
 *
 * @author zhucongquan
 */
public interface EventTypeService {

    /**
     * 创建监测事件类别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventType(@Valid EventTypeSaveReqVO createReqVO);

    /**
     * 更新监测事件类别
     *
     * @param updateReqVO 更新信息
     */
    void updateEventType(@Valid EventTypeSaveReqVO updateReqVO);

    /**
     * 删除监测事件类别
     *
     * @param id 编号
     */
    void deleteEventType(Long id);

    /**
     * 获得监测事件类别
     *
     * @param id 编号
     * @return 监测事件类别
     */
    EventTypeDO getEventType(Long id);

    /**
     * 获得监测事件类别分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件类别分页
     */
    PageResult<EventTypeDO> getEventTypePage(EventTypePageReqVO pageReqVO);

}