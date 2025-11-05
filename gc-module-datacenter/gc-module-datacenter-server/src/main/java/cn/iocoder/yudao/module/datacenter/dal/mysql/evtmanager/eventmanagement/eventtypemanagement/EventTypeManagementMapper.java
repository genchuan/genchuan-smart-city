package cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.eventmanagement.eventtypemanagement;

import java.util.*;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.eventtypemanagement.EventTypeManagementDO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 事件类型管理 Mapper
 *
 * @author Gyh
 */
@Mapper
public interface EventTypeManagementMapper extends BaseMapperX<EventTypeManagementDO> {

    /**
     * 根据事件类型ID查询
     */
    default EventTypeManagementDO selectByEventTypeId(String eventTypeId) {
        return selectOne("event_type_id", eventTypeId);
    }

    /**
     * 根据大类代码查询
     */
    default List<EventTypeManagementDO> selectByBigCode(String eventBigCode) {
        return selectList("event_big_code", eventBigCode);
    }

    /**
     * 根据中类代码查询
     */
    default List<EventTypeManagementDO> selectByMidCode(String eventMidCode) {
        return selectList("event_mid_code", eventMidCode);
    }

    /**
     * 根据小类代码查询
     */
    default EventTypeManagementDO selectBySmallCode(String eventSmallCode) {
        return selectOne("event_small_code", eventSmallCode);
    }

    /**
     * 根据启用状态查询
     */
    default List<EventTypeManagementDO> selectByEnableStatus(String enableStatus) {
        return selectList("enable_status", enableStatus);
    }

    /**
     * 分页查询事件类型
     */
    default Page<EventTypeManagementDO> selectPage(Page<EventTypeManagementDO> page,
                                                   String eventBigName,
                                                   String eventMidName,
                                                   String eventSmallName,
                                                   String enableStatus) {
        return selectPage(page, new LambdaQueryWrapperX<EventTypeManagementDO>()
                .likeIfPresent(EventTypeManagementDO::getEventBigName, eventBigName)
                .likeIfPresent(EventTypeManagementDO::getEventMidName, eventMidName)
                .likeIfPresent(EventTypeManagementDO::getEventSmallName, eventSmallName)
                .eqIfPresent(EventTypeManagementDO::getEnableStatus, enableStatus)
                .orderByDesc(EventTypeManagementDO::getCreateTime));
    }

    /**
     * 查询所有启用的大类
     */
    default List<EventTypeManagementDO> selectEnabledBigCategories() {
        return selectList(new LambdaQueryWrapperX<EventTypeManagementDO>()
                .isNull(EventTypeManagementDO::getEventMidCode)
                .isNull(EventTypeManagementDO::getEventSmallCode)
                .eq(EventTypeManagementDO::getEnableStatus, "启用"));
    }

    /**
     * 根据大类代码查询启用的中类
     */
    default List<EventTypeManagementDO> selectEnabledMidCategoriesByBigCode(String eventBigCode) {
        return selectList(new LambdaQueryWrapperX<EventTypeManagementDO>()
                .eq(EventTypeManagementDO::getEventBigCode, eventBigCode)
                .isNotNull(EventTypeManagementDO::getEventMidCode)
                .isNull(EventTypeManagementDO::getEventSmallCode)
                .eq(EventTypeManagementDO::getEnableStatus, "启用"));
    }

    /**
     * 根据中类代码查询启用的小类
     */
    default List<EventTypeManagementDO> selectEnabledSmallCategoriesByMidCode(String eventMidCode) {
        return selectList(new LambdaQueryWrapperX<EventTypeManagementDO>()
                .eq(EventTypeManagementDO::getEventMidCode, eventMidCode)
                .isNotNull(EventTypeManagementDO::getEventSmallCode)
                .eq(EventTypeManagementDO::getEnableStatus, "启用"));
    }

    /**
     * 检查大类代码是否存在
     */
    default boolean existsByBigCode(String eventBigCode) {
        return selectCount("event_big_code", eventBigCode) > 0;
    }

    /**
     * 检查中类代码是否存在
     */
    default boolean existsByMidCode(String eventMidCode) {
        return selectCount("event_mid_code", eventMidCode) > 0;
    }

    /**
     * 检查小类代码是否存在
     */
    default boolean existsBySmallCode(String eventSmallCode) {
        return selectCount("event_small_code", eventSmallCode) > 0;
    }
}