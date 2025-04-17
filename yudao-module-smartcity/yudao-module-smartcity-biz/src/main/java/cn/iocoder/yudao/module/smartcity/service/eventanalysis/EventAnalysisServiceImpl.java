package cn.iocoder.yudao.module.smartcity.service.eventanalysis;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.eventanalysis.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.eventanalysis.EventAnalysisDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.eventanalysis.EventAnalysisMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 事件关联分析 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class EventAnalysisServiceImpl implements EventAnalysisService {

    @Resource
    private EventAnalysisMapper eventAnalysisMapper;

    @Override
    public Long createEventAnalysis(EventAnalysisSaveReqVO createReqVO) {
        // 插入
        EventAnalysisDO eventAnalysis = BeanUtils.toBean(createReqVO, EventAnalysisDO.class);
        eventAnalysisMapper.insert(eventAnalysis);
        // 返回
        return eventAnalysis.getId();
    }

    @Override
    public void updateEventAnalysis(EventAnalysisSaveReqVO updateReqVO) {
        // 校验存在
        validateEventAnalysisExists(updateReqVO.getId());
        // 更新
        EventAnalysisDO updateObj = BeanUtils.toBean(updateReqVO, EventAnalysisDO.class);
        eventAnalysisMapper.updateById(updateObj);
    }

    @Override
    public void deleteEventAnalysis(Long id) {
        // 校验存在
        validateEventAnalysisExists(id);
        // 删除
        eventAnalysisMapper.deleteById(id);
    }

    private void validateEventAnalysisExists(Long id) {
        if (eventAnalysisMapper.selectById(id) == null) {
            throw exception(EVENT_ANALYSIS_NOT_EXISTS);
        }
    }

    @Override
    public EventAnalysisDO getEventAnalysis(Long id) {
        return eventAnalysisMapper.selectById(id);
    }

    @Override
    public PageResult<EventAnalysisDO> getEventAnalysisPage(EventAnalysisPageReqVO pageReqVO) {
        return eventAnalysisMapper.selectPage(pageReqVO);
    }

}