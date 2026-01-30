package cn.iocoder.yudao.module.park.service.park.basicAssociation.eventtype;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo.EventTypePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.eventtype.vo.EventTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.eventtype.EventTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.park.dal.mysql.park.basicAssociation.eventtype.EventTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 监测事件类别 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class EventTypeServiceImpl implements EventTypeService {

    @Resource
    private EventTypeMapper eventTypeMapper;

    @Override
    public Long createEventType(EventTypeSaveReqVO createReqVO) {
        // 插入
        EventTypeDO eventType = BeanUtils.toBean(createReqVO, EventTypeDO.class);
        eventTypeMapper.insert(eventType);
        // 返回
        return eventType.getId();
    }

    @Override
    public void updateEventType(EventTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateEventTypeExists(updateReqVO.getId());
        // 更新
        EventTypeDO updateObj = BeanUtils.toBean(updateReqVO, EventTypeDO.class);
        eventTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventType(Long id) {
        // 校验存在
        validateEventTypeExists(id);
        // 删除
        eventTypeMapper.deleteById(id);
    }

    private void validateEventTypeExists(Long id) {
        if (eventTypeMapper.selectById(id) == null) {
            throw exception(EVENT_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public EventTypeDO getEventType(Long id) {
        return eventTypeMapper.selectById(id);
    }

    @Override
    public PageResult<EventTypeDO> getEventTypePage(EventTypePageReqVO pageReqVO) {
        return eventTypeMapper.selectPage(pageReqVO);
    }

}