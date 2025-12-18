package cn.iocoder.yudao.module.datacenter.service.eventdisposition;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.eventdisposition.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.eventdisposition.EventDispositionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.eventdisposition.EventDispositionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 监测事件关联处置 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EventDispositionServiceImpl implements EventDispositionService {

    @Resource
    private EventDispositionMapper eventDispositionMapper;

    @Override
    public Long createEventDisposition(EventDispositionSaveReqVO createReqVO) {
        // 插入
        EventDispositionDO eventDisposition = BeanUtils.toBean(createReqVO, EventDispositionDO.class);
        eventDispositionMapper.insert(eventDisposition);
        // 返回
        return eventDisposition.getId();
    }

    @Override
    public void updateEventDisposition(EventDispositionSaveReqVO updateReqVO) {
        // 校验存在
        validateEventDispositionExists(updateReqVO.getId());
        // 更新
        EventDispositionDO updateObj = BeanUtils.toBean(updateReqVO, EventDispositionDO.class);
        eventDispositionMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventDisposition(Long id) {
        // 校验存在
        validateEventDispositionExists(id);
        // 删除
        eventDispositionMapper.deleteById(id);
    }

    private void validateEventDispositionExists(Long id) {
        if (eventDispositionMapper.selectById(id) == null) {
            throw exception(EVENT_DISPOSITION_NOT_EXISTS);
        }
    }

    @Override
    public EventDispositionDO getEventDisposition(Long id) {
        return eventDispositionMapper.selectById(id);
    }

    @Override
    public PageResult<EventDispositionDO> getEventDispositionPage(EventDispositionPageReqVO pageReqVO) {
        return eventDispositionMapper.selectPage(pageReqVO);
    }

    @Override
    public EventDispositionDO getEventDispositionByEventTypeId(String eventTypeId) {
        return eventDispositionMapper.selectOne(EventDispositionDO::getEventTypeId, eventTypeId);
    }

    @Override
    public EventDispositionDO getEventDispositionByEventTypeIdAndDivisionCode(String eventTypeId, String divisionCode) {
        return eventDispositionMapper.selectByEventTypeIdAndDivisionCode(eventTypeId, divisionCode);
    }

}