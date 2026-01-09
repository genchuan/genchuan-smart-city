package cn.iocoder.yudao.module.smartcity.service.eventlist;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventlist.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventlist.EventListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.eventlist.EventListMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 事件列表 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class EventListServiceImpl implements EventListService {

    @Resource
    private EventListMapper eventListMapper;

    @Override
    public Long createEventList(EventListSaveReqVO createReqVO) {
        // 插入
        EventListDO eventList = BeanUtils.toBean(createReqVO, EventListDO.class);
        eventListMapper.insert(eventList);
        // 返回
        return eventList.getId();
    }

    @Override
    public void updateEventList(EventListSaveReqVO updateReqVO) {
        // 校验存在
        validateEventListExists(updateReqVO.getId());
        // 更新
        EventListDO updateObj = BeanUtils.toBean(updateReqVO, EventListDO.class);
        eventListMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventList(Long id) {
        // 校验存在
        validateEventListExists(id);
        // 删除
        eventListMapper.deleteById(id);
    }

    private void validateEventListExists(Long id) {
        if (eventListMapper.selectById(id) == null) {
            throw exception(EVENT_LIST_NOT_EXISTS);
        }
    }

    @Override
    public EventListDO getEventList(Long id) {
        return eventListMapper.selectById(id);
    }

    @Override
    public PageResult<EventListDO> getEventListPage(EventListPageReqVO pageReqVO) {
        return eventListMapper.selectPage(pageReqVO);
    }

}