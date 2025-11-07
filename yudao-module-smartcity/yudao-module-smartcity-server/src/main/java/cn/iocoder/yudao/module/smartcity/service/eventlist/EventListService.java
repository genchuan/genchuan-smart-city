package cn.iocoder.yudao.module.smartcity.service.eventlist;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventlist.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventlist.EventListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import jakarta.validation.Valid;

/**
 * 事件列表 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface EventListService {

    /**
     * 创建事件列表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventList(@Valid EventListSaveReqVO createReqVO);

    /**
     * 更新事件列表
     *
     * @param updateReqVO 更新信息
     */
    void updateEventList(@Valid EventListSaveReqVO updateReqVO);

    /**
     * 删除事件列表
     *
     * @param id 编号
     */
    void deleteEventList(Long id);

    /**
     * 获得事件列表
     *
     * @param id 编号
     * @return 事件列表
     */
    EventListDO getEventList(Long id);

    /**
     * 获得事件列表分页
     *
     * @param pageReqVO 分页查询
     * @return 事件列表分页
     */
    PageResult<EventListDO> getEventListPage(EventListPageReqVO pageReqVO);

}