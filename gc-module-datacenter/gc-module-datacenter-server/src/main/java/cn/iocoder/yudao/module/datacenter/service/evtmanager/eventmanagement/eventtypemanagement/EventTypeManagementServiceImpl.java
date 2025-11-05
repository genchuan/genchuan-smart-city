package cn.iocoder.yudao.module.datacenter.service.evtmanager.eventmanagement.eventtypemanagement;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo.EventTypeManagementCreateReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo.EventTypeManagementPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.eventmanagement.eventtypemanagement.vo.EventTypeManagementUpdateReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.eventtypemanagement.EventTypeManagementDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.eventmanagement.eventtypemanagement.EventTypeManagementMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 事件类型管理 Service 实现类
 *
 * @author Gyh
 */
@Service
@Validated
public class EventTypeManagementServiceImpl implements EventTypeManagementService {

    @Resource
    private EventTypeManagementMapper eventTypeManagementMapper;

    @Override
    public Long createEventType(EventTypeManagementCreateReqVO createReqVO) {
        // 校验代码唯一性
        validateCodeUnique(createReqVO.getEventBigCode(), createReqVO.getEventMidCode(),
                createReqVO.getEventSmallCode(), null);

        // 插入事件类型
        EventTypeManagementDO eventType = BeanUtils.toBean(createReqVO, EventTypeManagementDO.class);
        eventTypeManagementMapper.insert(eventType);
        return eventType.getId();
    }

    @Override
    public void updateEventType(EventTypeManagementUpdateReqVO updateReqVO) {
        // 校验存在
        validateEventTypeExists(updateReqVO.getId());
        // 校验代码唯一性
        validateCodeUnique(updateReqVO.getEventBigCode(), updateReqVO.getEventMidCode(),
                updateReqVO.getEventSmallCode(), updateReqVO.getId());

        // 更新事件类型
        EventTypeManagementDO updateObj = BeanUtils.toBean(updateReqVO, EventTypeManagementDO.class);
        eventTypeManagementMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventType(Long id) {
        // 校验存在
        validateEventTypeExists(id);
        // 删除
        eventTypeManagementMapper.deleteById(id);
    }

    @Override
    public EventTypeManagementDO getEventType(Long id) {
        return eventTypeManagementMapper.selectById(id);
    }

    @Override
    public PageResult<EventTypeManagementDO> getEventTypePage(EventTypeManagementPageReqVO pageReqVO) {
        // 修正字段名冲突，使用明确的字段名
        return eventTypeManagementMapper.selectPage(pageReqVO, new LambdaQueryWrapperX<EventTypeManagementDO>()
                .likeIfPresent(EventTypeManagementDO::getEventBigName, pageReqVO.getEventBigName())
                .likeIfPresent(EventTypeManagementDO::getEventMidName, pageReqVO.getEventMidName())
                .likeIfPresent(EventTypeManagementDO::getEventSmallName, pageReqVO.getEventSmallName())
                .eqIfPresent(EventTypeManagementDO::getEnableStatus, pageReqVO.getEnableStatus())
                .orderByDesc(EventTypeManagementDO::getId)); // 改为使用 ID 排序，避免字段冲突
    }

    @Override
    public List<EventTypeManagementDO> getEventTypeList() {
        return eventTypeManagementMapper.selectList();
    }

    @Override
    public List<EventTypeManagementDO> getEnabledBigCategories() {
        return eventTypeManagementMapper.selectEnabledBigCategories();
    }

    @Override
    public List<EventTypeManagementDO> getEnabledMidCategoriesByBigCode(String eventBigCode) {
        return eventTypeManagementMapper.selectEnabledMidCategoriesByBigCode(eventBigCode);
    }

    @Override
    public List<EventTypeManagementDO> getEnabledSmallCategoriesByMidCode(String eventMidCode) {
        return eventTypeManagementMapper.selectEnabledSmallCategoriesByMidCode(eventMidCode);
    }

    @Override
    public void updateEnableStatus(Long id, String enableStatus) {
        // 校验存在
        EventTypeManagementDO eventType = validateEventTypeExists(id);

        // 更新状态
        EventTypeManagementDO updateObj = new EventTypeManagementDO();
        updateObj.setId(id);
        updateObj.setEnableStatus(enableStatus);
        eventTypeManagementMapper.updateById(updateObj);

        // 如果禁用大类，同时禁用其下所有子类
//        if ("禁用".equals(enableStatus) && eventType.getEventMidCode() == null) {
//            // 需要手动实现批量更新
//            List<EventTypeManagementDO> children = eventTypeManagementMappe.selectByBigCode(eventType.getEventBigCode());
//            for (EventTypeManagementDO child : children) {
//                if (!child.getId().equals(id)) { // 排除自身
//                    EventTypeManagementDO childUpdate = new EventTypeManagementDO();
//                    childUpdate.setId(child.getId());
//                    childUpdate.setEnableStatus(enableStatus);
//                    eventTypeManagementMapper.updateById(childUpdate);
//                }
//            }
//        }
        // 如果禁用中类，同时禁用其下所有小类
        if ("禁用".equals(enableStatus) && eventType.getEventSmallCode() == null) {
//        else if ("禁用".equals(enableStatus) && eventType.getEventSmallCode() == null) {
            List<EventTypeManagementDO> children = eventTypeManagementMapper.selectByMidCode(eventType.getEventMidCode());
            for (EventTypeManagementDO child : children) {
                if (!child.getId().equals(id)) { // 排除自身
                    EventTypeManagementDO childUpdate = new EventTypeManagementDO();
                    childUpdate.setId(child.getId());
                    childUpdate.setEnableStatus(enableStatus);
                    eventTypeManagementMapper.updateById(childUpdate);
                }
            }
        }
    }

    @Override
    public boolean checkCodeUnique(String eventBigCode, String eventMidCode, String eventSmallCode, Long id) {
        // 根据不同的层级检查唯一性
        if (eventSmallCode != null) {
            EventTypeManagementDO exist = eventTypeManagementMapper.selectBySmallCode(eventSmallCode);
            return exist == null || Objects.equals(exist.getId(), id);
        } else if (eventMidCode != null) {
            List<EventTypeManagementDO> existList = eventTypeManagementMapper.selectByMidCode(eventMidCode);
            return existList.isEmpty() || (existList.size() == 1 && Objects.equals(existList.get(0).getId(), id));
        } else if (eventBigCode != null) {
            List<EventTypeManagementDO> existList = eventTypeManagementMapper.selectByBigCode(eventBigCode);
            return existList.isEmpty() || (existList.size() == 1 && Objects.equals(existList.get(0).getId(), id));
        }
        return true;
    }

    @Override
    public List<EventTypeManagementDO> getEventTypeListByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        // 使用新的查询方式替代已弃用的 selectBatchIds
        return eventTypeManagementMapper.selectList(new LambdaQueryWrapperX<EventTypeManagementDO>()
                .in(EventTypeManagementDO::getId, ids));
    }

    @Override
    public EventTypeManagementDO getEventTypeBySmallCode(String eventSmallCode) {
        return eventTypeManagementMapper.selectBySmallCode(eventSmallCode);
    }

    /**
     * 校验事件类型是否存在
     */
    private EventTypeManagementDO validateEventTypeExists(Long id) {
        EventTypeManagementDO eventType = eventTypeManagementMapper.selectById(id);
        if (eventType == null) {
            throw exception(EVENT_TYPE_NOT_EXISTS);
        }
        return eventType;
    }

    /**
     * 校验代码唯一性
     */
    private void validateCodeUnique(String eventBigCode, String eventMidCode, String eventSmallCode, Long id) {
        if (!checkCodeUnique(eventBigCode, eventMidCode, eventSmallCode, id)) {
            throw exception(EVENT_TYPE_CODE_DUPLICATE);
        }
    }
}