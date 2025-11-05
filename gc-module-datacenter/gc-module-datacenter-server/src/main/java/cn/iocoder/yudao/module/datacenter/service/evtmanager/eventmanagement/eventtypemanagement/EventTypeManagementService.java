package cn.iocoder.yudao.module.datacenter.service.evtmanager.eventmanagement.eventtypemanagement;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo.EventTypeManagementCreateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo.EventTypeManagementPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo.EventTypeManagementUpdateReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.eventtypemanagement.EventTypeManagementDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 事件类型管理 Service 接口
 *
 * @author Gyh
 */
public interface EventTypeManagementService {

    /**
     * 创建事件类型
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEventType(@Valid EventTypeManagementCreateReqVO createReqVO);

    /**
     * 更新事件类型
     *
     * @param updateReqVO 更新信息
     */
    void updateEventType(@Valid EventTypeManagementUpdateReqVO updateReqVO);

    /**
     * 删除事件类型
     *
     * @param id 编号
     */
    void deleteEventType(Long id);

    /**
     * 获得事件类型
     *
     * @param id 编号
     * @return 事件类型
     */
    EventTypeManagementDO getEventType(Long id);

    /**
     * 获得事件类型分页
     *
     * @param pageReqVO 分页查询
     * @return 事件类型分页
     */
    PageResult<EventTypeManagementDO> getEventTypePage(@Valid EventTypeManagementPageReqVO pageReqVO);

    /**
     * 获得所有事件类型列表
     *
     * @return 事件类型列表
     */
    List<EventTypeManagementDO> getEventTypeList();

    /**
     * 获得所有启用的事件大类列表
     *
     * @return 事件大类列表
     */
    List<EventTypeManagementDO> getEnabledBigCategories();

    /**
     * 根据大类代码获得启用的中类列表
     *
     * @param eventBigCode 大类代码
     * @return 事件中类列表
     */
    List<EventTypeManagementDO> getEnabledMidCategoriesByBigCode(String eventBigCode);

    /**
     * 根据中类代码获得启用的小类列表
     *
     * @param eventMidCode 中类代码
     * @return 事件小类列表
     */
    List<EventTypeManagementDO> getEnabledSmallCategoriesByMidCode(String eventMidCode);

    /**
     * 更新启用状态
     *
     * @param id 编号
     * @param enableStatus 启用状态
     */
    void updateEnableStatus(Long id, String enableStatus);

    /**
     * 检查代码唯一性
     *
     * @param eventBigCode 大类代码
     * @param eventMidCode 中类代码
     * @param eventSmallCode 小类代码
     * @param id 编号（排除自身）
     * @return 是否唯一
     */
    boolean checkCodeUnique(String eventBigCode, String eventMidCode, String eventSmallCode, Long id);

    /**
     * 根据ID列表获取事件类型列表
     *
     * @param ids ID列表
     * @return 事件类型列表
     */
    List<EventTypeManagementDO> getEventTypeListByIds(List<Long> ids);

    /**
     * 根据小类代码获取事件类型
     *
     * @param eventSmallCode 小类代码
     * @return 事件类型
     */
    EventTypeManagementDO getEventTypeBySmallCode(String eventSmallCode);
}