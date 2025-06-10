package cn.iocoder.yudao.module.smartcity.service.eventprocessing;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventprocessing.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventprocessing.EventProcessingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.eventprocessing.EventProcessingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 事件处理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class EventProcessingServiceImpl implements EventProcessingService {

    @Resource
    private EventProcessingMapper eventProcessingMapper;

    @Override
    public Long createEventProcessing(EventProcessingSaveReqVO createReqVO) {
        // 插入
        EventProcessingDO eventProcessing = BeanUtils.toBean(createReqVO, EventProcessingDO.class);
        eventProcessingMapper.insert(eventProcessing);
        // 返回
        return eventProcessing.getId();
    }

    @Override
    public void updateEventProcessing(EventProcessingSaveReqVO updateReqVO) {
        // 校验存在
        validateEventProcessingExists(updateReqVO.getId());
        // 更新
        EventProcessingDO updateObj = BeanUtils.toBean(updateReqVO, EventProcessingDO.class);
        eventProcessingMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventProcessing(Long id) {
        // 校验存在
        validateEventProcessingExists(id);
        // 删除
        eventProcessingMapper.deleteById(id);
    }

    private void validateEventProcessingExists(Long id) {
        if (eventProcessingMapper.selectById(id) == null) {
            throw exception(EVENT_PROCESSING_NOT_EXISTS);
        }
    }

    @Override
    public EventProcessingDO getEventProcessing(Long id) {
        return eventProcessingMapper.selectById(id);
    }

    @Override
    public PageResult<EventProcessingDO> getEventProcessingPage(EventProcessingPageReqVO pageReqVO) {
        return eventProcessingMapper.selectPage(pageReqVO);
    }

}