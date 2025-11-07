package cn.iocoder.yudao.module.smartcity.service.eventreporting;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventreporting.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventreporting.EventReportingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.eventreporting.EventReportingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;
/**
 * 事件上报 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class EventReportingServiceImpl implements EventReportingService {

    @Resource
    private EventReportingMapper eventReportingMapper;

    @Override
    public Long createEventReporting(EventReportingSaveReqVO createReqVO) {
        // 插入
        EventReportingDO eventReporting = BeanUtils.toBean(createReqVO, EventReportingDO.class);
        eventReportingMapper.insert(eventReporting);
        // 返回
        return eventReporting.getId();
    }

    @Override
    public void updateEventReporting(EventReportingSaveReqVO updateReqVO) {
        // 校验存在
        validateEventReportingExists(updateReqVO.getId());
        // 更新
        EventReportingDO updateObj = BeanUtils.toBean(updateReqVO, EventReportingDO.class);
        eventReportingMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventReporting(Long id) {
        // 校验存在
        validateEventReportingExists(id);
        // 删除
        eventReportingMapper.deleteById(id);
    }

    private void validateEventReportingExists(Long id) {
        if (eventReportingMapper.selectById(id) == null) {
            throw exception(EVENT_REPORTING_NOT_EXISTS);
        }
    }

    @Override
    public EventReportingDO getEventReporting(Long id) {
        return eventReportingMapper.selectById(id);
    }

    @Override
    public PageResult<EventReportingDO> getEventReportingPage(EventReportingPageReqVO pageReqVO) {
        return eventReportingMapper.selectPage(pageReqVO);
    }

}